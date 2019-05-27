use master
go
drop database suv;
go
create database suv;
go
use suv;
go

/*
drop table vacinas;
drop table primeira_dose;
drop table segunda_dose;
drop table terceira_dose;
drop table tipos_vacinas;
drop table funcionario;
drop table empresa;
drop table cliente;

drop procedure insert1dose;
drop procedure insert2dose;
drop procedure insert3dose;
drop procedure listarDetalheVacina;
drop procedure listarVacinas;
*/

create table cliente(
id_cli int primary key identity not null,
nome varchar(50),
sobre_nome varchar(50),
email varchar(50),
sexo char,
data_nasc date,
nacionalidade varchar(50),
rg varchar(9),
cpf varchar(12),
endereco varchar(50),
cep varchar(10),
telefone varchar(10),
telefone_cel varchar(10),
senha varchar(50));

create table empresa(
id_emp int identity primary key not null,
nome varchar(50),
raz_social varchar(50),
CNPJ varchar(25),
endereco varchar(50),
cep varchar(50),
estado varchar(50),
telefone varchar(10),
usuario varchar(50),
email varchar(50),
senha varchar(50));

create table funcionario(
id_func int identity primary key not null,
id_empresa_fk int foreign key references empresa(id_emp),
nome varchar(50),
tipo int,
genero varchar(1),
idade varchar(2),
cpf varchar(15),
senha varchar(20));

create table Tipos_vacinas(
id_Tipo_Vacina int identity primary key not null,
empresa_id_fk int foreign key references empresa(id_emp),
nome_vacina varchar(50));

create table primeira_dose(
id_primeira_dose int identity primary key not null,
id_Tipo_Vacina_fk int foreign key references Tipos_vacinas(id_Tipo_Vacina),
id_cli_fk int foreign key references Cliente(id_cli),
id_func_fk int foreign key references funcionario(id_func),
data date,
local varchar(50),
registro_prof varchar(10));

create table segunda_dose(
id_segunda_dose int identity primary key not null,
id_Tipo_Vacina_fk int foreign key references Tipos_vacinas(id_Tipo_Vacina),
id_cli_fk int foreign key references Cliente(id_cli),
id_func_fk int foreign key references funcionario(id_func),
data date,
local varchar(50),
registro_prof varchar(10));

create table terceira_dose(
id_terceira_dose int identity primary key not null,
id_Tipo_Vacina_fk int foreign key references Tipos_vacinas(id_Tipo_Vacina),
id_cli_fk int foreign key references Cliente(id_cli),
id_func_fk int foreign key references funcionario(id_func),
data date,
local varchar(50),
registro_prof varchar(10));

create table primeiro_reforco(
id_primeiro_reforco int identity primary key not null,
id_Tipo_Vacina_fk int foreign key references Tipos_vacinas(id_Tipo_Vacina),
id_cli_fk int foreign key references Cliente(id_cli),
id_func_fk int foreign key references funcionario(id_func),
data date,
local varchar(50),
registro_prof varchar(10));

create table segundo_reforco(
id_segundo_reforco int identity primary key not null,
id_Tipo_Vacina_fk int foreign key references Tipos_vacinas(id_Tipo_Vacina),
id_cli_fk int foreign key references Cliente(id_cli),
id_func_fk int foreign key references funcionario(id_func),
data date,
local varchar(50),
registro_prof varchar(10));

create table vacinas(
id_vacinas int identity primary key not null,
id_cli_fk int foreign key references cliente(id_cli),
id_tipo_vacina_fk int foreign key references Tipos_vacinas(id_Tipo_Vacina),
primeira_dose_fk int foreign key references primeira_dose(id_primeira_dose),
segunda_dose_fk int foreign key references segunda_dose(id_segunda_dose),
terceira_dose_fk int foreign key references terceira_dose(id_terceira_dose),
primeiro_reforco varchar(10),
segundo_reforco varchar(10));

--alter table vacinas alter column primeiro_reforco int;
--alter table vacinas alter column segundo_reforco int;

/*go
alter table vacinas 
	add constraint fk_primeiro_reforco foreign key(primeiro_reforco_fk)
	references primeiro_reforco(id_primeiro_reforco)
	ON DELETE CASCADE    
		ON UPDATE CASCADE    
go
alter table vacinas 
	add constraint fk_segundo_reforco foreign key(segundo_reforco_fk)
	references segundo_reforco(id_segundo_reforco)
	ON DELETE CASCADE    
		ON UPDATE CASCADE
		
go
*/

