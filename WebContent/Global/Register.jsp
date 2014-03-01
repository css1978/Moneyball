<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<html>
<head>
	<s:head theme="xhtml"/>
	<sx:head extraLocales="en-us,nl-nl,de-de" />
    <title><s:property value="%{getText('label.title.loan')}"/></title>

</head>

<s:actionerror cssStyle="FONT-WEIGHT: bold;color:red;font-size:14px;"/>
<s:fielderror cssStyle="FONT-WEIGHT: bold;color:red;font-size:14px;"></s:fielderror>
<s:form action="loginadd" method="post">
<div id="templatemo_main">
    <div id="content"> 
    	<div class="splitsection"></div>
       <div id="home" class="section">
           <div class="text_box_label">
    		<label for="text_box_label">用户名：</label>
           </div>
           <div class="text_box_input">
    		<s:textfield name="login.login_name"/>*
           </div>
           <div class="text_box_label">
    		<label for="text_box_label">姓名：</label>
           </div>
           <div class="text_box_input">
    		<s:textfield name="login.firstname"/>
           </div>

           <div class="text_box_label">
			<label for="text_box_label">手机号： </label>
           </div>
           <div class="text_box_input">
		    <s:textfield name="login.mobile"/><label for="text_box_label">*(短信通知)</label>
           </div>
           <div class="text_box_label">
			<label for="text_box_label">电子邮件：</label>
           </div>
           <div class="text_box_input">
           <s:textfield name="login.email"/><label for="text_box_label">*(找回密码)</label>
           </div>

           <div class="text_box_label">
			<label for="text_box_label">登录密码：</label>
           </div>
           <div class="text_box_input">
           <s:textfield type="password" name="login.logpass"/><label for="text_box_label">*(最短6位)</label>
           </div>

           <div class="text_box_label">
			<label for="text_box_label">重复登录密码：</label>
           </div>
           <div class="text_box_input">
           <s:textfield type="password" name="repassword"/><label for="text_box_label">*(最短6位)</label>
           </div>

        </div>
	</div>       
	<div class="right">
    	<s:submit value="注册" id="submit_btn"/>
    </div>
</div>

</s:form>
</html>


 
 