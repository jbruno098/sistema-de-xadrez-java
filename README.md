# ♟️ Sistema de Xadrez em Java

## 📌 Sobre o projeto

Este projeto é uma implementação completa de um **jogo de xadrez via terminal**, desenvolvido em **Java**, com o objetivo principal de praticar e consolidar os conceitos de **Programação Orientada a Objetos (POO)**.

A aplicação simula uma partida real de xadrez, aplicando regras oficiais do jogo como movimentação válida das peças, controle de turnos, xeque e xeque-mate.

💡 Projeto desenvolvido durante meus estudos no curso de Java do professor Nélio Alves.
Esse foi meu **primeiro projeto maior utilizando orientação a objetos na prática**, onde consegui entender melhor como sistemas reais são estruturados.

---

## 🧠 Conceitos de Orientação a Objetos aplicados

Durante o desenvolvimento foram utilizados diversos conceitos fundamentais da POO:

* Encapsulamento
* Herança
* Polimorfismo
* Abstração
* Sobrescrita de métodos (Override)
* Associação entre objetos
* Tratamento de exceções
* Enumerações (Enums)

O projeto foi essencial para aprender a separar responsabilidades e modelar problemas reais utilizando objetos.

---

## 🏗️ Estrutura do sistema

O código foi organizado em camadas, separando bem as responsabilidades:

* **Tabuleiro** → gerenciamento das posições e peças
* **PartidaDeXadrez** → controle das regras da partida
* **Peca** → classe base para todas as peças
* **Peças específicas** → Rei, Rainha, Torre, Bispo, Cavalo e Peão
* **Posicao / PosicaoDeXadrez** → conversão entre coordenadas internas e notação do xadrez
* **Camada de aplicação** → interação com o usuário via terminal

Essa estrutura segue boas práticas de organização orientada a objetos, facilitando manutenção e entendimento do sistema.

---

## ♟️ Funcionalidades

* ✅ Movimentação válida das peças
* ✅ Validação de jogadas
* ✅ Captura de peças
* ✅ Controle de turnos
* ✅ Detecção de xeque
* ✅ Detecção de xeque-mate
* ✅ Interface em modo texto (console)

---

## 🛠️ Tecnologias utilizadas

* Java
* Programação Orientada a Objetos (POO)
* Aplicação Console (Terminal)
* Git e GitHub para versionamento

---

## ▶️ Como executar o projeto

1. Clone o repositório:

```bash
git clone https://github.com/jbruno098/sistema-de-xadrez-java.git
```

2. Abra o projeto em uma IDE Java (Eclipse, IntelliJ ou VS Code)

3. Execute a classe principal:

```
Program.java
```

4. Siga as instruções exibidas no terminal para jogar.

---

## 📚 Aprendizados

Esse projeto foi um divisor de águas no meu aprendizado em Java.

Antes eu focava apenas em fazer o código funcionar. Durante o desenvolvimento passei a entender melhor:

* como modelar problemas usando objetos
* separação de responsabilidades entre classes
* organização de projetos maiores
* uso prático de herança e polimorfismo
* importância do design do código além da lógica

Muitas soluções utilizadas no projeto eram abordagens que eu não imaginaria inicialmente, o que contribuiu muito para evoluir minha forma de pensar programação.

---

## 🚀 Próximos passos

* Implementar interface gráfica (GUI)
* Melhorar a experiência do usuário
* Adicionar salvamento de partidas
* Aplicar padrões de projeto (Design Patterns)

---

## 💭 Reflexão pessoal

Esse projeto marcou o momento em que comecei a entender orientação a objetos de verdade, não apenas na teoria.

Aqui percebi como cada classe possui uma responsabilidade específica e como a organização correta do código torna problemas complexos muito mais simples de resolver.

---

## 👨‍💻 Autor

Desenvolvido por **Bruno**
Estudante de Análise e Desenvolvimento de Sistemas.

📌 Projeto com fins educacionais e foco em aprendizado.
