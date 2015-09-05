package com.zavgorodniy.entity;

import javax.persistence.*;

@Entity
@Table(name = "CINEMAS")
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true, nullable = false)
    private Long id;

    @Column(name = "Name", unique = true, length = 30, nullable = false)
    private String name;

    @Column(name = "Location", nullable = false)
    private String location;

    @Column(name = "Number_of_seats", nullable = false)
    private Integer numberOfSeats;

    public Cinema() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public boolean equals(Object cinema) {
        if (cinema == this) {
            return true;
        }
        if (cinema == null) {
            return false;
        }
        if (getClass() != cinema.getClass()) {
            return false;
        }

        Cinema otherCinema = (Cinema) cinema;

        if ((otherCinema.id == this.id)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        final int INDEX = 11;
        int result = 1;
        Integer i = (int) (long) id;
        result = INDEX * result + i;
        return result;
    }

}
