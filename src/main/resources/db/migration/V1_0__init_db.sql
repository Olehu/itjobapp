CREATE TABLE company (
                         company_id SERIAL NOT NULL,
                         company_name VARCHAR(128),
                         industry VARCHAR(128),
                         city VARCHAR(128),
                         email VARCHAR(128) NOT NULL,
                         description VARCHAR(1000),
                         is_hiring BOOLEAN,
                         unique (company_name),
                         PRIMARY KEY (company_id)
);

CREATE TABLE candidate (
                           candidate_id SERIAL NOT NULL,
                           first_name VARCHAR(40),
                           last_name VARCHAR(40),
                           email VARCHAR(100) NOT NULL,
                           experience_level VARCHAR(30),
                           skills VARCHAR(255),
                           phone_number VARCHAR(20),
                           availability_status BOOLEAN,
                           description VARCHAR(1000),
                           profile_image BYTEA,
                           unique (email),
                           PRIMARY KEY (candidate_id)
);

CREATE TABLE job_offer (
                           job_offer_id SERIAL NOT NULL,
                           name VARCHAR(255) NOT NULL,
                           company_id INT NOT NULL,
                           skills VARCHAR(255),
                           experience_level VARCHAR(100),
                           other_requirements VARCHAR(1000),
                           remote VARCHAR(20),
                           PRIMARY KEY (job_offer_id),
                           CONSTRAINT fk_company_id
                               FOREIGN KEY (company_id)
                                   REFERENCES company(company_id)
);
CREATE TABLE skills (
                        skill_id SERIAL PRIMARY KEY,
                        skill_name VARCHAR(255)
);

CREATE TABLE job_offer_skills (
                                id_job_offfer_skills SERIAL,
                                job_offer_id INT,
                                skill_id INT,
                                PRIMARY KEY (id_job_offfer_skills),
                                CONSTRAINT fk_job_offer_id
                                    FOREIGN KEY (job_offer_id)
                                        REFERENCES job_offer(job_offer_id),
                                CONSTRAINT fk_skill_id
                                    FOREIGN KEY (skill_id)
                                        REFERENCES skills(skill_id)
);

CREATE TABLE candidate_skills (
                                 id_candidate_skills SERIAL,
                                 candidate_id INT,
                                 skill_id INT,
                                 PRIMARY KEY (id_candidate_skills),
                                 CONSTRAINT fk_candidate_id
                                     FOREIGN KEY (candidate_id)
                                         REFERENCES candidate(candidate_id),
                                 CONSTRAINT fk_skill_id
                                     FOREIGN KEY (skill_id)
                                         REFERENCES skills(skill_id)
);
