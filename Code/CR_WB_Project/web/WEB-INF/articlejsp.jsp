<%-- 
    Document   : articlejsp
    Created on : 1/11/2018, 03:43:07 PM
    Author     : wason
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.CR_WB_Article"%>
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
    </head>
    <body>
        <div class=wrapper>
            <div class=container-fluid>
                <div class=row>
                    <div class=col-md-12>
                        <div class=page-header clearfix>
                            <h2 class=pull-left>Article Details</h2>
                            <a href="" class="btn btn-success pull-right">
                                Add New Article</a>
                        </div>
                        <table class='table table-bordered table-striped'>
                            <thead>
                                <tr>
                                    <th>Article ID</th>
                                    <th>Article Name</th>
                                    <th>Article Price</th>
                                    <th>Article Stock</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>   

                            <c:forEach items="${objList}" var="x">
                                <c:if test="${x.state!='DELETED'}">
                                <tr>      
                                    <td>  ${x.article_id}  </td>
                                    <td>  ${x.article_name}  </td>
                                    <td>  ${x.article_price} </td>
                                    <td>  ${x.article_stock}</td>
                                    <td>                                        
                                        <a href='/CR_WB_Project/ArticleUpdate?article_id=${x.article_id}' title='Update Record' 
                                           data-toggle='tooltip'><span class='glyphicon glyphicon-pencil'></span></a>;
                                        <a href='/CR_WB_Project/ArticleDelete?article_id=${x.article_id}' title='Delete Record' 
                                           data-toggle='tooltip'><span class='glyphicon glyphicon-trash'></span></a>
                                    </td>
                                </tr>
                                </c:if>
                            </c:forEach>                            
                        </table>
                        <form>
                            <table>
                                <td id='article_name'></td>
                            </table>
                        </form>
                    </div>
                </div>        
            </div>
        </div>
    </body>
</html>
