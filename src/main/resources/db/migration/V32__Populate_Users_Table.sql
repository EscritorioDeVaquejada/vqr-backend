COPY public.users (user_id, account_non_expired, account_non_locked, cpf, credentials_non_expired, enabled, password, username) FROM stdin;
2f8762d4-e3fd-477e-aa8c-9989c376e198	t	t	123.456.789-09	t	t	{pbkdf2}8ff14360a447cc26bc370b21ff1c55c75d72b336989b9acfac01668fa7a8ad59ae1da85ae7c8957126bc291eed1870b92b097bdef03dda9c2133ce2b695a5715	original_admin_user
\.