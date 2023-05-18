package com.summercampquest.campquest.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "camps")
public class Camp{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    @Column(name="location")
    private String location;
    @Column(name="price")
    private Integer price;
    @Column(name="duration")
    private String duration;
    private int age;
    private LocalDate deadline;
    private int totalSeats;
    private String mode;
    @Column(name = "category")
    private String category;
    @Column(name = "campLink")
    private String campLink;
    @Column(name = "gradeGrp")
    @Enumerated(EnumType.STRING)
    private GradeGroup gradeGrp;

    //Constructors
    public Camp() {
    }

    public Camp(String name, String description, String location,Integer price, String duration, Integer age, Date deadline,
                Integer totalSeats, String mode, String category, String campLink, GradeGroup gradeGrp) {

    public Camp(String name, String description, int price, String duration, int age, LocalDate deadline, int totalSeats, String mode, String category, String campLink) {
        super();
        this.name = name;
        this.description = description;
        this.location=location;
        this.price = price;
        this.duration = duration;
        this.age = age;
        this.deadline = deadline;
        this.totalSeats = totalSeats;
        this.mode = mode;
        this.category = category;
        this.campLink = campLink;
        this.gradeGrp = gradeGrp;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public GradeGroup getGradeGrp() {
        return gradeGrp;
    }

    public void setGradeGrp(GradeGroup gradeGrp) {
        this.gradeGrp = gradeGrp;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCampLink() {
        return campLink;
    }

    public void setCampLink(String campLink) {
        this.campLink = campLink;
    }
}
