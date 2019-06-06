<%@ page import="java.util.ArrayList"%>
<%@ page import="com.excilys.cdb.mapper.DtoComputer"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>

<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath }/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath }/css/font-awesome.css"
	rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath }/css/main.css"
	rel="stylesheet" media="screen">
<link rel='stylesheet'
	href='https://use.fontawesome.com/releases/v5.7.0/css/all.css'
	integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ'
	crossorigin='anonymous'>
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> Application -
				ComputerDatabase </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<select id="locales">
				<option value=""><spring:message code="lang.change"
						text="default" /></option>
				<option value="en"><spring:message code="lang.eng"
						text="default" /></option>
				<option value="fr"><spring:message code="lang.fr"
						text="default" /></option>
			</select>
			<h1 id="homeTitle">
				<%=request.getAttribute("nbComputers")%>
				<spring:message code="found"
						text="default" />
			</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" method="GET" class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit" value=<spring:message code="filter"
						text="default" />
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addComputer"><spring:message code="add"
						text="default" /></a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();"><spring:message code="edit"
						text="default" /></a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="#" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th><spring:message code="name"
						text="default" /> <a class='fas fa-sort-alpha-down'
							href="?orderBy=computer&search=${search}" /></a> <a
							class="fas fa-sort-alpha-up"
							href="?orderBy=computerDesc&search=${search}"></a>
						</th>
						<th><spring:message code="iDate"
						text="default" /> <a class='fas fa-sort-alpha-down'
							href="?orderBy=introduced&search=${search}" /></a> <a
							class="fas fa-sort-alpha-up"
							href="?orderBy=introducedDesc&search=${search}"></a>
						</th>
						<!-- Table header for Discontinued Date -->
						<th><spring:message code="dDate"
						text="default" /> <a class='fas fa-sort-alpha-down'
							href="?orderBy=discontinued&search=${search}" /></a> <a
							class="fas fa-sort-alpha-up"
							href="?orderBy=discontinuedDesc&search=${search}"></a>
						</th>
						<!-- Table header for Company -->
						<th><spring:message code="company"
						text="default" /> <a class='fas fa-sort-alpha-down'
							href="?orderBy=company&search=${search}" /></a> <a
							class="fas fa-sort-alpha-up"
							href="?orderBy=companyDesc&search=${search}"></a>
						</th>

					</tr>
				</thead>
				<%
					ArrayList<DtoComputer> computersList = (ArrayList<DtoComputer>) request.getAttribute("computersList");
				%>
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach items="${computersList}" var="computer">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${computer.id}"></td>
							<td><a href="editComputer?id=${computer.id}" onclick=""><c:out
										value="${computer.name}" /></a></td>
							<td><c:out value="${computer.introduced}" /></td>
							<td><c:out value="${computer.discontinued}" /></td>
							<td><c:out value="${computer.companyName}" /></td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination" id="pagination">
				<li><a
					href="?page=${firstArrow}&search=${search}&orderBy=${orderBy}"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a></li>
				<c:forEach var="page" items="${availablePages}">
					<li><a
						href="?page=${page}&search=${search}&orderBy=${orderBy}">${page}</a></li>
				</c:forEach>
				<li><a
					href="?page=${secondArrow}&search=${search}&orderBy=${orderBy}"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>

			<ul class="pagination pull-right" role="group">
				<li><a href="?page=1&size=10&orderBy=${orderBy}">10</a></li>
				<li><a href="?page=1&size=50&orderBy=${orderBy}">50</a></li>
				<li><a href="?page=1&size=100&orderBy=${orderBy}">100</a></li>
			</ul>
		</div>

	</footer>
	<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/dashboard.js"></script>
	<script src="${pageContext.request.contextPath }/js/international.js"></script>

</body>
</html>