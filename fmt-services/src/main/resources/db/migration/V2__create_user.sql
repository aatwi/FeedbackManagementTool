CREATE TABLE USER
(
    USER_ID  BIGINT PRIMARY KEY,
    EMAIL    VARCHAR(255) PRIMARY KEY,
    NAME     VARCHAR(255),
    PASSWORD VARCHAR(255)
);
CREATE TABLE SESSION
(
    ID         BIGINT PRIMARY KEY,
    NAME       VARCHAR(255),
    START_DATE DATE,
    END_DATE   DATE
);

