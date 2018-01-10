package dcmax.services;

import dcmax.models.User;
import dcmax.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Primary
public class UserServiceJpaImpl implements UserService
{

    @Autowired
    private UserRepository userRepo;

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public List<User> findAll()
    {
        return this.userRepo.findAll();
    }

    @Override
    public User findById(Long id)
    {
        return this.userRepo.findOne(id);
    }

    @Override
    public User create(User user)
    {
        return this.userRepo.save(user);
    }

    @Override
    public User edit(User user)
    {
        return this.userRepo.save(user);
    }

    @Override
    public void deleteById(Long id)
    {
        this.userRepo.delete(id);
    }

    @Override
    public void registerUser(User user)
    {
//        String insertUserSql = "insert into users value (?,?,?,?,?,?,?,?)";
//        jdbcTemplate.update(insertUserSql, new Object[]{});
    }

    @Override
    public boolean authenticate(String username, String password)
    {
        // Provide a sample password check: username == password
        return Objects.equals(username, password);
    }

    @Override
    public boolean twoPasswordMatch(String password, String rePassword)
    {
        // Provide a sample password check: password == rePassword
        return Objects.equals(password, rePassword);
    }
}