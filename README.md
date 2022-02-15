# Desafio webservice fornecedor

O objetivo deste desafio é demonstrar sua experiência e conhecimento em programação back-end. Assim saberemos como você pensa e resolve estas questões.
Necessitamos que seja criado um webservice em REST para devolver os dados de nossos fornecedores, e ainda executar operações de Criação, Deleção e Atualização dos dados.


# Notas

 - Foi utilizado o Maven para a compilação do projeto
 - Para o armazenamento de dados foi utilizado o banco em memória HashMap. **HashMap Java** armazena os dados na forma de valor chave pares em que os dados principais devem ser exclusivos. Podemos acessar os valores com base nos dados-chave correspondentes.
 - Backend foi utilizado Jersey Rest API
 - Frontend foi utilizado AngularJs e Bootstrap
 - Como servidor da aplicação, foi utilizado o Apache Tomcat v10.0
 - A rota definida para o projeto é http://localhost:8080/desafioFornecedor/

## Executando o projeto

 1. Realizar o clone do repositório
 2. Maven > update project
 3. Run > Run as > Maven Build
 4. Goal > clean install / skip test
 5. Na view Servidor no eclipse, adicionar um novo servidor Apache Tomcat v10.0
 6. Ao definir um novo servidor, selecionar o snapshot gerado pela build
 5. Ao iniciar o servidor, a página http://localhost:8080/desafioFornecedor/ ficará disponível
