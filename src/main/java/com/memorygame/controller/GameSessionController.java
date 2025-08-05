package com.memorygame.controller;

import com.memorygame.model.ApiResponse;
import com.memorygame.model.GameSession;
import com.memorygame.service.GameSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/luot-choi")
public class GameSessionController {

    @Autowired
    private GameSessionService gameSessionService;

    @PostMapping("/gui-ket-qua/{userId}")
    public ResponseEntity<ApiResponse<GameSession>> guiKetQua(
        @PathVariable Long userId,
        @RequestBody GameSession luotChoi
    ) {
        GameSession daLuu = gameSessionService.luuLuotChoiVaTraVe(userId, luotChoi);
        if (daLuu == null) {
            return ResponseEntity.badRequest()
                .body(new ApiResponse<>(false, "Không tìm thấy người dùng!", null));
        }
        return ResponseEntity.ok(new ApiResponse<>(true, "✅ Đã lưu lượt chơi!", daLuu));
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> layLichSuNguoiChoi(@PathVariable Long userId) {
        List<GameSession> lichSu = gameSessionService.layLichSuTheoNguoiChoi(userId);

        List<Map<String, Object>> simplified = lichSu.stream().map(g -> {
            Map<String, Object> m = new HashMap<>();
            m.put("clickCount", g.getClickCount());
            m.put("durationSeconds", g.getDurationSeconds());
            m.put("playedAt", g.getPlayedAt());
            return m;
        }).toList();
        return ResponseEntity.ok(new ApiResponse<>(true, "📝 Lịch sử chơi", simplified));
    }


    @GetMapping("/leaderboard")
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> layBangXepHang() {
        List<GameSession> topPlayers = gameSessionService.layTopLuotChoi();

        List<Map<String, Object>> simplified = topPlayers.stream().map(g -> {
            Map<String, Object> m = new HashMap<>();
            m.put("username", g.getUser().getUsername());
            m.put("clickCount", g.getClickCount());
            m.put("durationSeconds", g.getDurationSeconds());
            m.put("playedAt", g.getPlayedAt());
            return m;
        }).toList();

        return ResponseEntity.ok(new ApiResponse<>(true, "📊 Top người chơi", simplified));
    }
}
