package com.vandejr.springjava.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity(name = "animes")
public class Anime {
    @Id
    @SequenceGenerator(name = "anime_id_generator", sequenceName = "animes_id_seq", allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "anime_id_generator")
    @JsonProperty("id")
    private Long id;

    @Column(name = "english_title", length = 100)
    @JsonProperty("english_title")
    private String englishTitle;

    @Column(name = "jap_title", length = 100)
    @JsonProperty("jap_title")
    private String japTitle;

    @Column(columnDefinition = "TEXT")
    @JsonProperty("synopsis")
    private String synopsis;

    @Column(length = 100)
    @JsonProperty("studio")
    private String studio;

    @Column(length = 100)
    @JsonProperty("director")
    private String director;

    @Column(name = "total_eps")
    @JsonProperty("total_eps")
    private Integer totalEps;

    @Column(name = "release_date")
    @JsonProperty("release_date")
    private Date releaseDate;

    @Column(length = 5)
    @JsonProperty("rating")
    private String rating;

    @Column(name = "mean_score")
    @JsonProperty("mean_score")
    private Float meanScore;

    public Anime() {
    }

    public Anime(Long id, String englishTitle, String japTitle, Float meanScore) {
        this.id = id;
        this.englishTitle = englishTitle;
        this.japTitle = japTitle;
        this.meanScore = meanScore;
    }

    public Anime(Object[] object) {
        this.id = (Long) object[0];
        this.englishTitle = (String) object[1];
        this.japTitle = (String) object[2];
        this.meanScore = (Float) object[3];
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnglishTitle() {
        return englishTitle;
    }

    public void setEnglishTitle(String englishTitle) {
        this.englishTitle = englishTitle;
    }

    public String getJapTitle() {
        return japTitle;
    }

    public void setJapTitle(String japTitle) {
        this.japTitle = japTitle;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Integer getTotalEps() {
        return totalEps;
    }

    public void setTotalEps(Integer totalEps) {
        this.totalEps = totalEps;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Float getMean_score() {
        return meanScore;
    }

    public void setMean_score(Float meanScore) {
        this.meanScore = meanScore;
    }
}
