# ms-renegociacao

Exemplo de microsserviço criado para fins de teste. Implementado com Spring boot, conecta-se a uma instância do Mongo (configuração padrão) para simular dívidas exportadas do Mainframe.

Para editar o código fonte, é necessário o instalar o plugin Lombok (https://projectlombok.org/) na IDE.

Ao rodar a aplicação, a documentação da API pode ser vista na URL: http://localhost:9090/swagger-ui.html

Para fazer build, executar a task "build" do gradle; será gerado relatório de cobertura de testes no diretório build/reports/jacoco/test/html ; foram priorizados testes no pacote que contém regras de negócio (org.banco.renegociacao.core.model)
