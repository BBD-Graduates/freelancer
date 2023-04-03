----fl_bid----

CREATE TABLE `Bids` (
  `BidId` int NOT NULL AUTO_INCREMENT,
  `ProjectId` int NOT NULL,
  `FreelancerId` int NOT NULL,
  `Amount` decimal(18,2) NOT NULL,
  `Description` varchar(500),
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
  `CategoryId` int REFERENCES Categories(CategoryId),
  `IsDeleted` bit DEFAULT "0",
  `CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime on UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`SkillId`)
);

CREATE TABLE `UserSkills` (
  `UserId` int,
  `SkillId` int REFERENCES Skills(SkillId),
  `CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime on UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`UserId`,`SkillId`)
);

CREATE TABLE `ProjectSkills` (
  `ProjectId` int NOT NULL,
  `SkillId` int NOT NULL REFERENCES Skills(SkillId),
  `CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime on UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ProjectId`, `SkillId`)
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
  `ProjectName` varchar(32),
  `ProjectDescription` varchar(232),
  `IsConfidential` bit,
  `PaymentTypeId` int REFERENCES paymentType(PaymentTypeId),
  `BidStartDate` datetime,
  `BidEndDate` datetime,
  `MinPrice` decimal(18,0),
  `MaxPrice` decimal(18,0),
  `Status` varchar(32),
  `CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime on UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ProjectId`)
);

CREATE TABLE `Ratings` (
  `RatingId` int NOT NULL AUTO_INCREMENT,
  `UserId` int,
  `ProjectId` int REFERENCES projects(ProjectsId),
  `RatingDescription` varchar(128),
  `CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime on UPDATE CURRENT_TIMESTAMP,
  `Rating` int,
  PRIMARY KEY (`RatingId`)
);


CREATE TABLE `ProjectAssignment` (
  `BidId` int,
  `CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`BidId`)
);

CREATE TABLE `ProjectLanguages` (
  `ProjectId` int REFERENCES projects(ProjectsId),
  `LanguageId` int,
  `CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime on UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ProjectId`, `LanguageId`)
);

---------------


------fl_users-------
CREATE TABLE `Users` (
  `UserId` int NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(16),
  `LastName` varchar(16),
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
  `LanguageId` int NOT NULL,
  `LanguageName` varchar(32),
  `CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime on UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`LanguageId`)
);

CREATE TABLE `UserLanguages` (
  `UserId` int REFERENCES Users(UserId),
  `LanguageId` int REFERENCES Languages(LanguageId),
  `CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime on UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`UserId`, `LanguageId`)
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
  `CountryId` int REFERENCES Countries(CountryId),
  `CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime on UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`StateId`)
);

CREATE TABLE `Cities` (
  `CityId` int NOT NULL AUTO_INCREMENT,
  `CityName` varchar(32) UNIQUE,
  `StateId` int REFERENCES States(StateId),
  `CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime on UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`CityId`)
);


CREATE TABLE `UserLocation` (
  `UserId` int NOT NULL REFERENCES Users(UserId),
  `CityId` int REFERENCES Cities(CityId),
  `Pincode` varchar(6),
  `Address1` varchar(256),
  `Address2` varchar(256),
  `CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime on UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`UserId`)
);


------------------




