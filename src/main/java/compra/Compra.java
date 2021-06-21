package compra;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class Compra {
    private List<Item> itens;

    // Metodo que retorna o valor da somas de todos os items da lista
    public BigDecimal getTotal() {
        return itens.stream()
                .map(Item::getValor) // Pegar todos os valores da Lista
                .reduce(BigDecimal.ZERO,BigDecimal::add); // Somar todos os valores
    }
}
