<%-- 
    Document   : createuser
    Created on : 3/11/2018, 02:33:56 PM
    Author     : wason
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Create User</title>
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
                            <h2>Create New User</h2>
                        </div>
                        <p>User Information</p>
                        <form action="/CR_WB_Project/UserCreation" method="post">
                            <div class="form-group">
                                <label>UserName</label>
                                <input type="text" name="name" class="form-control" value="">
                                <span class="help-block"></span>
                            </div>
                            <div class="form-group">
                                <label>Password</label>
                                <input type="text" name="pswd" class="form-control" value="">
                                <span class="help-block"></span>
                            </div>       
                            <input type="submit" class="btn btn-primary" value="Submit">
                            <a href="/CR_WB_Project/UserServlet" class="btn btn-default">Cancel</a>
                        </form>
                    </div>
                </div>        
            </div>
        </div>
    </body>
</html>