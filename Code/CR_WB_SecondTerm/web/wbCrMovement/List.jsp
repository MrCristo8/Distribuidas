<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing WbCrMovement Items</title>
            <link rel="stylesheet" type="text/css" href="/CR_WB_SecondTerm/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing WbCrMovement Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No WbCrMovement Items Found)<br />" rendered="#{wbCrMovement.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{wbCrMovement.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{wbCrMovement.pagingInfo.firstItem + 1}..#{wbCrMovement.pagingInfo.lastItem} of #{wbCrMovement.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{wbCrMovement.prev}" value="Previous #{wbCrMovement.pagingInfo.batchSize}" rendered="#{wbCrMovement.pagingInfo.firstItem >= wbCrMovement.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{wbCrMovement.next}" value="Next #{wbCrMovement.pagingInfo.batchSize}" rendered="#{wbCrMovement.pagingInfo.lastItem + wbCrMovement.pagingInfo.batchSize <= wbCrMovement.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{wbCrMovement.next}" value="Remaining #{wbCrMovement.pagingInfo.itemCount - wbCrMovement.pagingInfo.lastItem}"
                                   rendered="#{wbCrMovement.pagingInfo.lastItem < wbCrMovement.pagingInfo.itemCount && wbCrMovement.pagingInfo.lastItem + wbCrMovement.pagingInfo.batchSize > wbCrMovement.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{wbCrMovement.wbCrMovementItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="MovementId"/>
                            </f:facet>
                            <h:outputText value="#{item.movementId}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="MovementName"/>
                            </f:facet>
                            <h:outputText value="#{item.movementName}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="MovementDirection"/>
                            </f:facet>
                            <h:outputText value="#{item.movementDirection}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{wbCrMovement.detailSetup}">
                                <f:param name="jsfcrud.currentWbCrMovement" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][wbCrMovement.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{wbCrMovement.editSetup}">
                                <f:param name="jsfcrud.currentWbCrMovement" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][wbCrMovement.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{wbCrMovement.remove}">
                                <f:param name="jsfcrud.currentWbCrMovement" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][wbCrMovement.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{wbCrMovement.createSetup}" value="New WbCrMovement"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />


            </h:form>
        </body>
    </html>
</f:view>
