create table users(
	username varchar(50) not null primary key,
	password varchar(100) not null,
	enabled boolean not null
);

create table authorities (
	username varchar(50) not null,
	authority varchar(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);

create unique index ix_auth_username on authorities (username,authority);

create table temperature_humidity (
    id bigint not null primary key,
    humidity varchar(10),
    temp varchar(10),
    read_time timestamp
);
create unique index ix_timestamp on temperature_humidity (read_time);