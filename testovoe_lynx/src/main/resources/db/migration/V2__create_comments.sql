CREATE TABLE comments (
                          id BIGSERIAL PRIMARY KEY,
                          text TEXT NOT NULL,
                          post_id BIGINT NOT NULL REFERENCES posts(id) ON DELETE CASCADE,
                          parent_comment_id BIGINT REFERENCES comments(id) ON DELETE CASCADE,
                          status VARCHAR(50) NOT NULL DEFAULT 'PENDING',
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          CHECK (status IN ('PENDING', 'APPROVED'))
);

CREATE INDEX idx_comments_post_status ON comments(post_id, status);
CREATE INDEX idx_comments_parent ON comments(parent_comment_id);