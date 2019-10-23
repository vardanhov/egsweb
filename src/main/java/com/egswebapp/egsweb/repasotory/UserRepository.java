package com.egswebapp.egsweb.repasotory;


import com.egswebapp.egsweb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findUserByEmail(final String email);

    boolean existsUserByEmail(final String email);

    User findUserById(final String id);

    boolean existsUserById(final String id);

}
