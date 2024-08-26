--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3
-- Dumped by pg_dump version 16.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: clients; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.clients (
    client_id uuid NOT NULL,
    city character varying(255),
    state character varying(255),
    email character varying(255),
    name character varying(255) NOT NULL,
    number character varying(255) NOT NULL
);


ALTER TABLE public.clients OWNER TO postgres;

--
-- Name: events; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.events (
    event_id uuid NOT NULL,
    city character varying(255),
    state character varying(255),
    date_time timestamp(6) without time zone NOT NULL,
    default_ticket_price double precision NOT NULL,
    is_finished boolean,
    name character varying(255) NOT NULL,
    number_of_initial_tickets integer NOT NULL,
    price_of_boi_tv_anticipated double precision NOT NULL,
    price_of_boi_tv_purchased_on_demand double precision NOT NULL,
    financa_id uuid,
    client_id uuid
);


ALTER TABLE public.events OWNER TO postgres;

--
-- Name: finances; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.finances (
    finance_id uuid NOT NULL,
    total_boi_tv integer,
    total_cash double precision,
    total_credti double precision,
    total_debit double precision,
    total_pix double precision,
    total_tickets_sold integer,
    total_free integer
);


ALTER TABLE public.finances OWNER TO postgres;

--
-- Name: payments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.payments (
    payment_id uuid NOT NULL,
    date_time timestamp(6) without time zone,
    payment_method smallint,
    value double precision,
    CONSTRAINT payments_payment_method_check CHECK (((payment_method >= 0) AND (payment_method <= 4)))
);


ALTER TABLE public.payments OWNER TO postgres;

--
-- Name: permissions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.permissions (
    permission_id uuid NOT NULL,
    description character varying(255)
);


ALTER TABLE public.permissions OWNER TO postgres;

--
-- Name: tickets; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tickets (
    ticket_id uuid NOT NULL,
    boi_tv boolean,
    cowboy character varying(255),
    cowboy_horse character varying(255),
    representation character varying(255),
    status smallint,
    support character varying(255),
    support_horse character varying(255),
    event_id uuid,
    payment_id uuid,
    user_id uuid,
    CONSTRAINT tickets_status_check CHECK (((status >= 0) AND (status <= 1)))
);


ALTER TABLE public.tickets OWNER TO postgres;

--
-- Name: user_permission; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_permission (
    user_id uuid NOT NULL,
    permission_id uuid NOT NULL
);


ALTER TABLE public.user_permission OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    user_id uuid NOT NULL,
    account_non_expired boolean,
    account_non_locked boolean,
    cpf character varying(255),
    credentials_non_expired boolean,
    enabled boolean,
    password character varying(255),
    username character varying(255)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.clients (client_id, city, state, email, name, number) FROM stdin;
35ac1cb4-2070-4e0d-98b4-eeb91c194c66	Los Angeles	California	john_doe@email.com	John Doe	88999999999
aa7c332b-204b-4e46-910c-35b5b0e1db2d	New York City	New York	jane_smith@email.com	Jane Smith	88123456789
af80686a-dc50-4efc-b79c-095ede6ed5d5	Houston	Texas	carlos.oliveira@email.com	Carlos Oliveira	88234567890
3c969bc6-90ba-4d57-b03a-4fd5b3edb3e9	Miami	Florida	emily_johnson@email.com	Emily Johnson	88345678901
21c1e3c2-4ca6-463f-ada8-be2bb409e84c	Chicago	Illinois	michael_brown@email.com	Michael Brown	88456789012
abc2c628-2aa3-4233-beee-6a741d9ed623	Phoenix	Arizona	sophia_martinez@email.com	Sophia Martinez	88567890123
\.


--
-- Data for Name: events; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.events (event_id, city, state, date_time, default_ticket_price, is_finished, name, number_of_initial_tickets, price_of_boi_tv_anticipated, price_of_boi_tv_purchased_on_demand, financa_id, client_id) FROM stdin;
b72868f4-96d3-4bba-a78c-3ee6d0106241	Los Angeles	California	2024-08-25 22:46:35.73814	1000	\N	Summer Rodeo	100	300	400	\N	35ac1cb4-2070-4e0d-98b4-eeb91c194c66
19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	Houston	Texas	2024-08-25 22:51:54.341572	1200	\N	Winter Bull Riding Championship	40	350	450	\N	35ac1cb4-2070-4e0d-98b4-eeb91c194c66
9413b2c9-e890-4a70-9cf7-046fe5320a7e	Miami	Florida	2024-08-25 22:52:13.622462	900	\N	Spring Cattle Showdown	20	250	350	\N	35ac1cb4-2070-4e0d-98b4-eeb91c194c66
30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	Denver	Colorado	2024-08-25 22:52:24.408536	1100	\N	Autumn Ranch Festival	80	400	500	\N	35ac1cb4-2070-4e0d-98b4-eeb91c194c66
5f391f63-72b6-4ed8-9fef-6297ead0764b	Las Vegas	Nevada	2024-08-25 22:52:32.272233	1500	\N	National Rodeo Finals	250	500	600	\N	35ac1cb4-2070-4e0d-98b4-eeb91c194c66
77c4a348-2fce-4457-9696-e5fd73d6ab5b	Phoenix	Arizona	2024-08-25 22:52:58.107706	950	\N	Wild West Expo	180	280	380	\N	35ac1cb4-2070-4e0d-98b4-eeb91c194c66
\.


--
-- Data for Name: finances; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.finances (finance_id, total_boi_tv, total_cash, total_credti, total_debit, total_pix, total_tickets_sold, total_free) FROM stdin;
\.


--
-- Data for Name: payments; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.payments (payment_id, date_time, payment_method, value) FROM stdin;
\.


--
-- Data for Name: permissions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.permissions (permission_id, description) FROM stdin;
39e951f9-d7cd-4753-a310-114911ef0ae7	ROLE_ADMIN
\.


