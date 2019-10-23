package com.egswebapp.egsweb.model;

import com.egswebapp.egsweb.model.enums.TokenType;
import com.egswebapp.egsweb.model.enums.convert.TokenTypeConverter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "user_token")
public class UserToken {

    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @Convert(converter = TokenTypeConverter.class)
    @Column(name = "token_type_id")
    private TokenType type;

    @Column(name = "value")
    private String value;

    @Column(name="created_date", columnDefinition="DATE")
    @CreationTimestamp
    private Date createdDate;

    public UserToken() {
    }

    public UserToken(User user, TokenType type, String value, Date createdDate) {
        this.user = user;
        this.type = type;
        this.value = value;
        this.createdDate = createdDate;
   }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
