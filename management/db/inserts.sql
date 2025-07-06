-- 1. Insertar clubes
INSERT INTO club (name) VALUES
      ('Real Madrid'),
      ('FC Barcelona');

-- 2. Ejecutivos
INSERT INTO worker (dni, name, phoneNumber, type, clubId) VALUES
      ('DNI0001', 'Florentino Pérez', '612345678', 'executive', 1),
      ('DNI0002', 'Joan Laporta', '612345679', 'executive', 2);

INSERT INTO executive (dni, job) VALUES
      ('DNI0001', 'President'),
      ('DNI0002', 'President');

-- 3. Asistentes (Entrenadores)
INSERT INTO worker (dni, name, phoneNumber, type, clubId) VALUES
      ('DNI1001', 'Carlo Ancelotti', '612345680', 'assistant', 1),
      ('DNI1002', 'Hansi Flick', '612345681', 'assistant', 2);

INSERT INTO assistant (dni, job, speciality) VALUES
      ('DNI1001', 'Head Coach', 'Tactics'),
      ('DNI1002', 'Head Coach', 'Strategy');

-- 4. Jugadores del Real Madrid
INSERT INTO worker (dni, name, phoneNumber, type, clubId) VALUES
      ('DNI2001', 'Jude Bellingham', '612345682', 'player', 1),
      ('DNI2002', 'Vinícius Jr.', '612345683', 'player', 1),
      ('DNI2003', 'Thibaut Courtois', '612345684', 'player', 1),
      ('DNI2004', 'Luka Modrić', '612345685', 'player', 1),
      ('DNI2005', 'Éder Militão', '612345686', 'player', 1);

INSERT INTO player (dni, age, marketValue, conditionToPlay) VALUES
      ('DNI2001', 21, 120000000, TRUE),
      ('DNI2002', 23, 150000000, TRUE),
      ('DNI2003', 32, 40000000, TRUE),
      ('DNI2004', 38, 5000000, FALSE),
      ('DNI2005', 26, 70000000, TRUE);

-- 5. Jugadores del FC Barcelona
INSERT INTO worker (dni, name, phoneNumber, type, clubId) VALUES
      ('DNI3001', 'Pedri', '612345687', 'player', 2),
      ('DNI3002', 'Frenkie de Jong', '612345688', 'player', 2),
      ('DNI3003', 'Robert Lewandowski', '612345689', 'player', 2),
      ('DNI3004', 'João Félix', '612345690', 'player', 2),
      ('DNI3005', 'Ronald Araújo', '612345691', 'player', 2);

INSERT INTO player (dni, age, marketValue, conditionToPlay) VALUES
      ('DNI3001', 21, 90000000, TRUE),
      ('DNI3002', 27, 75000000, TRUE),
      ('DNI3003', 35, 15000000, TRUE),
      ('DNI3004', 24, 30000000, FALSE),
      ('DNI3005', 25, 80000000, TRUE);
