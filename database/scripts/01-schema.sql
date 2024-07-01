CREATE TABLE app_user (
    id SERIAL PRIMARY KEY,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(100) NOT NULL
);

CREATE TABLE exercise (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT
);

CREATE TABLE session (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    comment TEXT,
    FOREIGN KEY (user_id) REFERENCES app_user(id)
);

CREATE TABLE progress (
    id SERIAL PRIMARY KEY,
    session_id INT NOT NULL,
    exercise_id INT NOT NULL,
    sets INT NOT NULL,
    repetitions INT NOT NULL,
    weight DECIMAL(5, 2) NOT NULL,
    duration INTERVAL,
    FOREIGN KEY (session_id) REFERENCES session(id),
    FOREIGN KEY (exercise_id) REFERENCES exercise(id)
);
