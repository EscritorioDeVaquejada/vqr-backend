CREATE TABLE public.events
(
    event_id                            uuid                   NOT NULL,
    city                                character varying(255),
    state                               character varying(255),
    date_time                           timestamp(6) without time zone NOT NULL,
    default_ticket_price                double precision       NOT NULL,
    is_finished                         boolean,
    name                                character varying(255) NOT NULL,
    number_of_initial_tickets           integer                NOT NULL,
    price_of_boi_tv_anticipated         double precision       NOT NULL,
    price_of_boi_tv_purchased_on_demand double precision       NOT NULL,
    finance_id                          uuid,
    client_id                           uuid
);