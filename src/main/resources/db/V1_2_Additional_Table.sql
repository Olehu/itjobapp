CREATE TABLE skills (
    skill_id SERIAL NOT NULL,
    skill_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (skill_id)
);

CREATE TABLE candidate_skills (
    candidate_skill_id SERIAL NOT NULL,
    candidate_id INT NOT NULL,
    skill_id INT NOT NULL,
    PRIMARY KEY (candidate_skill_id),
    CONSTRAINT fk_candidate_skill
        FOREIGN KEY (candidate_id)
            REFERENCES Candidates(candidate_id),
    CONSTRAINT fk_skill_id
        FOREIGN KEY (skill_id)
            REFERENCES Skills(skill_id)
);

CREATE TABLE hired_candidates (
    hire_id SERIAL NOT NULL,
    company_id INT NOT NULL,
    candidate_id INT NOT NULL,
    hire_date DATE,
    PRIMARY KEY (hire_id),
    CONSTRAINT fk_hire_company
        FOREIGN KEY (company_id)
            REFERENCES Companies(company_id),
    CONSTRAINT fk_hire_candidate
        FOREIGN KEY (candidate_id)
            REFERENCES Candidates(candidate_id)
);