--sp_RENAME 'Vacinas.primeiro_reforco_fk' , 'primeiro_reforco_fk', 'COLUMN';

--EXEC sp_rename @objname = 'Vacinas."[primeiro_reforco_fk]"', @newname = 'primeiro_reforco_fk', @objtype = 'COLUMN'

insert into cliente (nome,sobre_nome,email,sexo,data_nasc,nacionalidade,rg,cpf,endereco,cep,telefone,telefone_cel,senha)
values('cli','sobre','teste@hotmail.com','M','1997-01-25','Brasileira','12345677','9876543214','Rua 123','0123456','12345678','999999999','123');

insert into cliente (nome,sobre_nome,email,sexo,data_nasc,nacionalidade,rg,cpf,endereco,cep,telefone,telefone_cel,senha)
values('b','fad','a@a.com.br','M','1998-08-24','brasileira','215415','21546','ragaata','13515','515','654156','12345');

insert into empresa(nome,raz_social,cnpj,endereco,cep,estado,telefone,usuario,email,senha)values('Empresa1','Empresa 1 de Saúde','1324657','Rua Mil e um','1324567','SP','1346579','Empresa1','empresa1@teste.com','123');
insert into empresa(nome,raz_social,cnpj,endereco,cep,estado,telefone,usuario,email,senha)values('Empresa2','Empresa 2 de Saúde','1231345','Rua Dois mil e um','1324547','SP','547878','Empresa2','empresa2@teste.com','12345');

insert into funcionario (id_empresa_fk,nome,tipo,genero,idade,cpf,senha)values(1,'func',1,'M','27','123455677','123');
insert into funcionario (id_empresa_fk,nome,tipo,genero,idade,cpf,senha)values(2,'Suelen',2,'F',23,'1234567','12345');

insert into Tipos_vacinas values(1,'BCG_ID');
insert into Tipos_vacinas values(1,'Hepatite_B');
insert into Tipos_vacinas values(1,'Rotavirus');
insert into Tipos_vacinas values(1,'Pneumococos');
insert into Tipos_vacinas values(1,'Tetravalente');
insert into Tipos_vacinas values(1,'Polio');
insert into Tipos_vacinas values(1,'Meningococo_C');
insert into Tipos_vacinas values(1,'Febre_Amarela');
insert into Tipos_vacinas values(1,'Triplice_Viral');
insert into Tipos_vacinas values(1,'Dupla_Adulto');
insert into Tipos_vacinas values(1,'Influenza');

insert into Tipos_vacinas values(2,'BCG_ID');
insert into Tipos_vacinas values(2,'Hepatite_B');
insert into Tipos_vacinas values(2,'Rotavirus');
insert into Tipos_vacinas values(2,'Pneumococos');
insert into Tipos_vacinas values(2,'Tetravalente');
insert into Tipos_vacinas values(2,'Polio');
insert into Tipos_vacinas values(2,'Meningococo_C');
insert into Tipos_vacinas values(2,'Febre_Amarela');
insert into Tipos_vacinas values(2,'Triplice_Viral');
insert into Tipos_vacinas values(2,'Dupla_Adulto');
insert into Tipos_vacinas values(2,'Influenza');
select*from primeira_dose;
select*from vacinas;
-------------
begin
insert into primeira_dose values(1,1,1,'12/02/2012','Local 123','12345');
declare @maxint int;
declare @tipovacina int;
select @maxint=max(id_primeira_dose)from primeira_dose where id_cli_fk=1;
select @tipovacina=max(id_Tipo_Vacina_fk)from primeira_dose where id_cli_fk=1;
insert into vacinas (id_cli_fk,id_tipo_vacina_fk,primeira_dose_fk) values(1,@tipovacina,@maxint);
end;
----------
-------------
begin
insert into segunda_dose values(1,1,1,'12/02/2014','Local 12345678','1234564');
declare @maxint2 int;
declare @maxintVacinas2 int;
select @maxint2=max(id_segunda_dose)from segunda_dose where id_cli_fk=1;
select @maxintVacinas2=max(id_vacinas)from vacinas;
update vacinas set segunda_dose_fk=@maxint2 where id_vacinas=@maxintVacinas2;
end;
----------
-------------
begin
insert into terceira_dose values(1,1,1,'07/07/2016','Local 32153','57045');
declare @maxint3 int;
declare @maxintVacinas3 int;
select @maxint3=max(id_terceira_dose)from terceira_dose where id_cli_fk=1;
select @maxintVacinas3=max(id_vacinas)from vacinas;
update vacinas set terceira_dose_fk=@maxint3 where id_vacinas=@maxintVacinas3;
end;
----------

