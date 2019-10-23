package com.egswebapp.egsweb.security.jwt;

import com.egswebapp.egsweb.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


/**
 * Spring Security wrapper for class {@link User}.
 *
 *
 * @version 1.0
 */

public class JwtUserDetails implements UserDetails {

      private   String id;

      private   String email;

      @JsonIgnore
      private   String password;

      private   String name;

      private   String surname;

      private   boolean enabled;

      private  Collection<? extends GrantedAuthority> authorities;

    public JwtUserDetails() {
    }

    public JwtUserDetails(String id, String email, String password, String name, String surname, boolean enabled, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    public String getId(){
        return this.id;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
