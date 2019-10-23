package com.egswebapp.egsweb.repasotory;


import com.egswebapp.egsweb.model.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Deprecated
public interface UserTokenRepository extends JpaRepository<UserToken, String> {

    /**
     *
     * Get user token by user id
     *
//     */
    @Query("SELECT u FROM UserToken as u WHERE u.user.id=?1")
    Optional<UserToken> getUserToken(String userId);

}



