package com.memorygame.service;

import com.memorygame.model.GameSession;
import com.memorygame.model.User;
import com.memorygame.repository.GameSessionRepository;
import com.memorygame.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GameSessionService {

    @Autowired
    private GameSessionRepository gameSessionRepo;

    @Autowired
    private UserRepository userRepo;

    public GameSession luuLuotChoiVaTraVe(Long userId, GameSession g) {
        User user = userRepo.findById(userId).orElse(null);
        if (user == null) return null;
        g.setUser(user);
        g.setPlayedAt(LocalDateTime.now());
        return gameSessionRepo.save(g);
    }

    public List<GameSession> layLichSuTheoNguoiChoi(Long userId) {
        Optional<User> user = userRepo.findById(userId);
        if (user.isEmpty()) return List.of();
        return gameSessionRepo.findByUser(user.get());
    }

    public List<GameSession> layTopLuotChoi() {
        return gameSessionRepo.findTop10ByOrderByClickCountAscDurationSecondsAsc();
    }
}
