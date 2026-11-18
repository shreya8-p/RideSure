create table policy (
    id                   uuid primary key,
    policy_number        varchar(20)    not null unique,
    customer_name        varchar(100)   not null,
    vehicle_registration varchar(15)    not null,
    vehicle_value        numeric(12, 2) not null,
    premium              numeric(10, 2) not null,
    status               varchar(20)    not null,
    start_date           date           not null,
    end_date             date           not null,
    created_at           timestamp      not null default now()
);