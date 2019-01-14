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
        <div class="wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="page-header">
                            <h2>Update Client</h2>
                        </div>
                        <p>Edit Client</p>
                        <form action="/CR_WB_Project/ClientUpdate" method="post">
                            <div class="form-group">
                                <label>Client Name</label>
                                <input type="text" name="name" class="form-control" value="${client.client_name}">
                                <span class="help-block"></span>
                            </div>
                            <div class="form-group">
                                <label>Client DNI</label>
                                <input type="text" name="dni" class="form-control" value="${client.client_dni}">
                                <span class="help-block"></span>
                            </div>
                            <div class="form-group">
                                <label>Client Address</label>
                                <input type="text" name="addr" class="form-control" value="${client.client_address}">
                                <span class="help-block"></span>
                            </div>

                            <input type="hidden" name="id" value="${client.client_id}"/>
                            <input type="submit" class="btn btn-primary" value="Submit">
                            <a href="/CR_WB_Project/ClientServlet" class="btn btn-default">Cancel</a>
                        </form>
                    </div>
                </div>        
            </div>
        </div>
    </body>
</html>
