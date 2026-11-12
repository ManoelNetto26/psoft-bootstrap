/** Regra de cálculo de uma modalidade de entrega. */
public interface ModalidadeEntrega {
    String getNome();

    double calcularCusto(Pedido pedido);

    int calcularPrazoEmDias(Pedido pedido);
}
