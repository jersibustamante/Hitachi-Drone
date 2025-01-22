INSERT INTO drone (drone_number, drone_model, weight_limit, battery_capacity, drone_state)
VALUES
    ('DRONE123', 'LIGHTWEIGHT', 500, 75, 'IDLE'),
    ('DRONE456', 'HEAVYWEIGHT', 1000, 85, 'IDLE'),
    ('DRONE789', 'MIDDLEWEIGHT', 700, 90, 'IDLE');

INSERT INTO medication (name, weight, code, image)
VALUES
    ('Paracetamol_500', 500, 'PARA_500', 'base64EncodedImage1'),
    ('Ibuprofen_300', 300, 'IBU_300', 'base64EncodedImage2'),
    ('Aspirin_200', 200, 'ASP_200', 'base64EncodedImage3');
