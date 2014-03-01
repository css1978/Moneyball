<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<html>
<head>
	<s:head theme="xhtml"/>
	<sx:head extraLocales="en-us,nl-nl,de-de" />
    <title>密码重置</title>

</head>

<s:actionerror cssStyle="FONT-WEIGHT: bold;color:red;font-size:14px;"/>
<s:fielderror cssStyle="FONT-WEIGHT: bold;color:red;font-size:14px;"></s:fielderror>
<s:form action="resetpass" method="post">
<div id="templatemo_main">
    <div id="content"> 
    	<div class="splitsection"></div>
       <div id="home" class="section">
       		<div hidden="true">
       			<s:textfield name="login.login_id"/>
    			用户名：<s:textfield readonly="true" name="login.login_name"/>(不允许修改)
    			姓名：	<s:textfield name="login.firstname"/>
				手机号：  <s:textfield name="login.mobile"/>(短信通知)
				电子邮件： <s:textfield name="login.email"/>
           </div>
           <div class="text_box_label">
			<label>新密码：</label>
           </div>
           <div class="text_box_input">
           <s:textfield type="password" name="login.logpass" value=""/>*(最短6位)
           </div>

           <div class="text_box_label" >
			<label>重复新密码：</label>
           </div>
           <div class="text_box_input">
           <s:textfield type="password" name="repassword" value=""/>*(最短6位)
           </div>

        </div>
	</div>       
	<div class="right">
    	<s:submit value="重置密码" id="submit_btn"/>
    </div>
</div>

</s:form>
</html>


 
 