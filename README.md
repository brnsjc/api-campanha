# api-campanha
Api Cadastro Consulta Insercao e Atualizacao de Campanha

* Verifica  : https://github.com/brnsjc/api-cliente


# api-cliente
* Api para Cadastro de Cliente

Arquitetura 

* Api pensada e desenvolvida em camadas, seguimentando e segarando deveres/tarefas por cada camada, sendo divida em Service,Business,Controller,Repository;
* Banco de dado Mysql usada para armazenamento e .sql anexado ao projeto para criação local;
* Padrão Rest, com envio e recebimento de Json


Banco de Dados

* Banco de Dados Mysql 5.8
* Criado 3 tabelas, sendo cliente,campanha e campanha_cliente para vincular campanhas e clientes seguindo melhor pratica de SQL de tabela intermedira;
* Query nativas foram integradas ao Java(camada Repository) e também utilizado HQL para querys de apenas consultas;

Fluxo

* Observando e analisando o fluxo para cadastro de cliente, foi desenvolvido o banco de dados para melhor atender a solução;
* Após entendimento da necessidade e regra de negocio, foi criada a camada de Business para executar toda a regra;
* Na camada business posui a verificação de campanhas, quais são vencidas, alteração, e save;
* Ao inserir nova campanha, a camada Business verifica campanhas com a mesma data e realiza todo o processo de atualização adicionando dias as datas de forma que não tenham campanhas que vençam no mesmo dia;
* Api-Campanha esta em localhost:8082 ficando a espera para consultas, cadastros, atualizações e delete.
* Api-Cliente consulta a api em 8082 localmente para obter informações das campanhas e gerenciar de acordo com o cliente. Usado Feign para a integração;

2 Apis
* Foi definida 2 apis ou dois microserviços que gerenciem e separem as reponsabilidades, permitindo estacabalidade e funcionamento indepennte, sendo assim, em futuros problemas sempre terá uma api UP pronta pra uso

Documentacao
* Api com rotas e model documentadas em : http://localhost:8080/swagger-ui.html
* Collection Postman para teste : https://www.postman.com/collections/dd1032ebf4c98696c0a5



PS: Na WIKI contem .sql para criar o banco de dados com alguns dados iniciais, segunda api em : https://github.com/brnsjc/api-cliente
