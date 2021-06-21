package banco_de_dados;

import venda.Venda;

import java.util.ArrayList;
import java.util.List;

public class BancoDeDados {
    public static List<Venda> vendas = new ArrayList<>();

    //Simulando o INSERT do BD
    public  static void addVenda(Venda venda){
        vendas.add(venda);
    }

    //Simulando o TRUNCATE
    public static void reset() {
        vendas.clear();
    }
}
