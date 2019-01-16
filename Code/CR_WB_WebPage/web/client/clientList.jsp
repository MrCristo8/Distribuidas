<%-- 
    Document   : list
    Created on : Jan 14, 2019, 3:50:39 PM
    Author     : csrm1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Client</title>
        <link rel="stylesheet" href="../style.css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous"/>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
        <script src="../script.js"></script>
    </head>
    <body>
        <div class=wrapper>
            <div class=container-fluid>
                <div class=row>
                    <div class=col-md-12>
                        <div class=page-header clearfix>
                            <h2 class=pull-left>Client Details</h2>
                            <form action="ClientSave" method="post">
                                <input type="submit" class="btn btn-success float-right" value="Save Changes">
                            </form>
                        </div>
                        <br><br>
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
                                    <td><input type="text" class="form-control" name="dni"></td>
                                    <td><input type="text" class="form-control" name="name"></td>
                                    <td><input type="text" class="form-control" name="address"></td>
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
