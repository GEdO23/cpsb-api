package stepDefinition;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import pages.BasePage;
import pages.HomePage;
import pages.LoginPage;

public class LoginSteps extends BasePage {

    LoginPage login = new LoginPage();
    HomePage home = new HomePage();

    @Dado("que estou na tela de login")
    public void queEstouNaTelaDeLogin() {
        login.isOnLoginPage();
    }

    @Quando("entro com credenciais validas")
    public void entroComCredenciaisValidas() {
        login.fillUserName();
        login.fillUserPassword();
    }

    @E("clico no botao de submit")
    public void clicoNoBotaoDeSubmit() {
        login.clickBtnSignIn();
    }

    @Entao("vejo a tela Home")
    public void vejoATelaHome() {
        home.isOnHomePage();
    }

    @Quando("entro com username invalido")
    public void entroComUsernameInvalido() {
        fillInput(login.etUserName, "invalid user");
        login.fillUserPassword();
    }

    @Quando("entro com password invalido")
    public void entroComPasswordInvalido() {
        login.fillUserName();
        fillInput(login.etUserPassword, "invalid password");
    }

    @Entao("vejo a mensagem de erro")
    public void vejoAMensagemDeErro() {
        isVisible(login.tvLoginError);
    }

    @Entao("vejo a mensagem de Logout")
    public void vejoAMensagemDeLogout() {
        isVisible(login.tvLogoutSuccess);
    }

    @Quando("clico no botao Sign Off")
    public void clicoNoBotaoSignOff() {
        isVisible(home.btnSair);
        click(home.btnSair);
    }
}
