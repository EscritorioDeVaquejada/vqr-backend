CREATE TABLE public.clients
(
    client_id uuid                   NOT NULL,
    city      character varying(255),
    state     character varying(255),
    email     character varying(255),
    name      character varying(255) NOT NULL,
    number    character varying(255) NOT NULL
);