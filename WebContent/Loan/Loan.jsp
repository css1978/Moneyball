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


<script type="text/javascript" src="js/jquery.js" ></script>
<script type="text/javascript" src="js/LoanAction.js">
</script>

<s:actionerror cssStyle="FONT-WEIGHT: bold;color:red;font-size:14px;"/>
<s:fielderror cssStyle="FONT-WEIGHT: bold;color:red;font-size:14px;"></s:fielderror>
<s:form name="SampleForm" action="AddLoan" method="post">
<div id="templatemo_main">
    <div id="double_content"> 
    	<div class="splitsection"></div>
       <div id="home" class="double_section">
           <div class="text_box_label">
    			<label for="text_box_label"><s:property value="%{getText('label.loan.loan_date')}"/></label>
           </div>
           <div class="text_box_input">
    		<sx:datetimepicker cssStyle="width:60%;" name="loan.loan_date" toggleType="explode" displayFormat="yyyy年MM月dd日" value="%{'today'}" language="zh_CN"/>
           </div>
           <div class="text_box_label">
    		<label for="text_box_label"><s:property value="%{getText('label.loan.platform_id')}"/></label>
           </div>
           <div class="text_box_input">
    		<s:select id="platform_id" name="platform_id" list="#request.platformList" listKey="platform_id" listValue="platform_name" value="platform_rate"/>  
           </div>

           <div class="text_box_label">
			<label for="text_box_label"><s:property value="%{getText('label.loan.amount')}"/> </label>
           </div>
           <div class="text_box_input">
		    <s:textfield name="loan.amount" value="0"/>
           </div>
           <div class="text_box_label">
			<label for="text_box_label"><s:property value="%{getText('label.loan.return_type')}"/></label>
           </div>
           <div class="text_box_input">
			<s:set name="lst_return_type" value="# {'1':'等额本息','2':'月还息到期还本','3':'到期还本息'}"></s:set>    
			 <s:select theme="simple" list="#lst_return_type" listKey="key" listValue="value" name="loan.return_type" headerKey="0"></s:select>
           </div>

           <div class="text_box_label">
			<label for="text_box_label"><s:property value="%{getText('label.loan.duration')}"/> </label>
           </div>
           <div class="text_box_input">
			<s:textfield name="loan.duration" theme="simple" value="0"/>
			<s:radio cssStyle="width:5%;" list="#{'0':'月','1':'天'}" theme="simple" name="loan.daily" value="0"/>
           </div>
           <div class="text_box_label">
			<label for="text_box_label"><s:property value="%{getText('label.loan.interest_rate_type')}"/> </label>
           </div>
           <div class="text_box_input">
			<s:set name="lst_rate_type" value="# {'0':'年利率','1':'日利率'}"></s:set>    
			<s:select  list="#lst_rate_type" listKey="key" listValue="value" name="loan.interest_rate_type" headerKey="0"></s:select>
           </div>

           <div class="text_box_label">
    		<label for="text_box_label"><s:property value="%{getText('label.loan.interest_rate')}"/></label>
           </div>
           <div class="text_box_input">
    		<s:textfield name="loan.interest_rate" value="0"/>%
           </div>
           <div class="text_box_label">
			<label for="text_box_label"><s:property value="%{getText('label.loan.continue_rate')}"/></label>
           </div>
           <div class="text_box_input">
			<s:textfield name="loan.continue_rate" value="0"/>%
           </div>

           <div class="text_box_label">
			<label for="text_box_label">投标奖励</label>
           </div>
           <div class="text_box_input">
			<s:textfield  name="loan.award_rate" label="投标奖励"  value="0"/>%
           </div>
           <div class="text_box_label">
			<label for="text_box_label"><s:property value="%{getText('label.loan.fee_rate')}"/></label>
           </div>
           <div class="text_box_input">
			<s:textfield id="fee_rate" name="loan.fee_rate" value="%{#request.platformList[0].platform_rate}"/>%
           </div>

           <div class="text_box_label">
			<label for="text_box_label"><s:property value="%{getText('label.loan.offline_rate')}"/></label>
           </div>
           <div class="text_box_input">
			<s:textfield name="loan.offline_rate"  value="0"/>%
           </div>

           <div class="text_box_label">
           </div>
           <div class="text_box_input">
           </div>

           <div class="text_box_label">
         		<label for="text_box_label"><s:property value="%{getText('label.loan.comments')}"/></label>
           </div>
           <div class="text_box_comments">
         		<s:textfield name="loan.comments" width="300"/>
           </div>
        </div>
	</div>       
	<div class="right">
    	<s:submit value="%{getText('label.button.add_loan')}" id="submit_btn"/>
    </div>
</div>

</s:form>
</html>