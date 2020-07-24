CREATE TABLE IF NOT EXISTS public.data (
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
);





