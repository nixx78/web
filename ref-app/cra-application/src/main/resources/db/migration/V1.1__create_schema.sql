create table if not exists projects
(
    id                    int not null auto_increment,
    name                  varchar(255),
    primary key (id)
) DEFAULT CHARSET=utf8;
