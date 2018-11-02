<%-- 
    Document   : articledelete
    Created on : 2/11/2018, 08:57:42 AM
    Author     : wason
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Delete Article</title>
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
                        <h1>Delete Article</h1>
                    </div>
                    <form action="/CR_WB_Project/ArticleDelete" method="post">
                        <div class="alert alert-danger fade in">                            
                            <input type="hidden" name="article_id" value="${article_id}"/>
                            <p>Are you shure you want to delete this article?</p><br>
                            <p>
                                <input type="submit" value="Yes" class="btn btn-danger">
                                <a href="/CR_WB_Project/ArticleServlet" class="btn btn-default">No</a>
                            </p>
                        </div>
                    </form>
                </div>
            </div>        
        </div>
    </div>
</body>
</html>