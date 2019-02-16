var usernameValid = false;
var usernameNotExist = true;
var passwordValid = false;
var confirmedPasswordValid = false;
var emailValid = false;
var emailNotExist = true;
var emailCaptchaValid = false;

function checkEmailSendButton() {
    document.getElementById("codebutton").disabled = !emailValid;
}

function checkRegisterButton() {
    console.log({
        "usernameValid": usernameValid,
        "usernameNotExist": usernameNotExist,
        "passwordValid": passwordValid,
        "confirmedPasswordValid": confirmedPasswordValid,
        "emailValid": emailValid,
        "emailNotExist": emailNotExist,
        "emailCaptchaValid": emailCaptchaValid
    });
    document.getElementById("submitbutton").disabled = !(usernameValid && passwordValid &&
        confirmedPasswordValid && emailValid && emailCaptchaValid && usernameNotExist && emailNotExist);
}

window.onload = function () {
    var usernameObject = document.getElementById("username");
    var passwordObject = document.getElementById("password");
    var confirmedPasswordObject = document.getElementById("confirmedPassword");
    var emailObject = document.getElementById("email");
    var emailCaptchaObject = document.getElementById("emailCaptcha");

    var firstPassword = "";

    functions.checkUsername = function () {
        usernameObject.onfocus = function () {
            functions.focusInputBack(this, "0px -55px");
            functions.resetOutline(this);
            functions.hideCheckSpan(this);
        };

        usernameObject.onkeyup = function () {
            functions.blurInputBack(this, "0px 0px");
            var thisValue = this.value;
            //非空,不能有空格
            if (thisValue == null || thisValue.replace(/\s/g, "").length === 0 || thisValue.indexOf(" ") !== -1) {
                functions.showWrongSpanAndMessage(this, "此处不能留空");
                usernameValid = false;
                // 5-15个字符
            } else if (thisValue.length < 5 || thisValue.length > 15) {
                functions.showWrongSpanAndMessage(this, "长度应为5-15个字符，请勿包含姓名/身份证/银行卡等隐私信息");
                usernameValid = false;
                // 仅中、英文字母（不区分大小写）、数字和下划线
            } else if (/^[0-9a-zA-Z\u4e00-\u9fa5_]{5,15}$/.test(thisValue) === false) {
                functions.showWrongSpanAndMessage(this, "用户名仅支持中英文、数字和下划线");
                usernameValid = false;
            } else {
                usernameValid = true;
            }
            if (usernameValid && usernameNotExist) {
                functions.showRightSpanAndHideMessage(this);
            }
            checkRegisterButton();
        }
    };

    functions.checkPassword = function () {
        passwordObject.onfocus = function () {
            functions.focusInputBack(this, "-325px -55px");
            functions.resetOutline(this);
            functions.hideCheckSpan(this);
        };

        passwordObject.onkeyup = passwordObject.onblur = function () {
            functions.focusInputBack(this, "-325px 0px");
            var thisValue = this.value;
            //不能全为空格，首尾允许有空格
            if (thisValue == null || thisValue.replace(/\s/g, "").length === 0) {
                functions.showWrongSpanAndMessage(this, "此处不能留空");
                passwordValid = false;
                //6~16个字符
            } else if (thisValue.length < 6 || thisValue.length > 16) {
                functions.showWrongSpanAndMessage(this, "长度应为6-16个字符");
                passwordValid = false;
                //仅字母（区分大小写）、数字及英文标点
            } else if (/^[0-9a-zA-Z\u4e00-\u9fa5_]{6,16}$/.test(thisValue) === false) {
                functions.showWrongSpanAndMessage(this, "密码仅支持字母、数字及标点符号");
                passwordValid = false;
            } else if (thisValue !== functions.aInputs[2].value) {
                firstPassword = thisValue;
                functions.showWrongSpanAndMessage(confirmedPasswordObject, "两个密码不匹配");
                functions.showRightSpan(this);
                functions.resetOutline(this);
                passwordValid = false;
            } else {
                firstPassword = thisValue;
                functions.showRightSpanAndHideMessage(this);
                functions.showRightSpanAndHideMessage(confirmedPasswordObject);
                passwordValid = true;
            }
            checkRegisterButton();
        }
    };

    functions.checkConfirmedPassword = function () {
        confirmedPasswordObject.onfocus = function () {
            functions.focusInputBack(this, "-325px -55px");
            functions.resetOutline(this);
            functions.hideCheckSpan(this);
        };

        confirmedPasswordObject.onkeyup = confirmedPasswordObject.onblur = function () {
            functions.focusInputBack(this, "-325px 0px");
            var thisValue = this.value;
            if (thisValue == null || thisValue.replace(/\s/g, "").length === 0) {
                functions.showWrongSpanAndMessage(this, "此处不能留空");
                confirmedPasswordValid = false;
            } else if (firstPassword !== thisValue) {
                functions.showWrongSpanAndMessage(this, "两个密码不匹配");
                confirmedPasswordValid = false;
            } else {
                functions.showRightSpanAndHideMessage(this);
                confirmedPasswordValid = true;
                passwordValid = true;
            }
            checkRegisterButton();
        }
    };

    functions.checkEmail = function () {
        emailObject.onfocus = function () {
            functions.focusInputBack(this, "-650px -55px");
            functions.resetOutline(this);
            functions.hideCheckSpan(this);
        };

        emailObject.onkeyup = emailObject.onblur = function () {
            functions.focusInputBack(this, "-650px 0px");
            var thisValue = this.value;
            // 非空
            if (thisValue == null || thisValue.replace(/\s/g, "").length === 0) {
                functions.showWrongSpanAndMessage(this, "此处不能留空");
                emailValid = false;
                // 邮箱长度不超过50
            } else if (thisValue.length > 50) {
                functions.showWrongSpanAndMessage(this, "邮箱长度不能超过50");
                emailValid = false;
                // 邮箱格式验证
            } else if (functions.checkEmailFormat(thisValue) === false) {
                functions.showWrongSpanAndMessage(this, "邮箱格式不符合要求");
                emailValid = false;
                // 不能与已有邮箱重复
            } else {
                emailValid = true;
            }
            if (emailValid && emailNotExist) {
                functions.showRightSpanAndHideMessage(this);
            }
            checkEmailSendButton();
        }
    };

    functions.checkEmailCaptcha = function () {
        emailCaptchaObject.onfocus = function () {
            functions.focusInputBack(this, "-975px -55px");
            functions.resetOutline(this);
            functions.hideCheckSpan(this);
        };

        emailCaptchaObject.onkeyup = emailCaptchaObject.onblur = function () {
            functions.focusInputBack(this, "-975px 0px");
            var thisValue = this.value;
            //非空
            if (thisValue == null || thisValue.replace(/\s/g, "").length === 0) {
                functions.showWrongSpanAndMessage(this, "此处不能留空");
                emailCaptchaValid = false;
            } else if (thisValue.length !== 6) {
                functions.showWrongSpanAndMessage(this, "验证码长度为6位");
                emailCaptchaValid = false;
            } else if (/^[0-9a-zA-Z]+$/.test(thisValue) === false) {
                functions.showWrongSpanAndMessage(this, "验证码只能包括字母或数字");
                emailCaptchaValid = false;
            } else {
                functions.showRightSpanAndHideMessage(this);
                emailCaptchaValid = true;
            }
            checkRegisterButton();
        }
    };

    functions.checkUsername();
    functions.checkPassword();
    functions.checkConfirmedPassword();
    functions.checkEmail();
    functions.checkEmailCaptcha();
};
