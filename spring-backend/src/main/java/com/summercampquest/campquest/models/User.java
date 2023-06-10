package com.summercampquest.campquest.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="users")
public class User {


    @Id
    @Column(name="user_name")
    private String userName;
    @Column(name="user_first_name",nullable = false)
    private String userFirstName;
    @Column(name="user_last_name",nullable = false)
    private String userLastName;
    @Column(name="user_email",nullable = false, unique = true)
    private String userEmail;
    @Column(name="user_password",nullable = false)
    private String userPassword;
    @Column(name = "age",nullable = true)
    private Integer age;
    @Column(name = "grade",nullable = true)
    private Integer grade;
    @Column(name = "phone",nullable = true)
    private Integer phone;
    @Column(name = "token")
    private String token;
    @Column(name = "token_expiry_date")
    private Date tokenExpiryDate;
    @Column(name = "profilePictureLink",nullable = true)
    private String profilePictureLink;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    )
    private Set<Role> role;

    // constructors, getters and setters

    public User(){
    }

    public User(String userName, String userFirstName, String userLastName, String userEmail, String userPassword, Integer age, Integer grade, Integer phone,
                String token, Date tokenExpiryDate, String profilePictureLink, Set<Role> role) {
        this.userName = userName;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.age = age;
        this.grade = grade;
        this.phone = phone;
        this.token = token;
        this.tokenExpiryDate = tokenExpiryDate;
        this.profilePictureLink = profilePictureLink;
        this.role = role;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String email) {
        this.userEmail = email;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getTokenExpiryDate() {
        return tokenExpiryDate;
    }

    public void setTokenExpiryDate(Date tokenExpiryDate) {
        this.tokenExpiryDate = tokenExpiryDate;
    }

    public String getProfilePictureLink() {
        return profilePictureLink;
    }

    public void setProfilePictureLink(String profilePictureLink) {
        this.profilePictureLink = profilePictureLink;
    }

    @MappedSuperclass
    public abstract static class AbstractEntity implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(nullable = false, updatable = false)
        private int id;

        public int getId() {
            return id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AbstractEntity that = (AbstractEntity) o;
            return id == that.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return "id=" + id;
        }

    }
}
