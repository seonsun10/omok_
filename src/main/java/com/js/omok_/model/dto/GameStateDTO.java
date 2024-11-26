package com.js.omok_.model.dto;

import lombok.Data;

@Data
public class GameStateDTO {
    private PlayerDTO currentPlayer;  // 현재 차례인 플레이어
    private boolean gameOver;         // 게임 종료 여부
    private String winner;            // 승자 ("black" 또는 "white")
}