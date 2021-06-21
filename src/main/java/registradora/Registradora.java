package registradora;

import banco_de_dados.BancoDeDados;
import cliente.Cliente;
import compra.Compra;
import lombok.Builder;
import venda.Venda;
import vendedor.Vendedor;

import java.math.BigDecimal;

@Builder(toBuilder = true)
public class Registradora {
    private final Compra compra;
    private final BigDecimal valorRecebido;
    private final Vendedor vendedor;
    private final Cliente cliente;

    public void efetivarVenda() { // Metodo para efetivar a Venda
        var totalCompra = compra.getTotal(); // Chama o metodo para realizar a soma dos itens da Compra e adicionar na variavel totalCompra

        if (totalCompra.compareTo(valorRecebido) > 0){
            return;
        }

        var troco  = valorRecebido.subtract(totalCompra); // Funcao que subtrai o valor recebido com o valor da compra e coloca na varialvel troco


        var venda = Venda.builder() //  monta a venda realizada com todos os parametros
                .totalCompra(totalCompra)
                .troco(troco)
                .vendedor(vendedor)
                .cliente(cliente)
                .build();

        BancoDeDados.addVenda(venda); // e salva no bando de dados(OBS.: Classe foi criada para simular um BD)
    }
}
