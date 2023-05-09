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
    name varchar(25) not null,
    type varchar(10) not null,
    primary key(id)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_bin comment='Taco配料表';

create table taco (
	id bigint not null auto_increment,
    name varchar(40) not null,
    created_at timestamp not null,
    primary key(id)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_bin comment='Taco定制表';
 
create table taco_ingredients (
  taco_id bigint not null,
  ingredient_id varchar(4) not null
) engine=innodb default charset=utf8mb4 collate=utf8mb4_bin comment='Taco配料关系表';

alter table taco_ingredients
    add constraint `taco_ingredients_fk_1` foreign key (taco_id) references taco(id);
alter table taco_ingredients
    add constraint `taco_ingredients_fk_2` foreign key (ingredient_id) references ingredient(id);
    
create table taco_order (
	id bigint not null auto_increment,
	user_id bigint not null,
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
) engine=innodb default charset=utf8mb4 collate=utf8mb4_bin comment='Taco订单表';

create table taco_order_tacos (
	taco_order bigint not null,
	taco bigint not null
) engine=innodb default charset=utf8mb4 collate=utf8mb4_bin comment='Taco订单关系表';

alter table taco_order_tacos
    add constraint `taco_order_tacos_fk_1` foreign key (taco_order) references taco_order(id);
alter table taco_order_tacos
    add constraint `taco_order_tacos_fk_2` foreign key (taco) references taco(id);
    
create table if not exists taco_user (
	id bigint not null auto_increment,
	username varchar(50) not null,
	password varchar(256) not null,
	fullname varchar(50) not null,
	street varchar(50) not null,
	city varchar(50) not null,
	state varchar(50) not null,
	zip varchar(50) not null,
	phone_number varchar(50) not null,
	primary key(id)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_bin comment='Taco用户表';
    