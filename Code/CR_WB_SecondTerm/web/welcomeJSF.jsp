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
            <title>JSP Page</title>
<link rel="stylesheet" type="text/css" href="/CR_WB_SecondTerm/faces/jsfcrud.css" />
        </head>
        <body>
            <h1><h:outputText value="JavaServer Faces"/></h1>
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

        </body>
    </html>
</f:view>
