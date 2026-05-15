create table customers (
	id bigint primary key identity(1,1),
	full_name varchar(160) not null,
	cpf varchar(11),
	whatsapp varchar(11) not null unique,
	email varchar(160),
	secondary_phone varchar(11),
	observation varchar(400),
	is_active bit default 1,
	created_at datetime2 default getdate(),
	updated_at datetime2 default getdate()
);

create table customer_addresses (
	id bigint primary key identity(1,1),
	customer_id bigint not null,
	postal_code varchar(8) not null,
	federal_unit varchar(2) not null,
	city varchar(80) not null,
	neighborhood varchar(160) not null,
	street varchar(240) not null,
	number varchar(10) default 'S/N',
	complement varchar(20),
	ref_point varchar(240),

	created_at datetime2 default getdate(),
	updated_at datetime2 default getdate(),

	foreign key (customer_id) references customers(id)
);