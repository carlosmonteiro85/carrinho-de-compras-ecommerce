### Frameworks
  
A arquitetura utilizada para o desenvolvimento dessa API foi a arquitetura REST, por permitir uma boa comunicação entre aplicações diferentes.

Para este projeto, foi escolhido o framework "Spring boot", pois possui todas as bibliotecas necessárias para desenvolver aplicações em REST, visando a rapidez  nas configurações do projeto, ganhando tempo para o foco no desenvolvimento.

A dependência dev-tools foi adicionada no pom.xml, por trazer a facilidade do projeto fazer o re-buid toda vez que é salvo algum arquivo fonte, desta forma não necessita finalizar o processo e recomeçá-lo.


### Executando o projeto

Para  executar o projeto, é necessário executar o arquivo "GameStoreApplication.java", como "Java application" ou executando o comando ./mvnw spring-boot:run

### Banco em Memoria H2

Foi utilizado o banco de dados H2 para a fase de desenvolvimento.

Após o projeto receber o build, é iniciado o H2, que é encontrado através do endpoint: http://localhost:8080/h2-console/ dados de acesso:

* JDBC URL: jdbc:h2:mem:game-store
* User: sa
* Password: vazio

<p float="left">
  <img src="static-files/print-h2-log.png" width="550" />
  <img src="static-files/print-h2-console.png" width="550" /> 
</p>

### Cliente REST para realizar testes - Postman

Para realizar os testes da aplicação, foi utilizado a ferramenta "Postman", escolhido pela facilidade de comunicação dos end-points.

### Endpoint usados:

#### Product
Criação de produto:  
* Método POST 
* End-point: http://localhost:8080/products/
<p float="left">
  <img src="static-files/print-postman-listAll.png" width="600" />
</p>


#### Atualização produto:  
* Método PUT 
* End-point: http://localhost:8080/products/{id_product}
<p float="left">
  <img src="static-files/print-postman-product-update.png" width="600" />
</p>


#### Deletar produto:  
* Método DELETE 
* End-point: http://localhost:8080/products/{id_product}
<p float="left">
  <img src="static-files/print-postman-product-delete.png" width="600" />
</p>


#### Obter produto por id:  
* Método GET 
* End-point: http://localhost:8080/products/{id_product}
<p float="left">
  <img src="static-files/print-postman-product-getById.png" width="600" />
</p>

#### Listar todos produto:  
* Método GET 
* End-point: http://localhost:8080/products/
<p float="left">
  <img src="static-files/print-postman-product-ListAll.png" width="600" />
</p>

### Ordenação

#### Ordenar por nome em ordem alfabética:  
* Método GET 
* End-point: http://localhost:8080/products?sort=name,asc
<p float="left">
  <img src="static-files/print-postman-product-orderByName.png" width="600" />
</p>


#### Ordenar por maior score:  
* Método GET 
* End-point: http://localhost:8080/products?sort=score,desc
<p float="left">
  <img src="static-files/print-postman-product-orderByScore.png" width="600" />
</p>


#### Ordenar por preço crescente:  
* Método GET 
* End-point: http://localhost:8080/products?sort=price,asc
<p float="left">
  <img src="static-files/print-postman-product-orderByPrice.png" width="600" />
</p>

### Carrinho

#### Criar carrinho:  
* Método POST 
* End-point: http://localhost:8080/cart
<p float="left">
  <img src="static-files/print-postman-cart-create.png" width="600" />
</p>

#### Adicionar item ao carrinho:  
* Método POST 
* End-point: http://localhost:8080/cart/addItemCart/{id_cart}
<p float="left">
  <img src="static-files/print-postman-cart-addItem.png" width="600" />
</p>

#### Atualizar carrinho:  
* Método PUT 
* End-point: http://localhost:8080/cart/{id}
<p float="left">
  <img src="static-files/print-postman-cart-updade.png" width="600" />
</p>

#### Remover item do carrinho:  
* Método DELETE 
* End-point: http://localhost:8080/cart/removeItemCart/{id_cart}
<p float="left">
  <img src="static-files/print-postman-cart-removeItem.png" width="600" />
</p>

mais alguns..

#### Obter carrinho por id: 
* Método GET
* End-point: http://localhost:8080/cart/{id_cart}

#### Obter todos carrinhos: 
* Método GET
* End-point: http://localhost:8080/cart/


### Check-out

#### Realizar pre-checkout com id do carrinho:  
* Método GET 
* End-point: http://localhost:8080/checkout/consult/{id_cart}
<p float="left">
  <img src="static-files/print-postman-checkout-pre-checkout.png" width="600" />
</p>

#### Salvar Checkout:  
* Método POST 
* End-point: http://localhost:8080/checkout
<p float="left">
  <img src="static-files/print-postman-checkout-create.png" width="600" />
</p>

#### Atualizar checkout: 
* Método PUT
* End-point: http://localhost:8080/checkout/{id_checkout}

#### Deletar checkout: 
* Método DELETE
* End-point: http://localhost:8080/checkout/{id_checkout}

***** As informações Subtotal, Total e Frete, são dinâmicas, iram mudar de acordo com os itens do carrinho,
cada item adicionado no carrinho é acrescido 10,00 no frete, se o valor total chegar a 250,00, o frete será Gratuito de acordo com a regra de negócio.
