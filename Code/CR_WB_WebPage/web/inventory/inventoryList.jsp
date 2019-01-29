<%-- 
    Document   : inventoryList
    Created on : Jan 27, 2019, 10:23:28 AM
    Author     : csrm1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventory</title>
        <link rel="stylesheet" type="text/css" href="http://localhost:8080/CR_WB_WebPage/css/style.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons"/>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
        <script src="http://localhost:8080/CR_WB_WebPage/js/script.js" type="text/javascript"></script>
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
                                <a class="dropdown-item" href="/CR_WB_WebPage/ArticleByClient.jsp">Article By Client</a>
                                <a class="dropdown-item" href="/CR_WB_WebPage/ArticleByMovement.jsp">Article By Movement</a>
                                <a class="dropdown-item" href="/CR_WB_WebPage/BalancePerArticle.jsp">Balance Per Article</a>
                                <a class="dropdown-item" href="/CR_WB_WebPage/SalesPerCity.jsp">Sales Per City</a>
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
                            <h2 class="pull-left">Inventory Details</h2>
                            <form action="InventorySave" method="post">
                                <input type="submit" class="btn btn-success float-right" value="Save Changes">
                            </form>
                        </div>
                        <br><br>
                        <table class='table table-bordered table-striped'>
                            <thead>
                                <tr>
                                    <th>Inventory ID</th>
                                    <th>Inventory Date</th>   
                                    <th>Movement ID</th>
                                    <th>Movement Name</th>     
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <c:forEach items="${objList}" var="x">
                                <c:if test="${x.state!='DELETED'}">
                                    <tr>      
                                        <td> ${x.inventory_id} </td>
                                        <td> ${x.inventory_date} </td>
                                        <td> ${x.movement.movement_name} </td> 
                                        <td> ${x.movement.movement_direction} </td>
                                        <td>   
                                            <a href='/CR_WB_WebPage/InventoryView?inventory_id=${x.inventory_id}' title='View Record' 
                                               data-toggle='tooltip'><i class="material-icons" data-toggle="tooltip">visibility</i></a>
                                            <a href='/CR_WB_WebPage/InventoryUpdate?inventory_id=${x.inventory_id}' title='Update Record' 
                                               data-toggle='tooltip'><i class="material-icons" data-toggle="tooltip">edit</i></a>
                                            <a href='/CR_WB_WebPage/InventoryDelete?inventory_id=${x.inventory_id}' title='Delete Record' 
                                               data-toggle='tooltip'><i class="material-icons" data-toggle="tooltip">delete</i></a>
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach> 
                        </table>                        
                        <a href="/CR_WB_WebPage/InventoryInsert" class="btn btn-primary float-right" role="button" aria-pressed="true">Add new inventory</a>
                        <br>                        
                        <hr>
                        <h2 class=pull-left>Inventory Filtered List</h2>
                        <br>
                        <form action="InventoryServlet" method="post">
                            <div class ="form-row">  
                                <div class="col-md-11">
                                    <input type="text" class="form-control" placeholder="Search Parammeter" name = "search_string">
                                </div>
                                <div class="col-md-1">
                                    <button type="submit" class="btn btn-primary">Search</button>        
                                </div>
                            </div>
                        </form>
                        <br>
                        <table class='table table-bordered table-striped'>
                            <thead>
                                <tr>
                                    <th>Inventory ID</th>
                                    <th>Inventory Date</th>   
                                    <th>Movement Name</th>
                                    <th>Movement Direction</th>  
                                </tr>
                            </thead>   
                            <c:forEach items="${objSearchList}" var="y">
                                <c:if test="${y.state!='DELETED'}">
                                    <tr>      
                                        <td> ${y.inventory_id} </td>
                                        <td> ${y.inventory_date} </td>
                                        <td> ${y.movement.movement_name} </td> 
                                        <td> ${y.movement.movement_direction} </td>                                        
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </table>                        
                    </div>
                </div>        
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="chatbox chatbox22 chatbox--tray">
                    <div class="chatbox__title">
                        <h5><a href="">Support</a></h5>
                        <button class="chatbox__title__close">
                            <span>
                                <svg viewBox="0 0 12 12" width="12px" height="12px">
                                <line stroke="#FFFFFF" x1="11.75" y1="0.25" x2="0.25" y2="11.75"></line>
                                <line stroke="#FFFFFF" x1="11.75" y1="11.75" x2="0.25" y2="0.25"></line>
                                </svg>
                            </span>
                        </button>
                    </div>
                    <div class="chatbox__body"id="msg">
                        <input type="hidden" id="user_name" value="${user_name}"/>
                        <div class="chatbox__body__message chatbox__body__message--right" >
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