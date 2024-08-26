ALTER TABLE ONLY public.user_permission
    ADD CONSTRAINT fkn8ba4v3gvw1d82t3hofelr82t FOREIGN KEY (user_id) REFERENCES public.users(user_id);