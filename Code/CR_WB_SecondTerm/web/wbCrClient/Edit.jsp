<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing WbCrClient</title>
            <link rel="stylesheet" type="text/css" href="/CR_WB_SecondTerm/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing WbCrClient</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="ClientId:"/>
                    <h:outputText value="#{wbCrClient.wbCrClient.clientId}" title="ClientId" />
                    <h:outputText value="ClientDni:"/>
                    <h:inputText id="clientDni" value="#{wbCrClient.wbCrClient.clientDni}" title="ClientDni" required="true" requiredMessage="The clientDni field is required." />
                    <h:outputText value="ClientName:"/>
                    <h:inputText id="clientName" value="#{wbCrClient.wbCrClient.clientName}" title="ClientName" required="true" requiredMessage="The clientName field is required." />
                    <h:outputText value="ClientAddress:"/>
                    <h:inputText id="clientAddress" value="#{wbCrClient.wbCrClient.clientAddress}" title="ClientAddress" />
                    <h:outputText value="WbCrBillCollection:"/>
                    <h:outputText escape="false" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getCollectionAsString'][wbCrClient.wbCrClient.wbCrBillCollection == null ? jsfcrud_null : wbCrClient.wbCrClient.wbCrBillCollection].jsfcrud_invoke}" title="WbCrBillCollection" />

                </h:panelGrid>
                <br />
                <h:commandLink action="#{wbCrClient.edit}" value="Save">
                    <f:param name="jsfcrud.currentWbCrClient" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][wbCrClient.wbCrClient][wbCrClient.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{wbCrClient.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentWbCrClient" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][wbCrClient.wbCrClient][wbCrClient.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{wbCrClient.listSetup}" value="Show All WbCrClient Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
