var functions = {};
functions.oFormplace = document.getElementById("formplace");
functions.oErrorinfo = document.getElementById("error-info");
functions.oErrorbox = document.getElementById("error-box");
functions.aInputs = functions.oFormplace.getElementsByTagName("input");

// 检查字符串是否是邮箱
functions.checkEmailFormat = function (strEmail) {
    //声明邮箱正则
    var emailRegex = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    if (!emailRegex.test(strEmail)) {
        console.log("invalid email");
        return false;
    }
    return true;
};

//显示正确图标
functions.showRightSpan = function (obj) {
    var oSpan = obj.parentNode.parentNode.getElementsByTagName("span")[0];
    if (oSpan) {
        oSpan.className = "checkspan checkright";
    }
};

//显示错误图标
functions.showWrongSpan = function (obj) {
    var oSpan = obj.parentNode.parentNode.getElementsByTagName("span")[0];
    if (oSpan) {
        oSpan.className = "checkspan checkwrong";
    }
};

functions.hideCheckSpan = function (obj) {
    var oSpan = obj.parentNode.parentNode.getElementsByTagName("span")[0];
    if (oSpan) {
        oSpan.className = "";
    }
};

functions.changeOutline = function (obj) {
    obj.style['border'] = "1px solid #ff7c87";
    obj.style['box-shadow'] = "0px 0px 20px #ffb3b2";
};

functions.resetOutline = function (obj) {
    obj.style['border'] = "0px";
    obj.style['box-shadow'] = "none";
};

// 设置消息
functions.setMessage = function (str) {
    functions.oErrorinfo.innerHTML = str;
};

//显示消息
functions.showMessageBox = function (obj) {
    var tempLeft = 0;
    var tempTop = 0;

    while (obj != undefined) {//等效 obj = obj.offsetParent;while (obj != undefined)
        tempLeft += obj.offsetLeft; //叠加父容器的上边距
        tempTop += obj.offsetTop; //叠加父容器的左边距
        obj = obj.offsetParent;
    }
    functions.oErrorbox.style['left'] = tempLeft + 320 + "px";
    functions.oErrorbox.style['top'] = tempTop + 2 + "px";
    functions.oErrorbox.style['display'] = "block";
    functions.oErrorinfo.style['display'] = "block";
};

//隐藏消息
functions.hideMessageBox = function () {
    functions.oErrorbox.style['display'] = "none";
    functions.oErrorinfo.style['display'] = "none";
};

functions.focusInputBack = function (obj, str) {
    obj.parentNode.style['background-position'] = str;

};

functions.blurInputBack = function (obj, str) {
    obj.parentNode.style['background-position'] = str;
};

// 显示正确图标并且隐藏消息
functions.showRightSpanAndHideMessage = function (obj) {
    functions.showRightSpan(obj);
    functions.resetOutline(obj);
    functions.hideMessageBox(obj);
};

// 显示正确图标和消息
functions.showRightSpanAndMessage = function (obj, str) {
    functions.showRightSpan(obj);
    functions.changeOutline(obj);
    functions.setMessage(str);
    functions.showMessageBox(obj);
};

// 显示错误图标和消息
functions.showWrongSpanAndMessage = function (obj, str) {
    functions.showWrongSpan(obj);
    functions.changeOutline(obj);
    functions.setMessage(str);
    functions.showMessageBox(obj);
};
