<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="java.sql.Date" %>
<div id="templatemo_main">
    <div id="content"> 
    
       <div id="home" class="section">
           <div class="text_box box1">
           		<div class="box_content">
                	<span class="about"></span>
                    <a href="view_summary" class="menu_title">财务信息</a>
                    <div class="h10"></div>
                    	查询您的财务汇总信息.
                    <a href="view_summary" class="more_btn">账务</a>
				</div>
           </div>
           <div class="photo_box">
           		<a href="loan-form" title="录入一笔"><img src="images/welcome_1.png" alt="input_record" /></a>
            </div>
         	<div class="text_box box2">
            	<div class="box_content">
                    <span class="services"></span>
                    <a href="platform-form?page=1" class="menu_title">平台管理</a>
                    <div class="h10"></div>
                    	配置您的平台信息. 
                    <a href="platform-form?page=1" class="more_btn">配置</a>
				</div>
           </div>
           <div class="photo_box">
            	<a href="loan_list?page=1" title="借出明细" ><img src="images/welcome_2.png" alt="loan_record" /></a>
           </div>
			<div class="text_box box3 no_margin_right">
            	<div class="box_content">
                	<span class="gallery"></span>
                    <a href="loan_detail_list?page=1" class="menu_title">待收明细</a>
                    <div class="h10"></div>
                     	显示目前待收明细记录.
					<a href="loan_detail_list?page=1" class="more_btn">待收</a>
				</div>
           </div>
        </div>
	<div id="home" class="section_due_today">
    <table id="mytable">
    <tr>
    	<td colspan=8>
    	今日待收：
    	</td>
    <tr>
	    <th width=100 bgcolor="#87CEFA">
    	序号 
    	</th>
	    <th width=100 bgcolor="#87CEFA">
    	平台 
    	</th>
	    <th width=100 bgcolor="#87CEFA">
    	总额 
    	</th>
	    <th width=100 bgcolor="#87CEFA">
    	本金 
    	</th>
	    <th width=100 bgcolor="#87CEFA">
    	收益 
    	</th>
	    <th width=100 bgcolor="#87CEFA">
    	期数 
    	</th>
	    <th width=100 bgcolor="#87CEFA">
    	收款日
    	</th>
	    <th width=100 bgcolor="#87CEFA">
    	操作
    	</th>
	</tr>    	
	<s:iterator value="pageBean.list"  status="status">
    	<tr>
        	<td bgcolor="#FFF8DC" hidden="true"><s:property value="record_id"/></td>
        	<td bgcolor="#FFF8DC" ><s:text name="format.number"><s:param value="10 * pageBean.currentPage - 10 + #status.count"/></s:text></td>
        	<td bgcolor="#FFF8DC"><s:property value="platform.platform_name"/></td>
        	<td bgcolor="#FFF8DC">
				<s:text name="format.money"><s:param value="own_amt+interest_amt-fee_amt"/></s:text></td>
        	<td bgcolor="#FFF8DC">
				<s:text name="format.money"><s:param value="own_amt"/></s:text></td>
        	<td bgcolor="#FFF8DC">
				<s:text name="format.money"><s:param value="interest_amt-fee_amt"/></s:text></td>
        	<td bgcolor="#FFF8DC"><s:property value="period"/>/<s:property value="total_period"/>
        	<td bgcolor="#FFF8DC"><s:text name="format.date"><s:param value="expire_date"/></s:text></td>
        	<td bgcolor="#FFF8DC"><a href="Loan_detail_return?Loan_detail_id=<s:property value="record_id"/>">回款  <a href="Loan_detail_bad?Loan_detail_id=<s:property value="record_id"/>">坏账</a></td>
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
            <a href="view_welcome.action?page=1">第一页</a>
            <a href="view_welcome.action?page=<s:property value="%{pageBean.currentPage-1}"/>">上一页</a>
        </s:else>
        <s:if test="%{pageBean.currentPage < pageBean.totalPage}">
            <a href="view_welcome.action?page=<s:property value="%{pageBean.currentPage+1}"/>">下一页</a>
            <a href="view_welcome.action?page=<s:property value="pageBean.totalPage"/>">最后一页</a>
        </s:if>
        <s:else>
            下一页 最后一页
        </s:else>	
	</div>       
	
    </div> 
	</div> 

</div>




</html>