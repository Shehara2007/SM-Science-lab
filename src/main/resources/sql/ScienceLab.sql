drop database sciencelabfinal;
create database sciencelabfinal;
use sciencelabfinal;


create table funder
(
    funder_Id    varchar(10)    not null
        primary key,
    funder_Name  varchar(100)   null,
    amount       decimal(12, 2) null,
    project      varchar(10)    null,
    organization varchar(100)   null
);

create table project
(
    project_Id     varchar(10)    not null
        primary key,
    start_date     date           null,
    end_date       date           null,
    funding_Amount decimal(12, 2) null,
    title          varchar(200)   null,
    description    text           null
);

create table funder_project
(
    funder_project_Id varchar(10) null,
    funder_Id         varchar(10) null,
    project_Id        varchar(10) null,
    constraint funder_project_ibfk_1
        foreign key (funder_Id) references funder (funder_Id),
    constraint funder_project_ibfk_2
        foreign key (project_Id) references project (project_Id)
);

create index funder_Id
    on funder_project (funder_Id);

create index project_Id
    on funder_project (project_Id);

create table progress
(
    progress_Id       varchar(10) not null
        primary key,
    project_Id        varchar(10) null,
    status            varchar(50) null,
    last_updated_date date        null,
    constraint progress_ibfk_1
        foreign key (project_Id) references project (project_Id)
);

create index project_Id
    on progress (project_Id);

create table scientist
(
    scientist_Id   varchar(10)  not null
        primary key,
    contact        varchar(100) null,
    scientist_Name varchar(100) null,
    employee       varchar(10)  null,
    specialization varchar(100) null
);

create table project_scientist
(
    project_scientist_Id varchar(10) null,
    project_Id           varchar(10) not null,
    scientist_Id         varchar(10) not null,
    primary key (project_Id, scientist_Id),
    constraint project_scientist_ibfk_1
        foreign key (project_Id) references project (project_Id),
    constraint project_scientist_ibfk_2
        foreign key (scientist_Id) references scientist (scientist_Id)
);

create index scientist_Id
    on project_scientist (scientist_Id);

create table research_group
(
    group_Id          varchar(10)  not null
        primary key,
    group_Name        varchar(100) null,
    progress          varchar(100) null,
    member            varchar(10)  null,
    research_progress varchar(10)  null,
    scientist_Id      varchar(10)  null,
    constraint research_group_ibfk_1
        foreign key (scientist_Id) references scientist (scientist_Id)
);

create table employee
(
    employee_Id   varchar(10)  not null
        primary key,
    role          varchar(50)  null,
    employee_Name varchar(100) null,
    contact       varchar(100) null,
    group_Id      varchar(10)  null,
    email         varchar(100) null,
    constraint employee_ibfk_1
        foreign key (group_Id) references research_group (group_Id)
);

create index group_Id
    on employee (group_Id);

create table group_progress
(
    group_progress_Id varchar(10) null,
    progress_Id       varchar(10) not null,
    group_Id          varchar(10) not null,
    primary key (progress_Id, group_Id),
    constraint group_progress_ibfk_1
        foreign key (progress_Id) references progress (progress_Id),
    constraint group_progress_ibfk_2
        foreign key (group_Id) references research_group (group_Id)
);

create index group_Id
    on group_progress (group_Id);

create index scientist_Id
    on research_group (scientist_Id);

create table supplier
(
    supplier_Id      varchar(10)  not null
        primary key,
    supplier_Name    varchar(15)  not null,
    supplier_Contact varchar(10)  not null,
    equipment        varchar(100) not null,
    type_of_supplier varchar(100) not null
);

create table chemical
(
    chemical_Id   varchar(10)  not null
        primary key,
    chemical_Name varchar(100) null,
    quantity      varchar(10)  null,
    concentration varchar(50)  null,
    supplier_Id   varchar(10)  null,
    constraint chemical_ibfk_1
        foreign key (supplier_Id) references supplier (supplier_Id)
);

create index supplier_Id
    on chemical (supplier_Id);

create table chemical_project
(
    chemical_project_Id varchar(10) null,
    chemical_Id         varchar(10) not null,
    project_Id          varchar(10) not null,
    primary key (chemical_Id, project_Id),
    constraint chemical_project_ibfk_1
        foreign key (chemical_Id) references chemical (chemical_Id),
    constraint chemical_project_ibfk_2
        foreign key (project_Id) references project (project_Id)
);

create index project_Id
    on chemical_project (project_Id);

create table equipment
(
    equipment_Id   varchar(10)  not null
        primary key,
    equipment_Name varchar(100) null,
    quantity       varchar(100) null,
    type           varchar(50)  null,
    supplier_Id    varchar(10)  null,
    constraint equipment_ibfk_1
        foreign key (supplier_Id) references supplier (supplier_Id)
);

create table equip_project
(
    equip_project_Id varchar(10) null,
    equipment_Id     varchar(10) not null,
    project_Id       varchar(10) not null,
    primary key (equipment_Id, project_Id),
    constraint equip_project_ibfk_1
        foreign key (equipment_Id) references equipment (equipment_Id),
    constraint equip_project_ibfk_2
        foreign key (project_Id) references project (project_Id)
);

create index project_Id
    on equip_project (project_Id);

create index supplier_Id
    on equipment (supplier_Id);

create table system_user
(
    user_Id   varchar(10)  not null
        primary key,
    user_Name varchar(100) null,
    password  varchar(100) null,
    email     varchar(100) null,
    role      varchar(50)  null
);