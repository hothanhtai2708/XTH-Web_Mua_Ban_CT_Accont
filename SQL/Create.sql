use master
go
drop database Mua_Ban_Acconut
go

create database Mua_Ban_Acconut
go
use Mua_Ban_Acconut
go

CREATE TABLE Account
(
    user_id         int IDENTITY (1,1) PRIMARY KEY,
    username        nvarchar(63) NOT NULL unique,
    email           varchar(255) NOT NULL unique,
    hashed_password varchar(255) NOT NULL,
    is_disable      bit default 0,
    is_admin        bit
);

create table [user]
(
    id          int primary key identity (1,1),
    username    varchar(63)  not null unique,
    email       varchar(63)  not null unique,
    is_admin    bit                   default 0,
    is_disable  bit                   default 0,
    fullname    nvarchar(63)          default 'fullname_val',
    gender      nvarchar(10)          default 'Nam',
    avatar      varchar(63)           default 'user.png',
    dob         date                  default getdate(),
    description nvarchar(300)         default 'description_val',
    job_title   nvarchar(63)          default 'Unemployed',
    role_id     int          not null default 3,
    role_name   nvarchar(63) not null default 'User',
    processed   bit                   default 0
);
go
alter table [user]
    add sdt varchar(15);
ALTER TABLE [user]
    ADD address nvarchar(255);
ALTER TABLE [user]
    ADD account_id int null foreign key references Account (user_id);
CREATE TABLE Product
(
    product_id   int IDENTITY (1,1) PRIMARY KEY,
    product_name nvarchar(255) NOT NULL,
    accountgame nvarchar(255),
    passwordgame nvarchar(255),
    price        float,
    category nvarchar(255),
    description  nvarchar(max),
    avatar     varchar(max),
    picture varchar(max),
    slug_url     varchar(255) default '',
);

create table [Cart]
(
    id           int FOREIGN KEY REFERENCES [user] (id),
    name_product varchar(255),
    quatity      int,
    price        int
);
go
 -- Khi thêm một account mới nó sẽ lưu thông tin liên quan vào user --
create or alter trigger trigger_after_insert_account
    on [Account]
    for insert
    as
begin
    insert into [user] (username, email, is_admin, is_disable, role_id, role_name, account_id)
    select i.username,
           i.email,
           i.is_admin,
           i.is_disable,
           iif(i.is_admin = 1, 1, 3),
           iif(i.is_admin = 1, 'Admin', 'User'),
           i.user_id
    from inserted i
end
go