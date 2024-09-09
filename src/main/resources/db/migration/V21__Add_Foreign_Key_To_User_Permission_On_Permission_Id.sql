ALTER TABLE ONLY public.user_permission
    ADD CONSTRAINT fk1r9shydjvgeefuwsrhrcqtkxd FOREIGN KEY (permission_id) REFERENCES public.permissions(permission_id);