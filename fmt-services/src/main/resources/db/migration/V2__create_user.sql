CREATE TABLE USER
(
    EMAIL    VARCHAR(255) PRIMARY KEY,
    NAME     VARCHAR(255),
    PASSWORD VARCHAR(255)
);
INSERT INTO USER(EMAIL, NAME, PASSWORD)
VALUES ('USER_ONE@FMT.COM', 'USER ONE', '7c6a180b36896a0a8c02787eeafb0e4c');
INSERT INTO USER(EMAIL, NAME, PASSWORD)
VALUES ('USER_TWO@FMT.COM', 'USER TWO', '6cb75f652a9b52798eb6cf2201057c73');
