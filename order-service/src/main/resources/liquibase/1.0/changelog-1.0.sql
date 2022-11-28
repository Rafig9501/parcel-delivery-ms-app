create schema if not exists order_ms;
set schema 'order_ms';
create table if not exists order_ms.order_
(
    id_          bigint not null
        constraint id_
            primary key generated always as identity,
    user_id      bigint not null,
    courier_id   bigint not null,
    name_        varchar(32),
    price_       numeric(5, 2),
    destination_ varchar(32),
    status_      varchar(16)
);
create index if not exists user_id_i
    on order_ms.order_ (user_id);
create index if not exists courier_id_i
    on order_ms.order_ (courier_id);