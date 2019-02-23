var usernameValid = false;
var passwordValid = false;
var captchaValid = true;

function checkLoginButton() {
    document.getElementById("submitbutton").disabled = !(usernameValid && passwordValid && captchaValid);
}

function recaptchaCallback() {
    captchaValid = true;
    console.log(usernameValid + "," + passwordValid + ","+captchaValid)
    checkLoginButton();
}

window.onload = function () {
    var accountNameObject = document.getElementById("accountName");
    var passwordObject = document.getElementById("password");

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

    functions.checkAccountName();
    functions.checkPassword();

    document.querySelector("#registerBtn").onclick = function () {
        window.location.href = "/register";
    };

    document.querySelector("#forgetPwdBtn").onclick = function () {
        window.location.href = "/forget";
    };
};
