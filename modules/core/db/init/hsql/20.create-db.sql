-- begin MARKETPLACE_SOLD_PRODUCT
alter table MARKETPLACE_SOLD_PRODUCT add constraint FK_MARKETPLACE_SOLD_PRODUCT_ON_PRODUCT foreign key (PRODUCT_ID) references MARKETPLACE_PRODUCT(ID)^
alter table MARKETPLACE_SOLD_PRODUCT add constraint FK_MARKETPLACE_SOLD_PRODUCT_ON_SHOP foreign key (SHOP_ID) references MARKETPLACE_SHOP(ID)^
create index IDX_MARKETPLACE_SOLD_PRODUCT_ON_PRODUCT on MARKETPLACE_SOLD_PRODUCT (PRODUCT_ID)^
create index IDX_MARKETPLACE_SOLD_PRODUCT_ON_SHOP on MARKETPLACE_SOLD_PRODUCT (SHOP_ID)^
-- end MARKETPLACE_SOLD_PRODUCT
-- begin MARKETPLACE_PURCHASED_PRODUCTS
alter table MARKETPLACE_PURCHASED_PRODUCTS add constraint FK_MARKETPLACE_PURCHASED_PRODUCTS_ON_SHOP foreign key (SHOP_ID) references MARKETPLACE_SHOP(ID)^
alter table MARKETPLACE_PURCHASED_PRODUCTS add constraint FK_MARKETPLACE_PURCHASED_PRODUCTS_ON_BASKET foreign key (BASKET_ID) references MARKETPLACE_BASKET(ID)^
alter table MARKETPLACE_PURCHASED_PRODUCTS add constraint FK_MARKETPLACE_PURCHASED_PRODUCTS_ON_SOLD_PRODUCT foreign key (SOLD_PRODUCT_ID) references MARKETPLACE_SOLD_PRODUCT(ID)^
create index IDX_MARKETPLACE_PURCHASED_PRODUCTS_ON_SHOP on MARKETPLACE_PURCHASED_PRODUCTS (SHOP_ID)^
create index IDX_MARKETPLACE_PURCHASED_PRODUCTS_ON_BASKET on MARKETPLACE_PURCHASED_PRODUCTS (BASKET_ID)^
create index IDX_MARKETPLACE_PURCHASED_PRODUCTS_ON_SOLD_PRODUCT on MARKETPLACE_PURCHASED_PRODUCTS (SOLD_PRODUCT_ID)^
-- end MARKETPLACE_PURCHASED_PRODUCTS
-- begin MARKETPLACE_MANUFACTURER
alter table MARKETPLACE_MANUFACTURER add constraint FK_MARKETPLACE_MANUFACTURER_ON_USER foreign key (USER_ID) references SEC_USER(ID)^
create unique index IDX_MARKETPLACE_MANUFACTURER_UNIQ_NAME on MARKETPLACE_MANUFACTURER (NAME)^
create index IDX_MARKETPLACE_MANUFACTURER_ON_USER on MARKETPLACE_MANUFACTURER (USER_ID)^
-- end MARKETPLACE_MANUFACTURER
-- begin MARKETPLACE_SHOP
alter table MARKETPLACE_SHOP add constraint FK_MARKETPLACE_SHOP_ON_TRADING_NETWORK foreign key (TRADING_NETWORK_ID) references MARKETPLACE_TRADING_NETWORK(ID)^
create unique index IDX_MARKETPLACE_SHOP_UNIQ_NUMBER on MARKETPLACE_SHOP (NUMBER)^
create index IDX_MARKETPLACE_SHOP_ON_TRADING_NETWORK on MARKETPLACE_SHOP (TRADING_NETWORK_ID)^
-- end MARKETPLACE_SHOP
-- begin MARKETPLACE_TRADING_NETWORK
create unique index IDX_MARKETPLACE_TRADING_NETWORK_UNIQ_NAME on MARKETPLACE_TRADING_NETWORK (NAME)^
-- end MARKETPLACE_TRADING_NETWORK
-- begin MARKETPLACE_BASKET
alter table MARKETPLACE_BASKET add constraint FK_MARKETPLACE_BASKET_ON_USER foreign key (USER_ID) references SEC_USER(ID)^
create index IDX_MARKETPLACE_BASKET_ON_USER on MARKETPLACE_BASKET (USER_ID)^
-- end MARKETPLACE_BASKET
-- begin MARKETPLACE_PRODUCT
alter table MARKETPLACE_PRODUCT add constraint FK_MARKETPLACE_PRODUCT_ON_MANUFACTURER foreign key (MANUFACTURER_ID) references MARKETPLACE_MANUFACTURER(ID)^
create unique index IDX_MARKETPLACE_PRODUCT_UNIQ_NAME on MARKETPLACE_PRODUCT (NAME)^
create index IDX_MARKETPLACE_PRODUCT_ON_MANUFACTURER on MARKETPLACE_PRODUCT (MANUFACTURER_ID)^
-- end MARKETPLACE_PRODUCT
-- begin MARKETPLACE_BUY_PRODUCT
alter table MARKETPLACE_BUY_PRODUCT add constraint FK_MARKETPLACE_BUY_PRODUCT_ON_SOLD_PRODUCT foreign key (SOLD_PRODUCT_ID) references MARKETPLACE_SOLD_PRODUCT(ID)^
alter table MARKETPLACE_BUY_PRODUCT add constraint FK_MARKETPLACE_BUY_PRODUCT_ON_ONLINE_ORDER foreign key (ONLINE_ORDER_ID) references MARKETPLACE_ONLINE_ORDER(ID)^
create index IDX_MARKETPLACE_BUY_PRODUCT_ON_SOLD_PRODUCT on MARKETPLACE_BUY_PRODUCT (SOLD_PRODUCT_ID)^
create index IDX_MARKETPLACE_BUY_PRODUCT_ON_ONLINE_ORDER on MARKETPLACE_BUY_PRODUCT (ONLINE_ORDER_ID)^
-- end MARKETPLACE_BUY_PRODUCT
-- begin MARKETPLACE_ONLINE_ORDER
alter table MARKETPLACE_ONLINE_ORDER add constraint FK_MARKETPLACE_ONLINE_ORDER_ON_BUYER foreign key (BUYER_ID) references MARKETPLACE_BUYER(ID)^
create index IDX_MARKETPLACE_ONLINE_ORDER_ON_BUYER on MARKETPLACE_ONLINE_ORDER (BUYER_ID)^
-- end MARKETPLACE_ONLINE_ORDER
-- begin MARKETPLACE_PRICE_HISTORY
alter table MARKETPLACE_PRICE_HISTORY add constraint FK_MARKETPLACE_PRICE_HISTORY_ON_PRODUCT foreign key (PRODUCT_ID) references MARKETPLACE_PRODUCT(ID)^
alter table MARKETPLACE_PRICE_HISTORY add constraint FK_MARKETPLACE_PRICE_HISTORY_ON_SHOP foreign key (SHOP_ID) references MARKETPLACE_SHOP(ID)^
create index IDX_MARKETPLACE_PRICE_HISTORY_ON_PRODUCT on MARKETPLACE_PRICE_HISTORY (PRODUCT_ID)^
create index IDX_MARKETPLACE_PRICE_HISTORY_ON_SHOP on MARKETPLACE_PRICE_HISTORY (SHOP_ID)^
-- end MARKETPLACE_PRICE_HISTORY
-- begin MARKETPLACE_PHYSICAL_ENTITY
alter table MARKETPLACE_PHYSICAL_ENTITY add constraint FK_MARKETPLACE_PHYSICAL_ENTITY_ON_ID foreign key (ID) references MARKETPLACE_BUYER(ID) on delete CASCADE^
-- end MARKETPLACE_PHYSICAL_ENTITY
-- begin MARKETPLACE_LEGAL_ENTITY
alter table MARKETPLACE_LEGAL_ENTITY add constraint FK_MARKETPLACE_LEGAL_ENTITY_ON_ID foreign key (ID) references MARKETPLACE_BUYER(ID) on delete CASCADE^
-- end MARKETPLACE_LEGAL_ENTITY
-- begin SEC_USER
alter table SEC_USER add constraint FK_SEC_USER_ON_BUYER foreign key (BUYER_ID) references MARKETPLACE_BUYER(ID)^
create index IDX_SEC_USER_ON_BUYER on SEC_USER (BUYER_ID)^
-- end SEC_USER