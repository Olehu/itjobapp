CREATE TABLE job_app_role
(
    role_id SERIAL      NOT NULL,
    role    VARCHAR(20) NOT NULL,
    PRIMARY KEY (role_id)
);

CREATE TABLE job_app_user
(
    user_id   SERIAL        NOT NULL,
    email     VARCHAR(32)   NOT NULL,
    password  VARCHAR(128)  NOT NULL,
    role_id int NOT NULL,
    PRIMARY KEY (user_id),
    CONSTRAINT fk_user_role_id
        FOREIGN KEY (role_id)
            REFERENCES job_app_role (role_id)
);


