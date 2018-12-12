<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New WbCrCity</title>
            <link rel="stylesheet" type="text/css" href="/CR_WB_SecondTerm/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New WbCrCity</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{wbCrCity.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="CityId:"/>
                    <h:inputText id="cityId" value="#{wbCrCity.wbCrCity.cityId}" title="CityId" required="true" requiredMessage="The cityId field is required." />
                    <h:outputText value="CityName:"/>
                    <h:inputText id="cityName" value="#{wbCrCity.wbCrCity.cityName}" title="CityName" required="true" requiredMessage="The cityName field is required." />
                    <h:outputText value="WbCrBillCollection:"/>
                    <h:outputText escape="false" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getCollectionAsString'][wbCrCity.wbCrCity.wbCrBillCollection == null ? jsfcrud_null : wbCrCity.wbCrCity.wbCrBillCollection].jsfcrud_invoke}" title="WbCrBillCollection" />

                </h:panelGrid>
                <br />
                <h:commandLink action="#{wbCrCity.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{wbCrCity.listSetup}" value="Show All WbCrCity Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
