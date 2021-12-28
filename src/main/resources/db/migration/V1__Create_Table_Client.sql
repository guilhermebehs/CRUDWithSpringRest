CREATE TABLE public.client (
	id bigserial NOT NULL,
	address varchar(255) NOT NULL,
	birth varchar(255) NOT NULL,
	last_name varchar(255) NOT NULL,
	name varchar(255) NOT NULL,
	CONSTRAINT client_pkey PRIMARY KEY (id)
);