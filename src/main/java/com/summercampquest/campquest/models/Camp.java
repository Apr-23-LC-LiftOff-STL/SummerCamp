package com.summercampquest.campquest.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Camp extends AbstractEntity{

    @NotBlank(message = "Required")
    @Size(min = 3, max = 50, message = "Must be between 3 and 50 characters")
    private String location;
    public String getLocation() { return location;}
    public void setLocation(String location) { this.location = location;}

    //Constructors
    public Camp(){}
    public Camp(String location){ this.location=location;}


    @Override
    public String toString() {
        return location;
    }
}
