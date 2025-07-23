package com.memorygame.service;

import com.memorygame.model.GameSession;
import com.memorygame.model.User;
import com.memorygame.repository.GameSessionRepository;
import com.memorygame.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameSessionService {

    @Autowired
    private GameSessionRepository gameSessionRepo;

    @Autowired
    private UserRepository userRepo;

    public GameSession luuLuotChoiVaTraVe(String tenDangNhap, GameSession g) {
        User user = userRepo.findByUsername(tenDangNhap);
        if (user == null) return null;
        g.setUser(user);
        g.setPlayedAt(java.time.LocalDateTime.now());
        return gameSessionRepo.save(g);
    }

    public List<GameSession> layLichSuTheoNguoiChoi(String username) {
        User user = userRepo.findByUsername(username);
        if (user == null) return List.of();
        return gameSessionRepo.findByUser(user);
    }

    public List<GameSession> layTopLuotChoi() {
        return gameSessionRepo.findTop10ByOrderByClickCountAscDurationSecondsAsc();
    }
}
