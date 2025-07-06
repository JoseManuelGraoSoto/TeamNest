CREATE TABLE worker (
    dni VARCHAR(15) PRIMARY KEY,
    name VARCHAR(50),
    phoneNumber VARCHAR(20),
    type ENUM('player', 'assistant', 'executive') NOT NULL
);

CREATE TABLE player (
     dni VARCHAR(15) PRIMARY KEY,
     age INT,
     marketValue BIGINT,
     conditionToPlay BOOLEAN,
     FOREIGN KEY (dni) REFERENCES worker(dni)
);

CREATE TABLE assistant (
     dni VARCHAR(15) PRIMARY KEY,
     job VARCHAR(50),
     speciality VARCHAR(50),
     FOREIGN KEY (dni) REFERENCES worker(dni)
);

CREATE TABLE executive (
   dni VARCHAR(15) PRIMARY KEY,
   job VARCHAR(50),
   FOREIGN KEY (dni) REFERENCES worker(dni)
);