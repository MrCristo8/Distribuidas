<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing WbCrCity Items</title>
            <link rel="stylesheet" type="text/css" href="/CR_WB_SecondTerm/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing WbCrCity Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No WbCrCity Items Found)<br />" rendered="#{wbCrCity.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{wbCrCity.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{wbCrCity.pagingInfo.firstItem + 1}..#{wbCrCity.pagingInfo.lastItem} of #{wbCrCity.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{wbCrCity.prev}" value="Previous #{wbCrCity.pagingInfo.batchSize}" rendered="#{wbCrCity.pagingInfo.firstItem >= wbCrCity.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{wbCrCity.next}" value="Next #{wbCrCity.pagingInfo.batchSize}" rendered="#{wbCrCity.pagingInfo.lastItem + wbCrCity.pagingInfo.batchSize <= wbCrCity.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{wbCrCity.next}" value="Remaining #{wbCrCity.pagingInfo.itemCount - wbCrCity.pagingInfo.lastItem}"
                                   rendered="#{wbCrCity.pagingInfo.lastItem < wbCrCity.pagingInfo.itemCount && wbCrCity.pagingInfo.lastItem + wbCrCity.pagingInfo.batchSize > wbCrCity.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{wbCrCity.wbCrCityItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="CityId"/>
                            </f:facet>
                            <h:outputText value="#{item.cityId}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="CityName"/>
                            </f:facet>
                            <h:outputText value="#{item.cityName}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{wbCrCity.detailSetup}">
                                <f:param name="jsfcrud.currentWbCrCity" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][wbCrCity.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{wbCrCity.editSetup}">
                                <f:param name="jsfcrud.currentWbCrCity" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][wbCrCity.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{wbCrCity.remove}">
                                <f:param name="jsfcrud.currentWbCrCity" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][wbCrCity.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{wbCrCity.createSetup}" value="New WbCrCity"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />


            </h:form>
        </body>
    </html>
</f:view>
