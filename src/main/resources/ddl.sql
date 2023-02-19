create table users(
    id serial primary key,
    login varchar(40),
    password varchar(40),
    email varchar(40)
);

insert into users(login,password,email) values
('korolev_es','admin','korolev_es@gmail.com'),
('korolev_sa','admin','korolev_sa@gmail.com'),
('koroleva_nv','admin','koroleva_nv@gmail.com');

CREATE TABLE MONTH_TRACE
(
  "ID" serial NOT NULL,
  "YYYY" character varying(4),
  "MM" character varying(2),
  "MASK" character varying(31) NOT NULL DEFAULT 0,
  "MAXDAYS" smallint DEFAULT 31,
  "STARTWD" smallint DEFAULT 1,
  CONSTRAINT "MONTH_TRACE_pkey" PRIMARY KEY ("ID")
)
WITH (
  OIDS=FALSE
);