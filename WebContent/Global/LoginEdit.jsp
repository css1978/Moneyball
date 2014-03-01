<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<html>
<head>
	<s:head theme="xhtml"/>
	<sx:head extraLocales="en-us,nl-nl,de-de" />
    <title>信息修改</title>

</head>

<s:actionerror cssStyle="FONT-WEIGHT: bold;color:red;font-size:14px;"/>
<s:fielderror cssStyle="FONT-WEIGHT: bold;color:red;font-size:14px;"></s:fielderror>
<s:form action="loginupdate" method="post">
<div id="templatemo_main">
    <div id="content"> 
    	<div class="splitsection"></div>
       <div id="home" class="section">
       		<div hidden="true">
       		<s:textfield name="login.login_id"/>
       		</div>
           <div class="text_box_label">
    		<label >用户名：</label>
           </div>
           <div class="text_box_input">
    		<s:textfield readonly="true" name="login.login_name"/>(不允许修改)
           </div>
           <div class="text_box_label">
    		<label >姓名：</label>
           </div>
           <div class="text_box_input">
    		<s:textfield name="login.firstname"/>
           </div>

           <div class="text_box_label">
			<label >手机号： </label>
           </div>
           <div class="text_box_input">
		    <s:textfield name="login.mobile"/>*(短信通知)
           </div>
           <div class="text_box_label">
			<label >电子邮件：</label>
           </div>
           <div class="text_box_input">
           <s:textfield name="login.email"/>*(重置密码)
           </div>

           <div  hidden="true">
			登录密码：
           </div>
           <div hidden="true">
           <s:textfield type="password" name="login.logpass" />*
           </div>

           <div hidden="true" >
			<label >重复登录密码：</label>
           </div>
           <div hidden="true">
           <s:textfield type="password" name="repassword" value="%{login.logpass}"/>*
           </div>

        </div>
	</div>       
	<div class="right">
    	<s:submit value="修改" id="submit_btn"/>
    </div>
</div>

</s:form>
</html>


 
 