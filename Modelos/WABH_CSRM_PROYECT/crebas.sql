/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2016                    */
/* Created on:     08/12/2018 01:00:00 a. m.                    */
/*==============================================================*/


if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('WB_CR_BILL') and o.name = 'FK_WB_CR_BI_BILL_IN_C_WB_CR_CI')
alter table WB_CR_BILL
   drop constraint FK_WB_CR_BI_BILL_IN_C_WB_CR_CI
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('WB_CR_BILL') and o.name = 'FK_WB_CR_BI_CLIENT_HA_WB_CR_CL')
alter table WB_CR_BILL
   drop constraint FK_WB_CR_BI_CLIENT_HA_WB_CR_CL
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('WB_CR_BILLDETAIL') and o.name = 'FK_WB_CR_BI_WB_CR_BIL_WB_CR_AR')
alter table WB_CR_BILLDETAIL
   drop constraint FK_WB_CR_BI_WB_CR_BIL_WB_CR_AR
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('WB_CR_BILLDETAIL') and o.name = 'FK_WB_CR_BI_WB_CR_BIL_WB_CR_BI')
alter table WB_CR_BILLDETAIL
   drop constraint FK_WB_CR_BI_WB_CR_BIL_WB_CR_BI
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('WB_CR_STOCK') and o.name = 'FK_WB_CR_ST_WB_CR_STO_WB_CR_MO')
alter table WB_CR_STOCK
   drop constraint FK_WB_CR_ST_WB_CR_STO_WB_CR_MO
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('WB_CR_STOCK') and o.name = 'FK_WB_CR_ST_WB_CR_STO_WB_CR_AR')
alter table WB_CR_STOCK
   drop constraint FK_WB_CR_ST_WB_CR_STO_WB_CR_AR
go

if exists (select 1
            from  sysobjects
           where  id = object_id('WB_CR_ARTICLE')
            and   type = 'U')
   drop table WB_CR_ARTICLE
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('WB_CR_BILL')
            and   name  = 'BILL_IN_CITY_FK'
            and   indid > 0
            and   indid < 255)
   drop index WB_CR_BILL.BILL_IN_CITY_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('WB_CR_BILL')
            and   name  = 'CLIENT_HAS_BILL_FK'
            and   indid > 0
            and   indid < 255)
   drop index WB_CR_BILL.CLIENT_HAS_BILL_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('WB_CR_BILL')
            and   type = 'U')
   drop table WB_CR_BILL
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('WB_CR_BILLDETAIL')
            and   name  = 'WB_CR_BILLDETAIL_FK'
            and   indid > 0
            and   indid < 255)
   drop index WB_CR_BILLDETAIL.WB_CR_BILLDETAIL_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('WB_CR_BILLDETAIL')
            and   name  = 'WB_CR_BILLDETAIL2_FK'
            and   indid > 0
            and   indid < 255)
   drop index WB_CR_BILLDETAIL.WB_CR_BILLDETAIL2_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('WB_CR_BILLDETAIL')
            and   type = 'U')
   drop table WB_CR_BILLDETAIL
go

if exists (select 1
            from  sysobjects
           where  id = object_id('WB_CR_CITY')
            and   type = 'U')
   drop table WB_CR_CITY
go

if exists (select 1
            from  sysobjects
           where  id = object_id('WB_CR_CLIENT')
            and   type = 'U')
   drop table WB_CR_CLIENT
go

if exists (select 1
            from  sysobjects
           where  id = object_id('WB_CR_MOVEMENT')
            and   type = 'U')
   drop table WB_CR_MOVEMENT
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('WB_CR_STOCK')
            and   name  = 'WB_CR_STOCK_FK'
            and   indid > 0
            and   indid < 255)
   drop index WB_CR_STOCK.WB_CR_STOCK_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('WB_CR_STOCK')
            and   name  = 'WB_CR_STOCK2_FK'
            and   indid > 0
            and   indid < 255)
   drop index WB_CR_STOCK.WB_CR_STOCK2_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('WB_CR_STOCK')
            and   type = 'U')
   drop table WB_CR_STOCK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('WB_CR_USER')
            and   type = 'U')
   drop table WB_CR_USER
go

/*==============================================================*/
/* Table: WB_CR_ARTICLE                                         */
/*==============================================================*/
create table WB_CR_ARTICLE (
   ARTICLE_ID           int                  not null,
   ARTICLE_NAME         varchar(50)          not null,
   ARTICLE_PRICE        float                not null,
   ARTICLE_STOCK        int                  not null,
   constraint PK_WB_CR_ARTICLE primary key (ARTICLE_ID)
)
go

/*==============================================================*/
/* Table: WB_CR_BILL                                            */
/*==============================================================*/
create table WB_CR_BILL (
   CITY_ID              int                  not null,
   CLIENT_ID            int                  not null,
   BILL_ID              int                  not null,
   BILL_DATE            datetime             not null,
   constraint PK_WB_CR_BILL primary key (CITY_ID, CLIENT_ID, BILL_ID)
)
go

/*==============================================================*/
/* Index: CLIENT_HAS_BILL_FK                                    */
/*==============================================================*/




create nonclustered index CLIENT_HAS_BILL_FK on WB_CR_BILL (CLIENT_ID ASC)
go

