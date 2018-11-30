function radio_pressed(r2) {
    r2 = +r2;
    let plot = document.getElementById("svg_plot");
    let toRemove = [];
    plot.childNodes.forEach(function (childNode) {
        if (childNode.nodeName === "circle")
            toRemove.push(childNode);
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
    toRemove.forEach(function (childNode) {
        // plot.removeChild(childNode);
        childNode.setAttribute('cx', convertXr(convertXReverse(childNode.getAttribute('cx')), r2));
        childNode.setAttribute('cy', convertYr(convertYReverse(childNode.getAttribute('cy')), r2));
        if (+childNode.ownR === r2) {
            childNode.setAttribute('fill-opacity', "1");
            childNode.setAttribute('stroke-opacity', "1");
        } else {
            childNode.setAttribute('fill-opacity', "0.2");
            childNode.setAttribute('stroke-opacity', "0.5");
        }
    });
    window.r1 = r2;
}

function convertXr(x, r) {
    return 160 * x / r + 200;
}

function convertYr(y, r) {
    return 200 - 160 * y / r;
}

function convertXReverse(cx) {
    return (cx - 200) * window.r1 / 160;
}

function convertYReverse(cy) {
    return (cy - 200) * window.r1 / -160;
}