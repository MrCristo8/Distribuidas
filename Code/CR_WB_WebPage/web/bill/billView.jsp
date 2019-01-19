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
        <title>Bill Details</title>
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
                        <form>
                            <div class="form-row">
                                <div class="col">
                                    <label for="bill_id">Bill ID</label>
                                    <input  id="bill_id" type="text" readonly class="form-control" value="${bill.bill_id}">
                                </div>
                                <div class="col">
                                    <label for="bill_date">Bill Date</label>
                                    <input id="bill_date" type="text" readonly class="form-control" value="${bill.bill_date}">
                                </div>
                                <div class="col">
                                    <label for="client_id">Client DNI</label>
                                    <input id="client_id" type="text" readonly class="form-control" value="${bill.client.client_dni}">
                                </div>
                                <div class="col">
                                    <label for="city_name">City</label>
                                    <input  id="city_name" type="text" readonly class="form-control" value="${bill.city.city_name}">
                                </div>
                            </div>
                        </form>
                        <br>
                        <table class='table table-bordered table-striped'>
                            <thead>
                                <tr>
                                    <th>Article Name</th>
                                    <th>Article Ammount</th>   
                                    <th>Article Price</th>                                    
                                </tr>
                            </thead>   
                            <c:forEach items="${detail_arr}" var="y">
                                <c:if test="${y.state!='DELETED'}">
                                    <tr>      
                                        <td> ${y.article.article_name} </td>
                                        <td> ${y.detail_ammount} </td>
                                        <td> ${y.article.article_price} </td>                                                                                  
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
