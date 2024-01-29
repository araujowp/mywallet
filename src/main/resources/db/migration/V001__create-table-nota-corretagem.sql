create table nota_corretagem
(
	id bigint identity(1,1) not null,
	numero nvarchar(10) not null,
	cliente bigint not null, 
	data_pregao smalldatetime not null,
	tipo_operacao nvarchar(1) not null,
	taxa_liquidacao money not null,
	taxa_registro money not null,
	taxa_termo_opcoes money not null, 
	taxa_ANA money not null,
	emolumentos money not null,
	Taxa_Operacional money not null,
	execucao money not null,
	taxa_custodia money not null, 
	impostros money not null,
	IRRF money not null,
	outros money not null,
	nome_arquivo nvarchar(50) not null,
	
	primary key(id)
);