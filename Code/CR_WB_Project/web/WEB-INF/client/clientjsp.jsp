<%-- 
    Document   : clientjsp
    Created on : 1/11/2018, 04:24:02 PM
    Author     : wason
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.CR_WB_Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>
        <link rel=stylesheet href=https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css>
        <script src=https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js></script>
        <script src=https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.js></script>
        <link rel="stylesheet" href="styles.css">
        <script src="script.js"></script>
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <ul class="nav navbar-nav">
                <li><a href="#">Bill</a></li>
                <li class="active"><a href="/CR_WB_Project/ClientServlet">Clients</a></li>
                <li><a href="/CR_WB_Project/ArticleServlet">Articles</a></li>
                <li><a href="/CR_WB_Project/CityServlet">Cities</a></li>
                <li><a href="/CR_WB_Project/MovementServlet">Movements</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/CR_WB_Project/UserServlet"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
            </ul>
        </div>
    </nav>
</head>
<body>
    <div class=wrapper>
        <div class=container-fluid>
            <div class=row>
                <div class=col-md-12>
                    <div class=page-header clearfix>
                        <h2 class=pull-left>Client Details</h2>
                        <a href="" class="btn btn-success pull-right">
                            Save Changes</a>
                    </div>
                    <form action="/CR_WB_Project/ClientInsert" method="post">
                        <table class='table table-bordered table-striped'>
                            <thead>
                                <tr>
                                    <th>Client ID</th>
                                    <th>Client DNI</th>
                                    <th>Client Name</th>
                                    <th>Client Adress</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>  

                            <c:forEach items="${objList}" var="x">
                                <c:if test="${x.state!='DELETED'}">
                                    <tr>      
                                        <td>  ${x.client_id}  </td>
                                        <td>  ${x.client_dni}  </td>
                                        <td>  ${x.client_name} </td>
                                        <td>  ${x.client_address}</td>
                                        <td>                                        
                                            <a href='/CR_WB_Project/ClientUpdate?client_id=${x.client_id}' title='Update Record' 
                                               data-toggle='tooltip'><span class='glyphicon glyphicon-pencil'></span></a>
                                            <a href='/CR_WB_Project/ClientDelete?client_id=${x.client_id}' title='Delete Record' 
                                               data-toggle='tooltip'><span class='glyphicon glyphicon-trash'></span></a>
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>  
                            <tr>
                                <td></td>
                                <td><input type="text" name="dni"></td>
                                <td><input type="text" name="name"></td>
                                <td><input type="text" name="address"></td>
                                <td><input type="submit" class="btn btn-primary" value="Add"></td>
                            </tr>
                        </table>
                    </form>                        
                </div>
            </div>        
        </div>
    </div>
</body>
</html>
