<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>WbCrMovement Detail</title>
            <link rel="stylesheet" type="text/css" href="/CR_WB_SecondTerm/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>WbCrMovement Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="MovementId:"/>
                    <h:outputText value="#{wbCrMovement.wbCrMovement.movementId}" title="MovementId" />
                    <h:outputText value="MovementName:"/>
                    <h:outputText value="#{wbCrMovement.wbCrMovement.movementName}" title="MovementName" />
                    <h:outputText value="MovementDirection:"/>
                    <h:outputText value="#{wbCrMovement.wbCrMovement.movementDirection}" title="MovementDirection" />

                    <h:outputText value="WbCrStockCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty wbCrMovement.wbCrMovement.wbCrStockCollection}" value="(No Items)"/>
                        <h:dataTable value="#{wbCrMovement.wbCrMovement.wbCrStockCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty wbCrMovement.wbCrMovement.wbCrStockCollection}">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="StrockNumber"/>
                                </f:facet>
                                <h:outputText value="#{item.strockNumber}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="StockDate"/>
                                </f:facet>
                                <h:outputText value="#{item.stockDate}">
                                    <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                                </h:outputText>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Stock"/>
                                </f:facet>
                                <h:outputText value="#{item.stock}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="WbCrArticle"/>
                                </f:facet>
                                <h:outputText value="#{item.wbCrArticle}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="WbCrMovement"/>
                                </f:facet>
                                <h:outputText value="#{item.wbCrMovement}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText escape="false" value="&nbsp;"/>
                                </f:facet>
                                <h:commandLink value="Show" action="#{wbCrStock.detailSetup}">
                                    <f:param name="jsfcrud.currentWbCrMovement" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][wbCrMovement.wbCrMovement][wbCrMovement.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentWbCrStock" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][wbCrStock.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="wbCrMovement" />
                                    <f:param name="jsfcrud.relatedControllerType" value="controller.WbCrMovementController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{wbCrStock.editSetup}">
                                    <f:param name="jsfcrud.currentWbCrMovement" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][wbCrMovement.wbCrMovement][wbCrMovement.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentWbCrStock" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][wbCrStock.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="wbCrMovement" />
                                    <f:param name="jsfcrud.relatedControllerType" value="controller.WbCrMovementController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{wbCrStock.destroy}">
                                    <f:param name="jsfcrud.currentWbCrMovement" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][wbCrMovement.wbCrMovement][wbCrMovement.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentWbCrStock" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][wbCrStock.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="wbCrMovement" />
                                    <f:param name="jsfcrud.relatedControllerType" value="controller.WbCrMovementController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{wbCrMovement.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentWbCrMovement" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][wbCrMovement.wbCrMovement][wbCrMovement.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{wbCrMovement.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentWbCrMovement" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][wbCrMovement.wbCrMovement][wbCrMovement.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{wbCrMovement.createSetup}" value="New WbCrMovement" />
                <br />
                <h:commandLink action="#{wbCrMovement.listSetup}" value="Show All WbCrMovement Items"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
