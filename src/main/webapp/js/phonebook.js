$(document).ready(function () {
    var isCheckedAll = false;
    var checkBoxes = $(".select-me");

    $(".select-all-label").click(function () {
        isCheckedAll = isCheckedAll === false;
        checkBoxes.prop("checked", isCheckedAll);
    });
});