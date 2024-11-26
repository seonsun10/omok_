package com.js.omok_.service.impl;

import com.js.omok_.model.dto.BoardDTO;
import com.js.omok_.model.dto.GameStateDTO;
import com.js.omok_.model.dto.MoveDTO;
import com.js.omok_.model.dto.PlayerDTO;
import com.js.omok_.service.GameService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("gameService")
public class GameServiceImpl implements GameService {

    private BoardDTO board;
    private PlayerDTO currentPlayer;
    private PlayerDTO player1;
    private PlayerDTO player2;
    private boolean gameOver;
    private String winner;

    // 게임 시작
    public BoardDTO startGame(List<PlayerDTO> players) {
        // 플레이어 설정
        player1 = players.get(0);
        player2 = players.get(1);
        currentPlayer = player1;  // 게임 시작 시 player1이 먼저
        board = new BoardDTO(19);  // 19x19 보드 생성
        gameOver = false;
        winner = null;

        return board;
    }

    // 돌 놓기
    public BoardDTO makeMove(MoveDTO move) {
        if (gameOver) {
            throw new IllegalArgumentException("게임이 이미 종료되었습니다.");
        }

        // 플레이어 확인
        if (move.getPlayerNumber() != currentPlayer.getPlayerNumber()) {
            throw new IllegalArgumentException("잘못된 플레이어입니다.");
        }

        // 보드에 돌 놓기
        if (board.getBoardState()[move.getX()][move.getY()] != null) {
            throw new IllegalArgumentException("이미 돌이 놓여 있는 곳입니다.");
        }
        board.getBoardState()[move.getX()][move.getY()] = currentPlayer.getStoneColor();

        // 승리 조건 체크
        if (checkWinner(move)) {
            gameOver = true;
            winner = currentPlayer.getStoneColor();
        } else {
            // 플레이어 차례 전환
            currentPlayer = currentPlayer == player1 ? player2 : player1;
        }

        return board;
    }

    // 게임 상태 조회
    public GameStateDTO getGameStatus() {
        GameStateDTO gameState = new GameStateDTO();
        gameState.setCurrentPlayer(currentPlayer);
        gameState.setGameOver(gameOver);
        gameState.setWinner(winner);
        return gameState;
    }

    // 게임 종료
    public String endGame() {
        if (!gameOver) {
            throw new IllegalArgumentException("게임이 진행 중입니다.");
        }
        return "게임 종료. 승자: " + winner;
    }

    // 승리 조건 체크
    private boolean checkWinner(MoveDTO move) {
        // 승리 조건을 체크하는 로직 (5개의 돌이 연속으로 놓였는지 확인)
        // 이 예시에서는 간단히 가로, 세로, 대각선으로 5개 연속 여부만 체크함
        String color = currentPlayer.getStoneColor();
        int x = move.getX();
        int y = move.getY();

        return checkDirection(x, y, color, 1, 0) ||  // 가로
                checkDirection(x, y, color, 0, 1) ||  // 세로
                checkDirection(x, y, color, 1, 1) ||  // 대각선 1
                checkDirection(x, y, color, 1, -1);   // 대각선 2
    }

    // 특정 방향으로 5개 연속 돌이 놓였는지 체크
    private boolean checkDirection(int x, int y, String color, int dx, int dy) {
        int count = 1;

        // 한 방향으로 돌 세기
        for (int i = 1; i < 5; i++) {
            int nx = x + dx * i;
            int ny = y + dy * i;
            if (nx >= 0 && ny >= 0 && nx < board.getSize() && ny < board.getSize() &&
                    color.equals(board.getBoardState()[nx][ny])) {
                count++;
            } else {
                break;
            }
        }

        // 반대 방향으로 돌 세기
        for (int i = 1; i < 5; i++) {
            int nx = x - dx * i;
            int ny = y - dy * i;
            if (nx >= 0 && ny >= 0 && nx < board.getSize() && ny < board.getSize() &&
                    color.equals(board.getBoardState()[nx][ny])) {
                count++;
            } else {
                break;
            }
        }

        return count >= 5;
    }
}
