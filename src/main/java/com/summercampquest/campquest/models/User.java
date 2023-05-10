package com.summercampquest.campquest.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "age")
    private Integer age;
    @Column(name = "grade")
    private Integer grade;
    @Column(name = "phone")
    private Integer phone;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;


    @Column(name = "forgot_password_token")
    private String forgotPasswordToken;

    @Column(name = "token_expiry_date")
    private Date tokenExpiryDate;


    // TODO:connect to favorites table
//    @OneToMany
//    @JoinColumn(name = "camp_id")
//    private List<Camp> favorites = new ArrayList<>();

    @Column(name = "profilePictureLink")
    private String profilePictureLink;
    //@Column(name="admin")
    //private boolean admin;


    //Constructors
    public User() {
    }

    public User(String firstName, String lastName, String email, Integer age, Integer grade, Integer phone,
                String username, String password, String profilePictureLink,
                String forgotPasswordToken, Date tokenExpiryDate) {
//        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.grade = grade;
        this.phone = phone;
        this.username = username;
        this.password = password;
//        this.favorites = favorites;
        this.profilePictureLink = profilePictureLink;
        this.forgotPasswordToken = forgotPasswordToken;
        this.tokenExpiryDate = tokenExpiryDate;
        // this.admin = false;
    }

    public int getId() {
        return id;
    }

    /*   public boolean isAdmin() {
           return admin;
       }

       public void setAdmin(boolean admin) {
           this.admin = admin;
       }
   */
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getForgotPasswordToken() {
        return forgotPasswordToken;
    }

    public void setForgotPasswordToken(String forgotPasswordToken) {
        this.forgotPasswordToken = forgotPasswordToken;
    }

    //    public List<Camp> getFavorites() {
//        return favorites;
//    }

//    public void setFavorites(List<Camp> favorites) {
//        this.favorites = favorites;
//    }

    public String getProfilePictureLink() {
        return profilePictureLink;
    }

    public void setProfilePictureLink(String profilePictureLink) {
        this.profilePictureLink = profilePictureLink;
    }


    public Date getTokenExpiryDate() {
        return tokenExpiryDate;
    }

    public void setTokenExpiryDate(Date tokenExpiryDate) {
        this.tokenExpiryDate = tokenExpiryDate;
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
//                ", favorites=" + favorites +
                ", profilePictureLink='" + profilePictureLink + '\'' +
                '}';
    }


}