/*==============================================================*/
/* Index: BILL_IN_CITY_FK                                       */
/*==============================================================*/




create nonclustered index BILL_IN_CITY_FK on WB_CR_BILL (CITY_ID ASC)
go

/*==============================================================*/
/* Table: WB_CR_BILLDETAIL                                      */
/*==============================================================*/
create table WB_CR_BILLDETAIL (
   CITY_ID              int                  not null,
   CLIENT_ID            int                  not null,
   BILL_ID              int                  not null,
   ARTICLE_ID           int                  not null,
   DETAIL_AMOUNT        int                  not null,
   constraint PK_WB_CR_BILLDETAIL primary key (CITY_ID, CLIENT_ID, BILL_ID, ARTICLE_ID)
)
go

/*==============================================================*/
/* Index: WB_CR_BILLDETAIL2_FK                                  */
/*==============================================================*/




create nonclustered index WB_CR_BILLDETAIL2_FK on WB_CR_BILLDETAIL (CITY_ID ASC,
  CLIENT_ID ASC,
  BILL_ID ASC)
go

/*==============================================================*/
/* Index: WB_CR_BILLDETAIL_FK                                   */
/*==============================================================*/




create nonclustered index WB_CR_BILLDETAIL_FK on WB_CR_BILLDETAIL (ARTICLE_ID ASC)
go

/*==============================================================*/
/* Table: WB_CR_CITY                                            */
/*==============================================================*/
create table WB_CR_CITY (
   CITY_ID              int                  not null,
   CITY_NAME            varchar(45)          not null,
   constraint PK_WB_CR_CITY primary key (CITY_ID)
)
go

/*==============================================================*/
/* Table: WB_CR_CLIENT                                          */
/*==============================================================*/
create table WB_CR_CLIENT (
   CLIENT_ID            int                  not null,
   CLIENT_DNI           varchar(10)          not null,
   CLIENT_NAME          varchar(50)          not null,
   CLIENT_ADDRESS       varchar(200)         null,
   constraint PK_WB_CR_CLIENT primary key (CLIENT_ID)
)
go

/*==============================================================*/
/* Table: WB_CR_MOVEMENT                                        */
/*==============================================================*/
create table WB_CR_MOVEMENT (
   MOVEMENT_ID          int                  not null,
   MOVEMENT_NAME        varchar(30)          not null,
   MOVEMENT_DIRECTION   varchar(1)           not null,
   constraint PK_WB_CR_MOVEMENT primary key (MOVEMENT_ID)
)
go

/*==============================================================*/
/* Table: WB_CR_STOCK                                           */
/*==============================================================*/
create table WB_CR_STOCK (
   ARTICLE_ID           int                  not null,
   MOVEMENT_ID          int                  not null,
   STROCK_NUMBER        int                  null,
   STOCK_DATE           datetime             null,
   STOCK                int                  null,
   constraint PK_WB_CR_STOCK primary key (ARTICLE_ID, MOVEMENT_ID)
)
go

/*==============================================================*/
/* Index: WB_CR_STOCK2_FK                                       */
/*==============================================================*/




create nonclustered index WB_CR_STOCK2_FK on WB_CR_STOCK (ARTICLE_ID ASC)
go

/*==============================================================*/
/* Index: WB_CR_STOCK_FK                                        */
/*==============================================================*/




create nonclustered index WB_CR_STOCK_FK on WB_CR_STOCK (MOVEMENT_ID ASC)
go

/*==============================================================*/
/* Table: WB_CR_USER                                            */
/*==============================================================*/
create table WB_CR_USER (
   USER_ID              int                  not null,
   USER_NAME            varchar(50)          not null,
   USER_PASSWORD        varchar(60)          not null,
   USER_PERMISSION      varchar(200)         not null,
   constraint PK_WB_CR_USER primary key (USER_ID)
)
go

alter table WB_CR_BILL
   add constraint FK_WB_CR_BI_BILL_IN_C_WB_CR_CI foreign key (CITY_ID)
      references WB_CR_CITY (CITY_ID)
go

alter table WB_CR_BILL
   add constraint FK_WB_CR_BI_CLIENT_HA_WB_CR_CL foreign key (CLIENT_ID)
      references WB_CR_CLIENT (CLIENT_ID)
go

alter table WB_CR_BILLDETAIL
   add constraint FK_WB_CR_BI_WB_CR_BIL_WB_CR_AR foreign key (ARTICLE_ID)
      references WB_CR_ARTICLE (ARTICLE_ID)
go

alter table WB_CR_BILLDETAIL
   add constraint FK_WB_CR_BI_WB_CR_BIL_WB_CR_BI foreign key (CITY_ID, CLIENT_ID, BILL_ID)
      references WB_CR_BILL (CITY_ID, CLIENT_ID, BILL_ID)
go

alter table WB_CR_STOCK
   add constraint FK_WB_CR_ST_WB_CR_STO_WB_CR_MO foreign key (MOVEMENT_ID)
      references WB_CR_MOVEMENT (MOVEMENT_ID)
go

alter table WB_CR_STOCK
   add constraint FK_WB_CR_ST_WB_CR_STO_WB_CR_AR foreign key (ARTICLE_ID)
      references WB_CR_ARTICLE (ARTICLE_ID)
go

