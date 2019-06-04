<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.util.ArrayList" %>
<%@ page import ="com.excilys.cdb.mapper.DtoCompany"%>
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

	<%ArrayList<DtoCompany> companiesList = (ArrayList<DtoCompany>)request.getAttribute("companiesList"); %>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1>Add Computer</h1>
                    <form:form id="addComputer" action="addComputer" method="POST" modelAttribute="dtoComputer">
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <form:input path="name" type="text" name="computerName" class="form-control" id="computerName" placeholder="Computer name" required="true" />
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <form:input path="introduced" type="date" name="introduced" class="form-control" id="introduced" placeholder="Introduced date" 
                                pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}" min="1970-01-01" max="3500-12-31"/>
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <form:input path="discontinued" type="date" name="discontinued" class="form-control" id="discontinued" placeholder="Discontinued date"
                               	pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}" min="1970-01-01" max="3501-01-01"/>
                            </div>
                            
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <form:select path="company_id" name="companyId" class="form-control" id="companyId" >
                                    <option value="null">--</option>
                                    	<c:forEach items="${companiesList}" var="company">
                                			<option value="${company.getId()}">${company.getName()}</option>
                						</c:forEach>
                                </form:select>
                            </div>                  
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="Add" class="btn btn-primary">
                            or
                            <a href="dashboard.html" class="btn btn-default">Cancel</a>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>