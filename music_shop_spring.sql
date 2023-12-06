create table Role
(
	ID_Role serial not null primary key,
	Name_Role varchar (50) unique not null

);

insert into Role (Name_Role, Role)
values('Администратор','ADMIN'),('Сотрудник склада','WAREHOUSE'),('Пользователь','USER');


create table Country
(
	ID_Country serial not null primary key,
	Name_Country varchar (50) unique not null
);

insert into Country (Name_Country)
values('Япония'),('Франция'),('США'),('Германия');
select * from Country


create table Category_Product
(
	ID_Category_Product serial not null primary key,
	Name_Category_Product varchar (50) unique not null
);

insert into Category_Product(Name_Category_Product)
values('Духовые'),('Струнные'),('Клавишные'),('Ударные');

create table Status_Product
(
	ID_Status_Product serial not null primary key,
	Name_Status_Product varchar (20) unique not null
);


insert into Status_Product(Name_Status_Product)
values('В наличии'),('Нет в наличии');

create table Provider
(
	ID_Provider serial not null primary key,
	Name_Provider varchar (50) unique not null,
	Country_ID int not null,
	foreign key (Country_ID) references Country(ID_Country)
);

insert into Provider (Name_Provider, Country_ID)
values('YAMAHA',1),('CASIO',1),('Selmer',2),('Fender',3),('Pearl',1);
select *from Provider
create table Status_Order
(
	ID_Status_Order serial not null primary key,
	Name_Status_Order varchar (20) unique not null
);

insert into Status_Order (Name_Status_Order)
values('Принят'),('Собирается'),('Готов к выдаче');


create table Users
(
	ID_User serial not null primary key,
	Surname varchar (50) not null,
	First_Name varchar (50) not null,
	Middle_Name varchar (50) null,
	Email_User varchar (50) unique not null check (Email_User similar to '%@%.%'),
	Password_User varchar (255) not null,
	Role_ID int not null,
	active boolean not null default true
	foreign key (Role_ID) references Role (ID_Role)
);
ALTER TABLE IF EXISTS public.users
    ADD COLUMN active boolean NOT NULL DEFAULT True;

insert into Users (Surname, First_Name, Middle_Name, Email_User, Password_User, Role_ID)
values ('Иванов','Петр','Сергеевич','ivanov_petr@mail.ru','soirrfjnf',3),
('Сидоров','Иван','Александрович','sidorov_ivan@gmail.com','qwertyuigrv',3),
('Петрова','Елена','Анатольевна','petrova_elena@yandex.ru','asdfghjkgbre',3),
('Кузнецова','Алиса','Владимировна','kuznetsova_alisa@mail.ru','zxcvbnmbhnj',1),
('Новиков','Дмитрий','Иванович','novikov_dmitry@gmail.com','mnbvcxzfqaerg',2),
('Медведева','Анастасия','Владимировна','medvedeva_anastasia@mail.ru','qazwsxedctmt',3),
('Куликова','Мария','Сергеевна','kulikova_maria@mail.ru','yuiopasrrsd',3);


select * from Users
create table Product
(
	ID_Product serial not null primary key,
	Name_Product varchar (40) not null,
	Price_Product int not null check (Price_Product >= 0),
	Count_Product int not null check (Count_Product >= 0),
	Category_Product_ID int not null,
	Provider_ID int not null,
	Status_Product_ID int not null,
	foreign key (Category_Product_ID) references Category_Product (ID_Category_Product),
	foreign key (Provider_ID) references Provider (ID_Provider),
	foreign key (Status_Product_ID) references Status_Product (ID_Status_Product)
);


insert into Product (Name_Product, Price_Product, Count_Product, Category_Product_ID,Provider_ID, Status_Product_ID)
values
('Гитара Fender',50000,159,2,4,1),
('Саксофон Yamaha',70000,36,1,1,1),
('Барабаны Pearl',40000,257,4,5,1),
('Клавишные Yamaha',80000,25,3,1,1),
('Труба CASIO',25000,246,1,2,1),
('Бас-гитара Selmer',60000,33,2,3,1),
('Акустическая гитара Pearl',20000,378,2,5,1),
('Электронная барабанная установка CASIO',45000,67,4,2,1);

create table Orders 
(
	ID_Order serial not null primary key,
	Price_Order int not null check (Price_Order >= 0),
	Number_Order varchar (6) unique not null check (Number_Order similar to '[0-9][0-9][0-9][0-9][0-9][0-9]'),
	Status_Order_ID int not null,
	User_ID int not null,
	Date_Order date not null,
	foreign key (Status_Order_ID) references Status_Order (ID_Status_Order),
	foreign key (User_ID) references Users (ID_User)
);

ALTER TABLE IF EXISTS public.orders
    ALTER COLUMN Date_Order type varchar(20);

insert into Orders(Price_Order, Number_Order, Status_Order_ID, User_ID ,Date_Order)
values(90000,'234567',1,1,'11.06.2023'),
(100000,'583957',1,2,'11.06.2023'),
(60000,'928475',1,3,'01.06.2023'),
(20000,'358462',1,4,'11.05.2023');

create table Order_List
(
	ID_Order_List serial not null primary key,
	Product_ID int not null,
	Order_ID int not null,
	foreign key (Product_ID) references Product (ID_Product),
	foreign key (Order_ID) references Orders (ID_Order)
);

 
insert into Order_List (Product_ID,Order_ID)
values(1,1),(3,1),(4,2),(7,2),(6,3),(7,4);
