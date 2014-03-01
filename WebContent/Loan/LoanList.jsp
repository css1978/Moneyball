<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<html>
<head>
	<s:head theme="xhtml"/>
	<sx:head extraLocales="en-us,nl-nl,de-de" />
	<title>
		<s:property value="%{getText('label.title.welcome')}"/>
		<s:property value="%{getText('label.head.loan')}"/>
	</title>
	

	
</head>
 
<body>
<div id="templatemo_main">
<s:form action="loan_list" method="post">
	
	<div id="content"> 
		<div id="home" class="section_query_condition">
		
           <div class="text_box_label_query">
			<label >投标时间从 </label>
           </div>
           <div class="text_box_input_query">
		    <sx:datetimepicker  cssStyle="width:60%;" name="loanStartDate" toggleType="explode" displayFormat="yyyy年MM月dd日" language="zh_CN"/>
           </div>
            <div class="text_box_label_query">
			<label >到 </label>
           </div>
           <div class="text_box_input_query">
		    <sx:datetimepicker  cssStyle="width:60%;" name="loanEndDate" toggleType="explode" displayFormat="yyyy年MM月dd日" language="zh_CN"/>
           </div>
           <div class="text_box_label_query">
			<label >平台</label>
           </div >
           <div class="text_box_input_query">
				<s:select cssStyle="width:80%;" id="platform_id"  name="platform_id" list="#request.platformList" listKey="platform_id" listValue="platform_name" headerKey="" headerValue="请选择平台"/>
			</div>
           <div class="text_box_label_query">
			<label >状态</label>
           </div>
           <div class="text_box_input_query">
			<s:select  cssStyle="width:70%;" id="status"  name="status" list="#{'0':'未还完','1':'已还完','2':'已坏账'}" headerKey="" headerValue="请选择"/>
			</div>


           <div class="right">
    			<s:submit value="查询"  />
    		</div>
		</div>
	</div>
    <table id="mytable">
    <tr>
    	<td colspan=2 >
    	借出明细：
    	</td>
    </tr>
	    <th width=100 bgcolor="#87CEFA">
    	序号 
    	</th>
	    <th width=100 bgcolor="#87CEFA">
    	操作 
    	</th>
	    <th width=100 bgcolor="#87CEFA">
    	平台 
    	</th>
	    <th width=100 bgcolor="#87CEFA">
    	金额 
    	</th>
	    <th width=100 bgcolor="#87CEFA">
    	收益 
    	</th>
	    <th width=100 bgcolor="#87CEFA">
    	期限 
    	</th>
	    <th width=100 bgcolor="#87CEFA">
    	投标日期
    	</th>
	    <th width=100 bgcolor="#87CEFA">
    	利率
    	</th>
	    <th width=100 bgcolor="#87CEFA">
    	还款方式
    	</th>
	</tr>    	
	<s:iterator value="pageBean.list" status="status">
    	<tr>
        	<td bgcolor="#FFF8DC" hidden="true"><s:property value="loansid"/></td>
        	<td bgcolor="#FFF8DC" ><s:text name="format.number"><s:param value="20 * pageBean.currentPage - 20 + #status.count"/></s:text></td>
        	
        	<td bgcolor="#FFF8DC"><a href="Loan_modify?loansid=<s:property value="loansid"/>" >改  <a href="Loan_delete?loansid=<s:property value="loansid"/>" onclick="return confirm('你确认要删除吗?将同时删除该记录下的待还款记录');">删</a></td>
        	<td bgcolor="#FFF8DC"><s:property value="platform.platform_name"/></td>
        	<td bgcolor="#FFF8DC">
				<s:text name="format.money"><s:param value="amount"/></s:text></td>
        	<td bgcolor="#FFF8DC" >
				<s:text name="format.money"><s:param value="interest_amt"/></s:text></td>
        	<td bgcolor="#FFF8DC"><s:property value="duration"/>
        	<s:if test="daily==1">天
        	</s:if>
        	<s:if test="daily==0">个月
        	</s:if>
        	</td>
        	<td bgcolor="#FFF8DC"><s:text name="format.date"><s:param value="loan_date"/></s:text></td>
        	<td bgcolor="#FFF8DC"><s:property value="interest_rate"/></td>
        	<td bgcolor="#FFF8DC">
        	<s:if test="return_type==0"> 等额本金
        	</s:if>
        	<s:if test="return_type==1"> 等额本息
        	</s:if>
        	<s:if test="return_type==2"> 月还息到期还本
        	</s:if>
        	<s:if test="return_type==3"> 到期还本息
        	</s:if>
        	<s:if test="return_type==4"> 按季还本息
        	</s:if>
        		
        	</td>
    	</tr> 
	</s:iterator>
	
	</table>
 	<div align="right">
        共<s:property value="pageBean.allRow"/> 条记录
        共<s:property value="pageBean.totalPage"/> 页
        当前第<s:property value="pageBean.currentPage"/>页<br/>
        
        <s:if test="%{pageBean.currentPage == 1}">
            第一页 上一页
        </s:if>
        <s:else>
            <a href="loan_list.action?page=1">第一页</a>
            <a href="loan_list.action?page=<s:property value="%{pageBean.currentPage-1}"/>">上一页</a>
        </s:else>
        <s:if test="%{pageBean.currentPage  < pageBean.totalPage}">
            <a href="loan_list.action?page=<s:property value="%{pageBean.currentPage+1}"/>">下一页</a>
            <a href="loan_list.action?page=<s:property value="pageBean.totalPage"/>">最后一页</a>
        </s:if>
        <s:else>
            下一页 最后一页
        </s:else>	
	</div>        


</s:form>	
</div>
</body>
</html>