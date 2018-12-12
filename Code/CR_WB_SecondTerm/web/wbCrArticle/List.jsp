<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing WbCrArticle Items</title>
            <link rel="stylesheet" type="text/css" href="/CR_WB_SecondTerm/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing WbCrArticle Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No WbCrArticle Items Found)<br />" rendered="#{wbCrArticle.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{wbCrArticle.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{wbCrArticle.pagingInfo.firstItem + 1}..#{wbCrArticle.pagingInfo.lastItem} of #{wbCrArticle.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{wbCrArticle.prev}" value="Previous #{wbCrArticle.pagingInfo.batchSize}" rendered="#{wbCrArticle.pagingInfo.firstItem >= wbCrArticle.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{wbCrArticle.next}" value="Next #{wbCrArticle.pagingInfo.batchSize}" rendered="#{wbCrArticle.pagingInfo.lastItem + wbCrArticle.pagingInfo.batchSize <= wbCrArticle.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{wbCrArticle.next}" value="Remaining #{wbCrArticle.pagingInfo.itemCount - wbCrArticle.pagingInfo.lastItem}"
                                   rendered="#{wbCrArticle.pagingInfo.lastItem < wbCrArticle.pagingInfo.itemCount && wbCrArticle.pagingInfo.lastItem + wbCrArticle.pagingInfo.batchSize > wbCrArticle.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{wbCrArticle.wbCrArticleItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="ArticleId"/>
                            </f:facet>
                            <h:outputText value="#{item.articleId}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="ArticleName"/>
                            </f:facet>
                            <h:outputText value="#{item.articleName}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="ArticlePrice"/>
                            </f:facet>
                            <h:outputText value="#{item.articlePrice}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="ArticleStock"/>
                            </f:facet>
                            <h:outputText value="#{item.articleStock}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{wbCrArticle.detailSetup}">
                                <f:param name="jsfcrud.currentWbCrArticle" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][wbCrArticle.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{wbCrArticle.editSetup}">
                                <f:param name="jsfcrud.currentWbCrArticle" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][wbCrArticle.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{wbCrArticle.remove}">
                                <f:param name="jsfcrud.currentWbCrArticle" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][wbCrArticle.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{wbCrArticle.createSetup}" value="New WbCrArticle"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />


            </h:form>
        </body>
    </html>
</f:view>
