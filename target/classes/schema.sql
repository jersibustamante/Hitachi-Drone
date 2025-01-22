CREATE TABLE drone (
                       drone_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       drone_number VARCHAR(100) NOT NULL UNIQUE,
                       drone_model VARCHAR(50) NOT NULL,
                       weight_limit DOUBLE NOT NULL,
                       battery_capacity INT NOT NULL,
                       drone_state VARCHAR(50) NOT NULL
);

CREATE TABLE medication (
                            medication_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            weight DOUBLE NOT NULL,
                            code VARCHAR(100) NOT NULL UNIQUE,
                            image VARCHAR(1000)
);
