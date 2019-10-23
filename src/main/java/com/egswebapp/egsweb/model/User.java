package com.egswebapp.egsweb.model;

import com.egswebapp.egsweb.model.enums.TokenType;
import com.egswebapp.egsweb.model.enums.UserProfile;
import com.egswebapp.egsweb.model.enums.UserStatus;
import com.egswebapp.egsweb.model.enums.convert.UserProfileConverter;
import com.egswebapp.egsweb.model.enums.convert.UserStatusConverter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * User class
 */


@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class User {

    @Id
    @GeneratedValue(generator = "uuid", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password")
    private String password;


    @Column(name = "img_name")
    private String imgName;


    @Column(name = "generated_password", columnDefinition = "BIT", length = 1)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flag;

    @Column(name="creation_date",columnDefinition = "DATE")
    @CreationTimestamp
    private Date createdDate;

    @Convert(converter = UserProfileConverter.class)
    @Column(name = "profile_id")
    private UserProfile userProfile;

    @Convert(converter = UserStatusConverter.class)
    @Column(name = "status_id")
    private UserStatus userStatus;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<PostContent> postContents;

    @Transient
    @Deprecated
    private Map<TokenType, UserToken> tokens;

    public User() {
    }

    public User(String name, String surname, String email, String password, String imgName, Boolean flag,  UserProfile userProfile, UserStatus status, Date createdDate) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.imgName = imgName;
        this.userProfile = userProfile;
        this.userStatus = status;
        this.flag = flag;
        this.createdDate=createdDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile profile_id) {
        this.userProfile = profile_id;
    }

    public UserStatus getStatus() {
        return userStatus;
    }

    public void setStatus(UserStatus status) {
        this.userStatus = status;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public Set<PostContent> getPostContents() {
        return postContents;
    }

    public void setPostContents(Set<PostContent> postContents) {
        this.postContents = postContents;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


    public Map<TokenType, UserToken> getTokens() {
        return tokens;
    }

    public void addToken(UserToken token) {
        if (tokens == null) {
            tokens = new HashMap<>();
        }
        tokens.put(token.getType(), token);
    }

    public UserToken getToken(TokenType type) {
        UserToken token = null;
        if (tokens != null) {
            token = tokens.get(type);
        }
        return token;
    }
    public void setTokens(Map<TokenType, UserToken> tokens) {
        this.tokens = tokens;
    }

}
