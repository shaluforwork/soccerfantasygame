CREATE SCHEMA IF NOT EXISTS FANTASYSOCCER;
USE FANTASYSOCCER;

CREATE TABLE IF NOT EXISTS USER (
	ID int primary key auto_increment,
	USER_ID varchar(200),
	EMAIL varchar(200),
	ENCRYPTED_PASSWORD varchar(200),
	TEAM_ID integer
);


CREATE TABLE IF NOT EXISTS TEAM (
	ID int identity primary key,
	TEAM_NAME varchar(200),
	COUNTRY varchar(200),
	TOTAL_VALUE bigint,
	BUDGET bigint
);

CREATE TABLE IF NOT EXISTS PLAYER (
	ID int identity primary key,
	FIRST_NAME varchar(200),
	LAST_NAME varchar(200),
	COUNTRY varchar(200),
	BIRTH_DATE date,
	MARKET_VALUE bigint,
	POSITION varchar(200),
	TEAM_ID integer
);

CREATE TABLE IF NOT EXISTS TRANSFER (
	ID int identity primary key,
	PLAYER_ID integer,
	FROM_TEAM_ID integer,
	TO_TEAM_ID integer,
	BUYING_PRICE bigint,
	DATE_TIME timestamp
);

CREATE TABLE IF NOT EXISTS TRANSFER_LIST (
	ID int identity primary key,
	PLAYER_ID integer,
	ASKING_PRICE bigint,
	TRANSFERRED boolean
);
