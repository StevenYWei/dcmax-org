package dcmax.services;

import dcmax.controllers.ForbiddenException;
import dcmax.models.Comment;

import dcmax.models.Post;
import dcmax.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private UserService userService;

    @Autowired
    private CommentRepository commentRepo;

    private static final int MAX_COMMENT_LEVEL = 5;

    @Override
    public Comment getComment(Long id){
        return commentRepo.findOne(id);
    }

    @Override
    public Long saveNewComment(Comment comment, Post post, Long parentId){
        if (parentId != null) {
            Comment parentComment = getComment(parentId);

            int level = parentComment.commentLevel();

            comment.setParentComment(level < MAX_COMMENT_LEVEL ? parentComment : parentComment.getParentComment());
        }

        comment.setCreateTime(new Date());

        comment.setPost(post);

        comment.setUser(userService.currentUser());

        commentRepo.saveAndFlush(comment);

        return comment.getId();
    }

    @Override
    public void deleteComment(Long commentId){
        Comment comment = getComment(commentId);

        boolean isAdmin = userService.isAdmin();

        if (!isAdmin && !userService.currentUser().getUsername().equals(comment.getUser().getUsername())) {
            throw new ForbiddenException();
        }

        comment.setActive(false);

        commentRepo.saveAndFlush(comment);
    }

    @Override
    public void updateComment(Comment newCommentData, Long commentId){
        Comment comment = getComment(commentId);

        boolean isAdmin = userService.isAdmin();

        if (!isAdmin && !userService.currentUser().getUsername().equals(comment.getUser().getUsername())) {
            throw new ForbiddenException();
        }

        comment.setCommentText(newCommentData.getCommentText());

        comment.setLastUpdateTime(new Date());

        commentRepo.saveAndFlush(comment);
    }

}
