CREATE TABLE public.tickets
(
    ticket_id      uuid NOT NULL,
    boi_tv         boolean,
    cowboy         character varying(255),
    cowboy_horse   character varying(255),
    representation character varying(255),
    status         smallint,
    support        character varying(255),
    support_horse  character varying(255),
    event_id       uuid,
    payment_id     uuid,
    user_id        uuid,
    CONSTRAINT tickets_status_check CHECK (((status >= 0) AND (status <= 1)))
);