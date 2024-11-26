package com.js.omok_.model.dto;

import lombok.Data;

@Data
public class PlayerDTO {
    private String name;         // 플레이어 이름
    private int playerNumber;    // 플레이어 번호 (1 또는 2)
    private String stoneColor;   // 돌 색상 (예: "black", "white")
}