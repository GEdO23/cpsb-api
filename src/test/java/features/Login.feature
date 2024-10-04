# language: pt

@Login
Funcionalidade: Login

  @LoginValido @SmokeTest
  Cenário: Login com credenciais validas
    Dado que estou na tela de login
    Quando entro com credenciais validas
    E clico no botao de submit
    Entao vejo a tela Home

  @LoginInvalido
  Cenário: Login com username invalido
    Dado que estou na tela de login
    Quando entro com username invalido
    E clico no botao de submit
    Entao vejo a mensagem de erro

  @LoginInvalido
  Cenário: Login com password invalido
    Dado que estou na tela de login
    Quando entro com password invalido
    E clico no botao de submit
    Entao vejo a mensagem de erro

  @SignOff
  Cenário: Realizar o Sign Off
    Dado que estou na tela de login
    Quando entro com credenciais validas
    E clico no botao de submit
    Entao vejo a tela Home
    Quando clico no botao Sign Off
    Entao vejo a mensagem de Logout