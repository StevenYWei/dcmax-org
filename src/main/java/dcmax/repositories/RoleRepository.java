package dcmax.repositories;

import dcmax.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

//    @Query(value = "select r from Role r where r.roleName = :name")
//    Role findByName(@Param("name") String name);

    Role findByRoleName(String name);

}

