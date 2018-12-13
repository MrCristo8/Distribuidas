<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%--
    This file is an entry point for JavaServer Faces application.
--%>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Login</title>
<link rel="stylesheet" type="text/css" href="/CR_WB_SecondTerm/faces/jsfcrud.css" />
            <link rel="stylesheet" href="style.css">
            <link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
            <script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
            <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        </head>
        <body>
            <div class="container" style="margin-top:40px">
                <h1><h:outputText value="JavaServer Faces"/></h1>
                <h:form>
                    <h:commandLink action="#{wbCrUser.listSetup}" value="Show All WbCrUser Items"/>
                </h:form>

                <h:form>
                    <h:commandLink action="#{wbCrMovement.listSetup}" value="Show All WbCrMovement Items"/>
                </h:form>

                <h:form>
                    <h:commandLink action="#{wbCrClient.listSetup}" value="Show All WbCrClient Items"/>
                </h:form>

                <h:form>
                    <h:commandLink action="#{wbCrCity.listSetup}" value="Show All WbCrCity Items"/>
                </h:form>

                <h:form>
                    <h:commandLink action="#{wbCrArticle.listSetup}" value="Show All WbCrArticle Items"/>
                </h:form>
                <div class="row">
                    <div class="col-sm-6 col-md-4 col-md-offset-4">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <strong> Sign in to continue</strong>
                            </div>
                            <div class="panel-body">
                                <h:form onsubmit="#{wbCrUser.login()}">
                                    <fieldset>
                                        <div class="row">
                                            <div class="center-block">
                                                <img class="profile-img"
                                                     src="https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=120" alt="">
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-12 col-md-10  col-md-offset-1 ">
                                                <div class="form-group">
                                                    <div class="input-group">
                                                        <span class="input-group-addon">
                                                            <i class="glyphicon glyphicon-user"></i>
                                                        </span> 
                                                        <h:inputText id="username" value="#{wbCrUser.wbCrUser.userName}" />
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="input-group">
                                                        <span class="input-group-addon">
                                                            <i class="glyphicon glyphicon-lock"></i>
                                                        </span>
                                                        <h:inputSecret id="password" value="#{wbCrUser.wbCrUser.userPassword}" style="form-control"/>
                                                    </div>
                                                </div>
                                                <div class="form-group" >
                                                    <input type="submit" class="btn btn-lg btn-primary btn-block" value="Sign in">
                                                </div>
                                                <a href="/CR_WB_Project/UserCreation"> Sign up </a>
                                            </div>
                                        </div>
                                    </fieldset>
                                </h:form>
                            </div>                    
                        </div>
                    </div>
                </div>
            </div>


        </body>
    </html>
</f:view>
