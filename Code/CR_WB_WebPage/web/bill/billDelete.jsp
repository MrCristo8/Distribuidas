<%-- 
    Document   : billDelete
    Created on : 18/01/2019, 02:23:30 PM
    Author     : wason
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Delete Bill</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous"/>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
        <style type="text/css">
            .wrapper{
                width: 500px;
                margin: 0 auto;
            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
            <c:forEach items="${permission}"  var="x">
                <ul class="navbar-nav">
                    <c:if test="${x=='Clients'}">
                        <li class="nav-item">
                            <a class="nav-link" href="/CR_WB_WebPage/ClientServlet">Clients</a>
                        </li>
                    </c:if>
                    <c:if test="${x=='Articles'}">
                        <li class="nav-item">
                            <a class="nav-link" href="/CR_WB_WebPage/ArticleServlet">Articles</a>
                        </li>
                    </c:if>
                    <c:if test="${x=='Movements'}">
                        <li class="nav-item">
                            <a class="nav-link" href="/CR_WB_WebPage/MovementServlet">Movements</a>
                        </li>
                    </c:if>
                    <c:if test="${x=='Cities'}">
                        <li class="nav-item">
                            <a class="nav-link" href="/CR_WB_WebPage/CityServlet">Cities</a>
                        </li>
                    </c:if>
                    <c:if test="${x=='Users'}">
                        <li class="nav-item">
                            <a class="nav-link" href="/CR_WB_WebPage/UserServlet">Users</a>
                        </li>
                    </c:if>
                    <c:if test="${x=='Inventory'}">
                        <li class="nav-item">
                            <a class="nav-link" href="/CR_WB_WebPage/InventoryServlet">Inventory</a>
                        </li>
                    </c:if>
                    <c:if test="${x=='Bills'}">
                        <li class="nav-item">
                            <a class="nav-link" href="/CR_WB_WebPage/BillServlet">Bills</a>
                        </li>
                    </c:if>
                    <c:if test="${x=='Reports'}">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="" id="navbardrop" data-toggle="dropdown">Reports</a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="/CR_WB_WebPage/ArticleByClient.jsp">Article By Client</a>
                                <a class="dropdown-item" href="/CR_WB_WebPage/ArticleByMovement.jsp">Article By Movement</a>
                                <a class="dropdown-item" href="/CR_WB_WebPage/BalancePerArticle.jsp">Balance Per Article</a>
                                <a class="dropdown-item" href="/CR_WB_WebPage/SalesPerCity.jsp">Sales Per City</a>
                            </div>
                        </li>
                    </c:if>
                </ul>
            </c:forEach>
            <navbar-nav class="ml-md-auto d-md-flex">
                <ul class="navbar-nav">
                    <li class="nav-item" id="logout">
                        <a class="nav-link" href="/CR_WB_WebPage/UserLogin">Log Out</a>
                    </li>
                </ul>
            </navbar-nav>
        </nav>
        <div class="wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="page-header">
                            <h1>Delete Bill</h1>
                        </div>
                        <form action="/CR_WB_WebPage/BillDelete" method="post">
                            <div class="alert alert-danger alert-dismissible fade show">                            
                                <input type="hidden" name="bill_id" value="${bill_id}"/>
                                <p>Are you shure you want to delete this bill?</p><br>
                                <p>
                                    <input type="submit" value="Yes" class="btn btn-danger">
                                    <a href="/CR_WB_WebPage/BillServlet" class="btn btn-default">No</a>
                                </p>
                            </div>
                        </form>
                    </div>
                </div>        
            </div>
        </div>
    </body>
</html>

