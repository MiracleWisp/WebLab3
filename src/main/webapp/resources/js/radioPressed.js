function radio_pressed(r2) {
    r2 = +r2;
    const plot = document.getElementById("svg_plot");
    let toMove = [];
    plot.childNodes.forEach(function (childNode) {
        if (childNode.nodeName === "circle")
            toMove.push(childNode);
        if (childNode.nodeName === "text") {
            let temp_r = r2;
            if ([].includes.call(document.getElementsByClassName("divis"), childNode)) {
                temp_r=r2/2;
            }
            childNode.childNodes.forEach(function (child) {
                if (child.nodeName === "tspan") {
                    let tspan = document.createElementNS("http://www.w3.org/2000/svg", 'tspan');
                    tspan.innerHTML = temp_r;
                    childNode.replaceChild(tspan, child);
                }
            });
        }
    });

    // move points
    toMove.forEach(function (childNode) {
        // plot.removeChild(childNode);
        childNode.setAttribute('cx', convertXr(childNode.getAttribute('cx'), r2));
        childNode.setAttribute('cy', convertYr(childNode.getAttribute('cy'), r2));
        if (+childNode.ownR === r2) {
            childNode.setAttribute('fill-opacity', "1");
            childNode.setAttribute('stroke-opacity', "1");
        } else {
            childNode.setAttribute('fill-opacity', "0.2");
            childNode.setAttribute('stroke-opacity', "0.5");
        }
    });

    // move frame
    const top = document.getElementById("frame-top");
    const bot = document.getElementById("frame-bot");
    const left = document.getElementById("frame-left");
    const right = document.getElementById("frame-right");
    // const frame = document.getElementById("frame");

    const top_Y = top.getAttribute('y1');
    const bot_Y = bot.getAttribute('y1');
    const left_X = left.getAttribute('x1');
    const right_X = right.getAttribute('x1');

    // const leftX = frame.getAttribute('x');
    // const topY = frame.getAttribute('y');
    // const rightX = frame.getAttribute('width') + leftX;
    // const botY = frame.getAttribute('height') + topY;


    const top_Y_n = convertYr(top_Y, r2);
    const bot_Y_n = convertYr(bot_Y, r2);
    const left_X_n = convertXr(left_X, r2);
    const right_X_n = convertXr(right_X, r2);

    // const leftX_n = convertXr(leftX, r2);
    // const topY_n = convertYr(topY, r2);
    // const width_n = convertXr(rightX, r2) - leftX_n;
    // const height_n = convertYr(botY, r2) - topY_n;

    // frame.setAttribute('x', leftX_n);
    // frame.setAttribute('y', topY_n);
    // frame.setAttribute('width', width_n);
    // frame.setAttribute('height', height_n);

    top.setAttribute('x1', left_X_n);
    top.setAttribute('x2', right_X_n);
    top.setAttribute('y1', top_Y_n);
    top.setAttribute('y2', top_Y_n);

    bot.setAttribute('x1', left_X_n);
    bot.setAttribute('x2', right_X_n);
    bot.setAttribute('y1', bot_Y_n);
    bot.setAttribute('y2', bot_Y_n);

    left.setAttribute('x1', left_X_n);
    left.setAttribute('x2', left_X_n);
    left.setAttribute('y1', top_Y_n);
    left.setAttribute('y2', bot_Y_n);

    right.setAttribute('x1', right_X_n);
    right.setAttribute('x2', right_X_n);
    right.setAttribute('y1', top_Y_n);
    right.setAttribute('y2', bot_Y_n);

    window.r1 = r2;
}

function convertXr(x, r) {
    return ((x - 150) * window.r1)/ r + 150;
}

function convertYr(y, r) {
    return 150 + ((y - 150) * window.r1)/ r;
}