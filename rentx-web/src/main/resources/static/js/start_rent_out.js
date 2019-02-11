jQuery(function ($) {
    let inputCategoryId = 0;
    const $categorySelectTreeBlock = $('#categorySelectTreeBlock');
    const $categorySelectModal = $('#categorySelectModal');
    const treeSettingData = {
        key: {
            title: "description"
        },
        simpleData: {
            enable: true,
            pIdKey: "parentId"
        }
    };

    const $hintModal = $('#hintModal');
    const $hintContent = $('#hintContent');
    const $hintModalHeader = $hintModal.find('.modal-header');
    const $hintModalBody = $hintModal.find('.modal-body');

    // 显示提示框
    function showHintModal(message, success) {
        if (success) {
            $hintModalHeader.attr('class', 'modal-header bg-success');
            $hintModalBody.attr('class', 'modal-body text-success');
        } else {
            $hintModalHeader.attr('class', 'modal-header bg-warning');
            $hintModalBody.attr('class', 'modal-body text-warning');
        }
        $hintContent.text(message);
        $hintModal.modal();
    }

    const $inputCategoryName = $('#inputCategoryName');

    // 类别选择树节点被点击相应的事件
    function onCategorySelectTreeNodeClick(event, treeId, treeNode) {
        if (treeNode.level !== 3) {
            showHintModal('只能选择第三级别', false);
            return;
        }
        $inputCategoryName.val(treeNode.name);
        inputCategoryId = treeNode.id;
        $categorySelectModal.modal('hide');
    }

    const categorySelectTreeSetting = {
        data: treeSettingData,
        callback: {
            onClick: onCategorySelectTreeNodeClick
        }
    };

    let categories = [];

    // 获取所有类别并初始化类别树
    function getCategoriesAndInitTree() {
        $.get("/categories", function (data) {
            console.log(data);
            if (data.code === RESPONSE_CODE.SUCCESS) {
                categories = data.data;
                let rootCategory = {
                    id: 0,
                    name: "根类别",
                    description: "根类别",
                    level: 0,
                    status: true,
                    open: true
                };
                categories.push(rootCategory);
                console.log(categories);
                // 初始化类别树
                $.fn.zTree.init($categorySelectTreeBlock, categorySelectTreeSetting, categories);
            } else {
                alert("error");
                // window.location.href = "/manage/errorPage";
            }
        }, DATA_TYPE.JSON);
    }

    function initBindSelectCategoryEvent() {
        $('#categorySelectBtn, #inputCategoryName').click(function () {
            // open modal
            $categorySelectModal.modal();
        });
    }

    function initAddListener() {
        $('#addBtn').click(function () {
            // report validity
            var valid = document.querySelector("#addForm").reportValidity();
            if (!valid) {
                return;
            }
            // send post request
            let stuff = JSON.stringify({
                categoryId: inputCategoryId,
                name: $('#inputName').val(),
                deposit: $('#inputDeposit').val(),
                rental: $('#inputRental').val(),
                status: $('#addForm').find('input[name="status"]:checked').val(),
                description: $('#inputDescription').val()
            });
            console.log(stuff);
            $.ajax('/stuffs/out', {
                data: stuff,
                contentType: 'application/json',
                type: 'POST',
                dataType: DATA_TYPE.JSON,
                success: function (data) {
                    // if data is the error page
                    if (data.code === RESPONSE_CODE.SUCCESS) {
                        // reset add form
                        document.querySelector('#addForm').reset();
                        showHintModal('添加成功', true);
                    } else {
                        showHintModal(data.message + ', 添加失败', false);
                    }
                }
            });
        });
    }

    function init() {
        getCategoriesAndInitTree();
        initBindSelectCategoryEvent();
        initAddListener();
    }

    $(document).ready(function () {
        init();
    });
});