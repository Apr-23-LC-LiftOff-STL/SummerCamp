package com.summercampquest.campquest.models;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name="users")
public class User extends AbstractEntity {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Column(name="firstName")
    private String firstName = "";

    @Column(name="lastName")
    private String lastName = "";
    @Column(name="email")
    private String email="";
    @Column(name="age")
    private Integer age=0;
    @Column(name="grade")
    private Integer grade=0;
    @Column(name="phone")
    private String phone="";
    @Column(name="username")
    private String username="";
    @Column(name="password")
    private String password="";


    // TODO:connect to favorites table
    @OneToMany
    @JoinColumn(name = "camp_id")
    @Column(name="favorites")
    private Set<Camp> favorites = new HashSet<>();

    @Column(name="profilePictureLink")
    private String profilePictureLink = "profile image link here";
    @Column(name="admin")
    private boolean isAdmin=false;

    private String[] authorities;
    private String role="";

    //Constructors
    public User(){}

    public User(String firstName, String lastName, String email, Integer age, Integer grade, String phone, String username, String password) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.grade = grade;
        this.phone = phone;
        this.username = username;
        this.password = password;

    }

    public String[] getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String[] authorities) {
        this.authorities = authorities;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
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

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Camp> getFavorites() {
        return favorites;
    }

    public void setFavorites(Set<Camp> favorites) {
        this.favorites = favorites;
    }

    public String getProfilePictureLink() {
        return profilePictureLink;
    }

    public void setProfilePictureLink(String profilePictureLink) {
        this.profilePictureLink = profilePictureLink;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", grade=" + grade +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", favorites=" + favorites +
                ", profilePictureLink='" + profilePictureLink + '\'' +
                ", isAdmin=" + isAdmin +
                ", authorities=" + Arrays.toString(authorities) +
                ", role='" + role + '\'' +
                '}';
    }
}
