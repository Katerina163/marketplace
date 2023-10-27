-- begin MARKETPLACE_SOLD_PRODUCT
create table MARKETPLACE_SOLD_PRODUCT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    PRODUCT_ID varchar(36) not null,
    PRICE decimal(19, 2) not null,
    QUANTITY integer not null,
    SHOP_ID varchar(36) not null,
    MINIM integer,
    --
    primary key (ID)
)^
-- end MARKETPLACE_SOLD_PRODUCT
-- begin MARKETPLACE_PURCHASED_PRODUCTS
create table MARKETPLACE_PURCHASED_PRODUCTS (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    SHOP_ID varchar(36) not null,
    BASKET_ID varchar(36),
    PRICE decimal(19, 2) not null,
    SOLD_PRODUCT_ID varchar(36),
    QUANTITY integer,
    --
    primary key (ID)
)^
-- end MARKETPLACE_PURCHASED_PRODUCTS
-- begin MARKETPLACE_MANUFACTURER
create table MARKETPLACE_MANUFACTURER (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    ADDRESS_CITY varchar(255) not null,
    LAT double precision,
    LNG double precision,
    ADDRESS_STREET varchar(255),
    ADDRESS_HOUSE varchar(255),
    --
    NAME varchar(255) not null,
    FULL_NAME varchar(255),
    USER_ID varchar(36),
    --
    primary key (ID)
)^
-- end MARKETPLACE_MANUFACTURER
-- begin MARKETPLACE_SHOP
create table MARKETPLACE_SHOP (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    ADDRESS_CITY varchar(255) not null,
    LAT double precision,
    LNG double precision,
    ADDRESS_STREET varchar(255),
    ADDRESS_HOUSE varchar(255),
    --
    NUMBER varchar(255) not null,
    TYPE_ varchar(50) not null,
    NAME varchar(255) not null,
    TRADING_NETWORK_ID varchar(36) not null,
    --
    primary key (ID)
)^
-- end MARKETPLACE_SHOP
-- begin MARKETPLACE_TRADING_NETWORK
create table MARKETPLACE_TRADING_NETWORK (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    FULL_NAME varchar(255),
    --
    primary key (ID)
)^
-- end MARKETPLACE_TRADING_NETWORK
-- begin MARKETPLACE_BUYER
create table MARKETPLACE_BUYER (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    DTYPE varchar(31),
    --
    FULL_NAME varchar(255),
    ADDRESS varchar(255),
    EMAIL varchar(255),
    --
    primary key (ID)
)^
-- end MARKETPLACE_BUYER
-- begin MARKETPLACE_BASKET
create table MARKETPLACE_BASKET (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    USER_ID varchar(36) not null,
    DATA_ timestamp not null,
    --
    primary key (ID)
)^
-- end MARKETPLACE_BASKET
-- begin MARKETPLACE_PRODUCT
create table MARKETPLACE_PRODUCT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    MANUFACTURER_ID varchar(36) not null,
    MANUFACTURER_PRICE decimal(19, 2) not null,
    --
    primary key (ID)
)^
-- end MARKETPLACE_PRODUCT
-- begin MARKETPLACE_BUY_PRODUCT
create table MARKETPLACE_BUY_PRODUCT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    QUANTITY integer not null,
    PRICE decimal(19, 2),
    SOLD_PRODUCT_ID varchar(36),
    ONLINE_ORDER_ID varchar(36) not null,
    --
    primary key (ID)
)^
-- end MARKETPLACE_BUY_PRODUCT
-- begin MARKETPLACE_ONLINE_ORDER
create table MARKETPLACE_ONLINE_ORDER (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    BUYER_ID varchar(36),
    AMOUNT decimal(19, 2) not null,
    DISCOUNT integer,
    STATUS varchar(50) not null,
    NUMBER_ varchar(255),
    --
    primary key (ID)
)^
-- end MARKETPLACE_ONLINE_ORDER
-- begin MARKETPLACE_PRICE_HISTORY
create table MARKETPLACE_PRICE_HISTORY (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    PRODUCT_ID varchar(36) not null,
    PRICE decimal(19, 2),
    SHOP_ID varchar(36) not null,
    DATE_PRICE_CHANGE timestamp,
    --
    primary key (ID)
)^
-- end MARKETPLACE_PRICE_HISTORY
-- begin MARKETPLACE_PHYSICAL_ENTITY
create table MARKETPLACE_PHYSICAL_ENTITY (
    ID varchar(36) not null,
    --
    NAME varchar(255),
    SURNAME varchar(255) not null,
    PATRONYMIC varchar(255),
    --
    primary key (ID)
)^
-- end MARKETPLACE_PHYSICAL_ENTITY
-- begin MARKETPLACE_LEGAL_ENTITY
create table MARKETPLACE_LEGAL_ENTITY (
    ID varchar(36) not null,
    --
    DESIGNATION varchar(255),
    --
    primary key (ID)
)^
-- end MARKETPLACE_LEGAL_ENTITY
-- begin SEC_USER
alter table SEC_USER add column BUYER_ID varchar(36) ^
alter table SEC_USER add column DTYPE varchar(31) ^
update SEC_USER set DTYPE = 'marketplace_ExtUser' where DTYPE is null ^
-- end SEC_USER
