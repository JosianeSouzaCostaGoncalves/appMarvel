# AppMarvel

  ## Sobre este projeto:
     AppMarvel e um aplicativo 100% kotlin que consome a API da Marvel e lista seus personagens permitindo favorita-los. 
  ## Funcionalidades:
       •	Tela splash (Primeira tela vista ao abrir o app na qual é carregada a logo por 3 segundos).
       •	Tela de home (Tela principal que mostra a lista de personagens da API, permite favoritar cada personagem, pesquisa personagem por nome e mostra erro quando esta sem internet).
            - Botão de recarregar: Regarrega a lista que foi consumida da API.
            - Botão de favoritos: Faz a navegação da tela de home para a tela favoritos.
       •	Tela de detalhes (Tela que mostra nome, descrição e foto do personagem selecionado na tela de home).
            - Botão de favoritar: Mostra um toast que foi cliclado no botão de favoritar.
            - Botão de compartilhar: Mostra um toast que foi cliclado no botão de compartilhar.

       •	Tela de favoritos (Tela com a lista de personagens favoritos e mostra erro quando esta sem internet).

   ## Conceitos:
      •	Consulmo de API
      •	Arquitetura Limpa e MVVM.
      •	Ciclo de Vida
      •	RecylcerView
      •	Navigation
      •	Injeção de Dependencia com Koin
      •	Room

  ## Descrição dos metodos:
  
      •	1º -> Criar as telas no xml e as classes, fazer a navegação entre elas.

      •	2º -> Criar e usar um mock e criar uma estrutura de arquitetura para que fosse possível ver as listas e recylcerView funcionando dentro da view.
      
      •	3º -> Remover o mock e consumir os dados da API.
          - Chaves de acesso API(Ao fazer essa parte percebi que não seria fácil, pois a configuração das chaves API precisa de uma junção especifica que demorei a conseguir fazer, foi necessario pesquisar videos e projetos que consumia a API Marvel).
          - Url API só retornava a lista utlizando o HTTPS
          (Após fazer toda lógica, a lista de personagens da API não vinha, então criei uma url com HTTPs me baseando em um comentario do stackOvweFlow e funcionou).
          Instalei tambem o Chucker para que fosse possível visualizar as respostas da API toda vez que a chamasse.
          
      •	4º -> Depois que eu tinha a lista de personagens no aplicativo, comecei a fazer a lógica para conseguir pesquisar um personagem na lista.
          (Nessa parte, após pesquisar um personagem específico e o carregamento dele, foi necessário criar um botão para recarregar a lista novamente, pois ao apagar o nome e pesquisar novamente não estava recarregando a lista completa de personagem.
          
      •	5º -> Para a tela de favoritos foi necessario criar uma lista de vazia que era populada com base se o personagem foi favoritado e esta lista e salva no banco de dados.
