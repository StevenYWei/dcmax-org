package dcmax.repositories;

import dcmax.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select u from User u where u.username = :username")
    User findByUsername(@Param("username") String username);

    @Query(value = "select u from User u where u.email = :email")
    User findByEmail(@Param("email") String email);

    @Query(value = "select u from User u where u.username = :username or u.email = :email")
    User findByUsernameOrEmail(@Param("username") String username, @Param("email") String email);

    @Transactional
    @Modifying
    @Query(value = "delete from User u where u.username = :username")
    int deleteUserByUsername(@Param("username") String username);

    @Query(value = "select u.username from User u")
    List<String> getUserLsit();


}

