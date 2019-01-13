$(document).ready(function () {
    //切换左侧导航栏+/-图标
    $(".nav-sidebar-head .nav li a").click(function () {
        $(this).children().toggleClass("glyphicon-plus glyphicon-minus");
    });

    var sidebarBodyLi = $(".nav-sidebar-body .nav li");
    sidebarBodyLi.click(function () {
        //切换左侧导航栏的选中栏
        sidebarBodyLi.not(this).removeClass("active");
    });

    // 动态创建iframe
    var mainContent = $("#mainContent");
    var contentInMainContent = '';
    $(".nav-sidebar-body .nav li a").each(function () {
        var val = $(this).attr("href").substr(1);
        var url = '/' + APP_NAME + val.replace(/_/g, '/');
        contentInMainContent += '<div id="' + val + '" class="tab-pane fade">\n' +
            '                <iframe class="main-iframe" src="' + url + '" width="100%" frameborder="0"' +
            ' scrolling="auto"></iframe>\n' +
            '            </div>';
    });
    mainContent.html(contentInMainContent);
    mainContent.find("div").first().addClass("active in");

    // iframe高度自适应
    $(".main-iframe").on("load", function () {
        $(this).height($(window).height() - 55);
    });

    var toggleSidebar = $("#toggleSidebar");
    toggleSidebar.click(function () {
        // change icon
        toggleSidebar.toggleClass("glyphicon-arrow-left glyphicon-menu-hamburger");
        // hide/show sidebar
        $(".sidebar").toggle();
        // stretch/collapse main content
        mainContent.toggleClass("col-sm-offset-3")
            .toggleClass("col-md-offset-2")
            .toggleClass("col-sm-9")
            .toggleClass("col-md-10");
    });
});