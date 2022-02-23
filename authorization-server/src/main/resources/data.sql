INSERT IGNORE INTO users (username, password, enabled)
VALUES ('user', '{noop}password', 1);

INSERT IGNORE INTO authorities (username, authority)
VALUES ('admin', 'ROLE_USER');