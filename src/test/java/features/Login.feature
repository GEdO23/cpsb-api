# language: pt

@Login
Funcionalidade: Login

  @LoginValido @SmokeTest
  Cenário: Login com credenciais validas
    Dado que estou na tela de login
    Quando entro com credenciais validas
    E clico no botao de submit
    Entao vejo a tela Home