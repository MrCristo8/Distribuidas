<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing WbCrArticle</title>
            <link rel="stylesheet" type="text/css" href="/CR_WB_SecondTerm/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing WbCrArticle</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="ArticleId:"/>
                    <h:outputText value="#{wbCrArticle.wbCrArticle.articleId}" title="ArticleId" />
                    <h:outputText value="ArticleName:"/>
                    <h:inputText id="articleName" value="#{wbCrArticle.wbCrArticle.articleName}" title="ArticleName" required="true" requiredMessage="The articleName field is required." />
                    <h:outputText value="ArticlePrice:"/>
                    <h:inputText id="articlePrice" value="#{wbCrArticle.wbCrArticle.articlePrice}" title="ArticlePrice" required="true" requiredMessage="The articlePrice field is required." />
                    <h:outputText value="ArticleStock:"/>
                    <h:inputText id="articleStock" value="#{wbCrArticle.wbCrArticle.articleStock}" title="ArticleStock" required="true" requiredMessage="The articleStock field is required." />
                    <h:outputText value="WbCrBilldetailCollection:"/>
                    <h:outputText escape="false" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getCollectionAsString'][wbCrArticle.wbCrArticle.wbCrBilldetailCollection == null ? jsfcrud_null : wbCrArticle.wbCrArticle.wbCrBilldetailCollection].jsfcrud_invoke}" title="WbCrBilldetailCollection" />
                    <h:outputText value="WbCrStockCollection:"/>
                    <h:outputText escape="false" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getCollectionAsString'][wbCrArticle.wbCrArticle.wbCrStockCollection == null ? jsfcrud_null : wbCrArticle.wbCrArticle.wbCrStockCollection].jsfcrud_invoke}" title="WbCrStockCollection" />

                </h:panelGrid>
                <br />
                <h:commandLink action="#{wbCrArticle.edit}" value="Save">
                    <f:param name="jsfcrud.currentWbCrArticle" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][wbCrArticle.wbCrArticle][wbCrArticle.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{wbCrArticle.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentWbCrArticle" value="#{jsfcrud_class['controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][wbCrArticle.wbCrArticle][wbCrArticle.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{wbCrArticle.listSetup}" value="Show All WbCrArticle Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
