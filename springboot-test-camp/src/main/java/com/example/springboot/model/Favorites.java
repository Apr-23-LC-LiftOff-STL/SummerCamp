package com.example.springboot.model;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name="favorites")
public class Favorites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "camp_id", nullable = false)
    private Camp camp;

    @Column(name = "date")
    private LocalDate date;

    // constructors, getters and setters

    public Favorites(){
    }

    public Favorites(User user, Camp camp) {
        super();
        this.user = user;
        this.camp = camp;
        this.date = LocalDate.now();
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Camp getCamp() {
        return camp;
    }

    public void setCamp(Camp camp) {
        this.camp = camp;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
