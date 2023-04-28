package com.summercampquest.campquest.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
//

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    //@NotBlank(message = "Required field")
    //@Size(min = 3, max = 120, message = "Must be between 3 and 120 characters")


    public int getId() {
        return id;
    }

//    @Override
//    public String toString() {
//        return name;
//    }

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

}
