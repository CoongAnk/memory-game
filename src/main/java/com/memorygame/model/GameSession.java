package com.memorygame.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "game_sessions")
public class GameSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int clickCount;

    private int durationSeconds;

    private LocalDateTime playedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore 
    private User user;

    // Constructors
    public GameSession() {}

    public GameSession(int clickCount, int durationSeconds, LocalDateTime playedAt, User user) {
        this.clickCount = clickCount;
        this.durationSeconds = durationSeconds;
        this.playedAt = playedAt;
        this.user = user;
    }

    // Getters & Setters
    public Long getId() { return id; }

    public int getClickCount() { return clickCount; }
    public void setClickCount(int clickCount) { this.clickCount = clickCount; }

    public int getDurationSeconds() { return durationSeconds; }
    public void setDurationSeconds(int durationSeconds) { this.durationSeconds = durationSeconds; }

    public LocalDateTime getPlayedAt() { return playedAt; }
    public void setPlayedAt(LocalDateTime playedAt) { this.playedAt = playedAt; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
