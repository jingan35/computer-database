<%@ page import ="java.util.ArrayList" %>
<%@ page import ="com.excilys.cdb.mapper.DtoComputer"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
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
            <h1 id="homeTitle">
            	<%= request.getAttribute("nbComputers") %> Computers found
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" method="GET" class="form-inline">

                        <input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" />
                        <input type="submit" id="searchsubmit" value="Filter by name"
                        class="btn btn-primary" />
                    </form>
                </div>
                <div class="pull-right">
                    <a class="btn btn-success" id="addComputer" href="addComputer">Add Computer</a> 
                    <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">Edit</a>
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

                        <th class="editMode" style="width: 60px; height: 22px;">
                            <input type="checkbox" id="selectall" /> 
                            <span style="vertical-align: top;">
                                 -  <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <i class="fa fa-trash-o fa-lg"></i>
                                    </a>
                            </span>
                        </th>
                        <th>
                            Computer name
                        </th>
                        <th>
                            Introduced date
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
                            Discontinued date
                        </th>
                        <!-- Table header for Company -->
                        <th>
                            Company
                        </th>

                    </tr>
                </thead>
                <%ArrayList<DtoComputer> computersList = (ArrayList<DtoComputer>)request.getAttribute("computersList"); %>
                <!-- Browse attribute computers -->
                <tbody id="results">
                	<c:forEach items="${computersList}" var="computer">
                		<tr>
                        <td class="editMode">
                            <input type="checkbox" name="cb" class="cb" value="0">
                        </td>
                        <td>
                            <a href="editComputer" onclick=""><c:out value="${computer.name}" /></a>
                        </td>
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
                <li>
                    <a href="?page=${firstArrow}&search=${search}" aria-label="Previous">
                      <span aria-hidden="true">&laquo;</span>
                  </a>
              </li>
              <c:forEach var="page" items="${availablePages}">
				<li><a href="?page=${page}&search=${search}">${page}</a></li>
			</c:forEach>
              <li>
                <a href="?page=${secondArrow}&search=${search}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
        
		<ul class="pagination pull-right" role="group" >
	            <li><a href="?page=1&size=10">10</a></li>
	            <li><a href="?page=1&size=50">50</a></li>
	            <li><a href="?page=1&size=100">100</a></li>
		</ul>
        </div>

    </footer>
<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath }/js/dashboard.js"></script>

</body>
</html>