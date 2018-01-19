package dcmax.services;

import dcmax.models.User;
import dcmax.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
//import org.springframework.security.authentication.AnonymousAuthenticationToken;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Primary
public class UserServiceJpaImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    JdbcTemplate jdbcTemplate;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return this.userRepo.findAll();
    }

    @Override
    public User findById(Long id) {
        return this.userRepo.findOne(id);
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepo.findByUsername(username);
    }

    @Override
    public User create(User user) {
        return this.userRepo.save(user);
    }

    @Override
    public User edit(User user) {
        return this.userRepo.save(user);
    }

    @Override
    public void deleteById(Long id) {
        this.userRepo.delete(id);
    }

    @Override
    public void registerUser(User user) {
        userRepo.saveAndFlush(user);
//        String insertUserSql = "insert into users value (?,?,?,?,?,?,?,?)";
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(insertUserSql, new Object[]{});
    }

    @Override
    public boolean authenticate(String username, String password) {
        // Provide a sample password check: username == password
        return Objects.equals(username, password);
    }

    @Override
    public boolean twoPasswordMatch(String password, String rePassword) {
        // Provide a sample password check: password == rePassword
        return Objects.equals(password, rePassword);
    }

//    @Override
//    public void register(User user)
//    {
////        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setUsername(user.getUsername());
//        user.setPassword(user.getPassword());
//        user.setEmail(user.getEmail());
//        user.setFirstName("");
//        user.setLastName("");
//
////        user.getRoles().add(roleRepository.findByName("ROLE_USER"));
//
////        user.setEnabled(true);
//
//        user.setCreateTime(new Date());
//        user.setLastUpdateTime(new Date());
//
//        userRepo.saveAndFlush(user);
//    }
}