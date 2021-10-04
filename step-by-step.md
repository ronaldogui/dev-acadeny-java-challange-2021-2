# Projeto Dev Academy

O projeto consiste em uma API que tem dois enpoints Nota Fiscal e Mudança de Status Da Nota Fiscal , onde no enpoint de nota fiscal teremos os metodos POST,GET,PUT E DELETE e em Mudança de Status teremos o metodo PATCH.

## ENDPOINT - NotaFiscal

## Como Inserir Uma Nota Fiscal (Metodo POST).

A url para utilizar deste método é : http://localhost:{porta}/api/v1/notas-fiscais

TEMOS 5 MANEIRAS DE CRIAR UMA NOTA FISCAL SÃO ELAS : 

###1. Com o valorTotal da nota fiscal corretamente, seguindo as regras de negocios : O valor total da Nota Fiscal deve ser a soma do valor total dos produtos e frete;
```json
{
"nomeCliente":"JOSE FRANCISCO",
"endereco":"Rua A, 500",
"telefone":"8532795578",
"frete":2.50,
"valorTotal":16.00,
"itens": 
    [{
        "descricao": "Refri",
        "precoUnitario": 5.5,
        "quantidade": 1
   },
   {
        "descricao": "Coxinha",
        "precoUnitario": 3.00,
        "quantidade": 1
   },
   {
        "descricao": "Batatinha",
        "precoUnitario": 5.00,
        "quantidade": 1
   }]
}
```
###2. Com o valorTotalProdutos da nota fiscal corretamente , seguindo as regras de negocios : O valor total dos produtos na Nota Fiscal deve ser o somátorios valor total dos Itens;

```json
{
"nomeCliente":"JOSE FRANCISCO",
"endereco":"Rua A, 500",
"telefone":"8532795578",
"frete":2.50,
"valorTotalProdutos":13.50,
"itens": 
    [{
        "descricao": "Refri",
        "precoUnitario": 5.5,
        "quantidade": 1
   },
   {
        "descricao": "Coxinha",
        "precoUnitario": 3.00,
        "quantidade": 1
   },
   {
        "descricao": "Batatinha",
        "precoUnitario": 5.00,
        "quantidade": 1
   }]
}
```

###3. Com o valorTotal dos Itens corretamente , seguindo as regras de negocios : O valor total do Item deve ser multiplicação do preco unitário pela quantidade;

```json
{
"nomeCliente":"JOSE FRANCISCO",
"endereco":"Rua A, 500",
"telefone":"8532795578",
"frete":2.50,
"itens": 
    [{
        "descricao": "Refri",
        "precoUnitario": 5.5,
        "quantidade": 1,
        "valorTotal": 5.5
   },
   {
        "descricao": "Coxinha",
        "precoUnitario": 3.00,
        "quantidade": 1,
        "valorTotal": 3.00
   },
   {
        "descricao": "Batatinha",
        "precoUnitario": 5.00,
        "quantidade": 1,
        "valorTotal": 5.50
   }]
}
```
###4.Com todos os campos inseridos corretamente , seguindo as regras de negocios e validações;

```json
{
  "nomeCliente":"JOSE FRANCISCO",
  "endereco":"Rua A, 500",
  "telefone":"8532795578",
  "valorTotalProdutos":13.50,
  "frete":2.50,
  "valorTotal":16.00,
  "itens":
  [{
    "descricao": "Refri",
    "precoUnitario": 5.5,
    "quantidade": 1,
    "valorTotal": 5.5
  },
    {
      "descricao": "Coxinha",
      "precoUnitario": 3.00,
      "quantidade": 1,
      "valorTotal": 3.00
    },
    {
      "descricao": "Batatinha",
      "precoUnitario": 5.00,
      "quantidade": 1,
      "valorTotal": 5.50
    }]
}
```
###5. Você pode preferir que o sistema ja insira os campos (ValorTotal da notaFiscal,ValorTotalProdutos e valorTotal dos itens) devidamente formatados segundo as regras de negocios;
```json
{
  "nomeCliente":"JOSE FRANCISCO",
  "endereco":"Rua A, 500",
  "telefone":"8532795578",
  "frete":2.50,
  "itens":
  [{
    "descricao": "Refri",
    "precoUnitario": 5.5,
    "quantidade": 1
  },
    {
      "descricao": "Coxinha",
      "precoUnitario": 3.00,
      "quantidade": 1
    },
    {
      "descricao": "Batatinha",
      "precoUnitario": 5.00,
      "quantidade": 1
    }]
}
```


