<%-- 
    Document   : movementDelete
    Created on : Jan 16, 2019, 6:45:48 PM
    Author     : csrm1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Delete Movement</title>
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
                            <h1>Delete Movement</h1>
                        </div>
                        <form action="/CR_WB_WebPage/ClientDelete" method="post">
                            <div class="alert alert-danger fade in">                            
                                <input type="hidden" name="client_id" value="${movement_id}"/>
                                <p>Are you shure you want to delete this movement?</p><br>
                                <p>
                                    <input type="submit" value="Yes" class="btn btn-danger">
                                    <a href="/CR_WB_WebPage/ClientServlet" class="btn btn-default">No</a>
                                </p>
                            </div>
                        </form>
                    </div>
                </div>        
            </div>
        </div>
    </body>
</html>
