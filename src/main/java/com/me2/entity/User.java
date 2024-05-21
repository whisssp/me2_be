package com.me2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.me2.global.enums.EnumUserAccountStatus;
import com.me2.global.enums.EnumUserRole;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Set;


@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users", schema = "public", catalog = "me2_db")
public class User extends AbstractAuditEntity<Long> implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "middle_name", nullable = true, length = 50)
    private String middleName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "phone", nullable = false, length = 10)
    private String phone;

    @Column(name = "avatar", nullable = true, length = 255)
    private String avatar;

    @Column(name = "last_login", nullable = true)
    private Instant lastLogin;

    @Column(name = "email_validated", nullable = true)
    private Boolean emailValidated;

    @Column(name = "role", nullable = true, length = 255)
    @Enumerated(EnumType.STRING)
    private EnumUserRole role;

    @Column(name = "status", nullable = true, length = 255)
    @Enumerated(EnumType.STRING)
    private EnumUserAccountStatus status;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"user", "recipients"}, allowSetters = true)
    private List<Address> addresses;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    @JsonIgnoreProperties(value = {"user", "cartItems"}, allowSetters = true)
    @JsonIgnore
    private Set<Cart> carts;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
//    @JsonIgnoreProperties(value = {"user", "recipient", "promotion", "orderDetails"}, allowSetters = true)
    private Set<Order> orders;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User that = (User) o;

        if (id != that.id) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (middleName != null ? !middleName.equals(that.middleName) : that.middleName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (avatar != null ? !avatar.equals(that.avatar) : that.avatar != null) return false;
        if (lastLogin != null ? !lastLogin.equals(that.lastLogin) : that.lastLogin != null) return false;
        if (emailValidated != null ? !emailValidated.equals(that.emailValidated) : that.emailValidated != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        result = 31 * result + (lastLogin != null ? lastLogin.hashCode() : 0);
        result = 31 * result + (emailValidated != null ? emailValidated.hashCode() : 0);
        return result;
    }
}