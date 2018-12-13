<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing WbCrUser Items</title>
            <link rel="stylesheet" type="text/css" href="/CR_WB_SecondTerm/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing WbCrUser Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No WbCrUser Items Found)<br />" rendered="#{wbCrUser.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{wbCrUser.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{wbCrUser.pagingInfo.firstItem + 1}..#{wbCrUser.pagingInfo.lastItem} of #{wbCrUser.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{wbCrUser.prev}" value="Previous #{wbCrUser.pagingInfo.batchSize}" rendered="#{wbCrUser.pagingInfo.firstItem >= wbCrUser.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{wbCrUser.next}" value="Next #{wbCrUser.pagingInfo.batchSize}" rendered="#{wbCrUser.pagingInfo.lastItem + wbCrUser.pagingInfo.batchSize <= wbCrUser.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{wbCrUser.next}" value="Remaining #{wbCrUser.pagingInfo.itemCount - wbCrUser.pagingInfo.lastItem}"
                                   rendered="#{wbCrUser.pagingInfo.lastItem < wbCrUser.pagingInfo.itemCount && wbCrUser.pagingInfo.lastItem + wbCrUser.pagingInfo.batchSize > wbCrUser.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{wbCrUser.wbCrUserItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="UserId"/>
                            </f:facet>
                            <h:outputText value="#{item.userId}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="UserName"/>
                            </f:facet>
                            <h:outputText value="#{item.userName}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="UserPassword"/>
                            </f:facet>
                            <h:outputText value="#{item.userPassword}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="UserPermission"/>
                            </f:facet>
                            <h:outputText value="#{item.userPermission}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{wbCrUser.detailSetup}">
                                <f:param name="jsfcrud.currentWbCrUser" value="#{jsfcrud_class['model.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][wbCrUser.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{wbCrUser.editSetup}">
                                <f:param name="jsfcrud.currentWbCrUser" value="#{jsfcrud_class['model.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][wbCrUser.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{wbCrUser.remove}">
                                <f:param name="jsfcrud.currentWbCrUser" value="#{jsfcrud_class['model.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][wbCrUser.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{wbCrUser.createSetup}" value="New WbCrUser"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />


            </h:form>
        </body>
    </html>
</f:view>
