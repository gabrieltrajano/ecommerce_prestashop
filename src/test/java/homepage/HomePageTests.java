package homepage;

import base.BaseTests;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.ProdutoPage;

import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class HomePageTests extends BaseTests {

    @Test
    public void testContarProdutos_oitoProdutosDiferentes(){
        carregarPaginaInicial();
        assertThat(homePage.contarProdutos(), is(8));
    }

    @Test
    public void testValidarCarrinhoZerado_zeroItensNoCarrinho(){
        int produtosNoCarrinho = homePage.obterQuantidadeProdutosNoCarrinho();
        assertThat(produtosNoCarrinho, is(0));
    }

    @Test
    public void testValidarDetalhesDoProduto_DescricaoEValorIguais(){
        int indice = 0;
        String nomeProduto_HomePage = homePage.obterNomeProduto(indice);
        String precoProduto_HomePage = homePage.obterPrecoProduto(indice);

        ProdutoPage produtoPage = homePage.clicarProduto(indice);

        String nomeProduto_ProdutoPage = produtoPage.obterNomeDoProduto();
        String precoProduto_ProdutoPage = produtoPage.obterPrecoDoProduto();

        System.out.println(nomeProduto_ProdutoPage);
        System.out.println(precoProduto_ProdutoPage);

        assertThat(nomeProduto_HomePage.toLowerCase(), is(nomeProduto_ProdutoPage.toLowerCase()));
        assertThat(precoProduto_HomePage.toLowerCase(), is(precoProduto_ProdutoPage.toLowerCase()));

    }

    @Test
    public void testLoginComSucesso_UsuarioLogado(){
        // Clicar no botão Sign In na Homepage.
        LoginPage loginPage = homePage.clicarBotaoSignIn();

        // Preencher os campos usuário e senha.
        loginPage.preencherEmail("marcelo@teste.com");
        loginPage.preencherPassword("marcelo");

        // Clicar no botão Sign In para logar.
        loginPage.clicarBotaoSignIn();

        // Validar se o usuário está logado.
        assertThat(homePage.estaLogado("Marcelo Bittencourt"), is(true));

    }
}
