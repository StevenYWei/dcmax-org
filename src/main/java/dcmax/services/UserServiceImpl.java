package dcmax.services;

import dcmax.forms.LoginForm;
import dcmax.models.Role;
import dcmax.models.User;
import dcmax.repositories.RoleRepository;
import dcmax.repositories.UserRepository;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    JdbcTemplate jdbcTemplate;

//    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepo.findByUsernameOrEmail(s, s);

        if (user == null)
            throw new UsernameNotFoundException("no such user");

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                user.getActive(), true, true, true, authorities);
    }

    @Override
    public List<User> findAll() {
        return this.userRepo.findAll();
    }

    @Override
    public List<String> getUserList() {
        return userRepo.getUserLsit();
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
    public boolean usernameExists(String username) {
        return findByUsername(username) != null;
    }

    @Override
    public User create(User user) {
        return this.register(user);
    }

    @Override
    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

//        user.getRoles().add(roleRepo.findByRoleName("Member"));
        user.getRoles().add(new Role("Member"));

        user.setActive(true);

        user.setCreateTime(new Date());

        return userRepo.saveAndFlush(user);
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
    public User update(User user) throws InvocationTargetException, IllegalAccessException {
        User oldUser = userRepo.findByUsername(user.getUsername());
        Long id = oldUser.getId();
        BeanUtils.copyProperties(oldUser, user);
        oldUser.setId(id);
        return userRepo.saveAndFlush(user);
    }

    @Override
    public boolean authenticate(LoginForm login) {
        User userLoginInfo = findByUsername(login.getUsername());
        return passwordEncoder.matches(userLoginInfo.getPassword(), login.getPassword());
//        return Objects.equals(userLoginInfo.getPassword(), login.getPassword());
    }

    @Override
    public void authenticate(User user) {
        UserDetails userDetails = loadUserByUsername(user.getUsername());

        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Override
    public boolean isAuthenticated() {
        SecurityContext securityContext = SecurityContextHolder.getContext();

        Authentication auth = securityContext.getAuthentication();

        return auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated();
    }

//    please implement this on the front end
//    @Override
//    public boolean twoPasswordMatch(String password, String rePassword) {
//        // Provide a sample password check: password == rePassword
//        return Objects.equals(password, rePassword);
//    }

    @Override
    public int deleteUser(String username) {
        return userRepo.deleteUserByUsername(username);
    }

    @Override
    public boolean isAdmin() {
        User user = currentUser();

        return user != null && user.hasRole("Admin");
    }

    @Override
    public User currentUser() {
        if (!isAuthenticated())
            return null;

        SecurityContext securityContext = SecurityContextHolder.getContext();

        Authentication auth = securityContext.getAuthentication();

        return userRepo.findByUsername(auth.getName());
    }
}