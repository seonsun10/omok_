package com.js.omok_.service;

import com.js.omok_.model.dto.BoardDTO;
import com.js.omok_.model.dto.GameStateDTO;
import com.js.omok_.model.dto.MoveDTO;
import com.js.omok_.model.dto.PlayerDTO;

import java.util.List;

public interface GameService {
    BoardDTO startGame(List<PlayerDTO> players);
    BoardDTO makeMove(MoveDTO move);
    GameStateDTO getGameStatus();
    String endGame();
}