begin
insert into primeira_dose values(12,2,2,'2009-02-12','Local 13486','988798');
declare @maxint4 int;
declare @tipovacina2 int;
select @maxint4=max(id_primeira_dose)from primeira_dose where id_cli_fk=2;
select @tipovacina2=max(id_Tipo_Vacina_fk)from primeira_dose where id_cli_fk=2;
insert into vacinas (id_cli_fk,id_tipo_vacina_fk,primeira_dose_fk) values(2,@tipovacina2,@maxint4);
end;
----------select*from vacinas;
-------------
begin
insert into segunda_dose values(12,2,2,'2011-08-24','Local 64878','87785');
declare @maxint5 int;
declare @maxintVacinas5 int;
select @maxint5=max(id_segunda_dose)from segunda_dose where id_cli_fk=2;
select @maxintVacinas5=max(id_vacinas)from vacinas;
update vacinas set segunda_dose_fk=@maxint5 where id_vacinas=@maxintVacinas5;
end;
----------
-------------
begin
insert into terceira_dose values(12,2,2,'2015-07-31','Local 001','154505');
declare @maxint6 int;
declare @maxintVacinas6 int;
select @maxint6=max(id_terceira_dose)from terceira_dose where id_cli_fk=2;
select @maxintVacinas6=max(id_vacinas)from vacinas ;
update vacinas set terceira_dose_fk=@maxint6 where id_vacinas=@maxintVacinas6;
end;
----------

select*from vacinas;

select*from cliente;
select*from funcionario;
select*from empresa;
select*from tipos_vacinas;

select*from primeira_dose;
select*from segunda_dose;
select*from terceira_dose;

select a.nome,b.* from funcionario as a inner join Empresa as b on a.id_empresa_fk=b.id_emp where id_empresa_fk=1;

/*consulta vacinas do cliente*/
select a.*,b.* from vacinas as a inner join primeira_dose as b on a.primeira_dose_fk=b.id_primeira_dose where a.id_cli_fk=1;
------------------------

select*from funcionario where id_empresa_fk=1

select*from cliente;

/*consulta de todas as vacinas do cliente*/
select a.*,b.nome_vacina from vacinas as a inner join Tipos_vacinas as b on a.id_tipo_vacina_fk=b.id_Tipo_Vacina where a.id_cli_fk=1;

/*consulta de primeira dose da vacina*/
select a.nome,b.id_tipo_vacina_fk,c.*,d.*,e.nome from cliente as a inner join vacinas as b on a.id_cli= b.id_cli_fk 
inner join Tipos_vacinas as c on b.id_tipo_vacina_fk=c.id_Tipo_Vacina inner join primeira_dose as d on b.primeira_dose_fk=d.id_primeira_dose 
inner join empresa as e on c.empresa_id_fk=e.id_emp where a.id_cli=1

/*consulta de segunda dose da vacina*/
select a.nome,b.id_tipo_vacina_fk,c.*,d.*,e.nome from cliente as a inner join vacinas as b on a.id_cli= b.id_cli_fk 
inner join Tipos_vacinas as c on b.id_tipo_vacina_fk=c.id_Tipo_Vacina inner join segunda_dose as d on b.segunda_dose_fk=d.id_segunda_dose 
inner join empresa as e on c.empresa_id_fk=e.id_emp where a.id_cli=1

/*consulta de terceira dose da vacina*/
select a.nome,b.id_tipo_vacina_fk,c.*,d.*,e.nome from cliente as a inner join vacinas as b on a.id_cli= b.id_cli_fk 
inner join Tipos_vacinas as c on b.id_tipo_vacina_fk=c.id_Tipo_Vacina inner join terceira_dose as d on b.terceira_dose_fk=d.id_terceira_dose 
inner join empresa as e on c.empresa_id_fk=e.id_emp where a.id_cli=1

