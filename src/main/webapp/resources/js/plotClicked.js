function plotClicked(event){
    if (document.elementFromPoint(event.clientX, event.clientY).tagName !== "circle") {
        let r = window.r1;
        let oX = convertXReverse(event.offsetX, r); //=== undefined ? event.layerX : event.offsetX;
        let oY = convertYReverse(event.offsetY, r); //=== undefined ? event.layerY : event.offsetY;
        addPoint(oX, oY, r);
    }

    function addPoint(x, y, r) {
        // TODO: backend call
        // document.getElementById("pointForm:user_X_hidden").value = x;
        remoteX([{name: 'x_value', value: x}]);
        remoteY([{name: 'y_value', value: y}]);
        remoteR([{name: 'r_value', value: r}]);
        remoteAdd();
    }

    function convertXReverse(cx) {
        return (cx - 200) * window.r1 / 160;
    }

    function convertYReverse(cy) {
        return (cy - 200) * window.r1 / -160;
    }
}