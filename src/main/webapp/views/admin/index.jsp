<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="common/taglibs.jsp"%>
<%@include file="common/global.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>欢迎</title>
<script type="text/javascript">
	function xgpasswrod() {
		$('#win').window('open');
	}
	function logout() {
		$.messager.confirm('警告', '你真的要退出？', function(r) {
			if (r) {
				$.ajax({
					type : "get",
					url : "${ctx}/logout",
					success : function(data) {
						$.messager.alert('提示', '退出成功', "info", function() {
							//window.open('${ctx}/');
							window.location.href = '${ctx}/';
						});
					},
					error : function() {
						$.messager.alert('提示', '退出失败', "error");
					}
				});
			}
		});
	}
	//初始化左侧
	function InitLeftMenu() {
		$('.easyui-accordion #group div a').click(function() {
			$('.easyui-accordion #group div').removeClass("selected");
			$(this).parent().addClass("selected");
		}).hover(function() {
			$(this).parent().addClass("hover");
		}, function() {
			$(this).parent().removeClass("hover");
		});

		//选中第一个
		var panels = $('#nav').accordion('panels');
		var t = panels[0].panel('options').title;
		$('#nav').accordion('select', t);
	}
	//获取左侧导航的图标
	function getIcon(menuid) {
		var icon = 'icon ';
		$.each(_menus.menus, function(i, n) {
			$.each(n.menus, function(j, o) {
				if (o.menuid == menuid) {
					icon += o.icon;
				}
			});
		});
		return icon;
	}

	function addTab(subtitle, url, icon) {
		if (!$('#index_tabs').tabs('exists', subtitle)) {
			$('#index_tabs').tabs('add', {
				title : subtitle,
				content : createFrame(url),
				closable : true,
				icon : icon
			});
		} else {
			$('#index_tabs').tabs('select', subtitle);
		}
	}

	function createFrame(url) {
		var s = '<iframe scrolling="auto" frameborder="0"  src="${ctx}'
				+ url + '" style="width:100%;height:100%;border: 0"></iframe>';
		return s;
	}
	function oksubmint() {
		var isValid = $('#formpasswed').form('validate');
		if (!isValid) {
			$.messager.alert('提示', '两次密码不一致', "error");
		}else{
			var formpasswed = $('#formpasswed').serializeArray();
			$.post('${ctx}/newpasswd',formpasswed,function(data){
				if(data.success){
					$.messager.alert('提示', data.message, "info");
				}else{
					$.messager.alert('提示', data.message, "error");
				}
			})
		}

	}
	function clockon() {
		var now = new Date();
		var year = now.getFullYear(); //getFullYear getYear
		var month = now.getMonth();
		var date = now.getDate();
		var day = now.getDay();
		var hour = now.getHours();
		var minu = now.getMinutes();
		var sec = now.getSeconds();
		var week;
		month = month + 1;
		if (month < 10)
			month = "0" + month;
		if (date < 10)
			date = "0" + date;
		if (hour < 10)
			hour = "0" + hour;
		if (minu < 10)
			minu = "0" + minu;
		if (sec < 10)
			sec = "0" + sec;
		var arr_week = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五",
				"星期六");
		week = arr_week[day];
		var time = "";
		time = year + "年" + month + "月" + date + "日" + " " + hour + ":" + minu
				+ ":" + sec + " " + week;

		$("#bgclock").html(time);

		var timer = setTimeout("clockon()", 200);
	}
	$(function() {
		clockon();
		InitLeftMenu();
		var index_tabsMenu = null;
		var index_tabs = $('#index_tabs')
				.tabs(
						{
							fit : true,
							border : false,
							onContextMenu : function(e, title) {
								e.preventDefault();
								index_tabsMenu.menu('show', {
									left : e.pageX,
									top : e.pageY
								}).data('tabTitle', title);
							},
							tools : [
									{
										iconCls : 'database_refresh',
										handler : function() {
											var href = index_tabs.tabs(
													'getSelected').panel(
													'options').href;
											if (href) {/*说明tab是以href方式引入的目标页面*/
												var index = index_tabs
														.tabs(
																'getTabIndex',
																index_tabs
																		.tabs('getSelected'));
												index_tabs
														.tabs('getTab', index)
														.panel('refresh');
											} else {/*说明tab是以content方式引入的目标页面*/
												var panel = index_tabs.tabs(
														'getSelected').panel(
														'panel');
												var frame = panel
														.find('iframe');
												try {
													if (frame.length > 0) {
														for ( var i = 0; i < frame.length; i++) {
															frame[i].contentWindow.document
																	.write('');
															frame[i].contentWindow
																	.close();
															frame[i].src = frame[i].src;
														}
														if (navigator.userAgent
																.indexOf("MSIE") > 0) {// IE特有回收内存方法
															try {
																CollectGarbage();
															} catch (e) {
															}
														}
													}
												} catch (e) {
												}
											}
										}
									},
									{
										iconCls : 'delete',
										handler : function() {
											var index = index_tabs
													.tabs(
															'getTabIndex',
															index_tabs
																	.tabs('getSelected'));
											var tab = index_tabs.tabs('getTab',
													index);
											if (tab.panel('options').closable) {
												index_tabs.tabs('close', index);
											} else {
												$.messager
														.alert(
																'提示',
																'['
																		+ tab
																				.panel('options').title
																		+ ']不可以被关闭！',
																'error');
											}
										}
									} ]
						});

		index_tabsMenu = $('#index_tabsMenu').menu(
				{
					onClick : function(item) {
						var curTabTitle = $(this).data('tabTitle');
						var type = $(item.target).attr('title');

						if (type === 'refresh') {
							index_tabs.tabs('getTab', curTabTitle).panel(
									'refresh');
							return;
						}

						if (type === 'close') {
							var t = index_tabs.tabs('getTab', curTabTitle);
							if (t.panel('options').closable) {
								index_tabs.tabs('close', curTabTitle);
							}
							return;
						}

						var allTabs = index_tabs.tabs('tabs');
						var closeTabsTitle = [];

						$.each(allTabs, function() {
							var opt = $(this).panel('options');
							if (opt.closable && opt.title != curTabTitle
									&& type === 'closeOther') {
								closeTabsTitle.push(opt.title);
							} else if (opt.closable && type === 'closeAll') {
								closeTabsTitle.push(opt.title);
							}
						});

						for ( var i = 0; i < closeTabsTitle.length; i++) {
							index_tabs.tabs('close', closeTabsTitle[i]);
						}
					}
				});
	});