/*Cliente da Empresa*/
select a.id_cli_fk,b.*,c.nome from vacinas as a inner join Tipos_vacinas as b on a.id_tipo_vacina_fk=b.id_Tipo_Vacina inner join cliente as c on a.id_cli_fk=c.id_cli where b.empresa_id_fk=1

/*Vacina da primeira dose por Empresa*/
select a.*,b.*,c.* from vacinas as a inner join primeira_dose as b on a.primeira_dose_fk=b.id_primeira_dose 
inner join Tipos_vacinas as c on b.id_Tipo_Vacina_fk=c.id_Tipo_Vacina where c.empresa_id_fk=2;

/*Seleciona detalhes da primeira dose da vacina*/
select a.nome,b.id_tipo_vacina_fk,c.nome_vacina,d.id_primeira_dose,convert(varchar,d.data,103)as data,
d.local,d.registro_prof,d.id_func_fk,e.nome as nome_func from cliente as a 
inner join vacinas as b on a.id_cli= b.id_cli_fk 
inner join Tipos_vacinas as c on b.id_tipo_vacina_fk=c.id_Tipo_Vacina 
inner join primeira_dose as d on b.primeira_dose_fk=d.id_primeira_dose 
inner join funcionario as e on d.id_func_fk=e.id_func where a.id_cli=1

/*Seleciona detalhes da segunda dose da vacina*/
select a.nome,b.id_tipo_vacina_fk,c.nome_vacina,d.id_segunda_dose,convert(varchar,d.data,103)as data,
d.local,d.registro_prof,d.id_func_fk,e.nome as nome_func from cliente as a 
inner join vacinas as b on a.id_cli= b.id_cli_fk 
inner join Tipos_vacinas as c on b.id_tipo_vacina_fk=c.id_Tipo_Vacina 
inner join segunda_dose as d on b.segunda_dose_fk=d.id_segunda_dose 
inner join funcionario as e on d.id_func_fk=e.id_func where a.id_cli=1

/*Seleciona detalhes da terceira dose da vacina*/
select a.nome,b.id_tipo_vacina_fk,c.nome_vacina,d.id_terceira_dose,convert(varchar,d.data,103)as data,
d.local,d.registro_prof,d.id_func_fk,e.nome as nome_func from cliente as a 
inner join vacinas as b on a.id_cli= b.id_cli_fk 
inner join Tipos_vacinas as c on b.id_tipo_vacina_fk=c.id_Tipo_Vacina 
inner join terceira_dose as d on b.terceira_dose_fk=d.id_terceira_dose 
inner join funcionario as e on d.id_func_fk=e.id_func where a.id_cli=1

go
/*procedure de select*/
create procedure listarVacinas
@idCli int,@idvacina int
as
select a.nome,b.id_tipo_vacina_fk,c.nome_vacina,d.id_primeira_dose,convert(varchar,d.data,103)as data1,
e.id_segunda_dose,convert(varchar,e.data,103)as data2,f.id_terceira_dose,convert(varchar,f.data,103)as data3,
g.id_primeiro_reforco,convert(varchar,g.data,103)as data4,h.id_segundo_reforco,convert(varchar,h.data,103)as data5 from
cliente as a inner join vacinas as b on a.id_cli= b.id_cli_fk 
inner join Tipos_vacinas as c on b.id_tipo_vacina_fk=c.id_Tipo_Vacina 
full join primeira_dose as d on b.primeira_dose_fk=d.id_primeira_dose 
full join segunda_dose as e on b.segunda_dose_fk=e.id_segunda_dose 
full join terceira_dose as f on b.terceira_dose_fk=f.id_terceira_dose 
full join primeiro_reforco as g on b.primeiro_reforco_fk=g.id_primeiro_reforco
full join segundo_reforco as h on b.segundo_reforco_fk=h.id_segundo_reforco
where a.id_cli=@idCli and b.id_tipo_vacina_fk=@idvacina;

