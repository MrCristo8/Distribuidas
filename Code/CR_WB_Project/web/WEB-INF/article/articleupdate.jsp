<%-- 
    Document   : articleupdate
    Created on : 2/11/2018, 12:43:13 PM
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
                        <h2>Update Article</h2>
                    </div>
                    <p>Edit Article</p>
                    <form action="/CR_WB_Project/ArticleUpdate" method="post">
                        <div class="form-group">
                            <label>Article Name</label>
                            <input type="text" name="name" class="form-control" value="${article.article_name}">
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group">
                            <label>Article Price</label>
                            <input type="text" name="price" class="form-control" value="${article.article_price}">
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group">
                            <label>Article Stock</label>
                            <input type="text" name="stock" class="form-control" value="${article.article_stock}">
                            <span class="help-block"></span>
                        </div>
                        
                        <input type="hidden" name="id" value="${article.article_id}"/>
                        <input type="submit" class="btn btn-primary" value="Submit">
                        <a href="/CR_WB_Project/ArticleServlet" class="btn btn-default">Cancel</a>
                    </form>
                </div>
            </div>        
        </div>
    </div>
</body>
</html>
