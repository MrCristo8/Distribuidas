<%-- 
    Document   : cityjsp
    Created on : 3/11/2018, 12:26:32 PM
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
                width: 650px;
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
                    <li class="active"><a href="/CR_WB_Project/CityServlet">Cities</a></li>
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
                            <h2 class=pull-left>City Details</h2>
                            <a href="" class="btn btn-success pull-right">
                                Save Changes</a>
                        </div>
                        <form action="/CR_WB_Project/CityInsert" method="post">
                        <table class='table table-bordered table-striped'>
                            <thead>
                                <tr>
                                    <th>City ID</th>
                                    <th>City Name</th>                                    
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <c:forEach items="${objList}" var="x">
                                <c:if test="${x.state!='DELETED'}">
                                    <tr>      
                                        <td>  ${x.city_id}  </td>
                                        <td>  ${x.city_name}  </td>                                    
                                        <td>                                        
                                            <a href='/CR_WB_Project/CityUpdate?city_id=${x.city_id}' title='Update Record' 
                                               data-toggle='tooltip'><span class='glyphicon glyphicon-pencil'></span></a>
                                            <a href='/CR_WB_Project/CityDelete?city_id=${x.city_id}' title='Delete Record' 
                                               data-toggle='tooltip'><span class='glyphicon glyphicon-trash'></span></a>
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>    
                            <tr>
                                <td></td>
                                <td><input type="text" name="name"></td>
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
