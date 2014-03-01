<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>


<script type="text/javascript" src="js/tab/jquery-1.3.1.min.js"></script>
<script type="text/javascript" src="js/tab/jquery.scrollTo.js"></script>

<script>


$(document).ready(function() {	

	//Get the height of the first item
	$('#mask').css({'height':$('#panel-1').height()});	
	
	//Calculate the total width - sum of all sub-panels width
	//Width is generated according to the width of #mask * total of sub-panels
	$('#panel').width(parseInt($('#mask').width() * $('#panel div').length));
	
	//Set the sub-panel width according to the #mask width (width of #mask and sub-panel must be same)
	$('#panel div').width($('#mask').width());
	
	//Get all the links with rel as panel
	$('a[rel=panel]').click(function () {
	
		//Get the height of the sub-panel
		var panelheight = $($(this).attr('href')).height();
		
		//Set class for the selected item
		$('a[rel=panel]').removeClass('selected');
		$(this).addClass('selected');
		
		//Resize the height
		$('#mask').animate({'height':panelheight},{queue:false, duration:500});			
		
		//Scroll to the correct panel, the panel id is grabbed from the href attribute of the anchor
		$('#mask').scrollTo($(this).attr('href'), 800);		
		
		//Discard the link default behavior
		return false;
	});
	
});
</script>

<style>
body {
	padding:0;
	margin:0 20px;
	background:#d2e0e5;
	font:20px arial;
}

#scroller-header a {
	text-decoration:none; 
	color:#867863; 
	padding:0 20px;
	size: 24px;
}

#scroller-header a:hover {
	text-decoration:none; 
	color:#4b412f
}

a.selected {
	text-decoration:underline !important; 
	color:#4b412f !important;
}

#scroller-header {
	width:100%;
	height:24px;
	padding:0px 0 20px 15px;
	font-weight:bold;
}

#scroller-body {
	width:100%;
	float:left;
	padding-bottom:30px;
}

#mask {
	width:800px;
	overflow:hidden;
	margin:0 auto;
}

#panel {

}

#panel div {
float:left;

}

/* Extra styling for each panel*/

#panel ul {
list-style:none;
margin:0 5px;
padding:0;
}

	#panel ul li {
		padding:5px;
		color:#557482;
		font-style:normal;size:20px
		border-bottom:1px dotted #ccc;
	}

	#panel ul li a{
		padding-left:30px;
		padding-right:70px;
		width:200px;
		color:red;
		font-style:normal;size:25px;font-weight:bold;
		border-bottom:2px dotted #ccc;
	}
	#panel ul li a1{
		padding-left:5px;
		padding-right:20px;
		color:red;
		font-style:normal;size:20px;font-weight:lighter;
		border-bottom:1px dotted #ccc;
	}

	#panel ul li.last {
		border-bottom:none !important;
	}

#panel-1 {
}

#panel-2 {
}

#panel-3 {
}


