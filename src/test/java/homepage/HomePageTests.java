package homepage;

import base.BaseTests;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.ModalProdutoPage;
import pages.ProdutoPage;

import java.util.List;
import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;


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

    ProdutoPage produtoPage;
    String nomeProduto_ProdutoPage;
    @Test
    public void testValidarDetalhesDoProduto_DescricaoEValorIguais(){
        int indice = 0;
        String nomeProduto_HomePage = homePage.obterNomeProduto(indice);
        String precoProduto_HomePage = homePage.obterPrecoProduto(indice);

        produtoPage = homePage.clicarProduto(indice);

        nomeProduto_ProdutoPage = produtoPage.obterNomeDoProduto();
        String precoProduto_ProdutoPage = produtoPage.obterPrecoDoProduto();

        assertThat(nomeProduto_HomePage.toLowerCase(), is(nomeProduto_ProdutoPage.toLowerCase()));
        assertThat(precoProduto_HomePage.toLowerCase(), is(precoProduto_ProdutoPage.toLowerCase()));

    }

    LoginPage loginPage;
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
        carregarPaginaInicial();
    }

    @Test
    public void incluirProdutoNoCarrinho_ProdutoIncluidoComSucesso(){
        String tamanhoProduto = "M";
        String corProduto = "Black";
        int quantidadeProduto = 2;

        // Pré-condição: Usuário logado.
        if(!homePage.estaLogado(("Marcelo Bittencourt"))){
            testLoginComSucesso_UsuarioLogado();
        }
        // Teste:
        // Selecionar produto
        testValidarDetalhesDoProduto_DescricaoEValorIguais();

        // Selecionar tamanho
        List<String> listaOpcoes = produtoPage.obterOpcoesSelecionadas();

        produtoPage.selecionarOpcaoDropdown(tamanhoProduto);

        listaOpcoes = produtoPage.obterOpcoesSelecionadas();

        // Selecionar cor
        produtoPage.selecionarCorPreta();

        // Selecionar quantidade
        produtoPage.alterarQuantidade(quantidadeProduto);

        // Adicionar no carrinho
        ModalProdutoPage modalProdutoPage = produtoPage.clicarBotaoAddToCart();

        // Validações
        assertTrue(modalProdutoPage.obterMensagemProdutoAdicionado().endsWith("Product successfully added to your shopping cart"));

        assertThat(modalProdutoPage.obterTamanhoProduto(), is(tamanhoProduto));
        assertThat(modalProdutoPage.obterCorProduto(), is(corProduto));
        assertThat(modalProdutoPage.obterQuantidadeProduto(), is(Integer.toString(quantidadeProduto)));

        String precoProdutoString = modalProdutoPage.obterPrecoProduto();
        precoProdutoString = precoProdutoString.replace("$", "");
        Double precoProduto = Double.parseDouble(precoProdutoString);

        String subtotalString = modalProdutoPage.obterSubtotal();
        subtotalString = subtotalString.replace("$", "");
        Double subtotal = Double.parseDouble(subtotalString);

        Double subtotalCalculado = quantidadeProduto * precoProduto;

        assertThat(subtotal, is(subtotalCalculado));

        assertThat(modalProdutoPage.obterDescricaoProduto().toLowerCase(), is(nomeProduto_ProdutoPage.toLowerCase()));
    }
}
