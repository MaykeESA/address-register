# Address Register - 🏠

* Rest API para registrar endereços de pessoas.
<br>

<br>
<h2> 📂 Estrutura de pastas: </h2>

```
├── address-register
|   
|   br.com.project
|   |
│   ├── config.validacao/
|   |   ├── ErroFormularioDto.java
│   │   └── ErroValidacaoHandler.java
|   |
│   ├── controller/
|   |   ├── EnderecoController.java
│   │   └── PessoaController.java
|   |
│   ├── model/
│   │   ├── dto/
|   |   |   ├── EnderecoDto.java
|   |   |   ├── PessoaDetalhadoDto.java
|   |   |   └── PessoaDto.java
|   |   |
│   │   └── form/
|   |   |   ├── EnderecoForm.java
|   |   |   └── PessoaForm.java
|   |   |
|   |   ├── Endereco.java
|   |   ├── Pessoa.java
|   |   └── Residencia.java
|   |
│   ├── repository/
│   │   ├── EnderecoRepository.java
│   │   ├── PessoaRepository.java
│   │   └── ResidenciasRepository.java
|   |
│   ├── service/
│   │   ├── PersistService.java
|   |
|   └── AddressRegisterApplication.java
```

<br>
<h2> 📈 Relacionamento: </h2>

<img height="210em" align="center" src="https://cdn.discordapp.com/attachments/817401092752932916/1080838645109501982/rounded-in-photoretrica.png"/>

<br>
<h2> 📌 Endpoints: </h2>

* GET: ```localhost:8080/pessoa```

* GET: ```localhost:8080/pessoa/{id}```

* GET: ```localhost:8080/endereco```

* POST: ```localhost:8080/pessoa```

  ```
  {
    "nome":"Mayke",
    "dataNascimento":"16/03/2003"
  }
  ```
  
* POST: ```localhost:8080/endereco/{idEndereco}/pessoa/{idPessoa}/principal```

* POST: ```localhost:8080/endereco```
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
  ```
  {
    "nome":"Mayke Erick",
    "dataNascimento":"16/03/2003"
  }
  ```
