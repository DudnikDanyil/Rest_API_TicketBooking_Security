CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(255) NOT NULL,
                       first_name VARCHAR(255) NOT NULL,
                       last_name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       status VARCHAR(25) DEFAULT 'ACTIVE'
);

CREATE TABLE roles (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255),
                       status VARCHAR(25) DEFAULT 'ACTIVE'
);

CREATE TABLE airlines (
                          id SERIAL PRIMARY KEY,
                          сompany_name VARCHAR(255)
);

CREATE TABLE flights (
                         id SERIAL PRIMARY KEY,
                         departure_city VARCHAR(255),
                         arrival_city VARCHAR(255),
                         date_and_time_of_departure TIMESTAMP,
                         date_and_time_of_arrival TIMESTAMP,
                         price DECIMAL,
                         airline_id INT,
                         FOREIGN KEY (airline_id) REFERENCES airlines(id)
);

CREATE TABLE booking (
                         id_booking SERIAL PRIMARY KEY,
                         user_id INT REFERENCES users(id),
                         flight_id INT REFERENCES flights(id)
);

CREATE TABLE user_roles (
                            user_id INT REFERENCES users(id),
                            role_id INT REFERENCES roles(id)
);