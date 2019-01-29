<%-- 
    Document   : billView
    Created on : 18/01/2019, 10:19:24 PM
    Author     : wason
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bill Update</title>
        <style type=text/css>
            .wrapper{
                width: auto;
                padding: 4em 16em 1em 16em;
                margin: 0 auto;
            }
            .page-header h2{
                margin-top: 0;
            }
            table tr td:last-child a{
                margin-right: 15px;
            }
        </style>
        <link rel="stylesheet" type="text/css" href="http://localhost:8080/CR_WB_WebPage/css/style.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons"/>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
        <script src="http://localhost:8080/CR_WB_WebPage/js/script.js" type="text/javascript"></script>
        <script type=text/javascript>
            $(document).ready(function () {
                $('[data-toggle=tooltip]').tooltip();
            });
        </script>
    </head>
    <body>
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
            <c:forEach items="${permission}"  var="x">
                <ul class="navbar-nav">
                    <c:if test="${x=='Clients'}">
                        <li class="nav-item">
                            <a class="nav-link" href="/CR_WB_WebPage/ClientServlet">Clients</a>
                        </li>
                    </c:if>
                    <c:if test="${x=='Articles'}">
                        <li class="nav-item">
                            <a class="nav-link" href="/CR_WB_WebPage/ArticleServlet">Articles</a>
                        </li>
                    </c:if>
                    <c:if test="${x=='Movements'}">
                        <li class="nav-item">
                            <a class="nav-link" href="/CR_WB_WebPage/MovementServlet">Movements</a>
                        </li>
                    </c:if>
                    <c:if test="${x=='Cities'}">
                        <li class="nav-item">
                            <a class="nav-link" href="/CR_WB_WebPage/CityServlet">Cities</a>
                        </li>
                    </c:if>
                    <c:if test="${x=='Users'}">
                        <li class="nav-item">
                            <a class="nav-link" href="/CR_WB_WebPage/UserServlet">Users</a>
                        </li>
                    </c:if>
                    <c:if test="${x=='Inventory'}">
                        <li class="nav-item">
                            <a class="nav-link" href="/CR_WB_WebPage/InventoryServlet">Inventory</a>
                        </li>
                    </c:if>
                    <c:if test="${x=='Bills'}">
                        <li class="nav-item">
                            <a class="nav-link" href="/CR_WB_WebPage/BillServlet">Bills</a>
                        </li>
                    </c:if>
                    <c:if test="${x=='Reports'}">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="" id="navbardrop" data-toggle="dropdown">Reports</a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="/CR_WB_WebPage/ArticleByClient">Article By Client</a>
                                <a class="dropdown-item" href="/CR_WB_WebPage/ArticleByMovement">Article By Movement</a>
                                <a class="dropdown-item" href="/CR_WB_WebPage/BalancePerArticle">Balance Per Article</a>
                                <a class="dropdown-item" href="/CR_WB_WebPage/SalesPerCity">Sales Per City</a>
                            </div>
                        </li>
                    </c:if>
                </ul>
            </c:forEach>
            <navbar-nav class="ml-md-auto d-md-flex">
                <ul class="navbar-nav">
                    <li class="nav-item" id="logout">
                        <a class="nav-link" href="/CR_WB_WebPage/UserLogin">Log Out</a>
                    </li>
                </ul>
            </navbar-nav>
        </nav>
        <div class=wrapper>
            <div class=container-fluid>
                <div class=row>
                    <div class=col-md-12>
                        <div class=page-header clearfix>
                            <h2 class="pull-left">Bill Details</h2>                            
                        </div>
                        <br><br>
                        <form  action="/CR_WB_WebPage/BillUpdate" method="post">
                            <input type="hidden" name="bill_id" value="${bill.bill_id}"/>                            
                            <div class="form-row">                                
                                <div class="col">
                                    <label for="bill_date">Bill Date</label>
                                    <input id="bill_date" readonly name ="bill_date" type="date" class="form-control" value="${bill.bill_date}">
                                </div>
                                <div class="col">
                                    <label for="client">Client DNI</label>
                                    <input id="client_dni" readonly name ="client" type="text" class="form-control" value="${bill.client.client_dni}">
                                </div>
                                <div class="col">
                                    <label for="city">City</label>
                                    <input id="city" readonly name ="city" type="text" class="form-control" value="${bill.city.city_name}">
                                </div>
                                <div class = "col-md-2">
                                    <label for="submit_bill">Actions</label>
                                    <input id="submit_bill" type="submit" class="form-control btn-success" value="Update Bill">
                                </div>
                            </div>
                        </form>
                        <br>
                        <form action="/CR_WB_WebPage/BillDetailInsert" method="post">
                            <input type="hidden" name="bill_id" value="${bill.bill_id}"/>
                            <input type="hidden" name="operation" value="update"/>
                            <table class='table table-bordered table-striped'>
                                <thead>
                                    <tr>
                                        <th>Article Name</th>
                                        <th>Article Ammount</th>   
                                        <th>Actions</th>                                    
                                    </tr>
                                </thead>   
                                <c:forEach items="${detail_arr}" var="y">
                                    <c:if test="${y.state!='DELETED'}">
                                        <tr>      
                                            <td> ${y.article.article_name} </td>
                                            <td> ${y.detail_ammount} </td>
                                            <td>
                                                <a href='/CR_WB_WebPage/BillDetailDelete?article_id=${y.article_id}&bill_id=${bill.bill_id}&op=update' title='Delete Record' 
                                                   data-toggle='tooltip'><i class="material-icons" data-toggle="tooltip">delete</i></a>
                                            </td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                                <tr>
                                    <td>
                                        <select class="form-control" name="article">
                                            <c:forEach items="${article_arr}" var="x">
                                                <option value="${x.article_id}"> ${x.article_name}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td><input type="text" class="form-control" name="detail_amount"></td>
                                    <td><input type="submit" class="form-control btn-primary" value="Add"></td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="chatbox chatbox22 chatbox--tray">
                    <div class="chatbox__title">
                        <h5><a href="">Support</a></h5>
                        <!--<button class="chatbox__title__tray">
                            <span></span>
                        </button>-->
                        <button class="chatbox__title__close">
                            <span>
                                <svg viewBox="0 0 12 12" width="12px" height="12px">
                                <line stroke="#FFFFFF" x1="11.75" y1="0.25" x2="0.25" y2="11.75"></line>
                                <line stroke="#FFFFFF" x1="11.75" y1="11.75" x2="0.25" y2="0.25"></line>
                                </svg>
                            </span>
                        </button>
                    </div>
                    <div class="chatbox__body">
                        <input type="hidden" id="user_name" value="${user_name}"/>
                        <div class="chatbox__body__message chatbox__body__message--right" id="msg">
                        </div>
                    </div>
                    <div class="panel-footer">
                        <div class="input-group">
                            <input id="msg_input" type="text" class="form-control input-sm chat_set_height" placeholder="Type your message here..." tabindex="0" dir="ltr" spellcheck="false" autocomplete="off" autocorrect="off" autocapitalize="off" contenteditable="true" />
                            <span class="input-group-btn">
                                <button class="btn bt_bg btn-sm" id="btn_chat">
                                    Send</button>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
