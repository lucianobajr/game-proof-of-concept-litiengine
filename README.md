# Prova de Conceito Jogo 2D com Litiengine

Este repositório é uma proposta de arquitetura para o nosso jogo 2D.

- [Design do Jogo: Mapas, Tiles e Tiled](#design-do-jogo-mapas-tiles-e-tiled)
- [Backend](#backend)
- [MySQL Docker Container](#mysql-docker-container)


![game](https://github.com/lucianobajr/game-proof-of-concept-litiengine/assets/45442173/5969e3cc-aba2-4457-8055-48a78e6b5e1b)

![coins](https://github.com/lucianobajr/game-proof-of-concept-litiengine/assets/45442173/fb1676ad-f98b-49b4-8d5b-89da4c949204)

![68747470733a2f2f7374617469632e6a616d2e76672f7261772f6633362f322f7a2f33303962652e706e67](https://github.com/lucianobajr/game-proof-of-concept-litiengine/assets/45442173/9063a45d-df9e-43dd-a85b-1f8f72944180)


# Arquitetura

![poc](https://github.com/lucianobajr/game-proof-of-concept-litiengine/assets/45442173/b0009462-aa4e-42e4-83d0-42177c2f41b6)

# Design do Jogo: Mapas, Tiles e Tiled
A construção da aplicação será baseada em um mapa no formato .tmx, utilizando tiles e imagens para compor cenários envolventes. Para otimizar este processo, decidimos adotar a ferramenta Tiled. A escolha do formato .tmx é respaldada por sua ampla aceitação na indústria de desenvolvimento de jogos, proporcionando eficiência na criação e edição de mapas. Após a conclusão do mapa no Tiled, o próximo passo envolve sua importação para a aplicação LITIENGINE.

LITIENGINE: Motor Gráfico Poderoso
A LITIENGINE foi escolhida devido à sua robustez e versatilidade no gerenciamento de mapas. Sua capacidade de incorporar colisões, inimigos, pontos de spawn e outros elementos cruciais para um jogo 2D a torna a escolha ideal para nosso projeto.


## Tileset

Um `tileset` é um termo frequentemente usado em design de jogos e gráficos digitais para descrever uma coleção de pequenas imagens ou `tiles` que são usados para construir um ambiente, cenário ou nível em um jogo ou aplicação interativa. Cada `tile` é uma pequena imagem que representa um elemento específico, como uma parede, um bloco, um personagem, uma árvore, etc. Esses `tiles` podem ser dispostos em um grid para criar o layout de um jogo.

Os `tilesets` são usados para economizar memória e permitir que os desenvolvedores de jogos criem ambientes complexos e variados sem ter que usar uma imagem grande e única para cada parte do cenário. Em vez disso, eles podem reutilizar os `tiles` do `tileset` para construir diferentes partes do jogo.

Por exemplo, em um jogo de plataforma, um `tileset` pode conter `tiles` para o chão, paredes, plataformas, obstáculos, e assim por diante. Os desenvolvedores podem usar esses `tiles` para criar diferentes níveis e cenários no jogo, combinando-os de maneira criativa.

Os `tilesets` são uma ferramenta importante no desenvolvimento de jogos 2D e são usados em muitos jogos clássicos e modernos. Eles facilitam a criação de mundos virtuais detalhados e interativos.

## Sprites

Os "sprites" são elementos gráficos 2D que são usados em jogos e aplicações interativas para representar personagens, objetos, inimigos e outros elementos visuais dentro do ambiente do jogo. Cada "sprite" é uma imagem ou animação que pode ser exibida em uma tela de computador ou dispositivo de jogo.

Os "sprites" têm algumas características distintas:

1. 2D: Os "sprites" são bidimensionais, o que significa que eles não possuem profundidade tridimensional. Eles são imagens planas que são exibidas em um plano 2D.

2. Recortável: Os "sprites" são frequentemente imagens recortáveis, o que significa que eles podem ser separados de um "sprite sheet" (uma única imagem que contém vários "sprites" organizados em uma grade) e colocados em diferentes posições na tela conforme necessário.

3. Animação: Muitos "sprites" são usados para criar animações. Por exemplo, um "sprite" de um personagem pode ter várias imagens diferentes que são trocadas rapidamente para criar uma animação de movimento.

4. Interatividade: "Sprites" podem responder a eventos e interações do jogador. Por exemplo, um "sprite" de um botão pode mudar de aparência quando o jogador passa o mouse sobre ele.

5. Uso em jogos: Os "sprites" são comumente usados em jogos 2D, especialmente em jogos retrô e em plataformas com recursos gráficos limitados, como consoles mais antigos. Eles são uma maneira eficiente de criar personagens e objetos em jogos desse tipo.

6. Flexibilidade: "Sprites" podem ser usados para representar uma variedade de elementos, desde personagens jogáveis até itens coletáveis, inimigos, cenários e muito mais.

No desenvolvimento de jogos, os "sprites" são frequentemente organizados e manipulados por meio de código para criar a jogabilidade e a interatividade desejadas. Eles desempenham um papel fundamental na criação da estética visual e na experiência do jogador em jogos 2D.

# Backend

No módulo ”backend,” concentramos a lógica essencial do jogo, incluindo a manipulação de dados, serviços e operações fundamentais. Embora neste cenário especı́fico de prova
de conceito simples, não tenhamos controladores, é importante entender o papel deles em um contexto mais amplo.

## Estrutura de Pastas

- `backend`: A pasta raiz do projeto, que contém todos os componentes relacionados ao backend da aplicação.

  - `application`: Esta pasta contém os serviços da aplicação, que são responsáveis por implementar a lógica de negócios. Por exemplo, `CreateItemService` e `ListItemsService` são serviços que executam operações relacionadas a itens.

    - `services`: Aqui, os serviços são organizados para manter a separação de preocupações. Cada serviço pode executar uma funcionalidade específica da aplicação.

  - `domain`: Nesta pasta, você encontra classes relacionadas ao domínio da aplicação. Isso inclui as entidades de negócios, DTOs (Data Transfer Objects) e outros componentes cruciais.

    - `constants`: Pode conter constantes ou valores fixos que são usados em toda a aplicação. O arquivo `ItemQueries.java` parece conter consultas SQL pré-definidas.

    - `dtos`: Armazena classes DTO, que são usadas para transferir dados entre as camadas da aplicação ou para fora da API. `CreateItemDTO.java` pode conter os dados necessários para criar um novo item.

    - `entities`: Aqui, você define as entidades de negócios da aplicação. `Item.java` parece ser uma classe que representa a entidade "Item".

    - `repositories`: Contém interfaces e implementações de repositórios, que são responsáveis pela comunicação com o banco de dados. `ItemRepository.java` pode ser uma interface que define operações relacionadas a itens, enquanto `ItemRepositoryImpl.java` é uma implementação concreta dessa interface.

  - `infra`: Esta pasta contém componentes de infraestrutura, como configuração de banco de dados.

    - `database`: É onde a classe `Database.java` reside, que lida com a configuração e a conexão do banco de dados.

  - `utils`: Contém utilitários ou classes de apoio que podem ser usadas em várias partes da aplicação. `NameNormalizer.java` parece ser um utilitário para normalização de nomes.

## Como Usar

- Os serviços na pasta `application` são chamados a partir dos controladores ou outras partes da aplicação para executar a lógica de negócios relacionada aos itens.

- As entidades e DTOs definidos na pasta `domain` são usados para representar os dados do aplicativo.

- Os repositórios na pasta `repositories` são responsáveis por interagir com o banco de dados, fornecendo métodos para criar, ler, atualizar e excluir itens.

- Os utilitários na pasta `utils` podem ser usados para realizar tarefas comuns, como normalização de nomes.

## Motivo

A estrutura de pastas segue as melhores práticas de desenvolvimento de software, como o princípio da separação de preocupações e a organização lógica de componentes. Ela ajuda a manter o código limpo, facilita a escalabilidade e torna mais fácil a colaboração entre desenvolvedores. Além disso, a divisão em camadas (serviços, domínio, infraestrutura) ajuda a manter a modularidade e a manutenção da aplicação. O uso de DTOs ajuda a controlar a transferência de dados entre as camadas e a manter uma API consistente.


# MySQL Docker Container

Exemplo simples de como construir e executar um contêiner Docker com o servidor MySQL 8.

## Construir a Imagem Docker

Para construir a imagem Docker, você pode executar o seguinte comando:

```sh
$ docker build -t mysql .
```

Este comando usa o Dockerfile neste repositório para criar uma imagem Docker baseada no servidor MySQL 8 oficial.

## Executar o Contêiner Docker

Para executar o contêiner Docker MySQL, você pode usar o seguinte comando:

```sh
$ docker run --detach -p 3306:3306 mysql
```

Isso iniciará o contêiner MySQL em segundo plano (-d), mapeando a porta 3306 do contêiner para a porta 3306 do host (-p 3306:3306).

## Informações de Conexão

Aqui estão as informações de conexão para acessar o banco de dados MySQL dentro do contêiner:

- URL de conexão: `jdbc:mysql://localhost:3306/poc` ou `jdbc:mysql://127.0.0.1:3306/poc`
- Nome de usuário: `user`
- Senha: `dummypassword`

Você pode usar essas informações de conexão para se conectar ao banco de dados MySQL a partir de um cliente JDBC ou qualquer outra ferramenta de banco de dados que você preferir.

## Acessar o Banco de Dados MySQL via Linha de Comando

Você também pode acessar o banco de dados MySQL dentro do contêiner usando a linha de comando. Para fazer isso, use o seguinte comando:

```sh

$ docker exec -it <ID_DO_CONTAINER_MYSQL> mysql -u user -p
```

Substitua `<ID_DO_CONTAINER_MYSQL>` pelo ID do contêiner MySQL. Isso abrirá um prompt de comando MySQL interativo, onde você pode executar consultas SQL diretamente.


## Observações

Certifique-se de que o Docker esteja instalado em seu sistema antes de executar esses comandos. Além disso, este exemplo utiliza senhas e informações de conexão simples para fins de demonstração. Em um ambiente de produção, é altamente recomendável usar senhas seguras e gerenciamento de segredos adequado.


