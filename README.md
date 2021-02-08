# TRABALHO Final de WEB SERVICE

Esse projeto foi proposto como trabalho final do segundo semestre do curso de desenvolvimento de sistemas do  **SENAI - São José -SC**, a atividade requeria

 - [x] Java Poo 														
 - [x] MVC																	
 - [x] REST																	
 - [x] Serviços Web Crud											
 - [x] Boas práticas de Programação						
 - [x] Leitura e escrita de Arquivos com JSON	
 - [x] Design Pattern DAO							
 - [x] JDBC *integração com banco de dados* **PostgreeSQL**
 - [x] Design Pattern Connection

#MVC
-
Nossa estrutura MVC consiste em 

java>
		
		-br  >-com > -voidDoor> 
			   -controle
				   -ControleLivro
				   -ControleUsuario
				   -ControleEmprestimo
			   -servico
				   -ServicoleLivro
				   -ServicoUsuario
				   -ServicoEmprestimo
				   -ApplicationConfig
			   -modelos
				   -Usuario
				   -Livro
				   -Emprestimo
			   -dao
				   -DaoLivro
				   -DaoUsuario
				   -DaoEmprestimo
			   -conexao
				   -ConnectionFactory
			   -utilidades	
				   -ConversorDeDatas																			
# Entidade 
A classe proposta para a atividade foi:

USUARIO 
-
> - id:int
 >- nome:String
 >- cpf: String
 >- idade:String
 >- endereco:String
 >- telefone:String

LIVRO
-
> - id:int
> - qtd:int
 >- nome:String
 >- genero: String
 >- resumo:String
 >- anoPublicacao:int
 >- edicao:int
 
EMPRESTIMO
-
> - id:int
> - id_user:int
 >-id_livro:String
 >- dataEmprestimo: Date
 >- dataEntrega:Date
 >-multa:double
 >- situacao:boolean


#Rotas
-
Nossa api usa as seguntes rotas:

**LIVRO**

>* ADD  LIVRO > http://localhost:8080/WebLoja/carro
 >* DELETAR LIVRO > http://localhost:8080/WebLoja/carro/{ID}
 >* ATUALIZAR LIVRO > http://localhost:8080/WebLoja/carro/{ID}
 >* BUSCAR LIVRO > http://localhost:8080/WebLoja/carro/{ID}
 >* BUSCAR TODOS LIVRO  > http://localhost:8080/WebLoja/carro
 >* SELECIONAR DISPONIVEIS> http://localhost:8080/WebLoja/carro/listardisponiveis

**EMPRESTIMO**

>* ADD  EMPRESTIMO> http://localhost:8080/WebLoja/carro
 >* DELETAR EMPRESTIMO> http://localhost:8080/WebLoja/carro/{ID}
 >* ATUALIZAR EMPRESTIMO> http://localhost:8080/WebLoja/carro/{ID}
 >* BUSCAR EMPRESTIMO> http://localhost:8080/WebLoja/carro/{ID}
 >* BUSCAR TODOS EMPRESTIMO> http://localhost:8080/WebLoja/carro
 >* SELECIONAR DISPONIVEIS> http://localhost:8080/WebLoja/carro/listardisponiveis

**USUARIO**

>* ADD  USUARIO> http://localhost:8080/WebLoja/carro
 >* DELETAR USUARIO> http://localhost:8080/WebLoja/carro/{ID}
 >* ATUALIZAR USUARIO> http://localhost:8080/WebLoja/carro/{ID}
 >* BUSCAR USUARIO> http://localhost:8080/WebLoja/carro/{ID}
 >* BUSCAR TODOS USUARIO> http://localhost:8080/WebLoja/carro
 >* SELECIONAR USUARIO> http://localhost:8080/WebLoja/carro/listardisponiveis

#Json
-
No nosso projeto o @Consume e @Produces trabalha com o arquivos Json;
[
	  {
			    "nome": "Maryucha",
			    "cpf": "xxx.xxx.xxx-xx",
			    "idade": "32",
			    "endereco": "barreiros",
			    "telefone": 48-984276739
	  },
	  {
		   "nome": "Sidnei",
			"cpf": "xxx.xxx.xxx-xx",
			"idade": "27",
			 "endereco": "Forquilhas",
			 "telefone": 48-9991779488
	  }
]

#JDBC
-
Nosso projeto tem integração com o PostgreSQL

**SQL's**

*CRIAR O BANCO*


 
*CRIAR TABELA USUARIO*




**Design Pattern** & Classe ConectionFactory
-
Seguindo esse padrão de conexão com o banco possibilita uma manutenção mais rápida e sem grande dor de cabeça.

