CREATE TABLE users (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255),
    created TIMESTAMP,
    modified TIMESTAMP,
    last_login TIMESTAMP,
    token VARCHAR(255),
    is_active BOOLEAN NOT NULL
);

CREATE TABLE phone (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    number VARCHAR(255),
    citycode VARCHAR(255),
    contrycode VARCHAR(255),
    user_id UUID,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);
