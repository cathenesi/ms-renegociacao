# MS Renegociação

Exemplo de microsserviço criado para fins de teste. Implementado com Spring boot, conecta-se a uma instância do Mongo (configuração padrão) para simular dívidas exportadas do Mainframe.

Ao inicializar, a aplicação cria alguns dados de teste, na collection Dividas; exemplo do modelo de dados:


	{
		"_id" : NumberLong("25429106852"),
		"dividas" : [
			{
				"dataVencimento" : ISODate("2018-11-10T02:00:00Z"),
				"valor" : "1200"
			},
			{
				"dataVencimento" : ISODate("2018-08-10T03:00:00Z"),
				"valor" : "560"
			}
		],
		"_class" : "org.banco.renegociacao.core.model.DividaCliente"
	}
	(o id é o CPF do Cliente)


# Execução

Para editar o código fonte, é necessário o instalar o plugin Lombok (https://projectlombok.org/) na IDE.

Ao rodar a aplicação, a documentação da API pode ser vista na URL: http://localhost:9090/swagger-ui.html

Para fazer build, executar a task "build" do gradle; será gerado relatório de cobertura de testes no diretório build/reports/jacoco/test/html ; foram priorizados testes no pacote que contém regras de negócio (org.banco.renegociacao.core.model)

# Observações


* Não foi implementado mecanismo de autenticação. 
