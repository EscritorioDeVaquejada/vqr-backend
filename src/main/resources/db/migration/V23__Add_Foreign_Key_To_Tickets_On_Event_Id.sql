ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT fk3utafe14rupaypjocldjaj4ol FOREIGN KEY (event_id) REFERENCES public.events(event_id);