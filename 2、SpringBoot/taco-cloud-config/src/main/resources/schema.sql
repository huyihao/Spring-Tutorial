# 暂时关闭外键索引检查，删完表之后重新打开
SET FOREIGN_KEY_CHECKS=0; alter table taco_ingredients drop foreign key `taco_ingredients_fk_1`;
SET FOREIGN_KEY_CHECKS=0; alter table taco_ingredients drop foreign key `taco_ingredients_fk_2`;
SET FOREIGN_KEY_CHECKS=0; alter table taco_order_tacos drop foreign key `taco_order_tacos_fk_1`;
SET FOREIGN_KEY_CHECKS=0; alter table taco_order_tacos drop foreign key `taco_order_tacos_fk_2`;
drop table if exists ingredient;
drop table if exists taco;
drop table if exists taco_ingredients;
drop table if exists taco_order;
drop table if exists taco_order_tacos;

create table ingredient (
	id varchar(4) not null,
    in_name varchar(25) not null,
    in_type varchar(10) not null,
    primary key(id)
);

create table taco (
	id bigint not null auto_increment,
    taco_name varchar(40) not null,
    create_at timestamp not null,
    primary key(id)
);
 
create table taco_ingredients (
  taco bigint not null,
  ingredient varchar(4) not null
);

alter table taco_ingredients
    add constraint `taco_ingredients_fk_1` foreign key (taco) references taco(id);
alter table taco_ingredients
    add constraint `taco_ingredients_fk_2` foreign key (ingredient) references ingredient(id);
    
create table taco_order (
	id bigint not null auto_increment,
	delivery_name varchar(50) not null,
	delivery_street varchar(50) not null,
	delivery_city varchar(50) not null,
	delivery_state varchar(20) not null,
	delivery_zip varchar(10) not null,
	cc_number varchar(16) not null,
	cc_expiration varchar(5) not null,
	cc_cvv varchar(3) not null,
    placed_at timestamp not null,
    primary key(id)
);

create table taco_order_tacos (
	taco_order bigint not null,
	taco bigint not null
);

alter table taco_order_tacos
    add constraint `taco_order_tacos_fk_1` foreign key (taco_order) references taco_order(id);
alter table taco_order_tacos
    add constraint `taco_order_tacos_fk_2` foreign key (taco) references taco(id);
    
    