function plotClicked(event){
    if (document.elementFromPoint(event.clientX, event.clientY).tagName !== "circle") {
        let r = window.r1;
        let oX = convertXReverse(event.offsetX, r); //=== undefined ? event.layerX : event.offsetX;
        let oY = convertYReverse(event.offsetY, r); //=== undefined ? event.layerY : event.offsetY;
        addPoint(oX, oY, r);
    }

    function addPoint(x, y, r) {
        // TODO: backend call
        let point = {
            x: x,
            y: y,
            r: r,
            success: true
        };
        let pointJ = JSON.stringify(point);
        draw_point(pointJ);

    }
}