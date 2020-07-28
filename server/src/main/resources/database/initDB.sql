/*CREATE TABLE IF NOT EXISTS public.data (
    id integer NOT NULL,
    name character varying(255),
    hand_raised boolean,
    loged_in boolean,
    PRIMARY KEY (id)
);

CREATE SEQUENCE IF NOT EXISTS public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.history (
    student_id integer NOT NULL,
    actions character varying(255),
    PRIMARY KEY (student_id),
    FOREIGN KEY (student_id) REFERENCES public.data(id)
);*/
CREATE TABLE IF NOT EXISTS public.data (
    id integer NOT NULL,
    hand_raised boolean,
    loged_in boolean,
    name character varying(255),
    PRIMARY KEY (id)
);

CREATE SEQUENCE IF NOT EXISTS public.data_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.history (
    id integer NOT NULL,
    action character varying(255),
    action_date date,
    student_id integer,
    PRIMARY KEY (id),
    FOREIGN KEY (student_id) REFERENCES public.data(id)
);

CREATE SEQUENCE IF NOT EXISTS public.history_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




