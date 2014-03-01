<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<html>
<head>
	<s:head theme="xhtml"/>
	<sx:head extraLocales="en-us,nl-nl,de-de" />
    <title><s:property value="%{getText('label.title.assetaccount')}"/> </title>
</head>
<body>
 
<h4><s:property value="%{getText('label.head.assetaccount')}"/> </h4>
<s:actionerror/>
 
<s:form action="AddAssetaccount" method="post">
    <s:textfield name="assetaccount.account_name" label="%{getText('label.assetaccount.account_name')}"/>

	<s:set name="lst_acct_type" value="# {'0':'储蓄卡','1':'信用卡','2':'投资账户','3':'电子账户'}"></s:set>    
	<s:property value="%{getText('label.assetaccount.account_type')}"/> <s:select theme="simple" list="#lst_acct_type" listKey="key" listValue="value" name="assetaccount.account_type" headerKey="0"></s:select>
    
    <s:textfield name="assetaccount.amount" label="%{getText('label.assetaccount.amount')}" value="0"/>
    <s:textfield name="assetaccount.interest_rate" label="%{getText('label.assetaccount.interest_rate')}" value="0"/>
    <s:property value="%{getText('label.assetaccount.create_date')}"/>
    <sx:datetimepicker name="assetaccount.create_date" />
    <s:submit value="%{getText('label.button.add_assetaccount')}" align="center"/>
</s:form>
 
 
<h5><s:property value="%{getText('label.head.assetaccount')}"/></h5>
<table>
<tr>
    <th><s:property value="%{getText('label.assetaccount.account_id')}"/></th>
    <th><s:property value="%{getText('label.assetaccount.account_name')}"/></th>
    <th><s:property value="%{getText('label.assetaccount.account_type')}"/></th>
    <th><s:property value="%{getText('label.assetaccount.amount')}"/></th>
    <th><s:property value="%{getText('label.assetaccount.interest_rate')}"/></th>
    <th><s:property value="%{getText('label.assetaccount.create_date')}"/></th>
</tr>
<s:iterator value="assetaccountList" var="assetaccount">
    <tr>
        <td><s:property value="account_id"/></td>
        <td><s:property value="account_name"/></td>
        <td><s:property value="account_type"/></td>
        <td><s:property value="amount"/></td>
        <td><s:property value="interest_rate"/></td>
        <td><s:property value="create_date"/></td>
        <td><a href="<s:property value=""/>">link</a></td>
        <td><a href="Assetaccount_delete?id=<s:property value="account_id"/>">delete</a></td>
    </tr> 
</s:iterator>
</table>
</body>
</html>