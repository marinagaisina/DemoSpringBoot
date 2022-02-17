package com.marinagaisina.casestudy.database.dao;

import com.marinagaisina.casestudy.database.entity.User;
import com.marinagaisina.casestudy.database.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDAO extends JpaRepository<User, Long> {

    User findById(@Param("id") Integer id);

    User findByEmail(@Param("email") String email);

    @Query("select ur from UserRole ur where ur.user.id = :userId")
    List<UserRole> getUserRoles(Integer userId);
}
