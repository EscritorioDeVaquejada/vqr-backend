ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT fk4eqsebpimnjen0q46ja6fl2hl FOREIGN KEY (user_id) REFERENCES public.users(user_id);