</style>



    
<div id="templatemo_main">
    <div id="content"> 

		<div id="scroller-header">
			<a href="#panel-1" rel="panel" class="selected">总体数据</a>
			<a href="#panel-2" rel="panel">平台数据</a>
			<a href="#panel-3" rel="panel">月度数据</a>
		</div>
		<div id="scroller-body">
			<div id="mask">
				<div id="panel">
					<div id="panel-1">
						<ul>
							<li>
								总借出： <a> <s:text name="format.money" ><s:param value="sumAmount"/></s:text></a>
								总收益： <a> <s:text name="format.money" ><s:param value="total_interest+total_award"/></s:text></a>
								总利息： <a> <s:text name="format.money" ><s:param value="total_interest"/></s:text></a>
							</li>
							<li>
								总奖励： <a> <s:text name="format.money" ><s:param value="total_award"/></s:text></a>
								<s:property value="%{getText('label.head.netamount')}"/>：
								<a> <s:text name="format.money" ><s:param value="netAmount"/></s:text></a>
								
								<s:property value="%{getText('label.head.curr_month_in')}"/>：
								 <a> <s:text name="format.money" ><s:param value="curr_month_interest+curr_month_award"/></s:text></a>
							</li>
							<li>
								<s:property value="%{getText('label.head.curr_month_interest')}"/>：
								 <a> <s:text name="format.money" ><s:param value="curr_month_interest"/></s:text></a>
								 
								<s:property value="%{getText('label.head.curr_month_award')}"/>： 
								<a> <s:text name="format.money" ><s:param value="curr_month_award"/></s:text></a>
								
								待收总额： <a> <s:text name="format.money" ><s:param value="dueAllOwn+dueAllIns"/></s:text></a>
							</li>
							<li>
								待收本金： <a> <s:text name="format.money" ><s:param value="dueAllOwn"/></s:text></a>
								待收利息： <a> <s:text name="format.money" ><s:param value="dueAllIns"/></s:text></a>
								平均年化： <a> <s:text name="format.money" ><s:param value="dueAllRate"/>%</s:text></a>
							</li>
							<li>
								最近6个月待收：
							</li>
							<li>
								<s:property value="curr_month" /><a1><s:text name="format.money"><s:param value="curr_month_amt"/></s:text></a1>
								<s:property value="next1_month" /><a1><s:text name="format.money"><s:param value="next1_month_amt"/></s:text></a1>
								<s:property value="next2_month" /><a1><s:text name="format.money"><s:param value="next2_month_amt"/></s:text></a1>
								<s:property value="next3_month" /><a1><s:text name="format.money"><s:param value="next3_month_amt"/></s:text></a1>
								<s:property value="next4_month" /><a1><s:text name="format.money"><s:param value="next4_month_amt"/></s:text></a1>
								<s:property value="next5_month" /><a1><s:text name="format.money"><s:param value="next5_month_amt"/></s:text></a1>
							</li>
						</ul>
					</div>
					<div id="panel-2">
						<ul>
						    <table id="mytable">
	    						<th width=100 bgcolor="#87CEFA">平台</th>
	    						<th width=100 bgcolor="#87CEFA">待收总额</th>
	    						<th width=100 bgcolor="#87CEFA">应收本金</th>
	    						<th width=100 bgcolor="#87CEFA">待收利息</th>
	    						<th width=100 bgcolor="#87CEFA">已赚利息</th>
	    						<th width=100 bgcolor="#87CEFA">已赚奖励</th>
	    						<th width=100 bgcolor="#87CEFA">总收益</th>
							<s:iterator value="summaryPlatform_list" status="status">
    							<tr>
        							<td bgcolor="#FFF8DC" ><s:text name="platform_name"></s:text></td>
        							<td bgcolor="#FFF8DC" ><s:text name="format.money"><s:param value="dueAllAmt"/></s:text></td>
        							<td bgcolor="#FFF8DC" ><s:text name="format.money"><s:param value="dueOwnAmt"/></s:text></td>
        							<td bgcolor="#FFF8DC" ><s:text name="format.money"><s:param value="dueIns"/></s:text></td>
        							<td bgcolor="#FFF8DC" ><s:text name="format.money"><s:param value="inIns"/></s:text></td>
        							<td bgcolor="#FFF8DC" ><s:text name="format.money"><s:param value="inAward"/></s:text></td>
        							<td bgcolor="#FFF8DC" ><s:text name="format.money"><s:param value="inIns+inAward"/></s:text></td>
    							</tr> 
							</s:iterator>
						</table>
						</ul>	
					</div>
					<div id="panel-3">
						<ul>
						    <table id="mytable">
	    						<th width=100 bgcolor="#87CEFA">月份</th>
	    						<th width=200 bgcolor="#87CEFA">投标总额</th>
	    						<th width=150 bgcolor="#87CEFA">已赚利息</th>
	    						<th width=150 bgcolor="#87CEFA">已赚奖励</th>
	    						<th width=200 bgcolor="#87CEFA">当月收益</th>
							<s:iterator value="summaryMonth_list" status="status">
    							<tr>
        							<td bgcolor="#FFF8DC" ><s:text name="monthVal"></s:text></td>
        							<td bgcolor="#FFF8DC" ><s:text name="format.money"><s:param value="ownAmt"/></s:text></td>
        							<td bgcolor="#FFF8DC" ><s:text name="format.money"><s:param value="insAmt"/></s:text></td>
        							<td bgcolor="#FFF8DC" ><s:text name="format.money"><s:param value="awardAmt"/></s:text></td>
        							<td bgcolor="#FFF8DC" ><s:text name="format.money"><s:param value="insAmt+awardAmt"/></s:text></td>
    							</tr> 
							</s:iterator>
						</table>
						</ul>	
					</div>
				</div>
			</div>
		</div>
		


	 </div> 
</div>

</html>