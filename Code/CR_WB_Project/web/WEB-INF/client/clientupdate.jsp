<%-- 
    Document   : clientupdate
    Created on : 2/11/2018, 01:31:24 PM
    Author     : wason
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Record</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css">
    <style type="text/css">
        .wrapper{
            width: 500px;
            margin: 0 auto;
        }
    </style>
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
