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
    window.r1 = r2;
}

function convertXr(x, r) {
    return ((x - 150) * window.r1)/ r + 150;
}

function convertYr(y, r) {
    return 150 + ((y - 150) * window.r1)/ r;
}