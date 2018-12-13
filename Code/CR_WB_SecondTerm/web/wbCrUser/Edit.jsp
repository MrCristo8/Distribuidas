<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing WbCrUser</title>
            <link rel="stylesheet" type="text/css" href="/CR_WB_SecondTerm/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing WbCrUser</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="UserId:"/>
                    <h:outputText value="#{wbCrUser.wbCrUser.userId}" title="UserId" />
                    <h:outputText value="UserName:"/>
                    <h:inputText id="userName" value="#{wbCrUser.wbCrUser.userName}" title="UserName" required="true" requiredMessage="The userName field is required." />
                    <h:outputText value="UserPassword:"/>
                    <h:inputText id="userPassword" value="#{wbCrUser.wbCrUser.userPassword}" title="UserPassword" required="true" requiredMessage="The userPassword field is required." />
                    <h:outputText value="UserPermission:"/>
                    <h:inputText id="userPermission" value="#{wbCrUser.wbCrUser.userPermission}" title="UserPermission" required="true" requiredMessage="The userPermission field is required." />

                </h:panelGrid>
                <br />
                <h:commandLink action="#{wbCrUser.edit}" value="Save">
                    <f:param name="jsfcrud.currentWbCrUser" value="#{jsfcrud_class['model.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][wbCrUser.wbCrUser][wbCrUser.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{wbCrUser.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentWbCrUser" value="#{jsfcrud_class['model.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][wbCrUser.wbCrUser][wbCrUser.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{wbCrUser.listSetup}" value="Show All WbCrUser Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
