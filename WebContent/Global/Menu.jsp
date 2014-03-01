<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<% if (session.getAttribute("username")==null || session.getAttribute("username").toString().isEmpty() ) { %> 
	 请<a href="Login.jsp" >登录</a>
<% } else { %>
	<%String username = session.getAttribute("username").toString(); %>
	<%=username %>
	<s:a href="loginupdate_req">修改个人信息</s:a>
	<s:a href="modifypass_req">修改密码</s:a>
	<s:a href="logout">退出</s:a>
<% } %>	

<div id="templatemo_header">
    <div id="site_title"><a style="display: block; width: 780px; background: #717171; padding-left: 20px" href="" rel="nofollow">Money Ball</a></div>
</div>

	<style type='text/css'>
		body{margin:0px;padding:0px;font-size:12px;}
		ul,li,ol{margin:0px;padding:0px;list-style:none;}
		a{text-decoration: none;}
		img{border:0px;}
		
		.P9CHead{width:100%;height:30px;border-bottom:0px solid #315880;}
		.P9CHContent,.P9CSContent{width:800px;height:100%;margin:0 auto;}
		.P9CCLogo{width:300px;height:100%;overflow:hidden;float: left;}
		.P9CCLogo a{float: left;position: relative;top:0px;}
		.P9CCLogo a:hover{opacity:0.8;}
		
		.P9CCMenu{float: right;height:100%;}
		.P9CCMNav,.P9CCMBg{width:120px;height:100%;float: left;margin-left:1px;text-align: center;position:relative;}
		.P9CCMBg{background:#6495ED;left:120px;}
		.P9CCMNav a,.P9CCMNav em{position:absolute;display:block;width:100%;float: left;left:0px;;top:0px;color:#000000;}
		.P9CCMNav a{font-family:微软雅黑;display:none;font-size:15px;}
		.P9CCMNav em{font-style: normal;font-size:12px;font-family:Arial;}

		.P9CSubNav{background:#ffffff;width:100%;height:20px;line-height:20px;border-bottom:1px solid #c67e00;overflow:hidden;}
		.P9CSContent{position: relative;height:100%;overflow: hidden;}
		.P9CCNav{width:100%;height:50%;text-align:right;position:absolute;top:0px;}
		.P9CCNav a{color:#8B0000;margin-left:30px;font-size:14px;}

	</style>
	<div class='P9Container' >
		<div class='P9CHead'>
			<div class='P9CHContent'>
				<ul class='P9CCMenu'>
					<li class='P9CCMBg'></li>

					<li class='P9CCMNav'>
						<a href='view_welcome?page=1' >首页</a>
						<em>HOME</em>
					</li>
					<li class='P9CCMNav'>
						<a href='#'>网贷记账</a>
						<em>网贷记账</em>
					</li>
					<li class='P9CCMNav'>
						<a href='#' title='' >交流互动</a>
						<em>交流互动</em>
					</li>
				</ul>
			</div>
		</div>
		<div class='P9CSubNav'>
			<ul class='P9CSContent'>
				<li class='P9CCNav'>
				</li>
				<li class='P9CCNav'>
					<a href='view_summary'>显示汇总</a>
					<a href='loan-form' >录入一笔</a>
					<a href='platform-form'>平台管理</a>
					<a href='loan_list?page=1'>借出明细</a>
					<a href='loan_detail_list?page=1'>待收明细</a>
				</li>
				<li class='P9CCNav'>
					<a href='WeixinFocus'>微信平台</a>
				</li>
			</ul>
		</div>
	</div>

	<script type="text/javascript" src="js/jquery.js" ></script>
	<script type="text/javascript">

		jQuery.fn.extend({
			p9:function(){
				var a=$('.P9CCNav'),
					b=$('.P9CCMNav'),
					c=$('.P9CCMBg'),
					p=parseInt(b.find('a').css('top'),10);
					n=0,t=0,v=0,m=500;
				//挂接按钮事件
				b.mouseenter(function(){
					t=$(this).index()-1;
					//初始化
					b.find('a').stop().hide().css({opacity:1,top:p});
					b.find('em').stop().show().css({opacity:1,top:p});
					//移动背景
					c.stop(true).animate({left: (t+1)*b.width() },m);
					//移动内容
					$(this).find('em').css({top:p,opacity:1}).stop(true).animate({top:p-20,opacity:0},m);
					$(this).find('a').show().css({top:p+10,opacity:0}).stop(true).animate({top:p,opacity:1},m);
					//子栏目
					a.css({top:0,opacity:0});
					t>n?p9a(-30):p9a(30);
					n=t;
				}).eq(0).mouseenter();
				//子栏目动画
				function p9a(c){
					a.eq(n).css({top:0,opacity:1}).stop(true).animate({top:c,opacity:0},m);
					a.eq(t).css({top:-(c),opacity:0}).stop(true).animate({top:0,opacity:1},m);
					for (var i=0;i<3;i++){
						if (i!=n && i!=t){
							a.eq(i).css({top:50,opacity:0});
						}
						
					}
					
				}
			}
		});

		$(window).p9();
		

	</script> 
