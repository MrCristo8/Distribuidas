<%-- 
    Document   : userList
    Created on : Jan 21, 2019, 4:03:07 PM
    Author     : csrm1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User</title>
        <style type=text/css>
            .wrapper{
                width: auto;
                padding: 4em 16em 1em 16em;
                margin: 0 auto;
            }
            .page-header h2{
                margin-top: 0;
            }
            table tr td:last-child a{
                margin-right: 15px;
            }
        </style>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons"/>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
        <script type=text/javascript>
            $(document).ready(function () {
                $('[data-toggle=tooltip]').tooltip();
            });
        </script>
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
        <div class=wrapper>
            <div class=container-fluid>
                <div class=row>
                    <div class=col-md-12>
                        <div class=page-header clearfix>
                            <h2 class=pull-left>User Details</h2>
                            <form action="UserSave" method="post">
                                <input type="submit" class="btn btn-success float-right" value="Save Changes">
                            </form>
                        </div>
                        <br><br>
                        <form action="/CR_WB_WebPage/UserInsert" method="post">
                            <table class='table table-bordered table-striped'>
                                <thead>
                                    <tr>
                                        <th>User ID</th>
                                        <th>Username</th>
                                        <th>Password</th>
                                        <th>User permission</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>  
                                <c:forEach items="${objList}" var="x">
                                    <c:if test="${x.state!='DELETED'}">
                                        <tr>      
                                            <td>  ${x.user_id}  </td>
                                            <td>  ${x.user_name}  </td>
                                            <td> XXXXXXXX </td>
                                            <td>  ${x.user_permission} </td>
                                            <td>                                        
                                                <a href='/CR_WB_WebPage/UserUpdate?user_name=${x.user_name}&user_password=${x.user_password}' title='Update Record' 
                                                   data-toggle='tooltip'><i class="material-icons" data-toggle="tooltip">edit</i></a>
                                                <a href='/CR_WB_WebPage/UserDelete?user_name=${x.user_name}&user_password=${x.user_password}' title='Delete Record' 
                                                   data-toggle='tooltip'><i class="material-icons" data-toggle="tooltip">delete</i></a>
                                            </td>
                                        </tr>
                                    </c:if>
                                </c:forEach>  
                                <tr>
                                    <td></td>
                                    <td><input type="text" class="form-control" name="username"></td>
                                    <td><input type="password" class="form-control" name="pwd"></td>
                                    <td><input type="text" class="form-control" name="permission"></td>
                                    <td><input type="submit" class="btn btn-primary" value="Add"></td>
                                </tr>
                            </table>
                        </form>    
                        <br>
                        <h2 class=pull-left>User Filtered List</h2>
                        <br>
                        <form action="UserServlet" method="post">
                            <div class ="form-row">  
                                <div class="col-md-11">
                                    <input type="text" class="form-control" placeholder="Search Parammeter" name = "search_string">
                                </div>
                                <div class="col-md-1">
                                    <button type="submit" class="btn btn-primary">Search</button>        
                                </div>
                            </div>
                        </form>
                        <br>
                        <table class='table table-bordered table-striped'>
                            <thead>
                                <tr>
                                    <th>User ID</th>
                                    <th>Username</th>
                                    <th>User Permission</th>
                                </tr>
                            </thead>   
                            <c:forEach items="${objSearchList}" var="y">
                                <c:if test="${y.state!='DELETED'}">
                                    <tr>      
                                        <td>  ${y.user_id}  </td>
                                        <td>  ${y.user_name}  </td>
                                        <td>  ${y.user_permission} </td>                                       
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </table>
                    </div>
                </div>        
            </div>
        </div>
    </body>
</html>