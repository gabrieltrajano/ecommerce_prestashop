package homepage;

import base.BaseTests;
import org.junit.jupiter.api.Test;

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
}
