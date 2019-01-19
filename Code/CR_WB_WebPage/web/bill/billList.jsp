<%-- 
    Document   : billList
    Created on : 18/01/2019, 02:14:41 PM
    Author     : wason
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bill</title>
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
                            <form action="BillSave" method="post">
                                <input type="submit" class="btn btn-success float-right" value="Save Changes">
                            </form>
                        </div>
                        <br><br>
                        <table class='table table-bordered table-striped'>
                            <thead>
                                <tr>
                                    <th>Bill ID</th>
                                    <th>Bill Date</th>   
                                    <th>Client Name</th>
                                    <th>Client ID</th>
                                    <th>City Name</th>     
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <c:forEach items="${objList}" var="x">
                                <c:if test="${x.state!='DELETED'}">
                                    <tr>      
                                        <td> ${x.bill_id} </td>
                                        <td> ${x.bill_date} </td>
                                        <td> ${x.client.client_name} </td> 
                                        <td> ${x.client.client_dni} </td>
                                        <td> ${x.city.city_name} </td>
                                        <td>   
                                            <a href='/CR_WB_WebPage/BillView?bill_id=${x.bill_id}' title='View Record' 
                                               data-toggle='tooltip'><i class="material-icons" data-toggle="tooltip">visibility</i></a>
                                            <a href='/CR_WB_WebPage/BillUpdate?bill_id=${x.bill_id}' title='Update Record' 
                                               data-toggle='tooltip'><i class="material-icons" data-toggle="tooltip">edit</i></a>
                                            <a href='/CR_WB_WebPage/BillDelete?bill_id=${x.bill_id}' title='Delete Record' 
                                               data-toggle='tooltip'><i class="material-icons" data-toggle="tooltip">delete</i></a>
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach> 
                        </table>
                        <br>
                        <a href="/CR_WB_WebPage/BillInsert" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Add new bill</a>
                        <br>
                        <h2 class=pull-left>Bill Filtered List</h2>
                        <br>
                        <form action="BillServlet" method="post">
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
                                    <th>Bill ID</th>
                                    <th>Bill Date</th>   
                                    <th>Client Name</th>
                                    <th>Client ID</th>
                                    <th>City Name</th>
                                </tr>
                            </thead>   
                            <c:forEach items="${objSearchList}" var="y">
                                <c:if test="${y.state!='DELETED'}">
                                    <tr>      
                                        <td> ${y.bill_id} </td>
                                        <td> ${y.bill_date} </td>
                                        <td> ${y.client.client_name} </td> 
                                        <td> ${y.client.client_dni} </td>
                                        <td> ${y.city.city_name} </td>                                            
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