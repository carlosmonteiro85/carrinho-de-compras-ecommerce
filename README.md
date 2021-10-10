### Frameworks
  
A arquitetura utilizada para o desenvolvimento dessa API foi a arquitetura REST, por permitir uma boa comunicação entre aplicações diferentes.

Para este projeto, foi escolhido o framework "Spring boot", pois possui todas as bibliotecas necessárias para desenvolver aplicações em REST, visando a rapidez  nas configurações do projeto, ganhando tempo para o foco no desenvolvimento.

A dependência dev-tools foi adicionada no pom.xml, por trazer a facilidade do projeto fazer o re-buid toda vez que é salvo algum arquivo fonte, desta forma não necessita finalizar o processo e recomeçá-lo.


### Executando o projeto

Para  executar o projeto, é necessário executar o arquivo “GameStoreApplication.java”, como “Java application” ou executando o comando ./mvnw spring-boot:run

### Banco em Memoria H2

Foi utilizado o banco de dados H2 para a fase de desenvolvimento.

Após o projeto receber o build, é iniciado o H2, que é encontrado através do endpoint: http://localhost:8080/h2-console/ dados de acesso:

* JDBC URL: jdbc:h2:mem:game-store
* User: sa
* Password: vazio

![print-h2-console](static-files/print-h2-console.png "H2 console") 
