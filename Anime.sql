CREATE TABLE animes (
    id SERIAL PRIMARY KEY,
    english_title VARCHAR(100) NOT NULL,
    jap_title VARCHAR(100) NOT NULL,
    synopsis TEXT,
    studio VARCHAR(100),
    director VARCHAR(100),
    total_eps INT,
    release_date DATE,
    rating VARCHAR(5),
    mean_score DECIMAL(3, 2)
);
