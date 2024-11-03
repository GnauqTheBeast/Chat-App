-- migrate up
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(100) NOT NULL
);

INSERT INTO users (username, password, full_name) VALUES
    ('quangnn', 'password', 'Nguyen Ngoc Quang'),
    ('johndoe', 'password123', 'John Doe'),
    ('janedoe', 'mypassword', 'Jane Doe'),
    ('mikepham', '123456', 'Mike Pham'),
    ('anhtran', 'pass2023', 'Anh Tran'),
    ('sarahnguyen', 'sarahpw', 'Sarah Nguyen'),
    ('davidle', 'davpass', 'David Le'),
    ('emilypham', 'emilypass', 'Emily Pham'),
    ('huytran', 'huysecure', 'Huy Tran'),
    ('lannguyen', 'lanlan', 'Lan Nguyen');

-- migrate down
DROP TABLE IF EXISTS users;
