<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>尚硅谷会员注册页面</title>
    <%--静态包含 base标签、css样式、jQuery文件--%>
    <%@include file="/pages/common/head.jsp" %>
    <style type="text/css">
        .login_form {
            height: 420px;
            margin-top: 25px;
        }

    </style>


    <script type="text/javascript">
		$(function () {
		    $("#username").blur(function () {
                // 1. 获取用户名
                var username = this.value;

                $.getJSON("${basePath}userServlet", "action=ajaxExistsUsername&username="+username, function (data) {
                    if(data.existsUsername) {
                        $("span.errorMsg").text("用户名已存在");
                    } else {
                        $("span.errorMsg").text("");
                    }
                })
            })
		    
		    
			$("#sub_btn").click(function () {
				// 1. 验证用户名
				var username = $("#username").val();
				var usernamePatt = /^\w{5,12}$/;
				if(!usernamePatt.test(username)) {
					$("span.errorMsg").text("用户名不合法");
					return false;
				}

				// 2. 密码
				var password = $("#password").val();
				var passwordPatt=/^\w{5,12}$/;
				if(!passwordPatt.test(password)) {
					$("span.errorMsg").text("密码不合法");
					return false;
				}

				// 3. 验证密码
				var repwd = $("#repwd").val();
				if(repwd != password) {
					$("span.errorMsg").text("两次输入的密码不一致");
					return false;
				}

				// 4. 检测邮箱是否符合格式
				var email = $("#email").val();
				var emailPatt=/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
				if(!emailPatt.test(email)) {
					$("span.errorMsg").text("输入邮箱不合法");
					return false;
				}

				var code = $("#code").val();
				$.trim(code);
				if(code == null || code == "") {
					$("span.errorMsg").text("验证码不能为空")
					return false;
				}


			});

			// 给验证码图片捆绑单击事件
			$("#code_img").click(function () {
			    // this是响应当前事件的dom对象
                this.src = "${basePath}kaptcha.jpg?d=" + new Date();
            })
		});
    </script>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册尚硅谷会员</h1>
                    <span class="errorMsg">
<%--                        <%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%>--%>
                        ${requestScope.msg}
                    </span>
                </div>
                <div class="form">
                    <form action="userServlet" method="post">
                        <input type="hidden" name="action" value="register"/>
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1"
                               name="username" id="username"
                               value="${requestScope.username}"/>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
                               name="password" id="password"/>
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1"
                               name="repwd" id="repwd"/>
                        <br/>
                        <br/>
                        <label>电子邮件：</label>
                        <input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1"
                               name="email" id="email"
                               value="${requestScope.email}"/>
                        <br/>
                        <br/>
                        <label>验证码：</label>
                        <input class="itxt" type="text" style="width: 100px;" name="code" id="code" value="abcde"/>
                        <img alt="" src="kaptcha.jpg" id="code_img" style="float: right; margin-right: 40px; width:140px; height: 40px">
                        <br/>
                        <br/>
                        <input type="submit" value="注册" id="sub_btn"/>

                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%--静态包含页脚--%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>