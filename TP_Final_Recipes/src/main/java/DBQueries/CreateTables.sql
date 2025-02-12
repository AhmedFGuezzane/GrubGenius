DROP TABLE Account;
DROP TABLE Ingredient;
DROP TABLE Restriction;
DROP TABLE Rating;
DROP TABLE Recipe;
DROP TABLE Restriction;
DROP TABLE GroceryList;
DROP TABLE Instruction;


CREATE TABLE Account (
    userID INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    firstname VARCHAR(255) NOT NULL, 
    lastname VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    status INT NOT NULL,
    securityQuestion VARCHAR(255) NOT NULL,
    securityAnswer VARCHAR(255) NOT NULL
);

CREATE TABLE Recipe (
    recipeID INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    category VARCHAR(255),
    difficulty VARCHAR(50),
    prepTime DOUBLE,
    servings DOUBLE,
    status INT NOT NULL
);

CREATE TABLE Ingredient (
    ingredientID INT PRIMARY KEY AUTO_INCREMENT,
    recipeID INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    quantity VARCHAR(50) NOT NULL,
    unit VARCHAR(50) NOT NULL,
    FOREIGN KEY (recipeID) REFERENCES Recipe(recipeID) ON DELETE CASCADE
);

CREATE TABLE Restriction (
    restrictionID INT PRIMARY KEY AUTO_INCREMENT,
    restrictionType VARCHAR(255) NOT NULL,
    recipeID INT NOT NULL,
    FOREIGN KEY (recipeID) REFERENCES Recipe(recipeID) ON DELETE CASCADE
);

CREATE TABLE Rating (
    ratingID INT PRIMARY KEY AUTO_INCREMENT,
    userID INT NOT NULL,
    recipeID INT NOT NULL,
    rate INT NOT NULL,
    FOREIGN KEY (userID) REFERENCES Account(userID) ON DELETE CASCADE,
    FOREIGN KEY (recipeID) REFERENCES Recipe(recipeID) ON DELETE CASCADE
);

CREATE TABLE Instruction (
    instructionID INT PRIMARY KEY AUTO_INCREMENT,
    instructions TEXT NOT NULL,
    recipeID INT NOT NULL,
    FOREIGN KEY (recipeID) REFERENCES Recipe(recipeID) ON DELETE CASCADE
);

CREATE TABLE GroceryList (
    groceryListID INT PRIMARY KEY AUTO_INCREMENT,
    recipesList TEXT,
    userID INT NOT NULL, 
    FOREIGN KEY (userID) REFERENCES Account(userID) ON DELETE CASCADE
);