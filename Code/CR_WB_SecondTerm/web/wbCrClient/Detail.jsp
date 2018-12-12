<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>WbCrClient Detail</title>
            <link rel="stylesheet" type="text/css" href="/CR_WB_SecondTerm/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>WbCrClient Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="ClientId:"/>
                    <h:outputText value="#{wbCrClient.wbCrClient.clientId}" title="ClientId" />
                    <h:outputText value="ClientDni:"/>
                    <h:outputText value="#{wbCrClient.wbCrClient.clientDni}" title="ClientDni" />
                    <h:outputText value="ClientName:"/>
                    <h:outputText value="#{wbCrClient.wbCrClient.clientName}" title="ClientName" />
                    <h:outputText value="ClientAddress:"/>
                    <h:outputText value="#{wbCrClient.wbCrClient.clientAddress}" title="ClientAddress" />

                    <h:outputText value="WbCrBillCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty wbCrClient.wbCrClient.wbCrBillCollection}" value="(No Items)"/>
                        <h:dataTable value="#{wbCrClient.wbCrClient.wbCrBillCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty wbCrClient.wbCrClient.wbCrBillCollection}">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="BillId"/>
                                </f:facet>
                                <h:outputText value="#{item.wbCrBillPK.billId}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="BillDate"/>
                                </f:facet>
                                <h:outputText value="#{item.billDate}">
                                    <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                                </h:outputText>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="WbCrCity"/>
                                </f:facet>
                                <h:outputText value="#{item.wbCrCity}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="WbCrClient"/>
                                </f:facet>
                                <h:outputText value="#{item.wbCrClient}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText escape="false" value="&nbsp;"/>
                                </f:facet>
                                <h:commandLink value="Show" action="#{wbCrBill.detailSetup}">
                                    <f:param name="jsfcrud.currentWbCrClient" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][wbCrClient.wbCrClient][wbCrClient.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentWbCrBill" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][wbCrBill.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="wbCrClient" />
                                    <f:param name="jsfcrud.relatedControllerType" value="controller.WbCrClientController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{wbCrBill.editSetup}">
                                    <f:param name="jsfcrud.currentWbCrClient" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][wbCrClient.wbCrClient][wbCrClient.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentWbCrBill" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][wbCrBill.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="wbCrClient" />
                                    <f:param name="jsfcrud.relatedControllerType" value="controller.WbCrClientController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{wbCrBill.destroy}">
                                    <f:param name="jsfcrud.currentWbCrClient" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][wbCrClient.wbCrClient][wbCrClient.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentWbCrBill" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][wbCrBill.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="wbCrClient" />
                                    <f:param name="jsfcrud.relatedControllerType" value="controller.WbCrClientController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{wbCrClient.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentWbCrClient" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][wbCrClient.wbCrClient][wbCrClient.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{wbCrClient.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentWbCrClient" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][wbCrClient.wbCrClient][wbCrClient.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{wbCrClient.createSetup}" value="New WbCrClient" />
                <br />
                <h:commandLink action="#{wbCrClient.listSetup}" value="Show All WbCrClient Items"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
