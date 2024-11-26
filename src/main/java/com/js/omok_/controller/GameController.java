package com.js.omok_.controller;

import com.js.omok_.model.dto.BoardDTO;
import com.js.omok_.model.dto.GameStateDTO;
import com.js.omok_.model.dto.MoveDTO;
import com.js.omok_.model.dto.PlayerDTO;
import com.js.omok_.service.GameService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {


    @Resource(name = "gameService")
    private GameService gameService;

    /**
     * 게임 시작
     * @param players
     * @return
     */
    @PostMapping("/start")
    public ResponseEntity<BoardDTO> startGame(@RequestBody List<PlayerDTO> players) {
        // 서비스 호출로 게임 초기화
        BoardDTO board = gameService.startGame(players);
        return ResponseEntity.ok(board);
    }

    /**
     * 돌 놓기
     * @param move
     * @return
     */
    @PostMapping("/move")
    public ResponseEntity<BoardDTO> makeMove(@RequestBody MoveDTO move) {
        BoardDTO updatedBoard = gameService.makeMove(move);
        return ResponseEntity.ok(updatedBoard);
    }

    /**
     * 게임 상태 조회
     * @return
     */
    @GetMapping("/status")
    public ResponseEntity<GameStateDTO> getGameStatus() {
        GameStateDTO gameState = gameService.getGameStatus();
        return ResponseEntity.ok(gameState);
    }

    /**
     * 게임 종료
     * @return
     */
    @PostMapping("/end")
    public ResponseEntity<String> endGame() {
        String result = gameService.endGame();
        return ResponseEntity.ok(result);
    }
}
