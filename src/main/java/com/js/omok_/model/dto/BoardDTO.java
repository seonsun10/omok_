package com.js.omok_.model.dto;

import lombok.Data;

@Data
public class BoardDTO {
    private int size;               // 보드 크기 (예: 19x19)
    private String[][] boardState;  // 현재 보드 상태 (각 위치에 "black", "white", null 값)

    public BoardDTO(int size) {
        this.size = size;
        this.boardState = new String[size][size];
    }
}