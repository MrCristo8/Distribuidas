<%-- 
    Document   : cityUpdate
    Created on : Jan 16, 2019, 6:40:56 PM
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
                            <h2>Update City</h2>
                        </div>
                        <p>Edit City</p>
                        <form action="/CR_WB_WebPage/CityUpdate" method="post">
                            <div class="form-group">
                                <label>City Name</label>
                                <input type="text" name="name" class="form-control" value="${city.city_name}">
                                <span class="help-block"></span>
                            </div>                                        
                            <input type="hidden" name="id" value="${city.city_id}"/>
                            <input type="submit" class="btn btn-primary" value="Submit">
                            <a href="/CR_WB_WebPage/CityServlet" class="btn btn-default">Cancel</a>
                        </form>
                    </div>
                </div>        
            </div>
        </div>
    </body>
</html>
