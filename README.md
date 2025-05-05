# Memoria Viva API

## 🌟 Visão Geral
Memoria Viva é uma API RESTful desenvolvida com Spring Boot que permite aos usuários registrarem suas memórias e sentimentos. A plataforma facilita o armazenamento de momentos importantes da vida como recordações familiares, conquistas profissionais, viagens inesquecíveis e muito mais, todos categorizados e associados a emoções específicas.

## 🛠️ Tecnologias Utilizadas
- **Spring Boot 3.4.5**: Framework Java para desenvolvimento de aplicações
- **Java 21**: Versão mais recente da linguagem Java
- **Spring Data JPA**: Facilita a integração com o banco de dados relacional
- **MySQL**: Sistema de gerenciamento de banco de dados relacional
- **Azure Blob Storage**: Serviço de armazenamento em nuvem para imagens
- **Maven**: Gerenciamento de dependências e build do projeto
- **Spring Validation**: Validação de dados de entrada

## 📋 Arquitetura

### Estrutura de Pacotes
```
com.api.memoriaviva
├── config        // Configurações do Spring e Azure
├── controller    // Controladores REST para endpoints da API
├── dto           // Objetos de Transferência de Dados
├── entity        // Entidades JPA mapeadas para o banco de dados
├── enums         // Enumerações para categorias e emojis
├── exception     // Manipuladores de exceções personalizadas
├── repository    // Interfaces de repositório para acesso aos dados
├── service       // Lógica de negócios
└── util          // Classes utilitárias
```

### Diagrama de Componentes
```
Cliente HTTP → Controller → Service → Repository → Database
                  ↓            ↓
                 DTO         Azure Blob Storage
```

## 🔄 Fluxo de Dados
1. O cliente faz uma requisição HTTP para a API
2. O controlador recebe a requisição e converte os dados para DTOs
3. O serviço processa a lógica de negócios
4. Os repositórios interagem com o banco de dados
5. Imagens são armazenadas no Azure Blob Storage
6. A resposta é formatada e retornada ao cliente

## 📝 Funcionalidades Principais

### Gerenciamento de Memórias
- **Criação**: Registrar novos momentos com título, descrição, categoria, emoji e imagem opcional
- **Leitura**: Listar todas as memórias registradas
- **Atualização**: Modificar detalhes de memórias existentes
- **Exclusão**: Remover memórias e seus recursos associados

### Categorização de Memórias
Cada memória pode ser associada a uma das seguintes categorias:
- Família (FAMILY)
- Escola (SCHOOL)
- Trabalho (WORK)
- Relacionamento (RELATIONSHIP)
- Amigos (FRIENDS)
- Viagem (TRAVEL)
- Hobby (HOBBY)
- Conquista (ACHIEVEMENT)
- Outro (OTHER)

### Expressão de Sentimentos
As memórias podem ser associadas a diferentes emoções:
- Alegria (HAPPY)
- Amor (LOVE)
- Tristeza (SAD)
- Diversão (FUNNY)
- Amizade (FRIENDSHIP)
- Gratidão (GRATEFUL)
- Admiração (ADMIRATION)
- Paz (PEACE)
- Pensamento (THOUGHT)
- Surpresa (SURPRISE)
- Ansiedade (ANXIETY)
- Raiva (ANGRY)

### Gerenciamento de Imagens
- Upload de imagens para Azure Blob Storage
- Atualização de imagens existentes
- Exclusão de imagens quando a memória é removida
- Limitação de tamanho de arquivo (2MB por imagem)

## 🌐 Endpoints da API

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/post/list` | Lista todas as memórias |
| POST | `/post/create` | Cria uma nova memória |
| PUT | `/post/update/{id}` | Atualiza uma memória existente |
| DELETE | `/post/delete/{id}` | Remove uma memória |

## 📊 Modelo de Dados

### Post
| Campo | Tipo | Descrição |
|-------|------|-----------|
| id | Long | Identificador único |
| title | String | Título da memória (5-30 caracteres) |
| description | String | Descrição detalhada (20-500 caracteres) |
| category | Enum | Categoria da memória |
| emoji | Enum | Emoção associada à memória |
| sensitiveContent | boolean | Indica se o conteúdo é sensível |
| imgUrl | String | URL da imagem armazenada no Azure |
| datePost | LocalDate | Data de criação |
| hourModifiedPost | LocalTime | Hora da última modificação |

## ⚡ Recursos Avançados
- **Validação de Dados**: Validação completa de entrada com mensagens de erro personalizadas
- **Gerenciamento de Exceções**: Tratamento centralizado de exceções
- **Armazenamento em Nuvem**: Integração com Azure Blob Storage para imagens
- **Configuração Flexível**: Variáveis de ambiente para configurar conexões de banco de dados

## 🔐 Segurança
- Proteção contra upload de arquivos maliciosos
- Validação de tamanho de arquivo
- Geração de nomes aleatórios para arquivos para evitar colisões

## 📦 Dependências Principais
- spring-boot-starter-data-jpa
- spring-boot-starter-validation
- spring-boot-starter-web
- mysql-connector-j
- azure-storage-blob
