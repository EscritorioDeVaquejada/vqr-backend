ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT fkryn3ko9bmcvl6ulh796fjsw9m FOREIGN KEY (payment_id) REFERENCES public.payments(payment_id);