function pre_init(r1) {
    window.r1 = r1;
}

function post_init() {
    move_frame(1, window.r1);
    radio_pressed(window.r1);
}