go
--exec listarVacinas 1
create procedure listarDetalheVacina
@dose int,@tipovacina int,@idcli int
as
if(@dose like 1)
select a.nome,b.id_tipo_vacina_fk,c.nome_vacina,d.id_primeira_dose,convert(varchar,d.data,103)as data,
            d.local,d.registro_prof,d.id_func_fk,e.nome as nome_func from cliente as a
            inner join vacinas as b on a.id_cli= b.id_cli_fk
            full join Tipos_vacinas as c on b.id_tipo_vacina_fk=c.id_Tipo_Vacina
            full join primeira_dose as d on b.primeira_dose_fk=d.id_primeira_dose
            full join funcionario as e on d.id_func_fk=e.id_func where a.id_cli=@idcli and b.id_tipo_vacina_fk=@tipovacina
else if(@dose like 2)
select a.nome,b.id_tipo_vacina_fk,c.nome_vacina,d.id_segunda_dose,convert(varchar,d.data,103)as data,
            d.local,d.registro_prof,d.id_func_fk,e.nome as nome_func from cliente as a
            inner join vacinas as b on a.id_cli= b.id_cli_fk
            full join Tipos_vacinas as c on b.id_tipo_vacina_fk=c.id_Tipo_Vacina
            full join segunda_dose as d on b.segunda_dose_fk=d.id_segunda_dose
            full join funcionario as e on d.id_func_fk=e.id_func where a.id_cli=@idcli and b.id_tipo_vacina_fk=@tipovacina
else if(@dose like 3)
select a.nome,b.id_tipo_vacina_fk,c.nome_vacina,d.id_terceira_dose,convert(varchar,d.data,103)as data,
            d.local,d.registro_prof,d.id_func_fk,e.nome as nome_func from cliente as a
            inner join vacinas as b on a.id_cli= b.id_cli_fk
            full join Tipos_vacinas as c on b.id_tipo_vacina_fk=c.id_Tipo_Vacina
            full join terceira_dose as d on b.terceira_dose_fk=d.id_terceira_dose
            full join funcionario as e on d.id_func_fk=e.id_func where a.id_cli=@idcli and b.id_tipo_vacina_fk=@tipovacina
else if(@dose like 4)
select a.nome,b.id_tipo_vacina_fk,c.nome_vacina,d.id_primeiro_reforco,convert(varchar,d.data,103)as data,
            d.local,d.registro_prof,d.id_func_fk,e.nome as nome_func from cliente as a
            inner join vacinas as b on a.id_cli= b.id_cli_fk
            full join Tipos_vacinas as c on b.id_tipo_vacina_fk=c.id_Tipo_Vacina
            full join primeiro_reforco as d on b.primeiro_reforco_fk=d.id_primeiro_reforco
            full join funcionario as e on d.id_func_fk=e.id_func where a.id_cli=@idcli and b.id_tipo_vacina_fk=@tipovacina

else if(@dose like 5)
select a.nome,b.id_tipo_vacina_fk,c.nome_vacina,d.id_segundo_reforco,convert(varchar,d.data,103)as data,
            d.local,d.registro_prof,d.id_func_fk,e.nome as nome_func from cliente as a
            inner join vacinas as b on a.id_cli= b.id_cli_fk
            full join Tipos_vacinas as c on b.id_tipo_vacina_fk=c.id_Tipo_Vacina
            full join segundo_reforco as d on b.segundo_reforco_fk=d.id_segundo_reforco
            full join funcionario as e on d.id_func_fk=e.id_func where a.id_cli=@idcli and b.id_tipo_vacina_fk=@tipovacina

--exec listarDetalheVacina 1,1.1
go
create procedure insert1dose
@idTipoVacina int,@id_cli int,@id_func int,@data varchar(20),@local varchar(50),@reg varchar(50)
as 
insert into primeira_dose values(@idTipoVacina,@id_cli,@id_func,convert(datetime,@data,103),@local,@reg);
declare @maxint int,@tipovacina int;
select @maxint=max(id_primeira_dose),@tipovacina=max(id_Tipo_Vacina_fk)from primeira_dose where id_cli_fk=@id_cli;
insert into vacinas (id_cli_fk,id_tipo_vacina_fk,primeira_dose_fk) values(@id_cli,@tipovacina,@maxint);

