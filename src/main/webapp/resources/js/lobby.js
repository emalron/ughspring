var cointime_now = 0;
var cointime_target = 0;
var cointimeInterval;
function coinCheck() {
    const url = `${window.location.origin}/pro30/cointime`;
    fetch(url)
    .then(res => {
        return res.json();
    }).then(result => {
        if(result.msg == 'cointime') {
            cointime_now = result.now;
            cointime_target = result.cointime;
            cointimeInterval = setInterval(cointimeUpdate, 1000);
        }
    })
}

function cointimeUpdate() {
    cointime_now += 1;
    const diff = cointime_target - cointime_now;
    const valuenow = diff/1800*100; // 30min * 60 sec/min = 1,800 sec
    const min = parseInt(diff/60);
    const second = diff%60;
    $('div.progress-bar')
    .css('width', valuenow+'%')
    .attr('aria-valuenow', valuenow)
    .text(`${min}:${second}`)
    if(valuenow <= 0) {
        cointime_now = 0;
        cointime_target = 0;
        clearInterval(cointimeInterval);
    }
}