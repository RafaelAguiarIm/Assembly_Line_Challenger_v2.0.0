
# Assembly Lines Challenger
Este projeto conssite na atualização da primeira versão desde desafio, 
referente ao repositorio Assembly_Line_Challenger_v1.0.0


![Badge em Desenvolvimento](http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN&style=for-the-badge)
> 🚧  Projeto sendo aperfeiçoado...  🚧

## Descrição

O objetivo deste projeto é criar uma aplicação que permita inserir um arquivo do tipo txt contendo uma 
lista de tarefas e organizá-las de acordo com as regras do desafio proposto.

### Regras do desafio

#### Algumas das regras do desafio eram as seguintes:

>### :hammer: Funcionalidades do projeto
- `Funcionalidade 1`: Inserir um arquivo.txt contendo a lista de tarefas
- `Funcionalidade 2`: Ler o arquivo linha por linha e adicionar cada linha em uma lista
- `Funcionalidade 3`: Identificar a descrição e o tempo de cada atividade na lista
- `Funcionalidade 4`: Organizar as atividades de forma a se encaixarem no turno da manhã (das 9h às 12h), pausa para o almoço e turno da tarde (das 13h às 17h)
- `Funcionalidade 5`: Garantir que a atividade de ginástica laboral começasse no mínimo às 16h e no máximo às 17h
- `Funcionalidade 6`: Permitir que a linha de produção aceitasse diversas linhas de montagem
- `Funcionalidade 7`: Inserir uma pausa para manutenção, que deveria ser respeitada antes de continuar as atividades


## Desenvolvimento e Melhorias

- [x] Melhorias e abstrações no método verificaLinha()
- [x] Melhorias na classe Assembly e AssemblyController
- [x] Melhorias na Classe ConversationListFromFile
- [x] Criação da classe de negócio AssemblyBO para melhor adequação ao padrão MVC
- [x] Correção e remoção de métodos e variaveis que não deveriam está no ManegedBean
- [ ] Modificar as instancias das Classes para injeção de dependencias
- [ ] Aplicação de testes unitários

### Tecnologias utilizadas

- Java 17
- CDI
- Lombok
- JSF 3.0
- Primefaces
- JPA
- MySQL
- Apache Tomcat 9


## Ferramentas Usadas Para Implementação
* ### IntelliJ IDEA

## Padrão de Projeto Aplicado
* MVC(MODEL,VIEW,CONTROLLER) em um sistema desktop java;
    * O princípio básico do MVC é a divisão da aplicação em três camadas: a camada de interação do usuário (view), a camada de manipulação dos dados (model) e a camada de            controle (controller);
    * MODEL
        1. A responsabilidade dos models é representar o negócio. Também é responsável pelo acesso e manipulação dos dados na sua aplicação.
    * CONTROLER
        1. É a camada de controle, responsável por ligar o model e a view, fazendo com que os models possam ser repassados para as views e vice-versa.
    * VIEW
        1. A view é responsável pela interface que será apresentada, mostrando as informações do model para o usuário.


# Para executar o projeto, siga os seguintes passos:

1. Clone o repositório em sua máquina
2. Abra o projeto em sua IDE de preferência
3. Configure o banco de dados MySQL em sua máquina e atualize as configurações do arquivo `persistence.xml` com as informações de conexão do seu banco de dados
4. Inicie o servidor de aplicação, a página `index.xhtml` deverá abrir em seu navegador
5. Acesse a aplicação em seu navegador através do endereço `http://localhost:8080`

## Como utilizar a aplicação

Ao acessar a aplicação, você será redirecionado para a página inicial, onde poderá selecionar o arquivo .txt que contém a lista de tarefas a serem organizadas. Após selecionar o arquivo, basta clicar em "Enviar" para que a aplicação faça a organização das tarefas de acordo com as regras do desafio.

## Contribuição

Este projeto foi desenvolvido por Rafael Aguiar. Se você deseja contribuir para o projeto, sinta-se à vontade para fazer um fork do repositório e submeter um pull request com suas alterações. Será um prazer receber contribuições da comunidade!

