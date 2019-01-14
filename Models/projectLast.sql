/*==============================================================*/
/* DBMS name:      PostgreSQL 9.x                               */
/* Created on:     12/01/2019 09:42:45 p. m.                    */
/*==============================================================*/


drop index WB_CR_ARTICLE_PK;

drop table WB_CR_ARTICLE;

drop index BILL_IN_CITY_FK;

drop index CLIENT_HAS_BILL_FK;

drop index WB_CR_BILL_PK;

drop table WB_CR_BILL;

drop index WB_CR_BILLDETAIL_FK;

drop index WB_CR_BILLDETAIL2_FK;

drop index WB_CR_BILLDETAIL_PK;

drop table WB_CR_BILLDETAIL;

drop index WB_CR_CITY_PK;

drop table WB_CR_CITY;

drop index WB_CR_CLIENT_PK;

drop table WB_CR_CLIENT;

drop index INVENTORY_MOVEMENT_FK;

drop index WB_CR_INVENTORY_PK;

drop table WB_CR_INVENTORY;

drop index WB_CR_INVENTORY_DETAIL_FK;

drop index WB_CR_INVENTORY_DETAIL2_FK;

drop index WB_CR_INVENTORY_DETAIL_PK;

drop table WB_CR_INVENTORY_DETAIL;

drop index WB_CR_MOVEMENT_PK;

drop table WB_CR_MOVEMENT;

drop index WB_CR_USER_PK;

drop table WB_CR_USER;

/*==============================================================*/
/* Table: WB_CR_ARTICLE                                         */
/*==============================================================*/
create table WB_CR_ARTICLE (
   ARTICLE_ID           INT4                 not null,
   ARTICLE_NAME         VARCHAR(50)          null,
   ARTICLE_PRICE        FLOAT8               null,
   ARTICLE_STOCK        INT4                 null,
   constraint PK_WB_CR_ARTICLE primary key (ARTICLE_ID)
);

/*==============================================================*/
/* Index: WB_CR_ARTICLE_PK                                      */
/*==============================================================*/
create unique index WB_CR_ARTICLE_PK on WB_CR_ARTICLE (
ARTICLE_ID
);

/*==============================================================*/
/* Table: WB_CR_BILL                                            */
/*==============================================================*/
create table WB_CR_BILL (
   BILL_DATE            DATE                 null,
   BILL_ID              INT4                 not null,
   CLIENT_ID            INT4                 not null,
   CITY_ID              INT4                 not null,
   constraint PK_WB_CR_BILL primary key (BILL_ID)
);

/*==============================================================*/
/* Index: WB_CR_BILL_PK                                         */
/*==============================================================*/
create unique index WB_CR_BILL_PK on WB_CR_BILL (
BILL_ID
);

/*==============================================================*/
/* Index: CLIENT_HAS_BILL_FK                                    */
/*==============================================================*/
create  index CLIENT_HAS_BILL_FK on WB_CR_BILL (
CLIENT_ID
);

/*==============================================================*/
/* Index: BILL_IN_CITY_FK                                       */
/*==============================================================*/
create  index BILL_IN_CITY_FK on WB_CR_BILL (
CITY_ID
);

/*==============================================================*/
/* Table: WB_CR_BILLDETAIL                                      */
/*==============================================================*/
create table WB_CR_BILLDETAIL (
   ARTICLE_ID           INT4                 not null,
   BILL_ID              INT4                 not null,
   DETAIL_AMOUNT        INT4                 null,
   constraint PK_WB_CR_BILLDETAIL primary key (ARTICLE_ID, BILL_ID)
);

/*==============================================================*/
/* Index: WB_CR_BILLDETAIL_PK                                   */
/*==============================================================*/
create unique index WB_CR_BILLDETAIL_PK on WB_CR_BILLDETAIL (
ARTICLE_ID,
BILL_ID
);

/*==============================================================*/
/* Index: WB_CR_BILLDETAIL2_FK                                  */
/*==============================================================*/
create  index WB_CR_BILLDETAIL2_FK on WB_CR_BILLDETAIL (
ARTICLE_ID
);

/*==============================================================*/
/* Index: WB_CR_BILLDETAIL_FK                                   */
/*==============================================================*/
create  index WB_CR_BILLDETAIL_FK on WB_CR_BILLDETAIL (
BILL_ID
);

/*==============================================================*/
/* Table: WB_CR_CITY                                            */
/*==============================================================*/
create table WB_CR_CITY (
   CITY_ID              INT4                 not null,
   CITY_NAME            VARCHAR(45)          null,
   constraint PK_WB_CR_CITY primary key (CITY_ID)
);

/*==============================================================*/
/* Index: WB_CR_CITY_PK                                         */
/*==============================================================*/
create unique index WB_CR_CITY_PK on WB_CR_CITY (
CITY_ID
);

/*==============================================================*/
/* Table: WB_CR_CLIENT                                          */
/*==============================================================*/
create table WB_CR_CLIENT (
   CLIENT_ID            INT4                 not null,
   CLIENT_DNI           VARCHAR(10)          null,
   CLIENT_NAME          VARCHAR(50)          null,
   CLIENT_ADDRESS       VARCHAR(200)         null,
   constraint PK_WB_CR_CLIENT primary key (CLIENT_ID)
);

