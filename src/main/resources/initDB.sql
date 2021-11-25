create database hw3;
use hw3;

create table companies (
  id int not null auto_increment,
  name varchar(45) default null,
  quantity_staff int default null,
  primary key (id)
);

create table customers (
  id int not null auto_increment,
  name varchar(45) default null,
  category varchar(45) default null,
  primary key (id)
);

create table developers (
  id int not null auto_increment,
  name varchar(45) not null,
  age int default null,  
  company_id int default null,
  salary decimal(10,0) default null,
  primary key (id),
  foreign key (company_id) references companies (id)
);

create table skills (
  id int not null auto_increment,
  field varchar(45) default null,
  level varchar(45) default null,
  primary key (id)
);

create table projects (
  id int not null auto_increment,
  name varchar(45) default null,
  start date default null,
  cost decimal(10,0) default null,
  primary key (id)
);

create table companies_projects (
  company_id int not null,
  project_id int not null,
  foreign key (company_id) references companies (id),
  foreign key (project_id) references projects (id)
);

create table customers_projects (
  customer_id int not null,
  project_id int not null,
  foreign key (customer_id) references customers (id),
  foreign key (project_id) references projects (id)
);

create table developers_projects (
  developer_id int not null,
  project_id int not null,
  foreign key (developer_id) references developers (id),
  foreign key (project_id) references projects (id)
);

create table developers_skills (
  developer_id int not null,
  skill_id int not null,
  foreign key (developer_id) references developers (id),
  foreign key (skill_id) references skills (id)
);