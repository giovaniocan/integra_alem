
# Integra Alem - Projeto Spring Boot com MySQL e RabbitMQ

Este projeto é uma aplicação **Spring Boot** configurada para rodar com **MySQL** e **RabbitMQ** usando **Docker**. Ele pode ser facilmente rodado em um ambiente de contêiner para facilitar o desenvolvimento e a implantação.

## **Pré-requisitos**

Antes de começar, certifique-se de ter as seguintes ferramentas instaladas em sua máquina:

- **Docker**: [Instruções de instalação](https://docs.docker.com/get-docker/)
- **Docker Compose**: [Instruções de instalação](https://docs.docker.com/compose/install/)

## **Passo a passo para rodar o projeto com Docker**

### 1. **Clonando o repositório**

Primeiro, clone o repositório para sua máquina local:

```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio
```

### 2. **Subindo o ambiente com Docker Compose**

O Docker Compose já está configurado para rodar a aplicação Spring Boot com MySQL e RabbitMQ. Para rodar o projeto, basta usar o comando:

```bash
docker-compose up --build
```

Esse comando vai:

- Construir as imagens Docker necessárias.
- Subir os containers do **backend**, **MySQL** e **RabbitMQ**.

### 3. **Verificando se o ambiente está funcionando**

Após o Docker terminar de construir e rodar os containers, você pode verificar se tudo está funcionando corretamente acessando as seguintes URLs:

- **MySQL**: O MySQL estará acessível na porta `3307`. Você pode conectar a ele utilizando um cliente MySQL, com as credenciais:
  - **Host**: `localhost`
  - **Porta**: `3307`
  - **Usuário**: `root`
  - **Senha**: `senha_mysql`

- **RabbitMQ**: O RabbitMQ estará acessível na interface de administração em [http://localhost:15672/](http://localhost:15672/). As credenciais padrão são:
  - **Usuário**: `guest`
  - **Senha**: `guest`

- **Backend (Spring Boot)**: A aplicação backend estará disponível na porta `8080`. Você pode acessar a API REST utilizando [http://localhost:8080](http://localhost:8080).

### 4. **Parar os containers**

Para parar os containers, basta rodar o seguinte comando:

```bash
docker-compose down
```

Esse comando vai parar e remover os containers criados.

### 5. **Personalizando o projeto**

Se você precisar personalizar o projeto para se ajustar ao seu ambiente, você pode editar os seguintes arquivos:

- **`application.properties`**: Para configurar variáveis específicas do Spring Boot, como a conexão com o banco de dados MySQL e RabbitMQ.
- **`docker-compose.yml`**: Para modificar configurações de serviços como MySQL, RabbitMQ ou o backend.

### 6. **Resolvendo problemas comuns**

- **Problema de porta ocupada**: Se a porta `3307` ou `8080` já estiver sendo usada, você pode alterar as portas no arquivo `docker-compose.yml`.
