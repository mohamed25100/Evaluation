-- ------------------------------------------------------------------------------
-- - Reconstruction de la base de données                                     ---
-- ------------------------------------------------------------------------------
DROP DATABASE IF EXISTS Training;
CREATE DATABASE Training;
USE Training;
-- -----------------------------------------------------------------------------
-- - Construction de la table des utilisateurs                               ---
-- -----------------------------------------------------------------------------
CREATE TABLE T_Users (
    IdUser INT(4) PRIMARY KEY AUTO_INCREMENT,
    Login VARCHAR(30) UNIQUE NOT NULL,
    Password VARCHAR(30) NOT NULL,
    Role ENUM('user', 'admin') NOT NULL
) ENGINE = InnoDB;


INSERT INTO T_Users (IdUser, Login, Password, Role) VALUES 
(1,'Mohamed' ,	'password123' , 'admin' ),
(2,'Stella' ,	'password123' , 'user' ),
(3,'Tristan' ,	'password123' , 'user' ),
(4,'Alexis' ,	'password123' , 'user' ),
(5,'Alik' ,	'password123' , 'user' );

SELECT * FROM T_Users;

-- -----------------------------------------------------------------------------
-- - Construction de la table des clients                           ---
-- -----------------------------------------------------------------------------
CREATE TABLE T_Customers (
    IdCustomer INT(4) PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(30) NOT NULL,
    LastName VARCHAR(30) NOT NULL,
    Email VARCHAR(30) UNIQUE NOT NULL,
    Phone VARCHAR(20),
    Address VARCHAR(100),
    IdUser INT(4) NOT NULL,
    FOREIGN KEY (IdUser) REFERENCES T_Users(IdUser)
) ENGINE = InnoDB;



INSERT INTO T_Customers (IdCustomer, LastName, FirstName, Email, Phone, Address, IdUser) VALUES 
( 1, 'Natalie' ,	'PASCAL' , 'natalie.pascal@fms-ea.com', '0758650844', '4 rue de la republique' ,3);

SELECT * FROM T_Customers;


-- -----------------------------------------------------------------------------
-- - Construction de la table des categories                               ---
-- -----------------------------------------------------------------------------
CREATE TABLE T_Categories (
    IdCategory INT(4) PRIMARY KEY AUTO_INCREMENT,
    CategoryName VARCHAR(30) NOT NULL UNIQUE,
    Description VARCHAR(100) NOT NULL
) ENGINE = InnoDB;

-- ------------------------------------------------------------------------------
-- - Insertion des catégories de formations                                     --
-- ------------------------------------------------------------------------------

INSERT INTO T_Categories (CategoryName, Description) VALUES 
('Dev Web', 'Development of websites and web applications'),
('Cms', 'Content management systems'),
('Bureautique', 'Office automation software and tools'),
('IA', 'Artificial Intelligence and related technologies'),
('Cyber securite', 'Cybersecurity practices and tools');

select * from t_categories;


-- -----------------------------------------------------------------------------
-- - Construction de la table des formations                               ---
-- -----------------------------------------------------------------------------
CREATE TABLE T_Trainings (
	IdTraining			int(4)			PRIMARY KEY	AUTO_INCREMENT,
	Name				varchar(50)		NOT NULL,
	Description			varchar(100)	NOT NULL,
	DurationDays		int(4),
	Format ENUM('presentiel', 'distanciel') NOT NULL,
	Price				FLOAT(4)		NOT NULL,
	IdCategory INT(4),
	FOREIGN KEY (IdCategory) REFERENCES T_Categories(IdCategory)
)	ENGINE = InnoDB;



-- ------------------------------------------------------------------------------
-- - Insertion des formations                                                   --
-- ------------------------------------------------------------------------------
INSERT INTO T_Trainings (Name, Description, DurationDays, Format, Price, IdCategory) 
VALUES 
('Frontend Web Development', 'Training on frontend technologies like HTML, CSS, and JavaScript', 5, 'presentiel', 800, 1),
('Content Management with Wordpress', 'Training on using Wordpress to create and manage websites', 3, 'distanciel', 500, 2),
('Mastering Office Tools', 'Learn to use office tools like Word, Excel, and PowerPoint', 4, 'presentiel', 600, 3),
('Introduction to Artificial Intelligence', 'An introduction to the fundamental concepts and applications of AI', 7, 'distanciel', 1000, 4),
('Cybersecurity for Businesses', 'Training on network security and data protection for companies', 6, 'presentiel', 1200, 5),
('Java Spring Boot', 'Learn Spring Boot with hands-on examples', 10, 'presentiel', 500.00, 1),
('Angular Masterclass', 'Comprehensive guide to Angular', 7, 'distanciel', 300.00, 1),
('Cybersecurity Basics', 'Introduction to cybersecurity concepts', 5, 'distanciel', 200.00, 5);

SELECT * FROM T_Trainings;


-- -----------------------------------------------------------------------------
-- - Construction de la table des orders                             ---
-- -----------------------------------------------------------------------------

CREATE TABLE T_Orders (
	IdOrder		int(4)		PRIMARY KEY AUTO_INCREMENT,
	Amount		float(4)	NOT NULL DEFAULT 0,
	DateOrder	DATE		NOT NULL DEFAULT NOW(),
	IdCustomer	INT(4)   	NOT NULL,
	FOREIGN KEY(IdCustomer) REFERENCES T_Customers(IdCustomer)
) ENGINE = InnoDB;

CREATE TABLE T_Order_Items (
	IdOrderItem			int(4)	PRIMARY KEY AUTO_INCREMENT,
	IdTraining           INT(4)   NOT NULL,
	FOREIGN KEY(IdTraining) REFERENCES T_Trainings(IdTraining),
	Quantity			FLOAT(4) NOT NULL DEFAULT 1,
	UnitaryPrice		FLOAT(4)	NOT NULL DEFAULT 0,
	IdOrder             INT(4)   NOT NULL,
	FOREIGN KEY(IdOrder) REFERENCES T_Orders(IdOrder)
) ENGINE = InnoDB;


