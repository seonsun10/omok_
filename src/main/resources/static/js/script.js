$(document).ready(function() {
    let currentPlayer = 'player1'; // X가 먼저 시작
    let gameOver = false;

    // 게임 시작 버튼 클릭
    $('#startGameBtn').click(function() {
        // 플레이어 정보 가져오기
        const player1Name = $('#player1').val();
        const player2Name = $('#player2').val();

        const player1Color = 'black';
        const player2Color = 'white';

        // 플레이어 DTO 객체 생성
        const players = [
            { name: player1Name ,
              stoneColor: player1Color },
            { name: player2Name ,
              stoneColor: player2Color }
        ];

        // AJAX 요청
        $.ajax({
            url: '/game/start',  // 요청 URL
            type: 'POST',  // HTTP 메서드
            contentType: 'application/json',  // 요청 본문이 JSON임을 명시
            data: JSON.stringify(players),  // 서버로 보낼 데이터
            success: function(response) {
                createBoard(response.boardState);
                gameOver = false;
                $('#gameState').text('진행 중');
                $('#winnerMessage').text('');
                currentPlayer = 'X';
                $('.cell').removeClass('x o');
                $('#currentPlayer').text('플레이어 1 (X)');

            },
            error: function(xhr, status, error) {
                console.error('Error starting the game:', error); // 에러 처리
            }
        });
    });

    // 셀 클릭 시 돌 놓기 --전체적으로 수정해야함
    $('#board').on('click', '.cell', function() {
        if (gameOver) return; // 게임이 종료되었으면 클릭하지 못함

        const x = $(this).data('x');
        const y = $(this).data('y');

        // 이미 돌이 놓인 자리라면 무시
        if ($(this).hasClass('x') || $(this).hasClass('o')) {
            return;
        }

        // 서버로 돌 놓기 요청
        $.ajax({
            url: '/game/move',
            type: 'POST',
            contentType: 'application/json',  // 요청의 콘텐츠 타입을 JSON으로 설정
            data: JSON.stringify({ x: x, y: y }),  // 데이터를 JSON 형식으로 전송
            success: function(data) {
                if (data.gameOver) {
                    $('#gameState').text('게임 종료');
                    $('#winnerMessage').text(data.winner === "없음" ? "무승부" : data.winner + " 승리!");
                    gameOver = true;
                } else {
                    // 차례 변경
                    currentPlayer = currentPlayer === 'X' ? 'O' : 'X';
                    $('#currentPlayer').text(currentPlayer === 'X' ? '플레이어 1 (X)' : '플레이어 2 (O)');
                }
                // 돌 놓기
                $(`.cell[data-x=${x}][data-y=${y}]`).addClass(currentPlayer.toLowerCase());
            },
            error: function(xhr, status, error) {
                console.error('Request failed: ', status, error);  // 에러 처리
            }
        });
    });
});


/**
 * 게임 보드를 생성한다.
 * @param boardState
 */
function createBoard(boardState){
    let $board = $("#board");
    $board.empty(); // 보드 초기화

    // 보드 상태를 기반으로 동적 생성
    boardState.forEach(function(row, x) { // 행 반복 (x 값)
        var $row = $("<div class='row'></div>");
        row.forEach(function(cell, y) { // 열 반복 (y 값)
            // 각 셀에 x, y 속성 추가
            var $cell = $(`<div class='cell' data-x='${x}' data-y='${y}'></div>`);
            $row.append($cell);
        });
        $board.append($row);
    });
}