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


INSERT INTO Product (product_name, price, quantity, description, pictures, slug_url)
VALUES 
('Acc PUBG: BATTLEGROUNDS', 1200.50, 10, 'Nick PUBG ', 'AccPubg/Avt1.png', 'laptop-dell-xps-13'),
('Acc PUBG: BATTLEGROUNDS', 999.99, 15, 'Nick PUBG', 'AccPubg/Avt2.jpg', 'iphone-13-pro'),
('Acc Liên Quân', 799.99, 20, 'Nick Liên Quân', 'AccLQ/1-1.jpg', 'samsung-galaxy-s21'),
('Acc Liên Quân', 349.99, 30, 'Nick Liên Quân', 'AccLQ/1-2.jpg', 'sony-wh-1000xm4'),
('Acc Liên Quân', 2399.99, 5, 'Nick Liên Quân', 'AccLQ/3-1.jpg', 'macbook-pro-16');
go

select * from Account
select * from [user]

-- Xóa bản ghi liên quan từ bảng user
DELETE FROM [user];
DBCC CHECKIDENT ('[user]', RESEED, 0);

DELETE FROM [Product];

-- Xóa bản ghi từ bảng Account và đặt lại IDENTITY
DELETE FROM Account;
DBCC CHECKIDENT ('Account', RESEED, 0);

