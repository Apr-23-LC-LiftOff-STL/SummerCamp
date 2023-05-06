package com.summercampquest.campquest.models;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="users")
public class User extends AbstractEntity {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;
    @Column(name="email")
    private String email;
    @Column(name="age")
    private Integer age;
    @Column(name="grade")
    private Integer grade;
    @Column(name="phone")
    private Integer phone;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;


    // TODO:connect to favorites table
    @OneToMany
    @JoinColumn(name = "camp_id")
    private List<Camp> favorites = new ArrayList<>();

    @Column(name="profilePictureLink")
    private String profilePictureLink;
    @Column(name="admin")
    private boolean isAdmin=false;

    @Column
    @CreationTimestamp
    private Date dateCreated;

    //Constructors
    public User(){}

    public User(String firstName, String lastName, String email, Integer age, Integer grade, Integer phone, String username, String pwHash, List<Camp> favorites, String profilePictureLink) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.grade = grade;
        this.phone = phone;
        this.username = username;
        this.password = encoder.encode(pwHash);
        this.favorites = favorites;
        this.profilePictureLink = profilePictureLink;
    }

    public boolean isMatchingPassword(String pwd) {
        return encoder.matches(pwd, password);
    }
    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

//    public void setPassword(String password) {
//        this.password = password;
//    }

    public List<Camp> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Camp> favorites) {
        this.favorites = favorites;
    }

    public String getProfilePictureLink() {
        return profilePictureLink;
    }

    public void setProfilePictureLink(String profilePictureLink) {
        this.profilePictureLink = profilePictureLink;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    @Override
    public String toString() {
        return "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", grade=" + grade +
                ", phone=" + phone +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", favorites=" + favorites +
                ", profilePictureLink='" + profilePictureLink + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
