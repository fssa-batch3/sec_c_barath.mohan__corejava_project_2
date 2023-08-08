# Database Design - Proplan

## Table: `user`

This table contains user details.

| Column Name     | Data Type | Constraints | Description |
|-----------------|----------|-------------|-------------|
| user_id         | int      | NOT NULL    | User's unique identifier (Auto-increment) |
| name            | varchar  | DEFAULT NULL| User's name |
| display_name    | varchar  | DEFAULT NULL| User's display name |
| phone_num       | varchar  | DEFAULT NULL| User's phone number |
| profession      | varchar  | DEFAULT NULL| User's profession |
| email_id        | varchar  | DEFAULT NULL| User's email address |
| password        | varchar  | DEFAULT NULL| User's password (encrypted or hashed) |
| active          | int      | DEFAULT NULL| User's active status |

**Primary Key**: user_id

---


## Table: `transactions`

This table stores transaction details related to users.

| Column Name     | Data Type | Constraints | Description |
|-----------------|----------|-------------|-------------|
| transaction_id  | int      | NOT NULL    | Transaction's unique identifier (Auto-increment) |
| transaction_type| varchar  | DEFAULT NULL| Type of the transaction |
| date            | date     | DEFAULT NULL| Date of the transaction |
| amount          | double   | DEFAULT NULL| Transaction amount |
| balance         | double   | DEFAULT NULL| User's balance after the transaction |
| user_id         | int      | DEFAULT NULL| User's unique identifier (Foreign key referencing `user` table) |
| remarks         | varchar  | DEFAULT NULL| Additional remarks or description |

**Primary Key**: transaction_id

**Foreign Key**: user_id REFERENCES `user` (`user_id`)

---

## Table: `balance`

This table represents the balance information for users.

| Column Name | Data Type | Constraints | Description |
|-------------|----------|-------------|-------------|
| user_id     | int      | NOT NULL    | User's unique identifier (Foreign key referencing `user` table) |
| balance     | double   | DEFAULT NULL| User's balance amount |

**Primary Key**: user_id

**Foreign Key**: user_id REFERENCES `user` (`user_id`)

---



![Db design](https://github.com/fssa-batch3/sec_c_barath.mohan__corejava_project_2/assets/116251480/23919ba5-60ef-4eac-a71b-eb59675985bb)
