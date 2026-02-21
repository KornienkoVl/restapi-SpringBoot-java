CREATE TABLE posts (
                       id BIGSERIAL PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       content TEXT,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       com_counter INTEGER NOT NULL DEFAULT 0,
                       is_deleted BOOLEAN NOT NULL DEFAULT FALSE
);