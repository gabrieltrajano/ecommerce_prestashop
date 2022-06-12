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
}
