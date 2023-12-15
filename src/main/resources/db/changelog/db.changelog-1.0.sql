--liquibase formatted sql

--changeset artur:1
CREATE TABLE  IF NOT EXISTS task(
    id BIGINT PRIMARY KEY auto_increment,
    title VARCHAR(32) NOT NULL,
    description VARCHAR(128) NOT NULL,
    status VARCHAR(16) NOT NULL,
    priority VARCHAR(16) NOT NULL,
    author_username VARCHAR(64) NOT NULL,
    executor_username VARCHAR(64)
);

--changeset artur:2
CREATE TABLE  IF NOT EXISTS users(
id BIGINT PRIMARY KEY auto_increment,
username VARCHAR(64) NOT NULL UNIQUE,
password VARCHAR(128) NOT NULL,
    name VARCHAR(64) NOT NULL,
);