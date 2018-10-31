/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     30/10/2018 06:40:13 p. m.                    */
/*==============================================================*/


alter table CR_WB_BILL
   drop constraint FK_CR_WB_BI_BILL_IN_C_CR_WB_CI;

alter table CR_WB_BILL
   drop constraint FK_CR_WB_BI_CLIENT_HA_CR_WB_CL;

alter table CR_WB_BILL_DETAIL
   drop constraint FK_CR_WB_BI_BILL_DETA_CR_WB_AR;

alter table CR_WB_BILL_DETAIL
   drop constraint FK_CR_WB_BI_BILL_DETA_CR_WB_BI;

alter table CR_WB_MOVEMENT
   drop constraint FK_CR_WB_MO_HISTORIC__CR_WB_AR;

drop table CR_WB_ARTICLE cascade constraints;

drop index BILL_IN_CITY_FK;

drop index CLIENT_HAS_BILL_FK;

drop table CR_WB_BILL cascade constraints;

drop index CSRM_WABH_BILL_DETAIL_FK;

drop index CSRM_WABH_BILL_DETAIL2_FK;

drop table CR_WB_BILL_DETAIL cascade constraints;

drop table CR_WB_CITY cascade constraints;

drop table CR_WB_CLIENT cascade constraints;

drop index HISTORIC_TRANSACTIONS_FK;

drop table CR_WB_MOVEMENT cascade constraints;

/*==============================================================*/
/* Table: CR_WB_ARTICLE                                         */
/*==============================================================*/
create table CR_WB_ARTICLE 
(
   ARTICLE_ID           INTEGER              not null,
   ARTICLE_NAME         VARCHAR2(50)         not null,
   ARTICLE_PRICE        FLOAT                not null,
   ARTICLE_STOCK        INTEGER              not null,
   constraint PK_CR_WB_ARTICLE primary key (ARTICLE_ID)
);

/*==============================================================*/
/* Table: CR_WB_BILL                                            */
/*==============================================================*/
create table CR_WB_BILL 
(
   BILL_ID              INTEGER              not null,
   CLIENT_ID            INTEGER              not null,
   CITY_ID              INTEGER              not null,
   BILL_DATE            DATE                 not null,
   constraint PK_CR_WB_BILL primary key (BILL_ID)
);

/*==============================================================*/
/* Index: CLIENT_HAS_BILL_FK                                    */
/*==============================================================*/
create index CLIENT_HAS_BILL_FK on CR_WB_BILL (
   CLIENT_ID ASC
);

/*==============================================================*/
/* Index: BILL_IN_CITY_FK                                       */
/*==============================================================*/
create index BILL_IN_CITY_FK on CR_WB_BILL (
   CITY_ID ASC
);

/*==============================================================*/
/* Table: CR_WB_BILL_DETAIL                                     */
/*==============================================================*/
create table CR_WB_BILL_DETAIL 
(
   ARTICLE_ID           INTEGER              not null,
   BILL_ID              INTEGER              not null,
   DETAIL_AMOUNT        INTEGER,
   constraint PK_CR_WB_BILL_DETAIL primary key (ARTICLE_ID, BILL_ID)
);

/*==============================================================*/
/* Index: CSRM_WABH_BILL_DETAIL2_FK                             */
/*==============================================================*/
create index CSRM_WABH_BILL_DETAIL2_FK on CR_WB_BILL_DETAIL (
   ARTICLE_ID ASC
);

/*==============================================================*/
/* Index: CSRM_WABH_BILL_DETAIL_FK                              */
/*==============================================================*/
create index CSRM_WABH_BILL_DETAIL_FK on CR_WB_BILL_DETAIL (
   BILL_ID ASC
);

/*==============================================================*/
/* Table: CR_WB_CITY                                            */
/*==============================================================*/
create table CR_WB_CITY 
(
   CITY_ID              INTEGER              not null,
   CITY_NAME            VARCHAR2(50)         not null,
   constraint PK_CR_WB_CITY primary key (CITY_ID)
);

/*==============================================================*/
/* Table: CR_WB_CLIENT                                          */
/*==============================================================*/
create table CR_WB_CLIENT 
(
   CLIENT_ID            INTEGER              not null,
   CLIENT_DNI           VARCHAR2(10)         not null,
   CLIENT_NAME          VARCHAR2(70)         not null,
   CLIENT_ADDRESS       VARCHAR2(150),
   constraint PK_CR_WB_CLIENT primary key (CLIENT_ID)
);

/*==============================================================*/
/* Table: CR_WB_MOVEMENT                                        */
/*==============================================================*/
create table CR_WB_MOVEMENT 
(
   MOVEMENT_ID          INTEGER              not null,
   ARTICLE_ID           INTEGER              not null,
   MOVEMENT_NAME        VARCHAR2(20)         not null,
   MOVEMENT_DATE        DATE                 not null,
   MOVEMENT_AMMOUNT     INTEGER,
   MOVEMENT_DIRECTION   VARCHAR2(1),
   constraint PK_CR_WB_MOVEMENT primary key (MOVEMENT_ID)
);

/*==============================================================*/
/* Index: HISTORIC_TRANSACTIONS_FK                              */
/*==============================================================*/
create index HISTORIC_TRANSACTIONS_FK on CR_WB_MOVEMENT (
   ARTICLE_ID ASC
);

alter table CR_WB_BILL
   add constraint FK_CR_WB_BI_BILL_IN_C_CR_WB_CI foreign key (CITY_ID)
      references CR_WB_CITY (CITY_ID);

alter table CR_WB_BILL
   add constraint FK_CR_WB_BI_CLIENT_HA_CR_WB_CL foreign key (CLIENT_ID)
      references CR_WB_CLIENT (CLIENT_ID);

alter table CR_WB_BILL_DETAIL
   add constraint FK_CR_WB_BI_BILL_DETA_CR_WB_AR foreign key (ARTICLE_ID)
      references CR_WB_ARTICLE (ARTICLE_ID);

alter table CR_WB_BILL_DETAIL
   add constraint FK_CR_WB_BI_BILL_DETA_CR_WB_BI foreign key (BILL_ID)
      references CR_WB_BILL (BILL_ID);

alter table CR_WB_MOVEMENT
   add constraint FK_CR_WB_MO_HISTORIC__CR_WB_AR foreign key (ARTICLE_ID)
      references CR_WB_ARTICLE (ARTICLE_ID);

