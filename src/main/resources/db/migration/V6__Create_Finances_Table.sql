CREATE TABLE public.finances
(
    finance_id         uuid NOT NULL,
    total_boi_tv       integer,
    total_cash         double precision,
    total_credit       double precision,
    total_debit        double precision,
    total_pix          double precision,
    total_tickets_sold integer,
    total_free         integer
);