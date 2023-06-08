----fl_bid----

CREATE TABLE `Bids` (
  `BidId` int NOT NULL AUTO_INCREMENT,
  `ProjectId` int NOT NULL,
  `FreelancerId` int NOT NULL,
  `Amount` decimal(18,2) NOT NULL,
  `Description` varchar(500),
  `Status` VARCHAR(36) DEFAULT "PENDING",
  `DeliveryDays` int ,
  `CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime on UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`BidId`),
  UNIQUE KEY(ProjectId,FreelancerId)
);
---------
----fl_skills-----
CREATE TABLE `Categories` (
  `CategoryId` int NOT NULL AUTO_INCREMENT,
  `CategoryName` varchar(32) UNIQUE,
  `LogoURl` varchar(128),
  `IsDeleted` bit DEFAULT "0",
  `CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime on UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`CategoryId`)
);

CREATE TABLE `Skills` (
  `SkillId` int NOT NULL AUTO_INCREMENT,
  `SkillName` varchar(32) UNIQUE,
  `CategoryId` int,
  `IsDeleted` bit DEFAULT "0",
  `CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime on UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`SkillId`),
  FOREIGN KEY(CategoryId)  REFERENCES Categories(CategoryId)
);

CREATE TABLE `UserSkills` (
  `UserId` int,
  `SkillId` int, 
  `CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime on UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`UserId`,`SkillId`),
  FOREIGN KEY(SkillId)  REFERENCES Skills(SkillId)
);

CREATE TABLE `ProjectSkills` (
  `ProjectId` int NOT NULL,
  `SkillId` int NOT NULL ,
  `CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime on UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ProjectId`, `SkillId`),
  FOREIGN KEY(SkillId)  REFERENCES Skills(SkillId)
);


--------------------


-----fl_projects-----

CREATE TABLE `PaymentType` (
  `PaymentTypeId` int NOT NULL AUTO_INCREMENT,
  `paymentType` varchar(32),
  `CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime on UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`PaymetTypeId`)
);

CREATE TABLE `Projects` (
  `ProjectId` int NOT NULL AUTO_INCREMENT,
  `ClientId` int,
  `ProjectName` varchar(64),
  `ProjectDescription` varchar(3000),
  `IsConfidential` bit,
  `PaymentTypeId` int DEFAULT 1,
  `BidStartDate` datetime,
  `BidEndDate` datetime,
  `MinPrice` decimal(18,0),
  `MaxPrice` decimal(18,0),
  `Status` varchar(32) DEFAULT 'POSTED',
  `CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime on UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ProjectId`),
  FOREIGN KEY(PaymentTypeId) REFERENCES paymentType(PaymentTypeId)
);

CREATE TABLE `Ratings` (
  `RatingId` int NOT NULL AUTO_INCREMENT,
  `UserId` int,
  `ProjectId` int,
  `RatingDescription` varchar(1000),
  `CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime on UPDATE CURRENT_TIMESTAMP,
  `Rating` int,
  PRIMARY KEY (`RatingId`),
  FOREIGN KEY(ProjectId) REFERENCES projects(ProjectsId)
  UNIQUE KEY(ProjectId)
);


CREATE TABLE `ProjectLanguages` (
  `ProjectId` int,
  `LanguageId` int,
  `CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime on UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ProjectId`, `LanguageId`),
  FOREIGN KEY(ProjectId) REFERENCES projects(ProjectsId)
  
);

---------------


------fl_users-------
CREATE TABLE `Users` (
  `UserId` int NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(16),
  `LastName` varchar(16),
  `HeadLine` varchar(64),
  `Summary` varchar(1000),
  `Company` varchar(256),
  `Email` varchar(64) UNIQUE,
  `PhNo` varchar(13)UNIQUE,
  `IsVerified` bit,
  `PhotoUrl` varchar(255),
  `UserRole` char(6) DEFAULT "User",
  `CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime on UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`UserId`)
);


CREATE TABLE `Languages` (
  `LanguageId` int NOT NULL AUTO_INCREMENT,
  `LanguageName` varchar(32),
  `CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime on UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`LanguageId`)
);

CREATE TABLE `UserLanguages` (
  `UserId` int ,
  `LanguageId` int ,
  `CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime on UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`UserId`, `LanguageId`),
   FOREIGN KEY(UserId) REFERENCES Users(UserId),
   FOREIGN KEY(LanguageId)REFERENCES Languages(LanguageId)
);

CREATE TABLE `Countries`(
  `CountryId` int NOT NULL AUTO_INCREMENT,
  `CountryName` varchar(32) UNIQUE,
  `CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime on UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`CountryId`)
);


CREATE TABLE `States` (
  `StateId` int NOT NULL AUTO_INCREMENT,
  `StateName` varchar(32) UNIQUE,
  `CountryId` int ,
  `CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime on UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`StateId`),
  FOREIGN KEY(CountryId)REFERENCES Countries(CountryId)
  
);

CREATE TABLE `Cities` (
  `CityId` int NOT NULL AUTO_INCREMENT,
  `CityName` varchar(32) UNIQUE,
  `StateId` int ,
  `CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime on UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`CityId`)
  FOREIGN KEY(StateId)REFERENCES States(StateId)
);


CREATE TABLE `UserLocation` (
  `UserId` int NOT NULL REFERENCES Users(UserId),
  `CityId` int ,
  `Pincode` varchar(6),
  `Address1` varchar(256),
  `Address2` varchar(256),
  `CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime on UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`UserId`),
  FOREIGN KEY(CityId)REFERENCES Cities(CityId)
);

CREATE TABLE `Portfolio` (
  `PortfolioId` int NOT NULL PRIMARY KEY,
  `UserId` int NOT NULL,
  `Title` varchar (64) ,
  `Description` varchar(1000),
  `ImageURL` varchar(128),
  `CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime on UPDATE CURRENT_TIMESTAMP,
   FOREIGN KEY(UserId)REFERENCES users(UserId)
  
);



------------------




