# task-manager
"Aplicação de gerenciamento de tarefas com operações CRUD (Java + Spring Boot no backend e frontend em Thymeleaf)"

Instruções para Configuração e Execução

1. Preparação Inicial
Clonar o repositório: Baixe o projeto diretamente do GitHub usando o comando git clone [URL-do-repositorio] ou através da interface do GitHub.
Abrir o projeto: Abra o projeto no IntelliJ.
2. Configuração do Banco de Dados
Abrir o pgAdmin: Conecte-se ao PostgreSQL usando o pgAdmin.
Criar o banco de dados: No pgAdmin, crie um banco de dados chamado taskmanager.
Executar o script SQL:
Localize o arquivo SQL na pasta Scripts SQL dentro do projeto.
Abra o script, copie seu conteúdo e cole-o em uma nova query no banco de dados taskmanager.
Execute a query para configurar as tabelas e dados iniciais necessários.

3. Configuração do Backend e Frontend
Compilar o projeto no IntelliJ:
Clique com o botão direito sobre o nome do projeto.
Vá até a opção Run Maven e selecione clean install.
Aguarde o processo finalizar.

Configurar as portas:
Abra o arquivo application.properties no backend:
Localizado em backend/backend-springtm/src/main/resources/application.properties
Certifique-se de que a configuração da porta esteja assim: server.port=8081
Abra o arquivo application.properties no frontend:
Localizado em frontend/frontend-springtm/src/main/resources/application.properties
Verifique se a configuração da porta está assim: server.port=8080
Observação: Se as portas 8081 ou 8080 já estiverem em uso, você pode alterá-las para outras, como 8091 e 8090, respectivamente.

Executar o Backend e o Frontend:
No IntelliJ, localize a classe BackendSpringTmApplication e execute-a.
Depois, localize a classe FrontendSpringTmApplication e execute-a.

Acessar o sistema:
Abra o navegador e digite localhost:8080 na URL.
A página inicial do sistema será exibida.

Tutorial do Sistema
Entrar: Na página inicial, selecione "Entrar" para visualizar todas as tarefas cadastradas que estão em andamento.

Cadastrar uma Tarefa:
Clique em "Cadastrar Tarefa" na barra lateral para ir para a página de cadastro.
Preencha os dados necessários e selecione "Cadastrar". Você será redirecionado para a página de Tarefas em Andamento, onde a nova tarefa aparecerá.

Visualizar Tarefas Concluídas:
Na barra lateral, selecione "Tarefas Concluídas" para ver todas as tarefas já concluídas. Para voltar, selecione "Início".

Editar uma Tarefa:
Na página de Tarefas em Andamento, selecione o ícone de caneta ao lado de uma tarefa para editá-la.
Faça as alterações desejadas e clique em "Salvar Alterações".

Deletar uma Tarefa:
Clique no ícone de lixeira ao lado de uma tarefa para excluí-la permanentemente.

Concluir uma Tarefa:
Para marcar uma tarefa como concluída, selecione o ícone de check.
A tarefa será movida para a lista de Tarefas Concluídas e não estará mais visível em Tarefas em Andamento.

Tecnologias Utilizadas e Justificativa das Escolhas

Backend:
Java com Spring Boot:
Motivo da escolha: Escolhi o Spring Boot porque ele facilita o desenvolvimento de APIs RESTful com pouca configuração e é fácil de integrar com outras ferramentas. Usei a clean arquitecture como arquitetura, pois é bem organizada.

Maven:
Motivo da escolha: Maven é uma ferramenta muito usada para gerenciar dependências e automatizar o processo de build de projetos Java.

Banco de Dados:
PostgreSQL:
Motivo da escolha: O PostgreSQL é um banco de dados confiável, robusto e bastante usado em projetos que envolvem dados complexos, mas também simples e com uma interface boa para o uso.
psql (PostgreSQL) 16.4

Frontend:
Thymeleaf:
Motivo da escolha: O Thymeleaf é fácil de usar e integra bem com o Spring Boot, permitindo renderizar páginas HTML dinâmicas no servidor. Usei mais de uma vez e me senti mais confortável de usar nessa oportunidade.
