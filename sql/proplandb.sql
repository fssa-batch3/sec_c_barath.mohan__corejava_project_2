
-- Query for creating a user table to store user details
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `display_name` varchar(45) DEFAULT NULL,
  `phone_num` varchar(20) DEFAULT NULL,
  `profession` varchar(45) DEFAULT NULL,
  `email_id` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `active` int DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ;

-- Query for creating a balance table to store user balance details
CREATE TABLE `balance` (
  `user_id` int NOT NULL,
  `balance` double DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
);

-- Query for creating a budget table to store user budget details
CREATE TABLE `budget` (
  `budget_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `monthly_income` double DEFAULT NULL,
  `expense_percentage` int DEFAULT NULL,
  `budget_amount` double DEFAULT NULL,
  `amount_spent` double DEFAULT NULL,
  PRIMARY KEY (`budget_id`),
  KEY `user_id_idx` (`user_id`)
);

-- Query for creating a budget_categories table to store budget category details
CREATE TABLE `budget_categories` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `category_name` varchar(45) DEFAULT NULL,
  `budget_amount` double DEFAULT NULL,
  `amount_spent` double DEFAULT NULL,
  PRIMARY KEY (`category_id`),
  KEY `user_category_idx` (`user_id`),
  CONSTRAINT `user_category` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ;

-- Query for creating a transaction table to store transaction details of users
CREATE TABLE `transactions` (
  `transaction_id` int NOT NULL AUTO_INCREMENT,
  `transaction_type` varchar(20) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `balance` double DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `remarks` varchar(100) DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `user_id1` (`user_id`),
  KEY `transaction_category_id_idx` (`category_id`),
  CONSTRAINT `transaction_category_id` FOREIGN KEY (`category_id`) REFERENCES `budget_categories` (`category_id`),
  CONSTRAINT `user_id1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
);


-- UserDao Queries 


-- Add User Query 

INSERT INTO user(name,phone_num,profession,email_id,password,active)VALUES(?,?,?,?,?,?;

-- Get All User Emails Query

SELECT email_id FROM USER

-- Clear All Users Query

DELETE FROM user

-- Get User Id By Name Query

SELECT user_id FROM user where name=?

-- Delete User Query

DELETE FROM user WHERE user_id = ?



-- TransactionDao Queries 


-- Add Income Query 

INSERT INTO transactions(user_id,transaction_type,date,amount,balance,remarks) VALUES(?,?,?,?,?,?)

-- Add Expense Query 

INSERT INTO transactions(user_id,transaction_type,date,amount,balance,remarks) VALUES(?,?,?,?,?,?)

-- Clear All Transaction Details Query 

TRUNCATE TABLE transactions

-- Get Transaction Details Query 

SELECT transaction_type,date,amount,remarks FROM transactions where user_id=? AND transaction_type=?



-- BalanceDao Queries 

-- Create New User Balance Query

INSERT INTO balance(user_id,balance) VALUES(?,0)

-- Update User Balance Query

UPDATE balance SET balance = ? WHERE user_id = ?

-- Get Balance By User Query

SELECT balance FROM balance WHERE user_id=?

-- Clear All Balance Details Query

TRUNCATE TABLE balance




