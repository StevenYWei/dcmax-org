package dcmax.services;

import dcmax.models.Comment;
import dcmax.models.Post;

public interface CommentService {
    Comment getComment(Long id);

    Long saveNewComment(Comment comment, Post post, Long parentId);

    void deleteComment(Long commentId);

    void updateComment(Comment newCommentData, Long commentId);

}
