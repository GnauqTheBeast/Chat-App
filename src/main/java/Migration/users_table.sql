-- migrate up
CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       full_name VARCHAR(100) NOT NULL,
                       email VARCHAR(100) NOT NULL UNIQUE
);

INSERT INTO users (username, password, full_name, email) VALUES
     ('quangnn', 'password', 'Nguyen Ngoc Quang', 'quangnn@example.com'),
     ('johndoe', 'password123', 'John Doe', 'johndoe@example.com'),
     ('janedoe', 'mypassword', 'Jane Doe', 'janedoe@example.com'),
     ('mikepham', '123456', 'Mike Pham', 'mikepham@example.com'),
     ('anhtran', 'pass2023', 'Anh Tran', 'anhtran@example.com'),
     ('sarahnguyen', 'sarahpw', 'Sarah Nguyen', 'sarahnguyen@example.com'),
     ('davidle', 'davpass', 'David Le', 'davidle@example.com'),
     ('emilypham', 'emilypass', 'Emily Pham', 'emilypham@example.com'),
     ('huytran', 'huysecure', 'Huy Tran', 'huytran@example.com'),
     ('lannguyen', 'lanlan', 'Lan Nguyen', 'lannguyen@example.com');

-- migrate down
DROP TABLE IF EXISTS users;