## Endpoint NotaFiscal – Listar Todas As Notas Fiscais (Metodo GET)

A url para utilizar deste método é : http://localhost:{porta}/api/v1/notas-fiscais

Este metodo retorna todas as Notas Ficais criadas;
Exemplo De Retorno:
```log
 [
    {
        "id": 1,
        "nomeCliente": "JOSE FRANCISCO",
        "endereco": "Rua A, 500",
        "telefone": "8532795578",
        "valorTotalProdutos": 13.5,
        "frete": 2.5,
        "valorTotal": 16.0,
        "status": "PENDENTE",
        "itens": [
            {
                "id": 1,
                "descricao": "Refri",
                "precoUnitario": 5.5,
                "quantidade": 1,
                "valorTotal": 5.5
            },
            {
                "id": 2,
                "descricao": "Coxinha",
                "precoUnitario": 3.0,
                "quantidade": 1,
                "valorTotal": 3.0
            },
            {
                "id": 3,
                "descricao": "Batatinha",
                "precoUnitario": 5.0,
                "quantidade": 1,
                "valorTotal": 5.0
            }
        ]
    },
    {
        "id": 2,
        "nomeCliente": "JOSE ronaldo",
        "endereco": "Rua A, 500",
        "telefone": "8532795578",
        "valorTotalProdutos": 13.5,
        "frete": 12.5,
        "valorTotal": 26.0,
        "status": "PENDENTE",
        "itens": [
            {
                "id": 4,
                "descricao": "Suco",
                "precoUnitario": 5.5,
                "quantidade": 1,
                "valorTotal": 5.5
            },
            {
                "id": 5,
                "descricao": "Cafe",
                "precoUnitario": 3.0,
                "quantidade": 1,
                "valorTotal": 3.0
            },
            {
                "id": 6,
                "descricao": "Arroz",
                "precoUnitario": 5.0,
                "quantidade": 1,
                "valorTotal": 5.0
            }
        ]
    }
]
```


## Endpoint NotaFiscal – Listar Todas As Notas Fiscais (Metodo GET)

A url para utilizar deste método é : http://localhost:{porta}/api/v1/notas-fiscais/{id}

Este metodo retorna a Nota Fiscal correspondente a identificação que você informou .

Exemplo de retorno , neste caso o usuario informou o id = 1:

```log
 [
    {
        "id": 1,
        "nomeCliente": "JOSE FRANCISCO",
        "endereco": "Rua A, 500",
        "telefone": "8532795578",
        "valorTotalProdutos": 13.5,
        "frete": 2.5,
        "valorTotal": 16.0,
        "status": "PENDENTE",
        "itens": [
            {
                "id": 1,
                "descricao": "Refri",
                "precoUnitario": 5.5,
                "quantidade": 1,
                "valorTotal": 5.5
            },
            {
                "id": 2,
                "descricao": "Coxinha",
                "precoUnitario": 3.0,
                "quantidade": 1,
                "valorTotal": 3.0
            },
            {
                "id": 3,
                "descricao": "Batatinha",
                "precoUnitario": 5.0,
                "quantidade": 1,
                "valorTotal": 5.0
            }
        ]
    }
]
```

## Endpoint NotaFiscal – Atualizar Nota Fical (Metodo PUT)

A url para utilizar deste método é : http://localhost:{porta}/api/v1/notas-fiscais/{id}

Para realizar a alteração da sua nota fiscal é necessario que informe a identicicação da mesma , e os campos que deseja atualizar !
E tudo seguindo as regras de negocios;

Exemplo de atualização:

url = http://localhost:8080/api/v1/notas-fiscais/1

