<h1 align="center" style="font-weight: bold;">OS - MANAGER 💻</h1>

<p align="center">
 <a href="#tech">Tecnologias</a> • 
 <a href="#started">Iniciando o Projeto</a> • 
 <a href="#colab">Colaboradores</a> •
 <a href="#contribute">Contribuições</a>
</p>

<p align="center">
    <b>Sistema para a emissão de Ordens de Serviço e gerenciamento de oficina.</b>
</p>

<h2 id="technologies">💻 Tecnologias</h2>

- Java 24
- MySQL 8
- Spring Boot 3.5.3


<h2 id="started">🚀 Iniciando o Projeto</h2>

Para iniciar o projeto você pode executar utilizando o docker compose ou por linha de comando / IDE de su preferência.


<h3>Executando por docker compose (<strong><i>recomendado</i></strong>)</h3>

Para executar o projeto, é necessário que tenha o docker instalado em seu computador.

<h3>Executando por linha de comando</h3>

<h4> Dependências</h4>

Para testar esse projeto você precisa ter instalado: 

- [Java 24](https://www.oracle.com/br/java/technologies/downloads/)
- [MySQL 8](https://www.mysql.com/downloads/)

<h3>Clonar</h3>

Para clonar o projeto.

```bash
git clone https://github.com/g-sadrack/os-manager.git
```

<h3>Starting</h3>

Para iniciar o projeto.

<h4>Executando por docker compose</h4>
Caso tenha usado a imagem docker, na pasta raiz do projeto execute o seguinte comando:

```./update.sh```

---
<h4>Executando por linha de comando</h4>
Caso tenha escolhido executar o projeto por linha de comando, execute o seguinte comando:

```bash
cd os-manager
mvn spring-boot:run
```
<h3>Em execução</h3>

Para ver o projeto em execução, basta acessar o seguinte endereço: [http://localhost:8080](http://localhost:8080) ou acessar a documentação com swagger: [http://localhost:8080/swagger-ui/](http://localhost:8080/swagger-ui/)

<h2 id="colab">🤝 Colaboradores</h2>

Um agradecimento especial para os colaboradores desse projeto.

<table>
  <tr>
    <td align="center">
      <a href="#">
        <img src="https://avatars.githubusercontent.com/u/97041836?v=4" width="100px;" alt="Gabriel Sadrack Profile Picture"/><br>
        <sub>
          <b>Gabriel Sadrack</b>
        </sub>
      </a>
    </td>
    <td align="center">
      </a>
        <a href="#">
        <img src="https://avatars.githubusercontent.com/u/193547302?v=4" width="100px;" alt="Wendel Nascimento Profile Picture"/><br>
        <sub>
          <b>Wendel Nascimento</b>
        </sub>
      </a>
    </td>
  </tr>
</table>

<h2 id="contribute">📫 Contribuições</h2>

Se você tiver interesse em participar do projeto, você pode clonar esse projeto e realizar uma implementação:

1. `git clone https://github.com/g-sadrack/os-manager`
2. `git checkout -b feature/NAME`
3. Siga os padrões de commit
4. Abra um Pull Request e explique o problema resolvido ou a funcionalidade criada. Caso existam modificações visuais, anexe capturas de tela e aguarde a revisão!

<h3>Documentação que pode ajudar</h3>

[📝 Como fazer um Pull Request ?](https://www.atlassian.com/br/git/tutorials/making-a-pull-request)

[💾 Commit pattern](https://medium.com/linkapi-solutions/conventional-commits-pattern-3778d1a1e657)
