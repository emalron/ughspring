var heap, fs_, address = 16338307; // index = address of the current score
var score_old = 0;
var onair=false;
const score_display = document.querySelector("span#current-score");
var update = function() {
    let score = heap[address];
    score_display.textContent = score;
    saveScore(score);
}
Dos(document.querySelector("canvas#jsdos"),
        {wdosboxUrl: '/pro30/resources/ugh/wdosbox.js'}).ready((fs, main) => {
        heap = fs.dos.HEAPU16;
        fs.createFile("dosbox.conf", `
            [joystick]
            joysticktype=none
            `);
        fs.extract("${contextPath}/resources/ugh/UGH.zip", "/UGH1").then(() => {
            main(["-conf", "dosbox.conf", "-c", "cd UGH1", "-c", "UGH.EXE"]);
        });
        setInterval(update, 500);
    })

var STATUS = {
    READY: 0,
    GAMING: 1
}

function getGameState() {
    if(onair) {
        return STATUS.GAMING;
    }
    return STATUS.READY;
}

function saveScore(score) {
    const score_ = score || 1;
    const game_state = getGameState();
    switch(game_state) {
        case STATUS.READY:
            if(score > 0) {
                onair = true;
                console.log(`게임 시작`);
            }
            break;
        case STATUS.GAMING:
            if(score == 0) {
                onair = false;
                sendScore(score_old);
                console.log(`게임 종료 최종 점수 ${score_old}`);
            } else {
                score_old = score;
            }
            break;
    }
}

function sendScore(score) {
    const url = `${window.location.origin}/pro30/ugh`;
    fetch(url, {
        method: 'post',
        body: JSON.stringify({"score": score}),
        headers: {"Content-Type": "application/json", "Accept": "application/json"}
    }).then(response => {
        console.log(response.text())
    }).catch(error => {
        console.error(error);
    })
}
