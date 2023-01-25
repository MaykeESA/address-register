<img height="150em" align="right" src="https://media.licdn.com/dms/image/C4E0BAQHjAdEZqe2NNg/company-logo_200_200/0/1579804295904?e=1682553600&v=beta&t=IBzWNEHhw8MiscQq0a0aXF21wDmLcU_4DNdeS7pe-a4"/>

# Avaliação Attornatus - 📃

* Avaliação Back-end da Attornatus, nessa avaliação será necessário criar um relacionamento entre duas tabelas, e torna-las funcionais através de endpoints.
<br>

<h2> 💻 Tecnologias: </h2>

* Java
* Spring Boot
* Spring Data

<br>
<h2> 🎯 Objetivos: </h2>

* 1 - Criar uma pessoa
* 2 - Editar uma pessoa 
* 3 - Consultar uma pessoa 
* 4 - Listar pessoas 
* 5 - Criar endereço para pessoa 
* 6 - Listar endereços da pessoa 
* 7 - Poder informar qual endereço é o principal da pessoa 

<br>
<h2> 📈 Relacionamento: </h2>

<img height="210em" align="center" src="https://media.discordapp.net/attachments/817401092752932916/1067792868082729080/rounded-in-photoretrica.png"/>

<br>
<h2> 📌 Endpoints: </h2>

* GET: ```localhost:8080/pessoa```
  <br>
  - _Objetivo 4: ✔️_

* GET: ```localhost:8080/pessoa/{id}```
  <br>
  - _Objetivo 3: ✔️_
    <br>
  - _Objetivo 6: ✔️_

* GET: ```localhost:8080/endereco```
  <br>
  - _Objetivo Extra: ✔️_

* POST: ```localhost:8080/pessoa```
  <br>
  - _Objetivo 1: ✔️_
  ```
  {
    "nome":"Mayke",
    "dataNascimento":"16/03/2003"
  }
  ```
  
* POST: ```localhost:8080/endereco/{idEndereco}/pessoa/{idPessoa}/principal```
  <br>
  - _Objetivo 7: ✔️_

* POST: ```localhost:8080/endereco```
  <br>
  - _Objetivo 5: ✔️_

  ```
  {
    "logradouro":"Rua Ayrton Senna",
    "cep":"010519941",
    "numero":"27",
    "cidade":"São Paulo",
    "idPessoa":"1"
  }
  ```
* PUT: ```localhost:8080/pessoa/{id}```
  <br>
  - _Objetivo 2: ✔️_
  ```
  {
    "nome":"Mayke Erick",
    "dataNascimento":"16/03/2003"
  }
  ```


