var usernameValid = false;
var passwordValid = false;
var captchaValid = false;

function checkLoginButton() {
    document.getElementById("submitbutton").disabled = !(usernameValid && passwordValid && captchaValid);
}

window.onload = function () {
    var accountNameObject = document.getElementById("accountName");
    var passwordObject = document.getElementById("password");
    var captchaObject = document.getElementById("captcha");

    functions.checkAccountName = function () {
        accountNameObject.onfocus = function () {
            functions.focusInputBack(this, "0px -55px");
            functions.resetOutline(this);
            functions.hideCheckSpan(this);
        };

        accountNameObject.onblur = function () {
            functions.blurInputBack(this, "0px 0px");
            var thisValue = this.value;
            if (thisValue == null || thisValue.replace(/\s/g, "").length === 0) {
                functions.showWrongSpanAndMessage(this, "请填写账户名");
                usernameValid = false;
            } else {
                functions.showRightSpanAndHideMessage(this);
                usernameValid = true;
            }
            checkLoginButton();
        }
    };

    functions.checkPassword = function () {
        passwordObject.onfocus = function () {
            functions.focusInputBack(this, "-325px -55px");
            functions.resetOutline(this);
            functions.hideCheckSpan(this);
        };

        passwordObject.onblur = function () {
            functions.focusInputBack(this, "-325px 0px");
            var thisValue = this.value;
            if (thisValue == null || thisValue.replace(/\s/g, "").length === 0) {
                functions.showWrongSpanAndMessage(this, "请输入密码");
                passwordValid = false;
            } else {
                functions.showRightSpanAndHideMessage(this);
                functions.showRightSpanAndHideMessage(functions.aInputs[2]);
                passwordValid = true;
            }
            checkLoginButton();
        }
    };

    functions.checkCaptcha = function () {
        captchaObject.onfocus = function () {
            functions.focusInputBack(this, "-975px -55px");
            functions.resetOutline(this);
            functions.hideCheckSpan(this);
        };

        captchaObject.onblur = function () {
            functions.focusInputBack(this, "-975px 0px");
            var thisValue = this.value;
            if (thisValue == null || thisValue.replace(/\s/g, "").length === 0) {
                functions.showWrongSpanAndMessage(this, "请输入验证码");
                captchaValid = false;
            } else if (thisValue.length !== 4) {
                functions.showWrongSpanAndMessage(this, "验证码长度应该为4位");
                captchaValid = false;
            } else {
                functions.showRightSpanAndHideMessage(this);
                captchaValid = true;
            }
            checkLoginButton();
        };

        captchaObject.onkeyup = function () {
            captchaValid = this.value.length === 4;
            checkLoginButton();
        };
    };

    document.getElementById("registerBtn").onclick = function () {
        window.location.href = "/register.html";
    };

    functions.checkAccountName();
    functions.checkPassword();
    functions.checkCaptcha();
};
