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
('Laptop Dell XPS 13', 1200.50, 10, 'Laptop Dell XPS 13 với bộ vi xử lý Intel Core i7, RAM 16GB, SSD 512GB.', 'dell_xps_13.jpg', 'laptop-dell-xps-13'),
('iPhone 13 Pro', 999.99, 15, 'iPhone 13 Pro với màn hình Super Retina XDR 6.1 inch, camera 12MP.', 'iphone_13_pro.jpg', 'iphone-13-pro'),
('Samsung Galaxy S21', 799.99, 20, 'Samsung Galaxy S21 với màn hình Dynamic AMOLED 6.2 inch, camera 64MP.', 'samsung_galaxy_s21.jpg', 'samsung-galaxy-s21'),
('Sony WH-1000XM4', 349.99, 30, 'Tai nghe Sony WH-1000XM4 với công nghệ chống ồn, thời lượng pin 30 giờ.', 'sony_wh_1000xm4.jpg', 'sony-wh-1000xm4'),
('MacBook Pro 16', 2399.99, 5, 'MacBook Pro 16 inch với chip Apple M1 Pro, RAM 16GB, SSD 1TB.', 'macbook_pro_16.jpg', 'macbook-pro-16');
go

select * from Account
select * from [user]

-- Xóa bản ghi liên quan từ bảng user
DELETE FROM [user];
DBCC CHECKIDENT ('[user]', RESEED, 0);

-- Xóa bản ghi từ bảng Account và đặt lại IDENTITY
DELETE FROM Account;
DBCC CHECKIDENT ('Account', RESEED, 0);

