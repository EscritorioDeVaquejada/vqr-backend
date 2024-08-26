CREATE TABLE public.users
(
    user_id                 uuid NOT NULL,
    account_non_expired     boolean,
    account_non_locked      boolean,
    cpf                     character varying(255),
    credentials_non_expired boolean,
    enabled                 boolean,
    password                character varying(255),
    username                character varying(255)
);