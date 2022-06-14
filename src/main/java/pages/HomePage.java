package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HomePage {

    private WebDriver driver;

    List<WebElement> listaProdutos = new ArrayList();
    private By produtos = By.className("product-description");
    private By textoProdutosNoCarrinho = By.className("cart-products-count");
    private By descricoesDosProdutos = By.cssSelector(".product-description a");
    private By precoDosProdutos = By.className("price");
    private By botaoSignIn = By.cssSelector("#_desktop_user_info span.hidden-sm-down");
    private By usuarioLogado = By.cssSelector("#_desktop_user_info span.hidden-sm-down");
    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    private void carregarListaProdutos(){
        listaProdutos = driver.findElements(produtos);
    }

    public int contarProdutos(){
        carregarListaProdutos();
        return listaProdutos.size();
    }

    public int obterQuantidadeProdutosNoCarrinho(){
        String qntdProdutosNoCarrinho = driver.findElement(textoProdutosNoCarrinho).getText();
        qntdProdutosNoCarrinho = qntdProdutosNoCarrinho.replace("(", "");
        qntdProdutosNoCarrinho = qntdProdutosNoCarrinho.replace(")", "");

        int qtdProtudosNoCarrinho = Integer.parseInt(qntdProdutosNoCarrinho);

        return qtdProtudosNoCarrinho;
    }

    public String obterNomeProduto(int indice){
        return driver.findElements(descricoesDosProdutos).get(indice).getText();
    }

    public String obterPrecoProduto(int indice){
        return driver.findElements(precoDosProdutos).get(indice).getText();
    }

    public ProdutoPage clicarProduto(int indice){
        driver.findElements(descricoesDosProdutos).get(indice).click();
        return new ProdutoPage(driver);
    }

    public LoginPage clicarBotaoSignIn(){
        driver.findElement(botaoSignIn).click();
        return new LoginPage(driver);
    }

    public boolean estaLogado(String texto){
        return texto.contentEquals(driver.findElement(usuarioLogado).getText());
    }
}
