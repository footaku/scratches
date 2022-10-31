use graal;

create table if not exists content
(
    code int not null primary key,
    description varchar(20) not null
);
