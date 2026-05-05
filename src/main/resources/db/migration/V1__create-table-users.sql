create table users (
	id bigint primary key identity(1,1),
	full_name varchar(160) not null,
	nick_name varchar(40),
	email varchar(160) not null unique,
	password_hash varchar(255) not null,
	is_active bit default 1,
	created_at datetime2 default getdate(),
	updated_at datetime2 default getdate()
);