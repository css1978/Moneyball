<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6 lt8"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7 lt8"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8 lt8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="UTF-8" />
        <!-- <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">  -->
        <title>P2P 网贷记账 财务管理</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <meta name="description" content="P2P 网贷管理 财务记账 集中管理你的财富" />
        <meta name="keywords" content="P2P 网贷管理 财务记账 集中管理你的财富" />
        <meta name="author" content="Moneyball" />
        <link rel="shortcut icon" href="../favicon.ico"> 
        <link rel="stylesheet" type="text/css" href="css/login/demo.css" />
        <link rel="stylesheet" type="text/css" href="css/login/style.css" />
		<link rel="stylesheet" type="text/css" href="css/login/animate-custom.css" />
    </head>
    <body>
        <div class="container">
            <!-- Codrops top bar -->
            <div class="codrops-top">
                <a href="">
                    <strong> </strong>
                </a>
                <span class="right">
                    <a href="WeixinFocus">
                        <strong>微信平台</strong>
                    </a>
                </span>
                <div class="clr"></div>
            </div><!--/ Codrops top bar -->
            <header>
                <h1>P2P 网贷管理 财务记账 集中管理你的财富</span></h1>
				<nav class="codrops-demos">
					<span>点击 <strong>“<a href="register_req.action" class="to_register">注册/Register</a>”</strong> 开启您的财务自由之路</span>
				</nav>
            </header>
            <section>				
                <div id="container_demo" >
                    <!-- hidden anchor to stop jump http://www.css3create.com/Astuce-Empecher-le-scroll-avec-l-utilisation-de-target#wrap4  -->
                    <a class="hiddenanchor" id="toregister"></a>
                    <a class="hiddenanchor" id="tologin"></a>
                    <div id="wrapper">
                        <div id="login" class="animate form">
                            <form  action="login" method="post" autocomplete="on"> 
                                <h1>Log in</h1> 
                                <p> 
                                    <label for="username" class="uname" data-icon="u" > 您的登录名称 </label>
                                    <input id="username" name="loginName" required="required" type="text" placeholder="您的登录名称"/>
                                </p>
                                <p> 
                                    <label for="password" class="youpasswd" data-icon="p"> 您的密码 </label>
                                    <input id="password" name="password" required="required" type="password" placeholder="您的密码" />
                                </p>
                                <p class="keeplogin">
                                	<label for="loginkeeping"><a href="#toregister">忘记密码</a></label>
                                </p> 
                                <p class="keeplogin"> 
									<input type="checkbox" name="loginkeeping" id="loginkeeping" value="loginkeeping" /> 
									<label for="loginkeeping">记住我</label>
								</p>
								
								<div class="errormsg">
									<label style="font-size: 8px;color: red;" for="errormsg"><s:actionerror theme="simple"/></label>
									<label style="font-size: 8px;color: blue;" for="errormsg"><s:actionmessage theme="simple"/></label>
								</div>
                                <p class="login button"> 
                                    <input type="submit" value="登录/Log in" /> 
								</p>
                                <p class="change_link">
									还没加入吗?
									<a href="register_req" class="to_register">注册/Register</a>
								</p>
                            </form>
                        </div>

                        <div id="register" class="animate form">
                            <form  action="forgotpass" method="post" autocomplete="on"> 
                                <h1> iForgot </h1> 
                                <p> 
                                    <label for="username" class="uname" data-icon="u" >您的登录名称 </label>
                                    <input id="username" name="login.login_name" required="required" type="text" placeholder="重置密码用"/>
                                </p>
                                <p>
                                    <label for="emailsignup" class="youmail" data-icon="e" >您注册的电子邮件</label>
                                    <input id="emailsignup" name="login.email" required="required" type="email" placeholder="接收重置密码链接"/> 
                                    <label style="font: italic;font-size: 8px" >如果您无法收到email请通过微信联系站长帮助解决</label>	
                                </p>
								<div class="errormsg">
									<label style="font-size: 8px;color: red;" for="errormsg"><s:actionerror theme="simple"/></label>
									<label style="font-size: 8px;color: blue;" for="errormsg"><s:actionmessage theme="simple"/></label>
								</div>                                
                                <p class="signin button"> 
									<input type="submit" value="提交/Submit"/> 
								</p>
                                <p class="change_link">  
									已经找回密码 ?
									<a href="#tologin" class="to_register"> 去登录</a>
								</p>
                            </form>
                        </div>
			
                    </div>

                    </div>
                </div>  
            </section>
        </div>
    </body>
</html>    