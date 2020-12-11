drop table if exists PLAYER;

CREATE TABLE PLAYER
(
    ID                 BIGINT AUTO_INCREMENT,
    NAME               VARCHAR2(50) NOT NULL,
    MOVEMENT_ALLOWANCE INT,
    STRENGTH           INT,
    AGILITY            INT,
    ARMOUR_VALUE       INT,
    PASSING            INT,
    PRICE              INT,
    PRIMARY KEY (ID)
);

INSERT INTO PLAYER(NAME, MOVEMENT_ALLOWANCE, STRENGTH, AGILITY, ARMOUR_VALUE, PASSING, PRICE)
VALUES ('Mighty Zug', 4, 5, 2, 9, 6, 260),
       ('Griff Oberwald', 7, 4, 4, 8, 4, 320),
       ('Helmut Wulf', 6, 3, 3, 8, 6, 110);