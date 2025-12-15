CREATE DATABASE IF NOT EXISTS digital_twin;
USE digital_twin;

CREATE TABLE IF NOT EXISTS statistieken (
    id INT PRIMARY KEY AUTO_INCREMENT,
    kosten INT NOT NULL,
    opbrengst INT NOT NULL,
    bewoners INT UNSIGNED NOT NULL,
    score INT NOT NULL,
    capaciteit INT UNSIGNED NOT NULL
);
