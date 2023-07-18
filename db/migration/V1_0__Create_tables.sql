create table brand (id bigint not null, name varchar(50), primary key (id));
create table product (id bigint not null, name varchar(50), primary key (id));
create table price (id bigint not null, currency varchar(255), end_date timestamp,
price double, price_list bigint, priority integer, start_date timestamp, brand_id bigint,
product_id bigint, primary key (id));

alter table price add constraint FKim0rl4mlodcwfy6d5d0vq5enr foreign key (brand_id) references brand;
alter table price add constraint FKk4mbgqf5yru5ib5b6w5l6ukkj foreign key (product_id) references product;



