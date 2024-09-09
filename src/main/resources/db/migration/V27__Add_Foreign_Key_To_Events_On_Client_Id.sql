ALTER TABLE ONLY public.events
    ADD CONSTRAINT fkh535ibmxvgfxkqkmoo8uyn193 FOREIGN KEY (client_id) REFERENCES public.clients(client_id);