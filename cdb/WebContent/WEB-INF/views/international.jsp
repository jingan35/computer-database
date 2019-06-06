<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>

<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js">
$(document).ready(function() {
    $("#locales").change(function () {
        var selectedOption = $('#locales').val();
        if (selectedOption != ''){
            window.location.replace('international?lang=' + selectedOption);
        }
    });
});
</script>
<head>
<meta charset="UTF-8">
<title>internationalization test</title>
</head>
<body>
	<span> <spring:message code="lang.change" text="default" /></span>:
	<select id="locales">
		<option value=""></option>
		<option value="en"><spring:message code="lang.eng"
				text="default" /></option>
		<option value="fr"><spring:message code="lang.fr"
				text="default" /></option>
	</select>
	<h1>
		<spring:message code="greeting" text="default" />
	</h1>
</body>
</html>