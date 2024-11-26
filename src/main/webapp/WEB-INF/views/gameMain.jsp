<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>오목 게임</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">오목 게임</h1>

        <!-- 게임 상태 -->
        <div id="gameStatus" class="mb-3 text-center">
            <p>현재 차례: <span id="currentPlayer">플레이어</span></p>
            <p>게임 상태: <span id="gameState">준비</span></p>
            <p id="winnerMessage"></p>
        </div>

        <!-- 보드 -->
        <div class="contents">
            <div id="board" class="board-container mb-5"></div>
        </div>

        <!-- 플레이어 정보 입력 폼 -->
        <div class="infoArea">
            <div>
                <label for="player1">Player 1 Name:</label>
                <input type="text" id="player1" placeholder="Player 1" />
            </div>

            <div>
                <label for="player2">Player 2 Name:</label>
                <input type="text" id="player2" placeholder="Player 2" />
            </div>
        </div>

        <!-- 게임 컨트롤 -->
        <div class="text-center">
            <button id="startGameBtn" class="btn btn-primary">게임 시작</button>
            <button id="endGameBtn" class="btn btn-danger">게임 종료</button>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="js/script.js"></script>
</body>
</html>