/*==============================================================*/
/* Index: WB_CR_CLIENT_PK                                       */
/*==============================================================*/
create unique index WB_CR_CLIENT_PK on WB_CR_CLIENT (
CLIENT_ID
);

/*==============================================================*/
/* Table: WB_CR_INVENTORY                                       */
/*==============================================================*/
create table WB_CR_INVENTORY (
   INVENTORY_ID         INT4                 not null,
   MOVEMENT_ID          INT4                 not null,
   INVENTORY_DATE       DATE                 null,
   constraint PK_WB_CR_INVENTORY primary key (INVENTORY_ID)
);

/*==============================================================*/
/* Index: WB_CR_INVENTORY_PK                                    */
/*==============================================================*/
create unique index WB_CR_INVENTORY_PK on WB_CR_INVENTORY (
INVENTORY_ID
);

/*==============================================================*/
/* Index: INVENTORY_MOVEMENT_FK                                 */
/*==============================================================*/
create  index INVENTORY_MOVEMENT_FK on WB_CR_INVENTORY (
MOVEMENT_ID
);

/*==============================================================*/
/* Table: WB_CR_INVENTORY_DETAIL                                */
/*==============================================================*/
create table WB_CR_INVENTORY_DETAIL (
   INVENTORY_ID         INT4                 not null,
   ARTICLE_ID           INT4                 not null,
   ARTICLE_AMMOUNT      INT4                 null,
   constraint PK_WB_CR_INVENTORY_DETAIL primary key (INVENTORY_ID, ARTICLE_ID)
);

/*==============================================================*/
/* Index: WB_CR_INVENTORY_DETAIL_PK                             */
/*==============================================================*/
create unique index WB_CR_INVENTORY_DETAIL_PK on WB_CR_INVENTORY_DETAIL (
INVENTORY_ID,
ARTICLE_ID
);

/*==============================================================*/
/* Index: WB_CR_INVENTORY_DETAIL2_FK                            */
/*==============================================================*/
create  index WB_CR_INVENTORY_DETAIL2_FK on WB_CR_INVENTORY_DETAIL (
INVENTORY_ID
);

/*==============================================================*/
/* Index: WB_CR_INVENTORY_DETAIL_FK                             */
/*==============================================================*/
create  index WB_CR_INVENTORY_DETAIL_FK on WB_CR_INVENTORY_DETAIL (
ARTICLE_ID
);

/*==============================================================*/
/* Table: WB_CR_MOVEMENT                                        */
/*==============================================================*/
create table WB_CR_MOVEMENT (
   MOVEMENT_ID          INT4                 not null,
   MOVEMENT_NAME        VARCHAR(30)          null,
   MOVEMENT_DIRECTION   VARCHAR(1)           null,
   constraint PK_WB_CR_MOVEMENT primary key (MOVEMENT_ID)
);

/*==============================================================*/
/* Index: WB_CR_MOVEMENT_PK                                     */
/*==============================================================*/
create unique index WB_CR_MOVEMENT_PK on WB_CR_MOVEMENT (
MOVEMENT_ID
);

/*==============================================================*/
/* Table: WB_CR_USER                                            */
/*==============================================================*/
create table WB_CR_USER (
   USER_ID              INT4                 not null,
   USER_NAME            VARCHAR(50)          null,
   USER_PASSWORD        VARCHAR(60)          null,
   USER_PERMISSION      VARCHAR(200)         null,
   constraint PK_WB_CR_USER primary key (USER_ID)
);

/*==============================================================*/
/* Index: WB_CR_USER_PK                                         */
/*==============================================================*/
create unique index WB_CR_USER_PK on WB_CR_USER (
USER_ID
);

alter table WB_CR_BILL
   add constraint FK_WB_CR_BI_BILL_IN_C_WB_CR_CI foreign key (CITY_ID)
      references WB_CR_CITY (CITY_ID)
      on delete restrict on update restrict;

alter table WB_CR_BILL
   add constraint FK_WB_CR_BI_CLIENT_HA_WB_CR_CL foreign key (CLIENT_ID)
      references WB_CR_CLIENT (CLIENT_ID)
      on delete restrict on update restrict;

alter table WB_CR_BILLDETAIL
   add constraint FK_WB_CR_BI_WB_CR_BIL_WB_CR_BI foreign key (BILL_ID)
      references WB_CR_BILL (BILL_ID)
      on delete restrict on update restrict;

alter table WB_CR_BILLDETAIL
   add constraint FK_WB_CR_BI_WB_CR_BIL_WB_CR_AR foreign key (ARTICLE_ID)
      references WB_CR_ARTICLE (ARTICLE_ID)
      on delete restrict on update restrict;

alter table WB_CR_INVENTORY
   add constraint FK_WB_CR_IN_INVENTORY_WB_CR_MO foreign key (MOVEMENT_ID)
      references WB_CR_MOVEMENT (MOVEMENT_ID)
      on delete restrict on update restrict;

alter table WB_CR_INVENTORY_DETAIL
   add constraint FK_WB_CR_IN_WB_CR_INV_WB_CR_AR foreign key (ARTICLE_ID)
      references WB_CR_ARTICLE (ARTICLE_ID)
      on delete restrict on update restrict;

alter table WB_CR_INVENTORY_DETAIL
   add constraint FK_WB_CR_IN_WB_CR_INV_WB_CR_IN foreign key (INVENTORY_ID)
      references WB_CR_INVENTORY (INVENTORY_ID)
      on delete restrict on update restrict;

