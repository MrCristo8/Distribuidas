<%-- 
    Document   : billView
    Created on : 18/01/2019, 10:19:24 PM
    Author     : wason
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bill Update</title>
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
        <div class=wrapper>
            <div class=container-fluid>
                <div class=row>
                    <div class=col-md-12>
                        <div class=page-header clearfix>
                            <h2 class="pull-left">Bill Details</h2>                            
                        </div>
                        <br><br>
                        <form  action="/CR_WB_WebPage/BillUpdate" method="post">
                            <input type="hidden" name="bill_id" value="${bill.bill_id}"/>                            
                            <div class="form-row">                                
                                <div class="col">
                                    <label for="bill_date">Bill Date</label>
                                    <input id="bill_date" name ="bill_date" type="date" class="form-control" value="${bill.bill_date}">
                                </div>
                                <div class="col">
                                    <label for="client">Client DNI</label>
                                    <select class="form-control" name="client">
                                        <c:forEach items="${client_arr}" var="x">
                                            <c:choose>
                                                <c:when test="${bill.client_id==x.client_id}">
                                                    <option selected value="${x.client_id}"> ${x.client_dni}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${x.client_id}"> ${x.client_dni}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col">
                                    <label for="city">City</label>
                                    <select class="form-control" name="city">
                                        <c:forEach items="${city_arr}" var="x">
                                            <c:choose>
                                                <c:when test="${bill.city_id==x.city_id}">
                                                    <option selected value="${x.city_id}"> ${x.city_name}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${x.city_id}"> ${x.city_name}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class = "col-md-2">
                                    <label for="submit_bill">Actions</label>
                                    <input id="submit_bill" type="submit" class="form-control btn-success" value="Submit Bill">
                                </div>
                            </div>
                        </form>
                        <br>
                        <form action="/CR_WB_WebPage/BillDetailInsert" method="post">
                            <input type="hidden" name="bill_id" value="${bill.bill_id}"/>
                            <input type="hidden" name="operation" value="update"/>
                            <table class='table table-bordered table-striped'>
                                <thead>
                                    <tr>
                                        <th>Article Name</th>
                                        <th>Article Ammount</th>   
                                        <th>Actions</th>                                    
                                    </tr>
                                </thead>   
                                <c:forEach items="${detail_arr}" var="y">
                                    <c:if test="${y.state!='DELETED'}">
                                        <tr>      
                                            <td> ${y.article.article_name} </td>
                                            <td> ${y.detail_ammount} </td>
                                            <td>
                                                <a href='/CR_WB_WebPage/BillDetailDelete?article_id=${y.article_id}' title='Delete Record' 
                                                   data-toggle='tooltip'><i class="material-icons" data-toggle="tooltip">delete</i></a>
                                                <a href='/CR_WB_WebPage/BillDetailUpdate?article_id=${y.article_id}' title='Update Record' 
                                                   data-toggle='tooltip'><i class="material-icons" data-toggle="tooltip">edit</i></a>
                                            </td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                                <tr>
                                    <td>
                                        <select class="form-control" name="article">
                                            <c:forEach items="${article_arr}" var="x">
                                                <option value="${x.article_id}"> ${x.article_name}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td><input type="text" class="form-control" name="detail_amount"></td>
                                    <td><input type="submit" class="form-control btn-primary" value="Add"></td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
