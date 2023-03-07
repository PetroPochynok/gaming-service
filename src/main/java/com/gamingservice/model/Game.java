package com.gamingservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(of = "name")
@Getter
@Setter
@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "release_date",nullable = false)
    private LocalDate releaseDate;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private BigDecimal price;

    @JsonBackReference
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name = "user_game",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users = new HashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "game")
    private List<Feedback> feedbacks = new LinkedList<>();
    
    public void addUser(User user) {
        users.add(user);
        user.getGames().add(this);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.getGames().remove(this);
    }

    public void addFeedback(Feedback feedback) {
        feedbacks.add(feedback);
        feedback.setGame(this);
    }

    public void removeFeedback(Feedback feedback) {
        feedbacks.remove(feedback);
        feedback.setGame(null);
    }
}
