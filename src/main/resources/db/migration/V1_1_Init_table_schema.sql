CREATE TABLE company (
    company_id SERIAL NOT NULL,
    company_name VARCHAR(128) NOT NULL,
    industry VARCHAR(64),
    location VARCHAR(255),
    email VARCHAR(64),
    is_hiring BOOLEAN,
    unique (company_name),
    PRIMARY KEY (company_id)
);



CREATE TABLE candidate (
    candidate_id SERIAL NOT NULL,
    first_name VARCHAR(40),
    last_name VARCHAR(40),
    email VARCHAR(100) NOT NULL,
    skills VARCHAR(255),
    phone_number VARCHAR(20),
    availability_status BOOLEAN,
    profile_image BYTEA,
    unique (email),
    PRIMARY KEY (candidate_id)
);


CREATE TABLE job_offer (
    job_offer_id SERIAL NOT NULL,
    name VARCHAR(255) NOT NULL,
    company_id INT NOT NULL,
    skill VARCHAR(255) NOT NULL,
    experience_level VARCHAR(100),
    other_requirements VARCHAR(255),
    PRIMARY KEY (job_offer_id),
    CONSTRAINT fk_company_id
        FOREIGN KEY (company_id)
            REFERENCES company(company_id)
);

