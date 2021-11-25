use hw3;

insert into companies values
(1,'GoIT',1000),
(2,'EPAM',10000),
(3,'DigiCODE',500),
(4,'N-iX',5000);

insert into developers values
(1,'Ivan',20,1,500),
(2,'Petro',25,2,550),
(3,'Vasyl',30,3,600),
(4,'Kyrylo',26,2,450);

insert into skills values
(1,'Java','junior'),
(2,'C++','middle'),
(3,'C#','junior'),
(4,'JS','senior');

insert into projects values
(1,'ShedullerBot','2005-05-20',10000),
(2,'NavigationBot','2006-06-20',20000),
(3,'TrainingBot','2007-07-20',25000),
(4,'LectionBot','2008-08-20',30000);

insert into customers values
(1,'NUPP','regular'),
(2,'PDAA','one-time'),
(3,'ChDTU','lost');

insert into companies_projects values
(1,1),(1,2),(1,3),(3,2),(2,3),(2,1);

insert into customers_projects values 
(1,1),(2,1),(1,2),(1,3),(2,3),(3,3),(1,4);

insert into developers_projects values
(3,1),(2,1),(3,2),(1,3),(1,4),(4,1),(4,2);

insert into developers_skills values
(1,1),(3,1),(4,1),(2,2),(3,2),(2,3),(1,4);