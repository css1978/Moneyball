<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<title>P2P 网贷记账 财务管理</title>
	<link href="css/templatemo_style.css" type="text/css" rel="stylesheet" /> 
    <link rel="stylesheet" href="css/table.css">
    <link rel="stylesheet" href="css/slimbox2.css" type="text/css" media="screen" /> 
    <% String root = request.getContextPath(); %>  
    <script type="text/javascript">  
 		var root = "<%=root%>"; 
	</script>	
</head>
<body style="margin: 0;	padding: 0;	color: #333;font-size: 12px;line-height: 1.6em;	font-family: sans-serif;background-color: #868686;	background-image:url(images/templatemo_body.jpg);	background-position: top;background-repeat: repeat-x">
<table border="0" cellpadding="2" cellspacing="2" align="center">
    <tr>
        <td height="30" colspan="2" width="800"><tiles:insertAttribute name="menu" />
        </td>
    </tr>
    <tr>

        <td width="800"><tiles:insertAttribute name="body" /></td>
    </tr>
    <tr>
        <td height="30" colspan="2"><tiles:insertAttribute name="footer" />
        </td>
    </tr>
</table>
</body>
</html>