--
-- Data for Name: tickets; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tickets (ticket_id, boi_tv, cowboy, cowboy_horse, representation, status, support, support_horse, event_id, payment_id, user_id) FROM stdin;
9529a72c-32cc-4811-bceb-650be022f0d4	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
fa43a642-8e90-407f-a70b-ca57f4c4939a	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
08113f7c-62c4-4e2f-a49b-bbcbf9d51f83	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
30aa0eaf-cf83-47d2-abe8-42fb8dc7ff09	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
26026d45-114f-48e4-952c-626957c7ffc7	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
59e9ee8b-358a-4aab-ba72-5d4f00b45eba	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
fab27bdb-1aa3-49dd-b371-18ab21dff5b2	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
7d789ef1-9f53-4c35-9de6-9108bebf2a6a	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
eb88aa09-4d07-4ea8-b136-7c5d0d8b5327	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
69de5a38-3ab7-4782-ab04-de6414f2f8d8	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
59ee0eac-dcd3-447c-8905-cdecc955c2d7	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
64f0c385-3eee-4a62-8653-b8f7cf3dfa3b	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
c8cf2ff3-5778-414e-8ebf-d89c27765625	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
c2dc6606-91e1-4a90-96f3-13e5bce42697	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
d9cf5762-d617-4523-b7bf-b4e337e1a3e4	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
059f2913-abd0-4d3e-bc1c-f5d47321176a	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
a0943333-9313-4c56-ae6e-5a0714870a89	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
21c6aa27-05f1-499a-8713-e39f6a6f45da	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
889bf291-2239-400e-b41e-cc256d62c388	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
815882aa-ed11-45e1-9bc9-1a3490c7552e	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
862eb328-017d-4ce1-b313-5a84e44cc3eb	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
3e781fec-2f3f-45d9-9791-d52d9da1c385	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
09255cbf-a9cd-4e2e-9722-18e608e6c04e	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
591e526e-4df3-46fc-83cb-badf65dc328b	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
8b5c7edd-4570-457f-8e84-2e0370e78283	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
ffd1a98c-06a8-4ba4-8f1e-c79842376f20	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
bd94356a-8dba-42b1-8b5c-8b841bcad242	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
ad6eaf5e-7cbf-4871-90f1-472bfb9dad98	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
31eef94c-1ba8-4e82-9f03-5e31c49d5c63	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
cede36af-ad85-467d-8fa1-1714871b173f	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
52ce9005-2eed-4e9c-864a-50b092086a9c	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
06fd4d08-2e81-4bf3-a386-2d3dc34917ce	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
d2930d68-d493-4e0f-804e-b4424d3d3db2	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
eac774b3-c799-42f5-b0cc-450973352e21	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
cb990134-b8e1-4220-a202-475e1a67fb1b	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
55f11fb4-acfa-4e38-aeee-d4999916cb66	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
62768df8-777f-49f3-a291-3df5265b9939	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
bccb70c0-4106-48ff-b90f-f12c5bcac70a	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
f8577b68-a134-4241-aa95-6ebb3dc49314	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
3c95cb96-85c6-40dd-823e-5cba8e8b272c	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
3584cf47-7ab4-4afa-ab2e-908d0d9283e3	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
b9cef939-c736-42e7-a5dc-d58e58596963	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
a26ccdd9-3b55-4637-800f-ac384324f064	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
ba37ac1a-031e-4546-b9c0-585ffb543005	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
211fad41-53fd-4cfb-b89e-3a4ad8fe1f78	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
a1da4e30-f6f4-4a79-82a4-a50ea82d4ecd	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
1109f237-653a-4bea-beae-202bfbbaee0e	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
b349eb07-866b-403c-a9e9-3f052f3b13a5	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
609f1bc2-585b-45c9-b68b-89ee8cb349a1	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
91d45fc5-29ce-4aea-9243-923bb769b286	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
307b6066-dda4-41f3-8201-ec35ce1ad4c4	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
356e5d39-0390-49bf-96cb-04dba6081a41	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
c579a266-1fe1-43c2-9ac7-1f96b19a2dd1	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
b7040c74-e647-42fe-97a1-77aeb622910b	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
79814139-0f41-42d8-8ec5-63258c453250	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
0869863a-39be-426e-94f2-8aa285edb9ac	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
a6b87ead-2e46-4362-9183-7c3d61d67d4c	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
ea9df5d5-222d-4fab-a880-dd4516fbc3e7	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
27b2a77f-a1b0-42b5-a0ff-57feede7835d	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
2588d1d8-6023-43a3-b1d3-adadf256e893	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
d9cd530b-4051-46c4-bb92-8ed6a2cd0865	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
c7d5951c-107a-4343-9ab9-96ef7825391c	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
094da1b9-1371-4188-b6a2-7481751b4f10	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
fb606da4-a105-42c7-9fe8-f25708635d90	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
80b1e3d0-02e7-4471-aa58-c204b37dc0e9	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
9235cf2b-141d-4286-a5c9-efbaa4f08b0f	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
99b49c6c-9eba-46d5-9cb1-77cb631ec1e5	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
c6b29c1f-a37b-4efb-b880-2fa99b462d47	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
7eb385c8-a2db-46fd-9c9f-791cd933ac37	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
842efac4-5f73-4054-bb3e-bef8d3ca4ea3	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
d91f48e4-8d46-4051-881f-70f5c73ad1cc	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
3e539d3d-20fb-4371-a95b-f668abb4362e	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
23062042-31cd-4cca-9291-441cea4664bb	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
19a157d9-cafe-40ee-b313-2349b9eb7c2d	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
6f723b87-2e80-4d91-ab46-6247cd02ce54	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
bb8eb008-ccc6-459c-8647-4bfcb20aefc8	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
816e7dcf-63f4-439d-984f-3925dac7f08e	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
fa15c869-2ed0-4d69-9e99-99d5022166f5	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
f6dfd20d-f68f-443d-b6d5-e803a3f1b0f5	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
6e7eee91-a856-428e-ac25-6b164db6e9d6	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
49340fae-58e2-43fb-b6a1-9b8cb80a7001	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
3813d95b-3476-4745-895a-2a87610f933e	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
5209266a-e2c8-4750-8b1a-3e6ed15307ac	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
0ed3b56b-ef67-445f-b61e-14b63f6f04d2	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
252054cd-6daa-4b61-91d3-e191e532b76d	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
51e1bd0a-772e-49e0-9399-5c75996f089a	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
05ca63e0-885e-4b68-98e1-92c100deefc8	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
1f497bab-bf31-4a3d-a160-a3a4721ce9d5	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
e2950f73-4bd2-4a3a-a203-c4fa45a64588	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
27a85b19-4f24-424a-ab0f-6b845ac99375	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
276e6f79-9f1a-4003-80d4-dc024979b6b1	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
b888dfc9-bbff-403f-8bad-0c1cb9dac4f5	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
a5ca38ee-e1b3-4156-abe4-5d0b32f03c8f	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
0c4e0dc2-0947-44f6-a4a3-7408df5528f8	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
83ae1227-febf-43a6-8d2b-3e3d584dfab1	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
7c125881-1223-4257-abf7-8b7d1e527073	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
776ccf53-6e32-4c6a-8d67-5efd1bc097b4	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
ef424020-a429-4ec3-8735-246901de49ee	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
5c7d3b98-916e-4192-b0ec-98debcfdfa01	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
4c092d1e-b2dc-4f8c-a9a1-67ab7b24fe23	f	\N	\N	\N	1	\N	\N	b72868f4-96d3-4bba-a78c-3ee6d0106241	\N	\N
413280ae-9766-4c15-a4e5-cea23009c711	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
6f700eb9-bf9b-4a30-bb8b-0df413299000	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
106b5db1-55fb-4e38-ae50-e252ce79980a	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
97d35a82-fd3d-4e7a-8d92-1518ca34d750	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
91b88013-413f-47d4-9925-5f29f7740192	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
ea0552eb-2922-47c0-8c6a-cced6595ff15	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
c4398551-a24b-4eff-bc45-b9dd89431ed9	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
9bfafde8-1742-44a2-b62e-df1fa34afcfa	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
fc69a63f-a376-4271-af0c-d59a75c0d07c	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
cbe3aae5-51d7-4050-9723-a556b246e795	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
aa1183af-4086-4f61-8ae7-28844ca87f3a	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
bb6b5072-ac9c-457b-a963-55fdf59aba70	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
8107c46e-bb82-4c3d-9fb8-16a71ee773c7	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
c4b84f8f-84a1-4d1b-9fd4-1e36162a93ba	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
216346e2-63de-4e31-8c7e-adb2070bc99a	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
f94bb60a-0694-442b-81a1-0f9a7c5edba8	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
d628a763-45b6-4503-9b19-7f53a85063bf	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
ddc1658d-c50c-4a78-a5f4-5de085fa4da1	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
7bbd6556-a8df-4e2b-98da-4c9f4b8a6233	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
77c7af49-8db6-4e40-9153-cc01873c13f4	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
c7e3b97f-e43f-4708-a368-cdf1e6cc944a	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
6b746b47-b2d4-4c83-bdff-eed1f5331983	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
0a53dff1-d19c-4b14-bfdf-ceb800c22d11	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
0f97bbd4-24e2-4d93-8182-a4444e91e1c4	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
20cb8062-a3ee-4127-963b-de4895aa8fb0	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
79b00b4d-4cb2-4997-96f0-9840baf69960	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
3128aa26-6829-4ced-8ef2-1801cb3bd943	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
f0ffcd7d-68b2-44a2-85c2-2c2000a79cba	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
9463cb66-9aa6-4bee-81f0-e67e02ed3f69	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
8489beec-22af-4b91-8707-e82ddc4e6898	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
d262a6ac-c265-4db8-b206-4d7b5ffe92dc	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
ced227aa-fe1e-43b1-8f0d-b5fb57ef6112	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
f984f864-d3c0-4a38-9684-d5f5b77ed33d	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
d80f3f97-72bb-4748-8997-c26b821fa4d6	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
e3a287f9-0e0b-4f15-9072-ebdc98baa8bf	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
e299a7b1-6464-4234-8c3b-5eb5a19a688d	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
f22fd2cb-c629-40c9-981f-77f1388d9b62	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
4435ade6-1b14-4fc9-85ad-48662fbc11d7	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
27de5d86-3d41-4f67-8e15-b8ada8d2bafa	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
34cb092e-f979-4d78-a8cf-28409365801b	f	\N	\N	\N	1	\N	\N	19f56442-dc5a-4fcb-8f1b-146d9e0eabfd	\N	\N
f6822c25-2ae1-4718-b6f0-c9b7c0198bfa	f	\N	\N	\N	1	\N	\N	9413b2c9-e890-4a70-9cf7-046fe5320a7e	\N	\N
04121c9b-539a-4cbf-a0a1-3a6263f445c1	f	\N	\N	\N	1	\N	\N	9413b2c9-e890-4a70-9cf7-046fe5320a7e	\N	\N
5e773e6c-cc76-4a45-9bab-4fe182c67dae	f	\N	\N	\N	1	\N	\N	9413b2c9-e890-4a70-9cf7-046fe5320a7e	\N	\N
dcfbce2b-5be8-4c22-af70-4588929dc272	f	\N	\N	\N	1	\N	\N	9413b2c9-e890-4a70-9cf7-046fe5320a7e	\N	\N
9ac6b17e-1023-45c5-8cfd-7441fb02c143	f	\N	\N	\N	1	\N	\N	9413b2c9-e890-4a70-9cf7-046fe5320a7e	\N	\N
b63e682f-447b-4d81-9604-ff46f9c93b69	f	\N	\N	\N	1	\N	\N	9413b2c9-e890-4a70-9cf7-046fe5320a7e	\N	\N
f4867572-3994-4a00-970a-925920c42668	f	\N	\N	\N	1	\N	\N	9413b2c9-e890-4a70-9cf7-046fe5320a7e	\N	\N
d20b5960-10bf-4fb7-8ac9-cdbc68bb61dd	f	\N	\N	\N	1	\N	\N	9413b2c9-e890-4a70-9cf7-046fe5320a7e	\N	\N
ae617431-0938-4caf-93ff-dd889b2f0c94	f	\N	\N	\N	1	\N	\N	9413b2c9-e890-4a70-9cf7-046fe5320a7e	\N	\N
c480bb39-30f5-44da-ac4b-6ac4ad5b22e3	f	\N	\N	\N	1	\N	\N	9413b2c9-e890-4a70-9cf7-046fe5320a7e	\N	\N
673553c9-fbe1-4e5d-b34e-9bf4a01dd846	f	\N	\N	\N	1	\N	\N	9413b2c9-e890-4a70-9cf7-046fe5320a7e	\N	\N
6b0eaabc-6747-4aa1-9b02-94997d44574c	f	\N	\N	\N	1	\N	\N	9413b2c9-e890-4a70-9cf7-046fe5320a7e	\N	\N
673fcafd-3c9e-438d-a236-5c40081f24c9	f	\N	\N	\N	1	\N	\N	9413b2c9-e890-4a70-9cf7-046fe5320a7e	\N	\N
5f641358-37c7-435f-b0e0-27f2a040d0b7	f	\N	\N	\N	1	\N	\N	9413b2c9-e890-4a70-9cf7-046fe5320a7e	\N	\N
09630c58-c2a5-4c14-9965-4d3deb8cf837	f	\N	\N	\N	1	\N	\N	9413b2c9-e890-4a70-9cf7-046fe5320a7e	\N	\N
635be3e9-12d7-4605-8e71-129071e4f34f	f	\N	\N	\N	1	\N	\N	9413b2c9-e890-4a70-9cf7-046fe5320a7e	\N	\N
d8260955-8696-44d2-b57a-ba9db77899d3	f	\N	\N	\N	1	\N	\N	9413b2c9-e890-4a70-9cf7-046fe5320a7e	\N	\N
ac3a59e9-6efc-459f-8b38-43dc5de155f7	f	\N	\N	\N	1	\N	\N	9413b2c9-e890-4a70-9cf7-046fe5320a7e	\N	\N
a326acd3-8923-47fe-a77a-fef5c4761226	f	\N	\N	\N	1	\N	\N	9413b2c9-e890-4a70-9cf7-046fe5320a7e	\N	\N
c1804252-e0e2-4320-89cc-a5257218f1f4	f	\N	\N	\N	1	\N	\N	9413b2c9-e890-4a70-9cf7-046fe5320a7e	\N	\N
25a0639f-d474-43af-bf69-adcc3ff9219a	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
bd503b5b-504f-48b6-acc9-9e0a2d74308a	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
8695e604-194c-4776-ab59-6e2c10013f13	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
11a904ba-c2e8-4eec-94b6-0f57dd95dff1	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
c5e30acd-de7f-4187-a1ac-2edf8a1c47c3	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
a239524a-a2fb-4ab1-944c-32b9c9c2d37c	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
3417c35f-11f2-461e-afcc-f4d703fea5e1	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
6dfc5d73-207e-4b00-877b-91cf3968cbde	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
58204c17-066b-4896-9b1d-8625e56d9c82	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
a0925d73-d897-4d02-b713-63f7dc3e5475	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
9fb0a97e-4801-4bf6-ae58-a51ced1142c1	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
28d67bd0-7302-41c3-bc8c-dea0ea59e5cd	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
0ed85e9a-becb-4798-9141-17fe2f77e018	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
541dac28-3444-4f3f-9ca0-b9deb8608b87	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
912324ef-6f4f-4c94-a2ca-7fd7657d4fc8	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
f4cf2b74-5496-4290-990e-6d39a6e53610	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
14be8987-c793-44d3-91ba-8c10d05e904f	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
0b322347-2448-46fb-81fb-ef878d1527b9	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
044336b0-abf4-4ca3-899f-6a0fdf7dba49	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
17c4f52e-d18d-471d-801e-0dcd850cdb16	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
4dfe7373-154d-457f-90de-1811a28c7e86	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
d23346f7-f79c-4143-8e81-cddaeb37f10b	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
d1f7254e-6e23-4aa6-9b56-13ef969bea46	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
1b5da58f-ff88-4910-a7fe-8ca8230a60cf	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
6f686273-22de-47c4-8a07-d3871207dfc0	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
fb968c37-72f2-4937-bb70-7bcb1423f0d7	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
7376d963-cf3d-416d-9b65-0d23292d9f3b	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
9a16b0e2-2086-4c48-b342-6c45c2ad011a	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
d1f13f10-78ab-4cf7-9a32-f26cca3edddc	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
f17f0904-8f02-4ca1-9adb-ae25a5dbed84	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
c92a8530-30ee-4397-bfaf-227ae9cd692c	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
f180e605-de2b-44d0-86b6-5154d03fbf5d	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
13f51065-734b-474e-80e8-bca70be7ef10	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
95501d00-2922-4e7e-9c38-35e27f92254b	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
7629b828-486a-4893-b4d9-dca1c39c8744	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
a3df2fd5-0d79-4a90-8a54-eada4958be2d	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
3532314d-e96e-4eed-9823-5d094cd83350	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
b8747c0f-d204-4af4-a935-d21b76d7316b	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
cad26953-18fd-43e9-93d5-bb2aaaec5fc0	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
75280cd7-e3c4-4f49-8ad1-fa47d2dfb836	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
b678a082-3b3b-426b-8b55-956039c3d4cb	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
448b1d50-eca7-42de-9b31-34c4ed3bb04c	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
2ee5483f-8c1c-4f24-be19-cb9b95729613	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
27f0a4d6-030a-42f5-a999-f97effbd7c5b	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
e26ea8f5-c4c5-423d-be7d-940e9574edfb	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
9926b983-ef17-4038-bf74-3a30de2f5c27	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
a824a784-9824-4337-b599-a71e22498955	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
a7c9ecac-1d9c-406f-b376-ec17eb2bbe06	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
0ccf651b-f6f9-4ee2-ae31-2e8446763ec3	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
ab8ec10e-7955-4321-9a37-08afbb904341	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
2659b484-c1b8-40b6-8671-d5d0cb29783f	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
326a5c14-fea5-4abc-9895-dccb8e111f7e	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
c82e5b0a-9b6d-4de4-8ad6-41a02413ada4	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
3dc85709-bfdb-4ba2-a1c0-bf523c20db23	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
68ed7054-ee98-4859-8bd7-6731b3718537	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
dfb79a93-a3e4-43a4-ab08-8fc68b001d9f	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
6ddd1cab-ef25-4dfd-b450-a6fb794062b1	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
e228ba44-c3f2-44bf-8cd0-0c2106766e80	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
4fc61632-93ae-4e25-a638-bc281bae8ff2	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
4e948bbf-cc7f-404e-b805-3b172193ddf5	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
1e523dbb-4e8f-4ad5-ae0d-39718e8a95a6	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
2b130b04-faf9-4a00-a51c-cc99ac84d1ef	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
027f9d1c-0cb6-49f4-857e-11ddf0f1b7ef	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
3011d0c3-b8f5-4534-b44f-4f939a7a94fd	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
b14d685a-d882-4419-825c-5c06cb4a1304	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
e4313dea-70b4-4fce-b2f8-2645c4f71142	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
cb364d4d-741e-4b61-9557-ae2a12cd656e	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
50998fe7-528e-4b5d-9dce-df5230d11a6b	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
9f49e10f-6cbb-4a81-8610-9379eb849803	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
ce6c04c2-016c-4443-809d-e9530acd1436	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
da7b5e89-6ba9-460a-8ec4-3e26b93529bb	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
871efc5f-3582-49f9-83bc-7906e852c3b5	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
9ecfbcaf-8468-42d3-847d-ea797f60388c	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
31913f69-a788-44f6-8fb2-babb53a9a111	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
4dc88f7d-7189-4cf2-8903-a9b096c2cb7d	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
58af0a8a-c052-4a48-a21e-949eb8032976	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
f6ed53b9-c96c-45c4-87d5-88939eea68fb	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
eb7cc57d-b391-4bd7-8720-7d2746b01ea1	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
b2b85e25-d142-4a58-8ee6-cf205da61e42	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
3e4f6be3-76f7-480d-b6b1-2000878f9547	f	\N	\N	\N	1	\N	\N	30b8d26b-4ff2-4ddb-a98b-d78f8601e56b	\N	\N
db720b05-8d29-421d-988a-07a683ef1b0f	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
3ea26a6a-2894-467a-8399-d5be4166bed7	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
59a19ad1-d9fa-425c-b549-72206fcda4c1	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
523813c4-13bc-4a84-972a-b951d2f34074	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
f2392e3d-5d6b-4898-a0a5-5a408d665dce	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
d7370ad9-fba6-4d1f-9813-93457642dd9b	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
107a3e76-89bd-4294-aade-c108cea50fdb	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
3771269c-d8df-496a-9e9d-c5d3a40576cd	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
7c942534-1e93-42da-b89f-5e022fcf8bb1	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
6010caee-130f-4472-8eeb-765a5d6f250a	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
e0d7b453-fcd0-4f79-8de8-b0012ecea3ae	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
75cd7773-eb84-4028-9c22-44557c5df1ab	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
247256fa-0ec3-470c-bf0f-7db47d6c84fb	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
48717164-adaa-4a5c-882d-c0e59cdb35a1	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
d81316c2-dfb6-4bac-bc1f-2384eb71b961	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
e528459d-effe-4814-b563-e21504ba093b	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
b5e36712-ae81-4fad-ac5a-5cb533c45d7f	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
c7c64837-c6cc-45fa-8cff-019257fa72af	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
a479db08-ed3b-435f-bbc8-7f4af0476acf	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
7205353f-760e-4f4c-93d4-9e9a030a3bd4	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
bb4fcd57-a5e9-4e85-b763-d4437a04c8f5	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
ee47e7ba-33dc-41f4-82f2-4d4f5b21e944	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
cccff5b6-077a-41b9-bdb3-69b6c6df38bc	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
ed866dfe-91ab-4f88-95d0-494697ea5f16	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
a32e7b0e-111f-4965-8ca9-8266d1310231	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
d2231d41-a6ef-4aac-bb78-707837d7ebdb	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
d394ab83-8dca-4445-8b51-eb72ed70d07e	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
9f657c04-7da0-4202-b407-1a0662a44465	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
e5b74d26-ebed-4eba-88d4-71a5bddfb03f	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
2ab676f9-eecf-49d4-9dc8-87cedab41ea4	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
a4171d9c-1fcd-4bce-8ceb-007c09d4103a	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
1fbbe1e9-1971-4437-b044-f01ae023d3ed	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
4a38491b-c982-481c-bb24-2da40e691e7a	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
2e681656-7f7b-45a1-a70e-6b133b8a4e41	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
f475b935-ceb2-45d4-bba5-d66ed7db940f	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
10caa812-b15d-4bfa-9a9b-431a3c78faf3	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
0b845718-51fa-4c05-ab6f-24a9fd609a3f	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
80b901a3-8449-4498-96ff-5b05c6b4e3e4	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
53647787-0246-40f9-8b3a-0ed4978384d7	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
46cfb12a-0dda-461d-8dd4-015ba3b90f87	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
6401b544-3f5f-4a73-8c3f-5a5f5e39c1d0	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
bef21875-8edd-4509-9a5c-98e0c80ff4eb	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
76e6805b-5310-4200-8788-3b4beea4d9db	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
b77c486a-84a6-41d0-803d-e2b03c963274	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
4319c494-99a9-4cb5-a8e2-33a67ea0841f	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
cbf5cc23-3ff3-438c-ab43-bd091a438a8b	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
6d9e7120-38ec-4afc-8143-58b602d17a72	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
dea84039-65bf-4f3d-911b-41dd93815264	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
1b23b212-d1cc-4f31-bf61-96ceb55936c2	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
b43218ba-bfe5-4361-9cad-1d57360d5182	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
31a76ec0-911f-4071-b582-0cda9ca38c79	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
ca662fe0-2fb4-4a6c-a18a-ef090dde8676	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
3731e6ba-4559-4852-957e-8381f92c32bd	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
1a580ba3-c19b-407d-aaa4-e81b492ec034	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
9d91357b-9a7a-4091-9cc3-69d4b47b9a5d	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
1996c2b4-7903-4685-a8a8-342f7cba5562	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
93f250d0-f344-4774-be9c-56e56d079e9f	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
96ab6d68-4231-4db2-8ede-455324033c9a	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
025957a9-0cb2-4d2f-ad78-8d19503768cd	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
2d477521-db04-4d57-b860-30b985b08a2b	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
62d52cdb-9520-4c21-90cf-b7c015fa0f2b	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
ed5ae4b4-a2ff-48e6-af8e-a950834815ed	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
b34389c0-8788-456a-b619-a2f14aa856ad	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
42181e67-06af-4203-8bbf-f687e5b83d4d	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
464db306-2aeb-4b54-852f-af4eaf7b300d	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
84b2bb88-3df1-4e0c-8ec8-fc8f1ac67d44	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
7bbcb2c1-3f52-4a2c-96bc-837eff33c4aa	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
c38b24a6-57c2-46da-83f0-96d9ae954353	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
56b5b3de-5c62-4407-9a40-be5c15081fc8	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
ae9c5ec1-f490-40ff-a34f-cba689bf055e	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
1c8afd99-23dd-40d0-bc85-ed944cb5feea	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
c730d4ac-ef22-4981-aae8-1d6acd024784	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
d6433f6e-4227-4693-b6bc-95d7019c24c9	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
e4bc934a-327e-4e99-99d6-953fcd2bc589	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
078327d6-7574-48d1-a84d-b6196dffbbd0	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
8458289d-34a9-47a3-b4da-171087324969	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
ecc6ab19-3823-4670-a718-c422cc585146	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
816140da-ce00-46ed-9df7-7367508dc9a3	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
ef9a2f85-b2e6-4593-ac5a-c48eb6e41bb2	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
1d575f49-2d35-4b5b-837d-898c0644ddf3	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
d9f7a4fb-3f65-4b30-876b-ca33fe4188aa	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
103457e4-78bc-4bde-90b4-75252ce5ec2c	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
ec7de4a6-2e14-4829-9676-3c58ad83b3ba	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
cd463ecd-aa90-45db-9e91-b94bdf50194f	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
34d249e3-bf44-4af8-aafc-4876dd87699b	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
ca4a2cb0-839c-4314-a35f-4e1c86b114af	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
bbe011f6-c33a-49e5-be5e-b3ae014149ab	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
09aa36b2-9cec-4f69-8300-7aaec6d1071f	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
fddb011b-a431-4ea9-94fd-6d95092ec14f	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
07ddd1ee-a96b-476d-821f-c5e802473dd6	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
23144556-ede9-4e61-ac48-f2d92d677555	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
97681121-24f2-4ba4-aa44-4280563862ec	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
31a962dc-94ed-4270-aa31-5090828f6450	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
75c6e527-daf4-4b4b-96e6-12e4c4f21dd1	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
1001c99f-178f-4004-b4f9-2e1270dd7a16	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
d7743ac6-8eed-4419-8536-bcbdd2a20bc5	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
f7c4a1e3-4ea0-4815-9e1e-7cf5abe8ea80	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
688226f4-80fa-457f-bfb3-cacc42eb1639	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
d7c503cd-a15a-449c-868f-e3c75c82f694	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
21ff779d-4036-4368-8224-0ae284eba70c	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
63d36e25-5cfc-4c28-908a-056595806b43	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
b4b98109-6e6c-44d9-906d-7b3a8583c1d8	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
6ff0b7ec-f504-4dcc-b0d8-c8a583630be0	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
4ef30e30-731a-4438-8531-cb0f2865eeb9	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
c2ce791f-c4bf-4d42-ba70-d3d6ece64934	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
20cfe2ae-919a-43fc-8d67-35a619ff5447	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
a3ee8157-dfd5-4f0d-965c-e422034a6d95	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
4f11d19e-1ed1-44a4-aab7-a9928e0d45c7	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
c65af8dc-a386-483d-86d0-359bda317802	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
a90b6a21-be14-4acb-bc6f-85708eee52cd	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
98ab9d19-d802-4e31-9b48-b5f8453e2a82	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
136c405e-e167-4b11-acb8-f12d21ca21d7	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
d2306033-71f6-4086-9ef5-446488099c0f	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
83913c1b-5f6a-4884-a4a2-0c51dca38e85	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
7480f31d-62cd-4802-9b63-24b6283d110a	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
4446bef6-f9bc-4acb-b2e5-d6e854304dea	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
c8450745-122f-4bd0-a049-d20cd9b9de9d	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
8a626d61-5932-48d4-a34e-b2eb2ecc849c	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
bf468e9d-ef58-4c27-8640-ca8f6814b975	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
79eb2b28-8058-45cc-9f6d-7f1913b53d87	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
2198e67d-cefe-4367-a956-09b62fb3bcf2	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
2f3e7020-20c5-489a-8a1f-aad259e6d88b	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
185b1c62-da8d-439e-95d4-65b2a04d3d38	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
0bba7acf-e3c3-4aa8-abef-642f242e293c	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
83d9087b-0a83-40db-be33-7bc589946f8e	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
43c9e4c2-9eef-445f-a0ae-e36a3c3e11b3	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
217f4f92-54e4-4db8-b539-2a1f0365c57f	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
644a6e39-56dc-429e-94e4-2f5dcebf61cf	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
0e366ca9-a746-4a5c-acd9-d8006a106e15	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
398bfbd0-862e-41d1-94b6-5b555335d2df	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
1a74dc63-2658-4607-a296-477165a27731	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
9469b6df-3e50-452f-97c7-e05450a9660a	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
af331b61-2663-43c4-b4f4-f53b4229e4dd	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
6822af29-4519-4600-82aa-66daf41ee0cc	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
53b4e946-e26a-4565-82f6-f78086f05cba	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
c513a994-a616-4122-b760-2446623d79fa	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
024dc9ca-c377-472d-81e2-1306de00a8db	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
c43505ee-c259-4f51-8405-cd338f4e0745	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
459e174a-2907-4118-8bab-91ae14e76964	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
2c71be31-036e-4db9-8c7f-92477190e378	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
77327f63-e553-48ee-9fe4-02d1337dbbdc	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
01dfd06c-0aa6-4bc8-a657-0677b39ef412	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
5e81f6d0-9e07-478a-bd23-885a51da7468	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
8fe118ed-bb5f-451f-b49c-531b43f5f009	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
091007f6-8b4c-4ff7-baac-837ef9833898	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
4a72dce8-85c5-449b-a7c8-6287f07e961f	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
0a73b4cd-409f-4717-80c0-aae1fe2ad39c	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
a4e42197-3ade-4b67-b9c2-2bfbf59b15a5	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
9a6e8bd3-918a-4293-ab56-d41a4acddb48	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
52e62051-d74b-4ff6-ac3a-fc6eddc50f42	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
70774daf-f2a9-42fe-add7-d88d02214796	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
5284b5a4-74b7-475b-b5f5-bb56e2abc661	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
52f9c1b7-a5e6-4dc6-8d40-d1a718c7c3d1	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
ac6d1dfb-c58d-4bef-8106-142e76fa999c	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
546c4284-712d-44ea-bbd4-10561af52e33	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
27ef269a-5477-4044-8b80-2a048ecbe4a1	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
8faf89fa-20ed-4e8c-a0c4-ca4976b7dbd6	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
f3ec623b-5da8-41dc-bcdf-8a468ec2a602	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
dcedafd7-5c2d-449c-b32c-67b6d144ceaf	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
48b13a0b-dba5-4268-83b4-d5a9da290eb0	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
0c9b4ed9-cc03-4989-b79c-5b61a5dda037	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
30eb97ba-63f2-4df2-9bfc-c18b384b7d38	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
bf5551c4-5000-4253-8842-d4f93a0f37de	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
9fb4747e-a711-4a4c-a6d1-04215787085b	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
f817a787-ee5b-444c-9cc9-2ef46697d79d	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
ad3de259-e954-4878-acf6-dd7f25d3adc7	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
e19ec2ff-e0b8-4ba0-b0cf-36a63b88ba65	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
2b1561cd-52a7-48f5-a26b-d7f57b4000b2	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
6cb69001-44a6-474f-8120-4052ebde3e95	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
b142b1e8-a9b7-4439-bbef-739bfbbf1711	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
a46e060d-3651-40d0-ac5b-3f91bb19e98f	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
f0cb67ac-6a09-4e0a-b8d3-f147cc82c015	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
d8b09802-52ec-4c60-be3c-b25e271892ee	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
842755a9-dcf5-4aa4-ac8b-97e5ecdce439	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
1c753f33-f8d0-49d4-9769-52b8614d45f7	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
eaac1231-3e1e-42e0-b37e-55b0803b2e81	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
0211bddc-3b0f-4ba0-b035-1ed86934fcbe	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
ed9f2117-3dd6-4221-a4db-0ffe92707f4a	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
64b6498b-c400-453f-89fb-ae3ab18dd99b	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
84a599ae-67f5-42fa-b584-3442414fb55f	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
f0ac1bde-1e9c-4b0e-aeca-284a8bba6fee	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
1b509281-85ad-4462-bf62-2235fb8d6e2d	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
ac26422e-9b84-484b-8e0f-c4839217e01c	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
81cd491f-5f5a-4900-b0bb-6a9a9f47b6d1	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
f816aaad-dd85-42d6-befe-060f05b5525d	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
332dca49-e58c-4894-ac71-f397b962aeec	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
a4a5a0ad-8d44-4097-bb02-f2c725b7b0eb	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
996d0bb5-92ae-42a7-b7c5-cdee83af4f8b	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
6cb4e6a1-71fc-4ebf-8537-e9af7a799b7a	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
281c473e-1189-4914-9379-f4c9d30477b2	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
014bbd6a-50f5-4d12-9cf0-1739b835398b	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
8295460e-3003-481e-a209-fcd42ffbd296	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
17780a8a-93be-4828-a3e7-1b4eb5c5547d	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
e1f7aafe-3cb3-4f48-ba66-d571d6918caf	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
9aac35e0-f278-4553-8920-3d6e73325a6f	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
1f7badc4-e82e-4bcf-b4d4-9b4f75ee7d0e	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
ab4bde6d-246c-425e-8fc2-8494ea07300f	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
74bcbec2-eb56-4079-a56d-10ff7e513742	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
d66dd3fd-4f4c-48a0-a967-70ab13e17ebd	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
26bdae80-0282-466c-a8b2-f3eadaf319e6	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
20813fe6-6485-47e2-9fd8-623542722ead	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
2a151b45-ddc7-4397-93b8-f57c13ea87a5	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
6e0da21e-49bc-4579-b87e-5b4d6b4a08d3	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
843fc3d9-710d-48be-940b-7d904aff1809	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
93daeccc-3278-46a3-97e8-e1f66f15b0c7	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
49e5f43c-d2c6-4975-8c0c-f71b8435bf7b	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
38037940-0a24-4b44-ac5f-89b01048e5cc	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
5651fc81-ff36-4962-969b-9e90d983c0ea	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
f943688d-ecfd-4df3-9ee1-6706e69d9c79	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
d0ea4217-dc7c-4f31-b6ce-7500d9791922	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
72493739-dcba-4bff-9074-9299de836446	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
b1393ecd-89a8-4dbb-85e7-4a51c45879a0	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
2096b9f3-e755-4bb2-ba11-c99ec29f2423	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
5dff6e08-d627-4b7a-939a-0da07ca60ca8	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
28b56d22-de08-4d18-a901-36754cf37ae7	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
d233c5e8-a367-49c4-a21a-ed80cdc62a74	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
117d6c2b-ec2e-46d0-bf18-3e61134f95e4	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
c9293110-ee1f-4e84-a580-6ad6380730a6	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
9ae5876a-e524-47f4-a244-44a6683776c8	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
2325a45a-ccc6-49bb-bdd4-915f0aade152	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
e7d9eeee-b862-41a4-9e84-75dada21f4a9	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
5305aa67-29fc-4fdc-b7f7-ffd6d2c8912c	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
48772927-1846-4ca1-a742-18396cf885ff	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
4243ca8f-2613-45e7-992a-85d1a605b61f	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
c931bc68-b56e-4af5-84f4-684bb4a6362d	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
0f54dbfc-783c-4946-aaf3-79cd92eb7089	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
687d6360-04e5-48c2-8b0d-f81a81f8dae4	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
ba480f99-ba75-4400-adbb-549bdc3614db	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
642afba9-28b0-4487-9c0d-c934ab0faa56	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
fb87f886-8aab-43c6-b0db-653e3294392a	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
dd59ea49-b8e6-4920-b453-73f9d8787e6c	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
b74bed47-e663-41d6-b3aa-a8fb9779517a	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
338ee107-7375-44e9-a768-ee158bed34fb	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
c9214264-5e31-47c4-8579-da7feda1069f	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
48a13d7f-3ee5-4e9c-bc31-234fcd4a9604	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
fca1dbae-fb2c-462f-b09e-dbddaf83876c	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
78c82db1-6819-4638-9022-12d111212fd5	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
37b081fe-3151-434d-a946-bc3c0e2dec01	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
ce4f2204-f144-4c5b-9b50-6b48e3332d12	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
fd87067d-1638-4613-9a4b-2e5c85877cf1	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
ab8fb3f3-8c57-44dd-aa3e-715828634816	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
ce996773-6506-4f61-8f6d-15fa4e5e48e9	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
79bd80a3-a8d2-474d-8fe0-56833d404201	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
50ef0086-4ae1-469b-9ebd-ed73b93091ab	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
8e4fc7cb-11c0-45a5-94be-f8b19e44db2b	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
2ef09891-b718-4de8-84df-d1f3e6fe0e08	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
4ac74cb0-0386-4b0c-96ec-9930bf9bc8c0	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
4d16c91d-17d6-421c-bc33-15a3110e5447	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
baad6362-f554-4c54-ac35-3d1d7e31de4f	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
5cfb9cbb-d5d7-4f3e-98ef-467bac705197	f	\N	\N	\N	1	\N	\N	5f391f63-72b6-4ed8-9fef-6297ead0764b	\N	\N
f45e177f-b58e-44ab-8706-76306c26c438	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
ab768f12-46ab-4bf7-bc7b-fb7ede3fa510	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
5a1d81f2-2b41-46af-975d-4e9a84066741	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
16d6befe-3b21-47b5-b7e8-66354651c824	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
b4918128-ca1d-45d6-b77f-928e703347b6	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
83674aa5-48ba-4ba8-a0f2-fded52983e16	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
382788d1-f55d-47b7-bf93-a74d98eed3d5	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
94387b47-1705-40cc-9add-e017a5579165	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
21fc8ace-5735-4c29-bb79-d0435a1e55fa	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
6c399ccc-ca5e-47de-b848-15eb369f2052	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
1811171e-81a7-4f4b-8d03-192d546bb86c	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
cc894b8d-44f0-4485-a9db-7b89becaad48	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
5aca72ce-1332-45d3-a762-481f6f7b76ea	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
64cbc467-0279-4db5-a097-0db8de15a70c	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
faa0eae7-c89e-4b24-b3b5-545da016f46d	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
8e943447-cf57-48e8-b43e-9ac4ba0d2222	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
e793d348-a090-420a-86ac-88c69812b76d	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
e3f47f01-5fa1-48a7-8d49-d9db88f3e898	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
73609610-2fb8-4a6b-a21c-35b39ad03569	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
51cb232e-c1c8-45bd-ba9c-3e6e12757f74	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
7a5fb995-b909-44a3-a082-a758f701cf0c	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
d596d1ad-0621-4625-81b7-c97c11938be2	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
3b1afdb8-99a2-4b21-8685-2c4efa4adc24	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
22b402da-8f9d-4518-ad86-2137c8602eca	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
ee0fe69c-f4ed-427a-8863-c321c1f83d32	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
978475fc-360e-47f8-a04d-efc9145519bd	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
7baab668-5dd0-4354-b9a7-5e992d158055	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
744ab225-7e35-46fe-a41f-0f7b165f31d9	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
300d48ad-438e-4444-b2b8-238e575251cb	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
54bf45b4-cf01-4d28-81d5-8a82e4e447cb	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
3d4f5535-2900-41f3-b182-05931276b5c7	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
a0b71c51-567d-42b1-9143-26da8b5db2f9	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
23d83ae0-4530-4bfa-b0fa-de2da4cfe090	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
caf3efd1-b73d-464f-a0df-caa6e9e9266a	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
405a5979-91ba-4aee-977f-7eb47fdbf647	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
09c94e71-2041-4895-afa7-9dce2c89349e	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
7ee0a2ea-bf6c-4f0c-b851-65a325302062	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
c5472f73-8cd7-480f-8b48-19f37158e566	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
17a444b0-32fa-49a9-aa4c-8c822759f37f	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
bbe7cf7d-5be5-4c7b-b624-bda8e5e913a4	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
fc3bf0ea-9b46-4b1c-abf2-54c2be9bb0f7	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
6b6ea38d-a9ef-4383-8e42-90cb1f3fcb11	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
d36cee20-6533-4bb9-a5c6-3cd6f06c63dd	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
3e54bbb4-7c6a-4488-86dd-9febd0676806	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
71815f9b-10de-4e50-9361-cee448685876	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
8cdfa9e6-1b39-48f7-9c98-d9f5bfa02316	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
86be5b03-61b6-4ace-a217-cce0e02edf87	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
90cb0669-9205-4aa3-909a-728e0862a12f	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
981a6bc1-23c9-442d-8e13-d6254d1ad1e5	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
bc1060bc-6a57-4203-bcd7-73da2be696f0	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
1a54ff6c-7d58-4b5f-935b-025e5220680d	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
342c340d-0a6e-42c0-970d-d9fa79e416f2	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
cebb3bcd-b722-49b7-b554-ec2d80457c96	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
9d110cce-c39b-40ab-98a4-1e487c9d8607	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
d23af5ce-47d9-4e5a-9a55-cd64c57fcf28	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
185f907e-1fbc-494b-a423-5d25dd3d9062	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
918b90a1-ce04-440a-adb6-23b42e1b55d8	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
46a28981-f372-4942-961d-12946475af10	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
867779b0-2425-4a5d-aafd-fe69d9c2f85e	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
350e250e-12b1-4baa-8ea1-1322d51cba3f	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
7c67530a-4ce8-4eef-9059-9ab5bddd6544	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
ee34bb09-0ac4-4fca-9f8b-160ad369b47f	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
b997d581-2eec-4ea4-b3ed-2e4724eddc16	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
ccd10dec-5f09-44d2-943e-3707476a39dc	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
60d3425d-b3b1-40d2-a717-346f8afb7f7f	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
f622b8f8-bba2-4762-9683-4976c1638ab5	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
9be14a53-d9ea-4a93-879a-07c2570b9b69	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
e2f0d47e-eac7-4417-83d4-6ba449a475f5	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
ec44da2c-05c4-490c-9e37-5f721edb3983	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
2f9c0091-3808-41ee-a9de-8d07008a3512	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
5e1d8d23-2230-486d-979e-7056429fb5c6	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
44224aa5-a97e-4d0c-ba49-ceecb6ad9c17	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
344bc2ab-f6d9-4dde-92f1-7dc945d224af	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
170af5d8-4e71-43d1-bf05-54ca26422ef3	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
6d95d9a8-4176-416b-891a-9e85dc0cd79d	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
d6e94b41-40ec-4a84-abe4-c02a869480ab	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
379853c2-fe96-4b22-a1f7-72af06a8269d	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
41b6e0f3-0be8-4bb7-b5fd-506f65214bbe	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
18e2a17c-45c5-4dec-bd25-39ffe9dd0c7e	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
cc26a27b-5b65-41fb-ad33-51b23bdbf5f3	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
def78b5e-67b3-4295-aff7-b55cc534b380	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
97d2843c-33bf-4682-a9cf-916c540b5924	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
4065129a-824a-4565-89af-0f2d5a555c65	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
713f6f1a-2381-4fc1-98a7-25a19ac89965	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
b954f181-dc7c-421d-88d4-d9f7bfc952e9	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
eff61d20-5625-47d1-ab79-47b00fa5601c	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
71284910-c8c1-465b-9ffc-1f0f464ac5fc	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
38070980-31f4-4d4c-acea-a2d00c5e8dde	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
259df730-e9a9-4856-ac68-53972a77c4fb	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
f9ea2c98-1cc8-4de2-99ff-ed7a5724b57b	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
971cd90b-affc-45d8-868b-f0258f895d19	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
2040bf50-f88e-484a-80d6-6324152d7ff3	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
320a83b9-867a-4303-8181-d2022edd348f	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
8c7e4575-6dca-4ad9-b179-edbea9d49ae2	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
f2ce624a-6883-4b6f-92ae-812d856908c3	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
f48c8c69-adee-44dc-9893-7f8feb4f731f	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
65a8820b-44c2-4cf9-80e5-48826a581c00	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
d3f88704-d6dd-4afe-944e-d8105192080a	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
ab46302f-344b-4d53-a707-a744a89d88c0	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
6c1a3c7d-37e5-48f7-8d3d-854db9b31e71	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
1f27fe8a-8e63-4da8-ace7-abe2d6d1d159	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
ce8122c5-798d-468d-b67e-0ecc25760093	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
1622e27b-a4d8-4b53-a0d5-f62116ff579b	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
cc2bc222-2725-4d32-931a-f99f63518332	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
3b218785-c1e3-4fd4-8997-8a8e9da614ec	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
f0ebb354-dc53-4824-ab2a-a5aedbf9160d	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
2e6092a0-6b86-4906-8e01-97e48e1cefe8	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
25c27a40-3fe6-4718-881b-0ed4ece965a7	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
31ac7ba4-a16b-4e98-83ca-060b28418c89	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
ebac03f7-b635-4ea4-af95-717564f13771	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
c3351a7e-78e2-41d2-8ec6-fcc3e4d19e02	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
1cb386aa-ee65-4e26-b3be-d15435913489	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
49203b17-7521-4906-a9a0-2451bdacfbcb	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
b8baa541-2e0b-4001-b793-57baa9c962c7	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
094dce1c-db89-4c34-9ac1-52ca13b40c62	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
524ad578-5026-4a91-b5ec-4cccd345fe31	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
37aae5f3-76ef-4e70-9a50-76504e4f8504	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
ee57ded4-811d-4e07-a5fd-1fecf8888295	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
2f8e3160-4078-4372-b1c1-14ae6541b71b	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
82e32486-c3bf-4aa1-84b7-764b6e4630f5	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
404ba873-b3ff-463b-86c6-3daf90ac2567	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
f88147af-a96f-4d9b-bf07-39d414746b6b	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
f1454041-f3ab-4735-963b-befa307c072b	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
00203dbe-1723-47d4-aa03-698683615fc3	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
638c2498-758b-4a1c-b9e2-ad4978c7ff95	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
ce2437dd-38e1-4f58-b5a9-fd2b97d471f5	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
03ed960b-d277-444f-99cb-679bf435102f	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
0b27bda1-3581-4bb6-b131-f00ffae3fe57	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
491b112f-9b31-403b-b641-9d1940a01124	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
7757ffbe-e1e4-4de1-ac89-b822def8a96b	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
2ef9b21a-a329-413a-985f-b7a7b975d1ea	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
ee3b67b9-6db9-4bce-8eaa-0b21716860dd	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
ff66a2c4-dd55-4537-b276-83a436b181b0	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
9c08c343-c0a5-4b53-8b21-51284f7b41fa	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
9e1bc66d-fb8e-4473-893e-45fa5ef142bf	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
9f4563c2-edc2-44e3-9186-3ad5057cd531	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
fa8c1ff8-d46b-4a1c-8f13-77df8317f74b	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
00523e52-3ba8-4f1f-93d4-dca4882a2b1e	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
daae0b2c-7c85-40ac-9230-395e51ba8905	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
c48207cf-c80c-4c19-9d7a-baaf7bcb7c32	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
72303a83-3919-46b4-b59b-427145a4ff6f	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
35266606-d580-417f-84cd-fe6353b56fb0	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
8bfd92af-8f04-441b-a56b-edde70712019	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
20f4ee5c-1994-4d65-9c95-fd6683395ffe	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
f23d0bb1-18eb-46ed-aa88-092b92dc0433	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
6a4176ce-5ac5-4a09-8ebb-b4abcc5114e4	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
1b22d5ba-564f-4629-9a2a-64a5ee9e7d65	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
abd6e005-8dcf-42eb-be6a-b2896762a0d5	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
31f4a005-3186-4c14-8d6f-e0be718ff1a8	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
0774af6c-210c-4f96-80f8-861302cb9fc0	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
5ace4f8b-1381-4fb0-a453-ba17b3c4f3c8	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
49f83204-af6d-4138-bcb6-8b94ea85e1b2	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
3a13d7fe-2daf-4221-b5f5-1380d71bb714	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
f81aba51-d678-4e5f-aaa5-5ba9c4400fbb	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
b6ca0166-6bb0-427b-9508-1198c3c6c499	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
6f1b6ee1-151b-4ec8-a399-908ee4872c1a	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
db3e96db-7a1e-48e4-85d2-d69f60b684c9	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
63207921-27f7-4aa1-99f8-cab33fc4bd1d	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
bd909478-0136-47b4-82cb-a834f9827759	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
e09214b8-8081-4fb3-a645-8bd6eae568c7	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
0800cfcd-831a-49d1-af70-2bbc76ca3440	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
45230654-3519-4d03-926f-72f557c8ea0f	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
ad0e8e63-0fbf-44b9-9a4e-cb14f9660159	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
943b45da-dab5-4a5c-852f-574f438cf6dd	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
7752c31d-5365-4352-b7ec-fac02733b725	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
bb422bc1-7c6b-4347-a926-c0fa90f81c83	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
1045bf1a-4794-417c-8ef4-e8c00b574383	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
7a0bcd90-c52b-40c1-a63f-0c341a697426	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
5d369980-c399-4a9d-bcc7-bd14e576b182	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
b33dda61-6adc-4981-aeba-d82cfb48fa19	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
79b2caf5-8113-4cad-ab6f-3edf7f1f4849	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
e2bb2464-49b1-44a7-a310-de606261e3a7	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
39fb5aca-b649-4dae-8762-b1ae4b55dd90	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
7926f740-3ce1-4fd6-ad7a-762946e36887	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
64ba2ee3-3bc3-4c9a-a991-e115a48a4035	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
87eed2b0-134f-4364-a39e-62b61651d6d4	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
84acfc5a-ea84-4693-9689-5ad55912f8cd	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
70a1d2cf-330a-4677-9892-1cefba15b927	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
73aa8034-799f-4ea9-9318-8a40286f4808	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
0cf5236d-bbd8-4cc4-baee-700bca078c5e	f	\N	\N	\N	1	\N	\N	77c4a348-2fce-4457-9696-e5fd73d6ab5b	\N	\N
\.


