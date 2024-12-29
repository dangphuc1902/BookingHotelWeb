CREATE database booking_hotel;
-- DROP DATABASE booking_hotel;
USE booking_hotel;

CREATE TABLE IF NOT EXISTS roles (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(225) NOT NULL,
   
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users (
    id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(225) NOT NULL,
		last_name VARCHAR(225) NOT NULL,
    email VARCHAR(225) NOT NULL,
    password VARCHAR(225) NOT NULL,
    role_id INT Not NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS room (
    id INT NOT NULL AUTO_INCREMENT,
    room_type VARCHAR(225) NOT NULL,
    room_price DECIMAL(10, 2) NOT NULL,
    image TEXT NOT NULL,
    is_booked BOOLEAN DEFAULT false,
   
    PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS booking (
    id INT NOT NULL AUTO_INCREMENT,
    check_in Date NOT NULL,
    check_out Date NOT NULL,
    adults VARCHAR(225) NOT NULL,
    children VARCHAR(225) NOT NULL,
    total_guest VARCHAR(225) NOT NULL,
		guest_email VARCHAR(225) NOT NULL,
		guest_full_name VARCHAR(225) NOT NULL,
    confirmation_code VARCHAR(225) NOT NULL,
    room_id INT NOT NULL,
   
    PRIMARY KEY (id)
);
-- ALTER TABLE booking ADD COLUMN user_id INT NOT NULL;	
ALTER TABLE users ADD CONSTRAINT FK_id_roles_user FOREIGN KEY(role_id) REFERENCES roles(id);
ALTER TABLE booking ADD CONSTRAINT FK_id_room_booking FOREIGN KEY(room_id) REFERENCES room(id);
ALTER TABLE booking DROP COLUMN user_id;

INSERT INTO roles( name) VALUES ("ROLE_ADMIN");
INSERT INTO roles( name) VALUES ("ROLE_USER");


INSERT INTO users( first_name,last_name, email, password, role_id) VALUES ("Nguyen Van","A", "nguyenvana@gmail.com", "$2a$12$EuJrWDT/svlz9HrOY7vbTeDrEaYq3XREj.Jpe7iouWuk6GkvczIuC", 1);
INSERT INTO users( first_name,last_name, email, password, role_id) VALUES ("Nguyen Van"," B", "nguyenvanb@gmail.com", "$2a$12$EuJrWDT/svlz9HrOY7vbTeDrEaYq3XREj.Jpe7iouWuk6GkvczIuC", 2);
INSERT INTO users( first_name,last_name, email, password, role_id) VALUES ("Nguyen Van"," C", "nguyenvanc@gmail.com", "$2a$12$EuJrWDT/svlz9HrOY7vbTeDrEaYq3XREj.Jpe7iouWuk6GkvczIuC", 2);

INSERT INTO room ( room_type , room_price , image , is_booked) VALUES ("Family Suite 6", "500.00", "FamilySuite6.jpg", 0);
INSERT INTO room ( room_type , room_price , image , is_booked) VALUES ("Single View", "150.00", "Single View.jpg", 0);
INSERT INTO room ( room_type , room_price , image , is_booked) VALUES ("TripleSuiteTwo", "500.00", "TripleSuiteTwo.jpg", 0);