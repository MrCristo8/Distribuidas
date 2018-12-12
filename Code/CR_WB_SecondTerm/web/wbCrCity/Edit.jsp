<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing WbCrCity</title>
            <link rel="stylesheet" type="text/css" href="/CR_WB_SecondTerm/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing WbCrCity</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="CityId:"/>
                    <h:outputText value="#{wbCrCity.wbCrCity.cityId}" title="CityId" />
                    <h:outputText value="CityName:"/>
                    <h:inputText id="cityName" value="#{wbCrCity.wbCrCity.cityName}" title="CityName" required="true" requiredMessage="The cityName field is required." />
                    <h:outputText value="WbCrBillCollection:"/>
                    <h:outputText escape="false" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getCollectionAsString'][wbCrCity.wbCrCity.wbCrBillCollection == null ? jsfcrud_null : wbCrCity.wbCrCity.wbCrBillCollection].jsfcrud_invoke}" title="WbCrBillCollection" />

                </h:panelGrid>
                <br />
                <h:commandLink action="#{wbCrCity.edit}" value="Save">
                    <f:param name="jsfcrud.currentWbCrCity" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][wbCrCity.wbCrCity][wbCrCity.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{wbCrCity.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentWbCrCity" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][wbCrCity.wbCrCity][wbCrCity.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{wbCrCity.listSetup}" value="Show All WbCrCity Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