</script>
</head>
<body class="easyui-layout">
	<div border="false" data-options="region:'north',border:false"
		style="overflow: hidden; height: 30px; background: url(${ctx}/resources/images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;line-height: 20px; color: #ffffff; font-family: Verdana, 微软雅黑,黑体">
		<span style="float: right; padding-right: 20px; padding-top: 2px"
			class="head">角色：${role.name}</span> <span
			style="float: right; padding-right: 20px; padding-top: 2px"
			class="head"></span> <span id="bgclock"
			style="float: right; padding-right: 10px; padding-top: 2px"
			class="head"></span> <span
			style="float: right; padding-right: 20px; padding-top: 2px"
			class="head">${user.username}&nbsp;&nbsp;</span> <span
			style="padding-left: 10px; font-size: 16px;">公司后台管理系统</span>
	</div>

	<div data-options="region:'west',split:true,title:'菜单'"
		style="width: 180px; padding1: 0px; overflow: hidden;">
		<div id="nav" class="easyui-accordion" fit="true" border="false">
			<div title="公司简介管理" data-options="iconCls:'icon1309'">
				<div class="easyui-panel" fit="true" border="false">
					<ul class="easyui-tree">
						<li data-options="iconCls:'icon1009'"><a
								href="javascript:addTab('用户管理','/usermanagement','icon1009')"> <span
									class='nav'>用户管理</span>
							</a></li>
							<li data-options="iconCls:'icon1009'"><a
								href="javascript:addTab('部门管理','/deparmanagement','icon1009')"> <span
									class='nav'>权限管理</span>
						</a></li>
					</ul>
				</div>
			</div>
			<div title="账号管理" data-options="iconCls:'icon0504'">
				<div class="easyui-panel" fit="true" border="false">
					<ul class="easyui-tree">
						<li data-options="iconCls:'icon0502'"><a
							href="javascript:xgpasswrod()"><span class='nav'> 修改密码</span></a></li>
						<li data-options="iconCls:'icon0602'"><a
							href="javascript:logout()"><span class='nav'>退出</span></a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="height: 10px; background: #A9FACD; padding: 10px;"></div>
	<div data-options="region:'center'" border="true" title='公司'
		style="overflow: hidden;">
		<div id="index_tabs" class="easyui-tabs"
			data-options="fit:true,border:true" style="overflow: hidden;">
			<div title="欢迎界面" data-options="iconCls:'icon-sys'"
				style="padding: 0px; overflow: hidden;">
				<iframe src="${ctx}/welcome" frameborder="0"
					style="border: 0; width: 100%; height: 100%;"></iframe>
			</div>
		</div>
	</div>

	<div id="index_tabsMenu" style="width: 120px; display: none;">
		<div title="refresh" data-options="iconCls:'transmit'">刷新</div>
		<div class="menu-sep"></div>
		<div title="close" data-options="iconCls:'delete'">关闭</div>
		<div title="closeOther" data-options="iconCls:'delete'">关闭其他</div>
		<div title="closeAll" data-options="iconCls:'delete'">关闭所有</div>
	</div>
	<div id="win" class="easyui-window" title="修改密码" closed="true"
		modal="true" style="width: 400px; height: 300px; padding: 5px;">
		<form style="padding: 10px 20px 10px 40px;" id="formpasswed">
			<p>
				原 来 密 码:<input type="password" id="oldpasswd" name="oldpasswd" />
			</p>
			<p>
				新 密 码:<input type="password" id="newpasswd" name="newpasswd"
					data-options="required:true" />
			</p>
			<p>
				再 次 输 入:<input type="password" id="newpasswd2"
					data-options="required:true,validType:'equals[\'newpasswd\']',missingMessage:'请再次输入密码',invalidMessage:'两次输入密码不对'" />
			</p>
			<div style="padding: 5px; text-align: center;">
				<a href="#" class="easyui-linkbutton" icon="icon-ok"
					onClick="oksubmint()">保存</a> <a href="#" class="easyui-linkbutton"
					icon="icon-cancel" id="cancel"
					onClick="javascript: $('#formpasswed').form('clear');   ">重置</a>
			</div>
		</form>
	</div>
</body>
</html>
