<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<link href="${ctxr}/jqueryeasyui/themes/default/easyui.css" type="text/css" rel="stylesheet" /> 
<link href="${ctxr}/jqueryeasyui/themes/icon.css" type="text/css" rel="stylesheet" />
<link href="${ctxr}/jqueryeasyui/themes/default/menu.css" type="text/css" rel="stylesheet" />
<!--[if !IE]> -->
<script src="${ctxr}/jquery/jquery-2.1.1.min.js"></script>
<!-- <![endif]-->

<!--[if IE]>-->
<script src="${ctxr}/jquery/jquery-1.11.1.min.js"></script>
<!--<![endif]-->

<!--[if !IE]> -->

<script type="text/javascript">
	window.jQuery|| document.write("<script src='http://code.jquery.com/jquery-2.1.1.min.js'>"+ "<"+"/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>-->
<script type="text/javascript">
	window.jQuery|| document.write("<script src='http://code.jquery.com/jquery-1.11.1.min.js'>"+ "<"+"/script>");
	/* window["contextPath"] = "${pageContext.request.contextPath}";
	window["sessionId"] = "${pageContext.session.id}";
	window["sessionName"] = "jsessionId"; */
</script>
<!-- <![endif]-->
<script type="text/javascript">
<!--
function convert(rows){
	function exists(rows, master){
		for(var i=0; i<rows.length; i++){
			if (rows[i].id == master) return true;
		}
		return false;
	}
	
	var nodes = [];
	// get the top level nodes
	for(var i=0; i<rows.length; i++){
		var row = rows[i];
		if (!exists(rows, row.master)){
			nodes.push({
				id:row.id,
				text:row.title
			});
		}
	}
	
	var toDo = [];
	for(var i=0; i<nodes.length; i++){
		toDo.push(nodes[i]);
	}
	while(toDo.length){
		var node = toDo.shift();	// the parent node
		// get the children nodes
		for(var i=0; i<rows.length; i++){
			var row = rows[i];
			if (row.master == node.id){
				var child = {id:row.id,text:row.title};
				if (node.children){
					node.children.push(child);
				} else {
					node.children = [child];
				}
				toDo.push(child);
			}
		}
	}
	return nodes;
}
//-->
</script>

<script src="${ctxr}/jqueryeasyui/jquery.easyui.min.js"></script>
<script src="${ctxr}/jqueryeasyui/locale/easyui-lang-zh_CN.js"></script>
