CREATE TABLE IF NOT EXISTS data
(
    id    INTEGER PRIMARY KEY ,
    name  VARCHAR(200) NOT NULL ,
    token VARCHAR(48) NOT NULL ,
    hand_raised BOOLEAN NOT NULL
);
CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;