--
-- Data for Name: user_permission; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_permission (user_id, permission_id) FROM stdin;
2f8762d4-e3fd-477e-aa8c-9989c376e198	39e951f9-d7cd-4753-a310-114911ef0ae7
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (user_id, account_non_expired, account_non_locked, cpf, credentials_non_expired, enabled, password, username) FROM stdin;
2f8762d4-e3fd-477e-aa8c-9989c376e198	t	t	123.456.789-09	t	t	{pbkdf2}8ff14360a447cc26bc370b21ff1c55c75d72b336989b9acfac01668fa7a8ad59ae1da85ae7c8957126bc291eed1870b92b097bdef03dda9c2133ce2b695a5715	original_admin_user
\.


--
-- Name: clients clients_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (client_id);


--
-- Name: events events_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.events
    ADD CONSTRAINT events_pkey PRIMARY KEY (event_id);


--
-- Name: finances finances_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.finances
    ADD CONSTRAINT finances_pkey PRIMARY KEY (finance_id);


--
-- Name: payments payments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payments
    ADD CONSTRAINT payments_pkey PRIMARY KEY (payment_id);


--
-- Name: permissions permissions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permissions
    ADD CONSTRAINT permissions_pkey PRIMARY KEY (permission_id);


--
-- Name: tickets tickets_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_pkey PRIMARY KEY (ticket_id);


