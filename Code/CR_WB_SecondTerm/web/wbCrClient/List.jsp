<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing WbCrClient Items</title>
            <link rel="stylesheet" type="text/css" href="/CR_WB_SecondTerm/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing WbCrClient Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No WbCrClient Items Found)<br />" rendered="#{wbCrClient.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{wbCrClient.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{wbCrClient.pagingInfo.firstItem + 1}..#{wbCrClient.pagingInfo.lastItem} of #{wbCrClient.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{wbCrClient.prev}" value="Previous #{wbCrClient.pagingInfo.batchSize}" rendered="#{wbCrClient.pagingInfo.firstItem >= wbCrClient.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{wbCrClient.next}" value="Next #{wbCrClient.pagingInfo.batchSize}" rendered="#{wbCrClient.pagingInfo.lastItem + wbCrClient.pagingInfo.batchSize <= wbCrClient.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{wbCrClient.next}" value="Remaining #{wbCrClient.pagingInfo.itemCount - wbCrClient.pagingInfo.lastItem}"
                                   rendered="#{wbCrClient.pagingInfo.lastItem < wbCrClient.pagingInfo.itemCount && wbCrClient.pagingInfo.lastItem + wbCrClient.pagingInfo.batchSize > wbCrClient.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{wbCrClient.wbCrClientItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="ClientId"/>
                            </f:facet>
                            <h:outputText value="#{item.clientId}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="ClientDni"/>
                            </f:facet>
                            <h:outputText value="#{item.clientDni}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="ClientName"/>
                            </f:facet>
                            <h:outputText value="#{item.clientName}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="ClientAddress"/>
                            </f:facet>
                            <h:outputText value="#{item.clientAddress}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{wbCrClient.detailSetup}">
                                <f:param name="jsfcrud.currentWbCrClient" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][wbCrClient.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{wbCrClient.editSetup}">
                                <f:param name="jsfcrud.currentWbCrClient" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][wbCrClient.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{wbCrClient.remove}">
                                <f:param name="jsfcrud.currentWbCrClient" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][wbCrClient.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{wbCrClient.createSetup}" value="New WbCrClient"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />


            </h:form>
        </body>
    </html>
</f:view>
