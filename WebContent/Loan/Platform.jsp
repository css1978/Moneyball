<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<html>
<head>
	<s:head theme="xhtml"/>
	<sx:head extraLocales="en-us,nl-nl,de-de" />
</head>
<body>

<s:fielderror/>
<s:actionerror/>
<div id="templatemo_main">
    <div id="content"> 
       <div id="home" class="section">
	    <table id="mytable">
	    <th width=30 bgcolor="#87CEFA">
    		<s:property value="%{getText('label.platform.id')}"/> 
    	</th>
	    <th width=100 bgcolor="#87CEFA">
    		<s:property value="%{getText('label.platform.name')}"/> 
    	</th>
	    <th width=150 bgcolor="#87CEFA">
	    	<s:property value="%{getText('label.platform.url')}"/> 
    	</th>
	    <th width=100 bgcolor="#87CEFA">
    		<s:property value="%{getText('label.platform.rate')}"/> 
    	</th>
	    <th width=100 bgcolor="#87CEFA">
    		<s:property value="%{getText('label.platform.active')}"/> 
    	</th>
	    <th width=100 bgcolor="#87CEFA">
    		<s:property value="%{getText('label.platform.online_time')}"/>
    	</th>
	    <th width=100 bgcolor="#87CEFA">
    		<s:property value="%{getText('label.platform.delegate_rate')}"/>
    	</th>
	    <th width=100 bgcolor="#87CEFA">
    		操作
    	</th>
	</tr>    	
	<s:iterator value="pageBean.list" var="platform" status="status">
    	<tr>
        	<td bgcolor="#FFF8DC" hidden="true"><s:property value="platform_id"/></td>
        	<td bgcolor="#FFF8DC" ><s:text name="format.number"><s:param value="pagesize * pageBean.currentPage - pagesize + #status.count"/></s:text></td>
        	<td bgcolor="#FFF8DC"><s:property value="platform_name"/></td>
        	<td bgcolor="#FFF8DC"><s:property value="platform_url"/></td>
        	<td bgcolor="#FFF8DC"><s:property value="platform_rate"/></td>
        	<td bgcolor="#FFF8DC">
        	<s:if test="active==1">正常
        	</s:if>
        	<s:if test="active==0">隐藏
        	</s:if>
        	</td>
        	<td bgcolor="#FFF8DC"><s:text name="format.date"><s:param value="online_time"/></s:text></td>
        	<td bgcolor="#FFF8DC"><s:property value="delegate_rate"/></td>
        	<td><a href="Platform_modify?id=<s:property value="platform_id"/>">改 </a>
        	<a href="Platform_delete?id=<s:property value="platform_id"/>"> 删</a></td>
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
            <a href="platform-form.action?page=1">第一页</a>
            <a href="platform-form.action?page=<s:property value="%{pageBean.currentPage-1}"/>">上一页</a>
        </s:else>
        <s:if test="%{pageBean.currentPage  < pageBean.totalPage}">
            <a href="platform-form.action?page=<s:property value="%{pageBean.currentPage+1}"/>">下一页</a>
            <a href="platform-form.action?page=<s:property value="pageBean.totalPage"/>">最后一页</a>
        </s:if>
        <s:else>
            下一页 最后一页
        </s:else>	
	</div>        
	</div>
    
    <div id="home" class="double_section">
		<s:form action="AddPlatform" method="post">

           <div class="text_box_label">
    		<label for="text_box_label">名称(必填)：</label>
           </div>
           <div class="text_box_input">
    		<s:textfield name="platform.platform_name" label="%{getText('label.platform.name')}"/>
           </div>
           <div class="text_box_label">
    		<label for="text_box_label">网址：</label>
           </div>
           <div class="text_box_input">
    		<s:textfield name="platform.platform_url" label="%{getText('label.platform.url')}" value="http://"/>  
           </div>

           <div class="text_box_label">
    		<label for="text_box_label">管理费率：</label>
           </div>
           <div class="text_box_input">
    		<s:textfield name="platform.platform_rate" label="%{getText('label.platform.rate')}" value="0"/>%
           </div>
           <div class="text_box_label">
    		<label for="text_box_label">代理人费率：</label>
           </div>
           <div class="text_box_input">
    		<s:textfield name="platform.delegate_rate" label="%{getText('label.platform.delegate_rate')}" value="0"/>%  
           </div>

           <div class="text_box_label">
    		<label for="text_box_label"><s:property value="%{getText('label.platform.online_time')}"/></label>
           </div>
           <div class="text_box_input">
    		<sx:datetimepicker  cssStyle="width:60%;" name="platform.online_time" toggleType="explode" displayFormat="yyyy年MM月dd日" value="%{'today'}" language="zh_CN"/>  
           </div>

           <div class="text_box_label">
    		<label for="text_box_label">状态：</label>
           </div>
           <div class="text_box_input">
           		<s:radio cssStyle="width:15%;" list="#{'1':'正常','0':'隐藏'}" name="platform.active" value="1" />
           </div>

           <div class="text_box_label">
           </div>
           <div class="text_box_input">
           </div>
           <div class="text_box_label">
           </div>
           <div class="text_box_input center">
    			<s:submit value="添加平台"  id="submit_btn"/>
           </div>
		</s:form>
	</div>
	
 
 
</body>
</html>