function pre_init(r1) {
    window.r1 = r1;
}

function post_init() {
    selectButtonX(document.getElementById("pointForm:x0btn"));
    radio_pressed(window.r1);
}