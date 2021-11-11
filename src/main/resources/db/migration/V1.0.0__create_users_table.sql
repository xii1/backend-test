CREATE TABLE users (
	id VARCHAR(36) PRIMARY KEY,
	email VARCHAR(255) UNIQUE NOT NULL,
	password VARCHAR(255) NOT NULL,
	first_name VARCHAR(255) NOT NULL,
	last_name VARCHAR(255) NOT NULL,
	active BOOLEAN NOT NULL,
	last_login_at TIMESTAMP,
	created_by VARCHAR(255),
	created_at TIMESTAMP,
	updated_by VARCHAR(255),
    updated_at TIMESTAMP
);