### Exemplo de nota fiscal antes de ser atualizada
```json
{
"id":"1",
"nomeCliente":"JOSE FRANCISCO",
"endereco":"Rua A, 500",
"telefone":"8532795578",
"valorTotalProdutos":13.50,
"frete":2.50,
"valorTotal":16.00,
"itens": 
    [{
        "descricao": "Refri",
        "precoUnitario": 5.5,
        "quantidade": 1,
        "valorTotal": 5.5
   },
   {
        "descricao": "Coxinha",
        "precoUnitario": 3.00,
        "quantidade": 1,
        "valorTotal": 3.00
   },
   {
        "descricao": "Batatinha",
        "precoUnitario": 5.00,
        "quantidade": 1,
        "valorTotal": 5.50
   }]
}
```
###Atualizando o nome do cliente

```json
{
"id":"1",
"nomeCliente":"Maria",
"endereco":"Rua A, 500",
"telefone":"8532795578",
"valorTotalProdutos":13.50,
"frete":2.50,
"valorTotal":16.00,
"itens": 
    [{
        "descricao": "Refri",
        "precoUnitario": 5.5,
        "quantidade": 1,
        "valorTotal": 5.5
   },
   {
        "descricao": "Coxinha",
        "precoUnitario": 3.00,
        "quantidade": 1,
        "valorTotal": 3.00
   },
   {
        "descricao": "Batatinha",
        "precoUnitario": 5.00,
        "quantidade": 1,
        "valorTotal": 5.50
   }]
}
```
### Retorno Esperado (STATUS 200 OK)

```log
 {
    "id": 1,
    "nomeCliente": "MARIA",
    "endereco": "Rua A, 500",
    "telefone": "8532795578",
    "valorTotalProdutos": 13.5,
    "frete": 2.5,
    "valorTotal": 16.0,
    "status": "PENDENTE",
    "itens": [
        {
            "id": 1,
            "descricao": "Refri",
            "precoUnitario": 5.5,
            "quantidade": 1,
            "valorTotal": 5.5
        },
        {
            "id": 2,
            "descricao": "Coxinha",
            "precoUnitario": 3.0,
            "quantidade": 1,
            "valorTotal": 3.0
        },
        {
            "id": 3,
            "descricao": "Batatinha",
            "precoUnitario": 5.0,
            "quantidade": 1,
            "valorTotal": 5.0
        }
    ]
}
```

## Endpoint NotaFiscal – Excluir Uma Nota Fiscal (Metodo DELETE)

A url para utilizar deste método é : http://localhost:{porta}/api/v1/notas-fiscais/{id}

Para realizar a exclusão da sua nota fiscal é necessario que informe a identicicação da mesma somente na url, não é necessario ter body !

Exemplo para exclusão : http://localhost:8080/api/v1/notas-fiscais/1

###Retorno esperado
```log
 HTTP 204 NO CONTENT
```

##Endpoint Mudança de Status Nota Fiscal – Mudar o status de uma nota fiscal (Metodo PATCH)

A url para utilizar deste método é : http://localhost:{porta}/api/v1/notas-fiscais/{id}/status

Para fazer a mudança de status de uma Nota Fiscal , só precisa informar qual o status que você quer mudar

O corpo da requisição fica assim :

```json
 {
  "status": "EM_PROCESSAMENTO"
 }
```

##Informações Adicionais 

Neste projeto foram feitas validações para não deixar que as regras de negocios fossem quebradas , essas são as regras de negócios;

### Regras de Negócio da notaFiscal

- Todo nova nota fiscal terá que ter o status inicial **PENDENTE**;
- O **valor total** do Item deve ser multiplicação do **preco unitário** pela **quantidade**;
-  O **valor total dos produtos** na Nota Fiscal deve ser o somátorios **valor total** dos Itens;
-  O **valor total** da Nota Fiscal deve ser a soma do **valor total dos produtos** e  **frete**.
   
O endpoint não deve permitir:

- Nota Fiscal sem item;
- Item sem quantidade;
- Item sem valor;
- Item sem nome.

### Regras de Negócio da Mudança de Status

- **Não permitir** alterar o status para CANCELADO caso o status atual seja EM_PROCESSAMENTO ou CANCELADO;
- **Permitir** alterar o status para EM_PROCESSAMENTO caso o status atual seja PENDENTE ou COM_ERRO;
- **Permitir** alterar o status para APROVADA caso o status atual seja EM_PROCESSAMENTO.

##Com essa informação fique a vontade para testar as regras :)