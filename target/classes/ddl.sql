create table users(
    id serial primary key,
    login varchar(40),
    password varchar(40),
    email varchar(40)
);

insert into users(login,password,email) values
('korlev_es','admin','korolev_es@gmail.com'),
('korlev_sa','admin','korolev_sa@gmail.com'),
('korleva_nv','admin','koroleva_nv@gmail.com');