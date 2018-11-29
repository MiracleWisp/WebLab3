function selectButtonX(elmt) {
    $(".x_btn").removeClass("selected");
    elmt.className = elmt.className + " selected";
}