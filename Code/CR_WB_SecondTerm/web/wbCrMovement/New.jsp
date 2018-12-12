<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New WbCrMovement</title>
            <link rel="stylesheet" type="text/css" href="/CR_WB_SecondTerm/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New WbCrMovement</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{wbCrMovement.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="MovementId:"/>
                    <h:inputText id="movementId" value="#{wbCrMovement.wbCrMovement.movementId}" title="MovementId" required="true" requiredMessage="The movementId field is required." />
                    <h:outputText value="MovementName:"/>
                    <h:inputText id="movementName" value="#{wbCrMovement.wbCrMovement.movementName}" title="MovementName" required="true" requiredMessage="The movementName field is required." />
                    <h:outputText value="MovementDirection:"/>
                    <h:inputText id="movementDirection" value="#{wbCrMovement.wbCrMovement.movementDirection}" title="MovementDirection" required="true" requiredMessage="The movementDirection field is required." />
                    <h:outputText value="WbCrStockCollection:"/>
                    <h:outputText escape="false" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getCollectionAsString'][wbCrMovement.wbCrMovement.wbCrStockCollection == null ? jsfcrud_null : wbCrMovement.wbCrMovement.wbCrStockCollection].jsfcrud_invoke}" title="WbCrStockCollection" />

                </h:panelGrid>
                <br />
                <h:commandLink action="#{wbCrMovement.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{wbCrMovement.listSetup}" value="Show All WbCrMovement Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
