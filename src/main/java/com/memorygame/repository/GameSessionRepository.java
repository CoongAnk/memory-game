package com.memorygame.repository;

import com.memorygame.model.GameSession;
import com.memorygame.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameSessionRepository extends JpaRepository<GameSession, Long> {

    List<GameSession> findByUser(User user);

    List<GameSession> findTop10ByOrderByClickCountAscDurationSecondsAsc();
}
