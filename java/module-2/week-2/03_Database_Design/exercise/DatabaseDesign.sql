DROP TABLE IF EXISTS member, interest_group, event, member_group, member_event CASCADE;

CREATE TABLE member (
    member_id SERIAL PRIMARY KEY,
    last_name VARCHAR(50) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    email VARCHAR(200) NOT NULL UNIQUE,
    phone VARCHAR(20),
    birthday DATE, 
    agree_to_notifications BOOLEAN
);

CREATE TABLE interest_group (
    interest_group_id SERIAL PRIMARY KEY,
    group_name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE event (
    event_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT, 
    start_datetime TIMESTAMP NOT NULL,
    duration INT CHECK (duration >= 30),
    interest_group_id INT,
    FOREIGN KEY (interest_group_id) REFERENCES interest_group(interest_group_id)
);

CREATE TABLE member_group (
    member_id INT,
    interest_group_id INT,
    PRIMARY KEY (member_id, interest_group_id),
    FOREIGN KEY (member_id) REFERENCES member(member_id),
    FOREIGN KEY (interest_group_id) REFERENCES interest_group(interest_group_id)
);

CREATE TABLE member_event (
    member_id INT,
    event_id INT,
    PRIMARY KEY (member_id, event_id),
    FOREIGN KEY (member_id) REFERENCES member(member_id),
    FOREIGN KEY (event_id) REFERENCES event(event_id)
);
