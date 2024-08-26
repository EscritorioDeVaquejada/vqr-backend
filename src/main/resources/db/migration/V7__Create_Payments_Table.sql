CREATE TABLE public.payments
(
    payment_id     uuid NOT NULL,
    date_time      timestamp(6) without time zone,
    payment_method smallint,
    value          double precision,
    CONSTRAINT payments_payment_method_check CHECK (((payment_method >= 0) AND (payment_method <= 4)))
);