--
-- Name: users uk7kqluf7wl0oxs7n90fpya03ss; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk7kqluf7wl0oxs7n90fpya03ss UNIQUE (cpf);


--
-- Name: tickets ukm6m4oxsunysar1ae2354y12m1; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT ukm6m4oxsunysar1ae2354y12m1 UNIQUE (payment_id);


--
-- Name: events ukpcamrqka0hx0ag662gos0jtvy; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.events
    ADD CONSTRAINT ukpcamrqka0hx0ag662gos0jtvy UNIQUE (finance_id);


--
-- Name: users ukr43af9ap4edm43mmtq01oddj6; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT ukr43af9ap4edm43mmtq01oddj6 UNIQUE (username);


--
-- Name: clients uksrv16ica2c1csub334bxjjb59; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT uksrv16ica2c1csub334bxjjb59 UNIQUE (email);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- Name: user_permission fk1r9shydjvgeefuwsrhrcqtkxd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_permission
    ADD CONSTRAINT fk1r9shydjvgeefuwsrhrcqtkxd FOREIGN KEY (permission_id) REFERENCES public.permissions(permission_id);


--
-- Name: tickets fk3utafe14rupaypjocldjaj4ol; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT fk3utafe14rupaypjocldjaj4ol FOREIGN KEY (event_id) REFERENCES public.events(event_id);


--
-- Name: tickets fk4eqsebpimnjen0q46ja6fl2hl; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT fk4eqsebpimnjen0q46ja6fl2hl FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- Name: events fkgjifwpv492885yhsi6khwcmjd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.events
    ADD CONSTRAINT fkgjifwpv492885yhsi6khwcmjd FOREIGN KEY (finance_id) REFERENCES public.finances(finance_id);


--
-- Name: events fkh535ibmxvgfxkqkmoo8uyn193; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.events
    ADD CONSTRAINT fkh535ibmxvgfxkqkmoo8uyn193 FOREIGN KEY (client_id) REFERENCES public.clients(client_id);


--
-- Name: user_permission fkn8ba4v3gvw1d82t3hofelr82t; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_permission
    ADD CONSTRAINT fkn8ba4v3gvw1d82t3hofelr82t FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- Name: tickets fkryn3ko9bmcvl6ulh796fjsw9m; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT fkryn3ko9bmcvl6ulh796fjsw9m FOREIGN KEY (payment_id) REFERENCES public.payments(payment_id);


--
-- PostgreSQL database dump complete
--

