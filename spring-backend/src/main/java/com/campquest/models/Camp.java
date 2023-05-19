package com.campquest.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Entity
public class Camp extends AbstractEntity{
        @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;
    @Column(name="location")
    private String location;
    @Column(name="price")
    private Integer price;
    @Column(name="duration")
    private String duration;
    @Column(name="age")
    private Integer age;
    @Column(name = "deadline")
    private Date deadline;
    @Column(name = "totalSeats")
    private Integer totalSeats;
    @Column(name = "mode")
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
        super();
        this.name=name;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(Integer totalSeats) {
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

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", duration='" + duration + '\'' +
                ", age=" + age +
                ", deadline=" + deadline +
                ", totalSeats=" + totalSeats +
                ", mode='" + mode + '\'' +
                ", category='" + category + '\'' +
                ", campLink='" + campLink + '\'' +
                '}';
    }
}