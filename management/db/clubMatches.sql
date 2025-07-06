CREATE TABLE club (
      id INT AUTO_INCREMENT PRIMARY KEY,
      name VARCHAR(100) NOT NULL
);

ALTER TABLE worker ADD clubId INT,
ADD FOREIGN KEY (clubId) REFERENCES club(id);

CREATE TABLE client (
      cif VARCHAR(20) PRIMARY KEY,
      name VARCHAR(100) NOT NULL
);

CREATE TABLE invoices (
      code INT PRIMARY KEY,
      quantity INT,
      date DATE,
      clientCif VARCHAR(20),
      clubId INT,
      FOREIGN KEY (clientCif) REFERENCES client(cif),
      FOREIGN KEY (clubId) REFERENCES club(id)
);

CREATE TABLE match (
       id INT AUTO_INCREMENT PRIMARY KEY,
       rivalClub VARCHAR(100),
       date DATE,
       localTeamGoals INT,
       rivalTeamGoals INT,
       clubId INT,
       trainerId VARCHAR(15),
       FOREIGN KEY (clubId) REFERENCES club(id),
       FOREIGN KEY (trainerId) REFERENCES assistant(dni)
)