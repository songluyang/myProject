create table taco_Ingredient (
id varchar(8) not null,
name varchar(25) not null,
type varchar(10) not null); 

create table if not exists taco_Taco (
id varchar(8) not null,
name varchar(50) not null,
createdAt timestamp not null);

create table if not exists taco_Taco_Ingredients (
taco varchar(8) not null,
ingredient varchar(4) not null);

alter table taco_Taco_Ingredients add foreign key (taco) references taco_Taco(id); 

alter table taco_Taco_Ingredients add foreign key (ingredient) references taco_Ingredient(id);

create table if not exists taco_Taco_Order (
id varchar(8) not null,
Name varchar(50) not null,
Street varchar(50) not null, 
City varchar(50) not null, 
State varchar(2) not null, 
Zip varchar(10) not null, 
ccNumber varchar(16) not null,
ccExpiration varchar(5) not null, 
ccCVV varchar(3) not null, 
createdAt timestamp not null);

create table taco_Taco_Order_Tacos (
tacoOrder varchar(8) not null, 
taco varchar(8) not null); 

alter table taco_Taco_Order_Tacos add foreign key (tacoOrder) references Taco_Order(id); 
alter table taco_Taco_Order_Tacos add foreign key (taco) references Taco(id);

create sequence seq_tacoId
increment by 1
start with 1
maxvalue 999999999;

ALTER TABLE taco_Taco_Ingredients ADD CONSTRAINT taco_Taco_Ingredients_pk PRIMARY KEY (taco,ingredient);  
ALTER TABLE taco_Taco_Order_Tacos ADD CONSTRAINT taco_Taco_Order_Tacos_pk PRIMARY KEY (tacoOrder,taco);  
ALTER TABLE taco_Ingredient ADD CONSTRAINT taco_Ingredient_pk PRIMARY KEY (id);  
ALTER TABLE taco_Taco ADD CONSTRAINT taco_Taco_pk PRIMARY KEY (id);  
ALTER TABLE taco_Taco_Order ADD CONSTRAINT taco_Taco_Order_pk PRIMARY KEY (id);  

alter table taco_Taco_Ingredients add foreign key (taco) references taco_Taco(id); 
alter table taco_Taco_Ingredients add foreign key (ingredient) references taco_Ingredient(id);
alter table taco_Taco_Order_Tacos add foreign key (tacoOrder) references taco_Taco_Order(id); 
alter table taco_Taco_Order_Tacos add foreign key (taco) references taco_Taco(id);


--废弃表
select 'drop table '||table_name||';' 
from cat 
where table_type='TABLE'


drop table BIN$uYBtqwzbQKSU62Eh1bN0UA==$0;
drop table TACO_INGREDIENT;
drop table TACO_TACO;
drop table TACO_TACO_INGREDIENTS;
drop table TACO_TACO_ORDER;
drop table TACO_TACO_ORDER_TACOS;

ALTER TABLE taco_Taco_Ingredients DROP CONSTRAINT taco_Taco_Ingredients_pk;
ALTER TABLE taco_Taco_Order_Tacos DROP CONSTRAINT taco_Taco_Order_Tacos_pk;
ALTER TABLE taco_Ingredient DROP CONSTRAINT taco_Ingredient_pk;
ALTER TABLE taco_Taco DROP CONSTRAINT taco_Taco_pk;
ALTER TABLE taco_Taco_Order DROP CONSTRAINT taco_Taco_Order_pk;

