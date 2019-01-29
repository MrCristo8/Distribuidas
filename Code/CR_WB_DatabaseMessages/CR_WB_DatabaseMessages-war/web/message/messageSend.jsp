<%-- 
    Document   : articleUpdate
    Created on : Jan 16, 2019, 6:16:32 PM
    Author     : csrm1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Send Message</title>
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
                            <h2>Message Form</h2>
                        </div>
                        <p>Fill In Your Requests</p>
                        <form action="/CR_WB_DatabaseMessages-war/MessageServlet" method="post">
                            <div class="form-group">
                                <label>Your Name</label>
                                <input type="text" name="message_from" class="form-control" placeholder="fill in your name">
                                <span class="help-block"></span>
                            </div>
                            <div class="form-group">
                                <label>What do you want to say</label>
                                <textarea  name="message_body" rows="5" class="form-control" placeholder="your inquire"></textarea>
                                <span class="help-block"></span>
                            </div>                                                       
                            <input type="submit" class="btn btn-primary" value="Submit">
                            <a href="#" class="btn btn-default">Cancel</a>
                        </form>
                    </div>
                </div>        
            </div>
        </div>
    </body>
</html>
