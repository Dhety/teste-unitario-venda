import banco_de_dados.BancoDeDados;
import compra.Compra;
import compra.Item;
import modelos.RegistradoraModelo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class RegistradoraTeste {

    @BeforeEach //Antes de todos os testes esse metodo vai ser chamado para limpar a lista
    void reiniciarBancoDeDados(){
        BancoDeDados.reset();
    }
    public void deveRegistrarAVendaNoBancoDeDados(){

        /**   Arranque -> Entrada: Constroi o cenario */
       var pastelDeCarne = Item.builder().nome("Pastel de  Carne").valor(new BigDecimal("7.00")).build();
       var pastelDeFrango = Item.builder().nome("Pastel de  Frango").valor(new BigDecimal("5.50")).build();

        List<Item> listaDeItens = Arrays.asList(pastelDeCarne, pastelDeFrango);

        var compra = Compra.builder().itens(listaDeItens).build();

        var registradora = RegistradoraModelo.construirCenario()
                .compra(compra)
                .valorRecebido(new BigDecimal("12.50"))
                .build();

        /**  Act -> Ação: Realiza a montagem do cenario  */
        registradora.efetivarVenda();

        /** Assert -> Resultado: Verificação se o cénario está correto (Testes) */
        Assertions.assertEquals(1, BancoDeDados.vendas.size()); // Verificar se a unica venda foi realziada
        Assertions.assertEquals(new BigDecimal("12.50"), BancoDeDados.vendas.get(0).getTotalCompra()); // Verificar se o total da compra 12.50
        Assertions.assertEquals(new BigDecimal("00.00"), BancoDeDados.vendas.get(0).getTroco()); // Verificar se o troco da compra foi 00.00
        Assertions.assertEquals("Julian", BancoDeDados.vendas.get(0).getVendedor().getNome()); // Verificar se o vendedor da compra tem o nome de Julian
        Assertions.assertEquals("Dorinha", BancoDeDados.vendas.get(0).getCliente().getNome()); // Verificar se o Cliente da compra tem o nome de Dorinha
        Assertions.assertEquals(LocalDate.now(), BancoDeDados.vendas.get(0).getDataDaVenda()); // Verificar se a data da compra foi hoje

    }

    @Test
    public void naoDeveRegistrarAVendaValorRecebidoMenorQueTotal(){
        /**   Arranque -> Entrada: Constroi o cenario */
        var pastelDeCarne = Item.builder().nome("Pastel de  Carne").valor(new BigDecimal("7.00")).build();
        var pastelDeFrango = Item.builder().nome("Pastel de  Frango").valor(new BigDecimal("5.50")).build();

        List<Item> listaDeItens = Arrays.asList(pastelDeCarne, pastelDeFrango);

        var compra = Compra.builder().itens(listaDeItens).build();

        var registradora = RegistradoraModelo.construirCenario()
                .compra(compra)
                .valorRecebido(new BigDecimal("12.00"))
                .build();

        /**  Act -> Ação: Realiza a montagem do cenario  */
        registradora.efetivarVenda();

        /** Assert -> Resultado: Verificação se o cénario está correto (Testes) */
        Assertions.assertEquals(0, BancoDeDados.vendas.size()); // Verificar se a unica venda foi realziada

    }
}
