ALTER TABLE clips DROP CONSTRAINT clips_country_id_fkey;
ALTER TABLE clips DROP CONSTRAINT clips_performer_id_fkey;
ALTER TABLE clips DROP CONSTRAINT clips_style_id_fkey;
ALTER TABLE artists DROP CONSTRAINT artists_country_code_fkey;
ALTER TABLE voting DROP CONSTRAINT voting_clip_id_fkey;
ALTER TABLE user_role DROP CONSTRAINT role_forkey;
ALTER TABLE user_role DROP CONSTRAINT user_forkey;
ALTER TABLE comments DROP CONSTRAINT comment_clip_fkey;
ALTER TABLE comments DROP CONSTRAINT comment_user_fkey;
ALTER TABLE voting DROP CONSTRAINT voting_clip_id_fkey;
ALTER TABLE voting DROP CONSTRAINT voting_user_id_fkey;


drop table clips;
drop table artists;
drop table styles;
drop table countries;
drop table user_role;
drop table roles;
drop table users;
drop table comments;
drop table voting;

create table countries
(
	id serial primary key,
	code_id character varying(2) unique NOT NULL,
	name character varying(255) unique Not null
);

create table styles 
(
	id serial primary key,
	name character varying(255),
	description text
);

create table artists
(
	id serial primary key,
	name character varying(255) unique NOT NULL,
	site character varying(255),
	biography character varying(255),
	country_id integer,
  CONSTRAINT artists_country_code_fkey FOREIGN KEY (country_id )
      REFERENCES public.countries (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

create table clips 
(
  id serial primary key,
  name character varying(255) NOT NULL,
  url character varying(255) unique NOT NULL,
  artist_id integer,
  style_id integer,
  country_id integer,
  CONSTRAINT clips_country_id_fkey FOREIGN KEY (country_id)
      REFERENCES public.countries (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT clips_performer_id_fkey FOREIGN KEY (artist_id)
      REFERENCES public.artists (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT clips_style_id_fkey FOREIGN KEY (style_id)
      REFERENCES public.styles (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT clips_unique_url UNIQUE (url)
);


CREATE TABLE users
(
  id serial NOT NULL,
  name character varying(255) NOT NULL,
  email character varying(255) NOT NULL,
  password character varying(255) NOT NULL,
  active integer,
  CONSTRAINT users_pkey PRIMARY KEY (id),
  CONSTRAINT users_email_unique UNIQUE (email)
)

CREATE TABLE roles
(
  role_id serial NOT NULL,
  role character varying(255) NOT NULL,
  CONSTRAINT roles_pkey PRIMARY KEY (role_id),
  CONSTRAINT roles_role_unique UNIQUE (role)
)

CREATE TABLE user_role
(
  user_id integer NOT NULL,
  role_id integer NOT NULL,
  CONSTRAINT user_role_pmkey PRIMARY KEY (user_id, role_id),
  CONSTRAINT role_forkey FOREIGN KEY (role_id)
      REFERENCES public.roles (role_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT user_forkey FOREIGN KEY (user_id)
      REFERENCES public.users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
----
Roles

INSERT INTO public.roles(role_id, role)
    VALUES (1, 'ADMIN');

INSERT INTO public.roles(role_id, role)
    VALUES (2, 'USER');


CREATE TABLE comments
(
  id serial NOT NULL,
  clip_id integer NOT NULL,
  user_id integer NOT NULL,
  comment text NOT NULL,
  created timestamp with time zone NOT NULL,
  updated timestamp with time zone NOT NULL,
  CONSTRAINT comments_pkey PRIMARY KEY (id),
  CONSTRAINT comment_clip_fkey FOREIGN KEY (clip_id)
      REFERENCES public.clips (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT comment_user_fkey FOREIGN KEY (user_id)
      REFERENCES public.users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)

CREATE TABLE voting
(
  id serial NOT NULL,
  user_id integer NOT NULL,
  clip_id integer NOT NULL,
  CONSTRAINT voting_pkey PRIMARY KEY (id),
  CONSTRAINT voting_clip_id_fkey FOREIGN KEY (clip_id)
      REFERENCES public.clips (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT voting_user_id_fkey FOREIGN KEY (user_id)
      REFERENCES public.users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)