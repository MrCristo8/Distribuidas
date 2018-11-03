<%-- 
    Document   : cityupdate
    Created on : 3/11/2018, 12:27:03 PM
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
                        <h2>Update City</h2>
                    </div>
                    <p>Edit City</p>
                    <form action="/CR_WB_Project/CityUpdate" method="post">
                        <div class="form-group">
                            <label>City Name</label>
                            <input type="text" name="name" class="form-control" value="${city.city_name}">
                            <span class="help-block"></span>
                        </div>                                        
                        <input type="hidden" name="id" value="${city.city_id}"/>
                        <input type="submit" class="btn btn-primary" value="Submit">
                        <a href="/CR_WB_Project/CityServlet" class="btn btn-default">Cancel</a>
                    </form>
                </div>
            </div>        
        </div>
    </div>
</body>
</html>
