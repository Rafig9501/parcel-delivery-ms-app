CREATE SCHEMA IF NOT EXISTS COURIER_MS;
SET SCHEMA 'COURIER_MS';
CREATE TABLE IF NOT EXISTS USER_MS.USER_
(
    ID_            UUID PRIMARY KEY            NOT NULL,
    USERNAME_      VARCHAR(256)                NOT NULL,
    PASSWORD_      VARCHAR(256)                NOT NULL,
    NAME_          VARCHAR(256)                NOT NULL,
    SURNAME_       VARCHAR(256)                NOT NULL,
    ROLE_          VARCHAR(32)                 NOT NULL,
    PHONE_         VARCHAR(256)                NOT NULL,
    CREATION_DATE_ TIMESTAMP WITHOUT TIME ZONE NOT NULL
);
CREATE INDEX IF NOT EXISTS PHONE_ID_I
    ON USER_MS.USER_ (PHONE_);
CREATE INDEX IF NOT EXISTS USERNAME_ID_I
    ON USER_MS.USER_ (USERNAME_);