CREATE TABLE user_roles (
	id VARCHAR(36) PRIMARY KEY,
	role VARCHAR(10),
	user_id VARCHAR(36),
	created_by VARCHAR(255),
	created_at TIMESTAMP,
	modified_by VARCHAR(255),
    modified_at TIMESTAMP
);

ALTER TABLE user_roles ADD CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES users (id);