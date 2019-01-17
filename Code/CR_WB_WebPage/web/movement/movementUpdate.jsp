<%-- 
    Document   : movementUpdate
    Created on : Jan 16, 2019, 6:49:13 PM
    Author     : csrm1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Update Record</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous"/>
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
                            <h2>Update Movement</h2>
                        </div>
                        <p>Edit Movement</p>
                        <form action="/CR_WB_WebPage/ClientUpdate" method="post">
                            <div class="form-group">
                                <label>Movement Name</label>
                                <input type="text" name="name" class="form-control" value="${movement.movement_name}">
                                <span class="help-block"></span>
                            </div>
                            <div class="form-group">
                                <label>Movement Direction</label>
                                <input type="text" name="direction" class="form-control" value="${movement.movement_direction}">
                                <span class="help-block"></span>
                            </div>
                            <input type="hidden" name="id" value="${movemnet.movement_id}"/>
                            <input type="submit" class="btn btn-primary" value="Submit">
                            <a href="/CR_WB_WebPage/ClientServlet" class="btn btn-default">Cancel</a>
                        </form>
                    </div>
                </div>        
            </div>
        </div>
    </body>
</html>