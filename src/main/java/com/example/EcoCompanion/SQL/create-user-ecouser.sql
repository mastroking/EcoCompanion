CREATE LOGIN EcoUser WITH PASSWORD = 'StrongPassword123!';
USE EcoCompanionDB;
CREATE USER EcoUser FOR LOGIN EcoUser;
EXEC sp_addrolemember 'db_owner', 'EcoUser';