go
create procedure insert2dose
@idTipoVacina int,@id_cli int,@id_func int,@data varchar(20),@local varchar(50),@reg varchar(50)
as 
insert into segunda_dose values(@idTipoVacina,@id_cli,@id_func,convert(datetime,@data,103),@local,@reg);
declare @maxint int,@tipovacina int;
select @maxint=max(id_segunda_dose),@tipovacina=max(id_Tipo_Vacina_fk)from segunda_dose where id_cli_fk=@id_cli;
update vacinas set segunda_dose_fk=@maxint where id_tipo_vacina_fk=@idTipoVacina;
--exec insert3dose 13,2,2,'27/04/2019','1740','1740'
go
create procedure insert3dose
@idTipoVacina int,@id_cli int,@id_func int,@data varchar(20),@local varchar(50),@reg varchar(50)
as 
insert into terceira_dose values(@idTipoVacina,@id_cli,@id_func,convert(datetime,@data,103),@local,@reg);
declare @maxint int,@tipovacina int;
select @maxint=max(id_terceira_dose),@tipovacina=max(id_Tipo_Vacina_fk) from terceira_dose where id_cli_fk=@id_cli;
update vacinas set terceira_dose_fk= @maxint where id_tipo_vacina_fk=@idTipoVacina;

--exec insert1dose 1,1,1,'14/04/2019','Local1404','28081997'

create procedure insert1reforco
@idTipoVacina int,@id_cli int,@id_func int,@data varchar(20),@local varchar(50),@reg varchar(50)
as 
insert into primeiro_reforco values(@idTipoVacina,@id_cli,@id_func,convert(datetime,@data,103),@local,@reg);
declare @maxint int,@tipovacina int;
select @maxint=max(id_primeiro_reforco),@tipovacina=max(id_Tipo_Vacina_fk) from primeiro_reforco where id_cli_fk=@id_cli;
update vacinas set primeiro_reforco_fk= @maxint where id_tipo_vacina_fk=@idTipoVacina;

create procedure insert2reforco
@idTipoVacina int,@id_cli int,@id_func int,@data varchar(20),@local varchar(50),@reg varchar(50)
as 
insert into segundo_reforco values(@idTipoVacina,@id_cli,@id_func,convert(datetime,@data,103),@local,@reg);
declare @maxint int,@tipovacina int;
select @maxint=max(id_segundo_reforco),@tipovacina=max(id_Tipo_Vacina_fk) from segundo_reforco where id_cli_fk=@id_cli;
update vacinas set segundo_reforco_fk= @maxint where id_tipo_vacina_fk=@idTipoVacina;

create procedure VacinasRecentesCli
@idCli int
as
select a.*,b.nome_vacina from primeira_dose  as a  inner join Tipos_vacinas as b on a.id_Tipo_Vacina_fk=b.id_tipo_vacina where id_cli_fk=@idCli
union
select a.*,b.nome_vacina from segunda_dose  as a  inner join Tipos_vacinas as b on a.id_Tipo_Vacina_fk=b.id_tipo_vacina where id_cli_fk=@idCli
union
select a.*,b.nome_vacina from terceira_dose  as a  inner join Tipos_vacinas as b on a.id_Tipo_Vacina_fk=b.id_tipo_vacina where id_cli_fk=@idCli
union
select a.*,b.nome_vacina from primeiro_reforco  as a  inner join Tipos_vacinas as b on a.id_Tipo_Vacina_fk=b.id_tipo_vacina where id_cli_fk=@idCli
union
select a.*,b.nome_vacina from segundo_reforco  as a  inner join Tipos_vacinas as b on a.id_Tipo_Vacina_fk=b.id_tipo_vacina where id_cli_fk=@idCli
order by data desc;

create procedure VacinasRecentesFunc
@idFunc int
as
select a.*,b.nome_vacina from primeira_dose  as a  inner join Tipos_vacinas as b on a.id_Tipo_Vacina_fk=b.id_tipo_vacina where id_func_fk=@idFunc
union
select a.*,b.nome_vacina from segunda_dose  as a  inner join Tipos_vacinas as b on a.id_Tipo_Vacina_fk=b.id_tipo_vacina where id_func_fk=@idFunc
union
select a.*,b.nome_vacina from terceira_dose  as a  inner join Tipos_vacinas as b on a.id_Tipo_Vacina_fk=b.id_tipo_vacina where id_func_fk=@idFunc
union
select a.*,b.nome_vacina from primeiro_reforco  as a  inner join Tipos_vacinas as b on a.id_Tipo_Vacina_fk=b.id_tipo_vacina where id_func_fk=@idFunc
union
select a.*,b.nome_vacina from segundo_reforco  as a  inner join Tipos_vacinas as b on a.id_Tipo_Vacina_fk=b.id_tipo_vacina where id_func_fk=@idFunc
order by data desc;

