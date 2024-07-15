use Mua_Ban_Acconut
go

INSERT INTO Account (username, email, hashed_password, is_admin)
VALUES ('john.doe', 'john.doe@example.com', 'your_hashed_password1', 0);
INSERT INTO Account (username, email, hashed_password, is_admin)
VALUES ('jane.smith', 'jane.smith@example.com', 'your_hashed_password2', 0);
INSERT INTO Account (username, email, hashed_password, is_admin)
VALUES ('admin', 'admin@gmail.com', '$2a$12$en5ZfBVuv44iqK6IktThDOUw3QYoKnbPpRymLs1o1Duc.cy4G7Hy.', 1);
INSERT INTO Account (username, email, hashed_password, is_admin)
VALUES ('site_user', 'abc@gmail.com', '$2a$12$qTSuT5h7Y3tsXQ/YO63iEecCyKmu.Bgg0K6Lr.lktxdAwxc8MhJE6', 0);
go

select * from Account
select * from [user]

-- Xóa bản ghi liên quan từ bảng user
DELETE FROM [user];
DBCC CHECKIDENT ('[user]', RESEED, 0);

-- Xóa bản ghi từ bảng Account và đặt lại IDENTITY
DELETE FROM Account;
DBCC CHECKIDENT ('Account', RESEED, 0);
