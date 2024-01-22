-- Just for test purpose

CREATE TABLE IF NOT EXISTS instrument(
    id INT PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