create procedure VacinasRecentesCli
@idcli int
as
select a.nome,b.id_cli_fk,b.ult_atual as 'data',b.id_tipo_vacina_fk,c.nome_vacina from 
cliente as a inner join vacinas as b on a.id_cli= b.id_cli_fk 
inner join Tipos_vacinas as c on b.id_tipo_vacina_fk=c.id_Tipo_Vacina where a.id_cli=@idcli order by data desc
/*
select convert(varchar,data_nasc,103)from cliente;

insert into cliente (data_nasc) values (convert(datetime,'28/08/1997',103));


select nome,sobre_nome,email,sexo,convert(varchar,data_nasc,103)as data_nasc,nacionalidade,rg,cpf,endereco,cep,telefone,telefone_cel from cliente where id_cli=1;
select* from cliente;

insert into cliente (nome,data_nasc) values ('Teste2',convert(datetime,'28/08/1997',103));
select*from vacinas;
select*from primeira_dose;
select*from tipos_vacinas


select a.id_Tipo_Vacina_fk,a.id_cli_fk,a.id_func_fk,convert(varchar,a.data,103) as data,a.local,a.registro_prof,b.nome_vacina 
from primeira_dose as a inner join Tipos_vacinas as b on a.id_Tipo_Vacina_fk=b.id_Tipo_Vacina where a.id_primeira_dose=1;

select a.nome,b.id_tipo_vacina_fk,c.nome_vacina,d.id_primeira_dose,convert(varchar,d.data,103)as data1,
e.id_segunda_dose,convert(varchar,e.data,103)as data2,f.id_terceira_dose,convert(varchar,f.data,103)as data3 from 
cliente as a inner join vacinas as b on a.id_cli= b.id_cli_fk 
inner join Tipos_vacinas as c on b.id_tipo_vacina_fk=c.id_Tipo_Vacina 
inner join primeira_dose as d on b.primeira_dose_fk=d.id_primeira_dose 
inner join segunda_dose as e on b.segunda_dose_fk=e.id_segunda_dose 
inner join terceira_dose as f on b.terceira_dose_fk=f.id_terceira_dose where a.id_cli=1

Seleciona todos os IDs da vacina
select a.*,b.nome_vacina from vacinas as a inner join Tipos_vacinas as b on a.id_tipo_vacina_fk=b.id_Tipo_Vacina where a.id_cli_fk=1

begin 
declare @maxdata date,@maxdata2 date,@maxdata3 date,@maxdata4 date ,@maxdata5 date;
select @maxdata=max(data)from primeira_dose where id_cli_fk=1 and id_Tipo_Vacina_fk=4;
select @maxdata2=max(data)from segunda_dose where id_cli_fk=1 and id_Tipo_Vacina_fk=4;
select @maxdata3=max(data)from terceira_dose where id_cli_fk=1 and id_Tipo_Vacina_fk=4;
select @maxdata4=max(data)from primeiro_reforco where id_cli_fk=1 and id_Tipo_Vacina_fk=4;
select @maxdata5=max(data)from segundo_reforco where id_cli_fk=1 and id_Tipo_Vacina_fk=4;

if((@maxdata5>@maxdata4)and (@maxdata4>@maxdata2))
select*from segundo_reforco where data=@maxdata5 and id_cli_fk=1 and id_Tipo_Vacina_fk=4;
else if(@maxdata4>@maxdata3)
select*from primeiro_reforco where data=@maxdata4 and id_cli_fk=1 and id_Tipo_Vacina_fk=4;
else if((@maxdata3>@maxdata2)and(@maxdata3>@maxdata))
select*from terceira_dose where data=@maxdata3 and id_cli_fk=1 and id_Tipo_Vacina_fk=4;
else if(@maxdata2>@maxdata)
select*from segunda_dose where data=@maxdata2 and id_cli_fk=1 and id_Tipo_Vacina_fk=4;
else
select*from primeira_dose where data=@maxdata and id_cli_fk=1 and id_Tipo_Vacina_fk=4;
end;

alter table vacinas ADD ult_atual date

begin
declare @valor int,@loop int=0;
select @valor=id_vacinas from vacinas where id_cli_fk=1;
print @valor
end;
*/