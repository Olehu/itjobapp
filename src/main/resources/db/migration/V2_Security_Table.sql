CREATE TABLE job_app_user
(
    user_id   SERIAL        NOT NULL,
    email     VARCHAR(32)   NOT NULL,
    password  VARCHAR(128)  NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE job_app_role
(
    role_id SERIAL      NOT NULL,
    role    VARCHAR(20) NOT NULL,
    PRIMARY KEY (role_id)
);

CREATE TABLE job_app_user_role
(
    user_id   INT      NOT NULL,
    role_id   INT      NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_job_app_user_role_user
        FOREIGN KEY (user_id)
            REFERENCES job_app_user (user_id),
    CONSTRAINT fk_job_app_user_role_role
        FOREIGN KEY (role_id)
            REFERENCES job_app_role (role_id)
);
