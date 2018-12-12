<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New WbCrArticle</title>
            <link rel="stylesheet" type="text/css" href="/CR_WB_SecondTerm/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New WbCrArticle</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{wbCrArticle.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="ArticleId:"/>
                    <h:inputText id="articleId" value="#{wbCrArticle.wbCrArticle.articleId}" title="ArticleId" required="true" requiredMessage="The articleId field is required." />
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
                <h:commandLink action="#{wbCrArticle.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{wbCrArticle.listSetup}" value="Show All WbCrArticle Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
