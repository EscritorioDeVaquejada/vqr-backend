ALTER TABLE ONLY public.events
    ADD CONSTRAINT fkgjifwpv492885yhsi6khwcmjd FOREIGN KEY (finance_id) REFERENCES public.finances(finance_id);