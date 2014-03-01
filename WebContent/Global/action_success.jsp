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
<div id="templatemo_main">
    <div id="content"> 
    	<div class="splitsection"></div>
       <div id="home" class="section">

	<s:actionmessage cssStyle="FONT-WEIGHT: bold;color:blue;font-size:14px;"/>
	<s:actionerror cssStyle="FONT-WEIGHT: bold;color:red;font-size:14px;"/>
		</div>
	</div>
</div>
	
</html>