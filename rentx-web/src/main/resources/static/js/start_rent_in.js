$(document).ready(function () {
    //确认删除区域
    var deleteConfirmBlock = $("#deleteConfirm");
    var singleCheckBoxes = $(".single-checkbox");
    var selectAllCheckBox = $("#selectAllCheckBox");
    var multiDeleteBtn = $("#multiDeleteBtn");
    var dropdownMenu1 = $("#dropdownMenu1");

    //显示确认删除警告框
    $(".btn-single-delete").click(function () {
        deleteConfirmBlock.slideDown();
    });

    multiDeleteBtn.click(function () {
        if (singleCheckBoxes.filter(":checked").length === 0) {
            $(this).popover('show');
            setTimeout(function () {
                multiDeleteBtn.popover('hide');
            }, 1000);
        } else {
            deleteConfirmBlock.slideDown();
        }
    });

    //隐藏确认删除警告框
    deleteConfirmBlock.find("button").click(function () {
        deleteConfirmBlock.hide(500);
    });

    //点击确认删除后的操作
    deleteConfirmBlock.find(".confirm").click(function () {
        // delete the selected item
    });

    var pageNumBtns = $(".pagination .number");

    //切换页码中的高亮页
    pageNumBtns.click(function () {
        pageNumBtns.removeClass("active");
        $(this).addClass("active");
    });

    //前一页的操作
    $(".pagination .previous").click(function () {
        var activePageObject = $(".pagination .active");
        var pageNum = parseInt(activePageObject.text());
        activePageObject.removeClass("active");
        if (pageNum === parseInt(pageNumBtns.first().text())) {
            pageNumBtns.last().addClass("active");
            return;
        }
        pageNumBtns.each(function () {
            if ($(this).text() === pageNum - 1 + "") {
                $(this).addClass("active");
                return false;
            }
        });
    });

    //后一页的操作
    $(".pagination .next").click(function () {
        var activePageObject = $(".pagination .active");
        var pageNum = parseInt(activePageObject.text());
        activePageObject.removeClass("active");
        if (pageNum === parseInt(pageNumBtns.last().text())) {
            pageNumBtns.first().addClass("active");
            return;
        }
        pageNumBtns.each(function () {
            if ($(this).text() === pageNum + 1 + "") {
                $(this).addClass("active");
                return false;
            }
        });
    });

    //表头内容数组
    var thsText = [];
    $("table").find("th").each(function () {
        thsText.push($(this).text());
    });

    //显示表格一条的具体信息
    $(".detail").parents("td").click(function () {
        $(this).find("span").toggleClass("glyphicon-plus glyphicon-minus");

        if ($(this).parents("tr").next().hasClass("detail-block")) {
            $(this).parents("tr").next().fadeOut(function () {
                $(this).remove();
            });
            return;
        }
        var tdsText = [];
        $(this).parents("tr").children().each(function () {
            tdsText.push($(this).text());
        });

        var detailContent = '';
        for (var i = 2; i < thsText.length - 1; i++) {
            detailContent += '<dt>' + thsText[i] + ':</dt><dd>' + tdsText[i] + '</dd>';
        }
        var colNum = $(this).parents("tr").children().length;
        var detail = '<tr class="detail-block info">\n' +
            '                <td colspan="' + colNum + '">' +
            '<dl class="dl-horizontal">\n' +
            detailContent +
            '</dl>' +
            '</td>\n' +
            '         </tr>';
        $(this).parents("tr").after(detail);
        var newRow = $(this).parents("tr").next();
        newRow.hide();
        newRow.fadeIn("slow");
    });

    //set table show animate
    var tableBlock = $("#tableBlock");
    tableBlock.hide();
    tableBlock.slideDown(1000);

    // checkbox select all
    selectAllCheckBox.click(function () {
        if ($(this).is(':checked')) {
            singleCheckBoxes.prop("checked", true);
        } else {
            singleCheckBoxes.prop("checked", false);
        }
    });
    singleCheckBoxes.click(function () {
        if ($(this).is(':checked')) {
            if (singleCheckBoxes.length === singleCheckBoxes.filter(":checked").length) {
                selectAllCheckBox.prop("checked", true);
            }
        } else {
            selectAllCheckBox.prop("checked", false);
        }
    });

    $("#refreshBtn").click(function () {
        window.location.reload(true);
    });

    // init dropdown menu content
    var dropdownMenuContent = '';
    for (var i in thsText) {
        if (thsText[i].trim().length !== 0) {
            dropdownMenuContent += '<li>\n' +
                '                <label>\n' +
                '                    <input type="checkbox" checked="checked">' + thsText[i] + '\n' +
                '                </label>\n' +
                '            </li>';
        }
    }
    dropdownMenu1.html(dropdownMenuContent);

    // toggle a column of table
    dropdownMenu1.find("input").click(function () {
        var index = thsText.indexOf($(this).parents("label").text().trim());
        // toggle th
        tableBlock.find("th").eq(index).toggle();
        // toggle td
        tableBlock.find("tr").each(function () {
            $(this).find("td").eq(index).toggle();
        });
    });
});