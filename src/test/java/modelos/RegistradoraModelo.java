package modelos;

import cliente.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import registradora.Registradora;
import vendedor.Vendedor;

public class RegistradoraModelo {
    public static Registradora.RegistradoraBuilder construirCenario(){
        return Registradora.builder()
                .vendedor(new Vendedor(1,"Julian"))
                .cliente(new Cliente("Dorinha"));

    }
}
