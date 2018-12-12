<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing WbCrMovement</title>
            <link rel="stylesheet" type="text/css" href="/CR_WB_SecondTerm/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing WbCrMovement</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="MovementId:"/>
                    <h:outputText value="#{wbCrMovement.wbCrMovement.movementId}" title="MovementId" />
                    <h:outputText value="MovementName:"/>
                    <h:inputText id="movementName" value="#{wbCrMovement.wbCrMovement.movementName}" title="MovementName" required="true" requiredMessage="The movementName field is required." />
                    <h:outputText value="MovementDirection:"/>
                    <h:inputText id="movementDirection" value="#{wbCrMovement.wbCrMovement.movementDirection}" title="MovementDirection" required="true" requiredMessage="The movementDirection field is required." />
                    <h:outputText value="WbCrStockCollection:"/>
                    <h:outputText escape="false" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getCollectionAsString'][wbCrMovement.wbCrMovement.wbCrStockCollection == null ? jsfcrud_null : wbCrMovement.wbCrMovement.wbCrStockCollection].jsfcrud_invoke}" title="WbCrStockCollection" />

                </h:panelGrid>
                <br />
                <h:commandLink action="#{wbCrMovement.edit}" value="Save">
                    <f:param name="jsfcrud.currentWbCrMovement" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][wbCrMovement.wbCrMovement][wbCrMovement.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{wbCrMovement.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentWbCrMovement" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][wbCrMovement.wbCrMovement][wbCrMovement.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{wbCrMovement.listSetup}" value="Show All WbCrMovement Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
