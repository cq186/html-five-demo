<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

<script type="text/javascript" language="javascript">
	function showHtmlcallJava() {
		var str = window.android.HtmlcallJava();
		alert(str);
	}

	function showHtmlcallJava2() {
		var str = window.android.HtmlcallJava2("IT-homer blog");
		alert(str);
	}

	function showFromHtml() {
		document.getElementById("id_input").value = "Java call Html";
	}

	function showFromHtml2(param) {
		document.getElementById("id_input2").value = "Java call Html : "
				+ param;
	}
	function jscallphone(param) {
		window.android.Callphone(param);
	}
</script>

</head>
<body bgcolor="#456789">
	你好 test html

	<a href="second.jsp">跳转到第二个jsp界面</a>
	<input type="button" value="测试本地js" onclick="testjs()">

	<form action="Servlet1" method="post">

		<input type="submit" value="单击2">

	</form>





	<br>
	<br>
	<br>

	<input type="button" value="HtmlcallJava" onclick="showHtmlcallJava()" />
	<br>
	<input type="button" value="HtmlcallJava2"
		onclick="showHtmlcallJava2()" />

	<br>

	<input type="button" value="点击打电话" onclick="jscallphone('10010')">


	<br>
	<br>
	<br>

	<input id="id_input" style="width: 90%" type="text" value="null" />
	<br>
	<input type="button" value="JavacallHtml"
		onclick="window.android.JavacallHtml()" />

	<br>
	<br>
	<br>

	<input id="id_input2" style="width: 90%" type="text" value="null" />
	<br>
	<input type="button" value="JavacallHtml2"
		onclick="window.android.JavacallHtml2()" />


	<input id="id_input3" style="width: 90%" type="text" value="null" />
	<input id="id_input4" style="width: 90%" type="text" value="null" />

	<input id="id_input5" style="width: 90%" type="text" value="null" />
	<input id="id_input2" style="width: 90%" type="text" value="null" />

	<input id="id_input6" style="width: 90%" type="text" value="null" />

	<input id="id_input7" style="width: 90%" type="text" value="null" />
	<input id="id_input8" style="width: 90%" type="text" value="null" />

	<input id="id_input9" style="width: 90%" type="text" value="null" />

	<input id="id_input10" style="width: 90%" type="text" value="null" />
	<input id="id_input11" style="width: 90%" type="text" value="null" />
	<input id="id_input12" style="width: 90%" type="text" value="null" />
	<input id="id_input13" style="width: 90%" type="text" value="null" />

</body>
</html>