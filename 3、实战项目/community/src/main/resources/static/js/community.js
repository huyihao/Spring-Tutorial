/**
 * 通用评论方法
 * @param questionId
 * @param comment
 * @param type
 */
function universalComment(questionId, comment, type) {
    var appendHtml = "";
    $.ajax({
        type: "POST",
        contentType : "application/json",
        url: "/comment",
        data: JSON.stringify({
            "parentId" : questionId,
            "content" : comment,
            "type" : type
        }),
        async : false,
        success: function (response) {
            console.log(response);
            if (response.code !== 0) {
                if (response.code === 2002) {
                    var isAccepted = confirm(response.message);
                    if (isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=ce46b85af14a2d21447b&redirect_uri=http://localhost:8080/callback&scope=user&state=2");
                        window.localStorage.setItem("closable", true);
                    }
                } else {
                    alert(response.message);
                }
            } else {
                var userAvatar = $("#userAvatar")[0].src;
                var userName = $("#userName").text();

                var template = $("#comment-template").children(".media");
                template.find(".small-avatar").attr("src", userAvatar);
                template.find(".media-heading").text(userName);
                template.find(".comment-text").text(comment);
                template.find(".pull-right").text(getFormatDate(response.data.gmtCreate));
                if (type === 2) {
                    template.find(".text-desc").hide();
                }
                appendHtml = template.prop("outerHTML");
                template.find(".text-desc").show();
            }
        },
        dataType: "json"
    });
    return appendHtml;
}

/**
 * 提交评论
 **/
function comment() {
    var questionId = $("#questionId").val();
    var comment = $("#commentText").val();
    var type = 1;
    var appendHtml = universalComment(questionId, comment, type);

    if (appendHtml !== "") {
        // 显示刚刚提交的评论
        $("#comment-divider").after(appendHtml);

        // 评论成功清空文本域
        $("#commentText").val("");

        // 如果评论类型是一级评论，评论总数加1
        const commentCount = $("#comment-count");
        commentCount.text(parseInt(commentCount.text()) + 1)
    }
}

/**
 * 提交二级评论
 */
function subComment(e) {
    var commentId = e.getAttribute("data-id");
    var comment = $(e).prev().val();
    var type = 2;
    var appendHtml = universalComment(commentId, comment, type);
    if (appendHtml !== "") {
        $("#subComments-" + commentId).prepend(appendHtml);
        const subCommentCount = $("#subCommentCount-" + commentId);
        subCommentCount.text(parseInt(subCommentCount.text()) + 1)
        $(e).prev().val("");
    }
}

/* 获取当前日期 */
function getFormatDate(timestamp){
    var nowDate = null;
    if (timestamp !== null) {
        nowDate = new Date(timestamp);
    } else {
        nowDate = new Date();
    }
    var year = nowDate.getFullYear();
    var month = nowDate.getMonth() + 1 < 10 ? "0" + (nowDate.getMonth() + 1) : nowDate.getMonth() + 1;
    var date = nowDate.getDate() < 10 ? "0" + nowDate.getDate() : nowDate.getDate();
    var hour = nowDate.getHours()< 10 ? "0" + nowDate.getHours() : nowDate.getHours();
    var minute = nowDate.getMinutes()< 10 ? "0" + nowDate.getMinutes() : nowDate.getMinutes();
    var second = nowDate.getSeconds()< 10 ? "0" + nowDate.getSeconds() : nowDate.getSeconds();
    return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
}

/**
 * 切换二级评论
 */
function toggleSubComments(e) {
    var id = e.getAttribute("data-id");
    var loaded = e.getAttribute("loaded-subComments");
    console.log(loaded);
    // 用loaded-subComments判断是否已加载过子评论
    if (loaded !== "true") {
        $.ajax({
            type: "GET",
            contentType : "application/json",
            url: "/subComment/" + id,
            data: {},
            success: function (response) {
                console.log(response);
                if (response.code !== 0) {
                    if (response.code === 2002) {
                        var isAccepted = confirm(response.message);
                        if (isAccepted) {
                            window.open("https://github.com/login/oauth/authorize?client_id=ce46b85af14a2d21447b&redirect_uri=http://localhost:8080/callback&scope=user&state=2");
                            window.localStorage.setItem("closable", true);
                        }
                    } else {
                        alert(response.message);
                    }
                } else {
                    e.setAttribute("loaded-subComments", true);
                    var commentData = response.data;
                    var template = $("#comment-template").children(".media");
                    template.find(".text-desc").hide();
                    for (var i = 0; i < commentData.length; i++) {
                        template.find(".small-avatar").attr("src", commentData[i].user.avatarUrl);
                        template.find(".media-heading").text(commentData[i].user.name);
                        template.find(".comment-text").text(commentData[i].content);
                        template.find(".pull-right").text(getFormatDate(commentData[i].gmtCreate));
                        if (i === commentData.length - 1) {
                            template.find(".comment-hr").hide();
                        }
                        $("#subComments-" + id).append(template.prop("outerHTML"));
                    }
                    template.find(".text-desc").show();
                    template.find(".comment-hr").show();
                    $("#subCommentsBlock-" + id).addClass("in");
                }
            },
            dataType: "json"
        });
    } else {
        var subCommentsBlock = $("#subCommentsBlock-" + id);
        if (subCommentsBlock.hasClass("in")) {
            subCommentsBlock.removeClass("in");
        } else {
            subCommentsBlock.addClass("in");
        }
    }
}

/**
 * 点赞
 * @param e
 */
function like(e) {
    var id = e.getAttribute("data-id");
    var liked = e.getAttribute("liked");
    if (liked === "true") {
        alert("你已经点赞过了，不要重复点哦~");
    } else {
        $.ajax({
            type: "GET",
            contentType : "application/json",
            url: "/commentLike/" + id,
            data: {},
            dataType: "json",
            success: function (response) {
                if (response.code !== 0) {
                    if (response.code === 2002) {
                        var isAccepted = confirm(response.message);
                        if (isAccepted) {
                            window.open("https://github.com/login/oauth/authorize?client_id=ce46b85af14a2d21447b&redirect_uri=http://localhost:8080/callback&scope=user&state=2");
                            window.localStorage.setItem("closable", true);
                        }
                    } else {
                        alert(response.message);
                    }
                } else {
                    e.setAttribute("liked", true);
                    const likeCount = $(e).children(".icon-num");
                    likeCount.text(parseInt(likeCount.text()) + 1);
                }
            }
        });
    }
}