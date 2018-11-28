window.r1 = 1;

function draw_point(point) {
    console.log(point);
    point = JSON.parse(point);
    let plot = document.getElementById("svg_plot");
    let circle = document.createElementNS("http://www.w3.org/2000/svg", 'circle');
    circle.setAttribute('cx', convertX(point.x));
    circle.setAttribute('cy', convertY(point.y));
    circle.setAttribute('r', "3");
    circle.setAttribute("fill", +point.success ? "#00ff00" : "#ff0000");
    circle.setAttribute("stroke-width", "1");
    circle.setAttribute("stroke", "black");
    // circle.onclick = circlePressed;
    circle.ownR = point.r;
    plot.appendChild(circle);
    // window.results[point.id] = point;
    // addRow(point);
    // circle.id = point.id;
    // if (+circle.ownR !== +window.r1) {
    //     circle.setAttribute('fill-opacity', "0.2");
    //     circle.setAttribute('stroke-opacity', "0.5");
    // }
    // circle.onclick.apply(circle);
}

function convertX(x) {
    return 120 * x / window.r1 + 150;
}

function convertY(y) {
    return 150 - 120 * y / window.r1;
}