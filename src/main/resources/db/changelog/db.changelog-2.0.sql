--liquibase formatted sql

--changeset artur:1
CREATE TABLE IF NOT EXISTS commentary
(
    id bigint PRIMARY KEY auto_increment,
    user_id BIGINT NOT NULL,
    foreign key (user_id)REFERENCES users (id),
    task_id BIGINT NOT NULL,
    foreign key (task_id)REFERENCES task (id)
    );
--changeset artur:2
alter table commentary
add column comment varchar(256);