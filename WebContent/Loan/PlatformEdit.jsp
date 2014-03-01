<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<html>
<head>
	<s:head theme="xhtml"/>
	<sx:head extraLocales="en-us,nl-nl,de-de" />
</head>
<body>

<s:fielderror /> 
<s:actionerror/>
<div id="templatemo_main">
    <div id="content"> 	
    <div id="home" class="section">
	<div class="splitsection"></div>
		<s:form action="Platform_update" method="post">
			<s:textfield name="platform.platform_id" hidden="true"/>
			<s:textfield name="platform.login_name" hidden="true"/>

           <div class="text_box_label">
    		<label >名称(必填)：</label>
           </div>
           <div class="text_box_input">
    		<s:textfield name="platform.platform_name" />
           </div>
           <div class="text_box_label">
    		<label >网址：</label>
           </div>
           <div class="text_box_input">
    		<s:textfield name="platform.platform_url" />  
           </div>

           <div class="text_box_label">
    		<label >管理费率：</label>
           </div>
           <div class="text_box_input">
    		<s:textfield name="platform.platform_rate"/>%
           </div>
           <div class="text_box_label">
    		<label >代理人费率：</label>
           </div>
           <div class="text_box_input">
    		<s:textfield name="platform.delegate_rate" />%  
           </div>

           <div class="text_box_label">
    		<label><s:property value="%{getText('label.platform.online_time')}"/></label>
           </div>
           <div class="text_box_input">
    		<sx:datetimepicker  cssStyle="width:60%;" name="platform.online_time" toggleType="explode" displayFormat="yyyy年MM月dd日"  language="zh_CN"/>  
           </div>

           <div class="text_box_label">
    		<label >状态：</label>
           </div>
           <div class="text_box_input">
           		<s:radio  cssStyle="width:15%;" list="#{'1':'正常','0':'隐藏'}" name="platform.active" />
           </div>

           <div class="text_box_label">
           </div>
           <div class="text_box_input">
           </div>
           <div class="text_box_label">
           </div>
           <div class="text_box_input right">
    			<s:submit value="修改"  id="submit_btn"/>
           </div>
		</s:form>
	</div>
 </div>
 </div>
 
</body>
</html>