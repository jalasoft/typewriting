create table exercises
(
    id            bigint,
    lesson_number bigint not null,
    input varchar(1024),
    primary key (id)
);