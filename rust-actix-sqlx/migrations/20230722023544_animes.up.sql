-- Add up migration script here
create table animes (
    id serial primary key,
    english_title varchar(100) not null,
    jap_title varchar(100) not null,
    synopsis text,
    studio varchar(100),
    director varchar(100),
    total_eps int,
    release_date date,
    rating varchar(5),
    mean_score double precision
)