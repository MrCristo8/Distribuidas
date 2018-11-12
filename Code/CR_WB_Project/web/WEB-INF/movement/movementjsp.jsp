<%-- 
    Document   : movementjsp
    Created on : 3/11/2018, 08:34:26 PM
    Author     : wason
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang=en>
    <head>
        <meta charset=UTF-8>
        <title>Dashboard</title>
        <link rel=stylesheet href=https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css>
        <script src=https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js></script>
        <script src=https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.js></script>
        <style type=text/css>
            .wrapper{
                width: 800px;
                margin: 0 auto;
            }
            .page-header h2{
                margin-top: 0;
            }
            table tr td:last-child a{
                margin-right: 15px;
            }
        </style>
        <script type=text/javascript>
            $(document).ready(function () {
                $('[data-toggle=tooltip]').tooltip();
            });
        </script>
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <ul class="nav navbar-nav">
                <li><a href="#">Bill</a></li>
                <li><a href="/CR_WB_Project/ClientServlet">Clients</a></li>
                <li><a href="/CR_WB_Project/ArticleServlet">Articles</a></li>
                <li><a href="/CR_WB_Project/CityServlet">Cities</a></li>
                <li class="active"><a href="/CR_WB_Project/MovementServlet">Movements</a></li>
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
                        <h2 class=pull-left>Movement Details</h2>
                        <a href="" class="btn btn-success pull-right">
                            Save Changes</a>
                    </div>
                    <table class='table table-bordered table-striped'>
                        <thead>
                            <tr>
                                <th>Movement ID</th>
                                <th>Article Name</th>
                                <th>Movement Name</th>
                                <th>Movement Date</th>
                                <th>Movement Ammount</th>
                                <th>Movement Direction</th>
                            </tr>
                        </thead>   

                        <c:forEach items="${objList}" var="x">
                            <c:if test="${x.state!='DELETED'}">
                                <tr>      
                                    <td>  ${x.movement_id}  </td>
                                    <td>  ${x.article.article_name}  </td>
                                    <td>  ${x.movement_name} </td>
                                    <td>  ${x.movement_date}</td>
                                    <td>  ${x.movement_ammount}</td> 
                                    <td>  ${x.movement_direction}</td>                                         
                                </tr>
                            </c:if>
                        </c:forEach>                            
                    </table>
                    <form>
                        <table>
                            <td id='movement_name'></td>
                        </table>
                    </form>
                </div>
            </div>        
        </div>
    </div>
</body>
</html>
