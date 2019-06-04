<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath }/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath }/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="dashboard"> Application - Computer Database </a>
        </div>
    </header>
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <div class="label label-default pull-right">
                        id: ${id}
                    </div>
                    <h1>Edit Computer</h1>

                    <form:form id="editComputer" action="editComputer" method="POST" modelAttribute="dtoComputer">
                        <form:input path="id" type="hidden" value="${id}" id="id" name="id" /> <!-- TODO: Change this value with the computer id -->
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <form:input path="name" type="text" class="form-control" id="computerName" placeholder="Computer name" name="UpdateComputerName" value="${name}" required="true" />
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <form:input path="introduced" type="date" class="form-control" id="introduced" name="UpdateIntroduced" placeholder="Introduced date" value="${introduced}"
                               	 pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}" min="1970-01-01" max="3500-12-31"/>
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <form:input path="discontinued" type="date" class="form-control" id="discontinued" name="UpdateDiscontinued" placeholder="Discontinued date" value="${discontinued}"
                                pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}" min="1970-01-01" max="3501-01-01"/>
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <form:select path="company_id" name="UpdateCompanyId" class="form-control" id="companyId" >
                                    <form:option value="${company_id}">${companyName}</form:option>
                                    	<c:forEach items="${companiesList}" var="company">
                                			<option value="${company.getId()}">${company.getName()}</option>
                						</c:forEach>
                                </form:select>
                            </div>            
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="Edit" class="btn btn-primary">
                            or
                            <a href="dashboard" class="btn btn-default">Cancel</a>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>