package com.zavgorodniy.entity;

import javax.persistence.*;

@Entity
@Table(name = "MOVIES")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true, nullable = false)
    private Long id;

    @Column(name = "Name", unique = true, length = 30, nullable = false)
    private String name;

    @Column(name = "Description", length = 1000, nullable = false)
    private String description;

    @Column(name = "Duration", length = 5)
    private Integer movieDuration;

    @Column(name = "Image_directory")
    private String imgDirectory;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(Integer movieDuration) {
        this.movieDuration = movieDuration;
    }

    public String getImgDirectory() {
        return imgDirectory;
    }

    public void setImgDirectory(String imgDirectory) {
        this.imgDirectory = imgDirectory;
    }

    @Override
    public boolean equals(Object movie) {
        if (movie == this) {
            return true;
        }
        if (movie == null) {
            return false;
        }
        if (getClass() != movie.getClass()) {
            return false;
        }

        Movie otherMovie = (Movie) movie;

        if ((otherMovie.id == this.id)) {
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
