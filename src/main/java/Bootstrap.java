public class Bootstrap {

    public static void main(String[] args) {
        EntregaConvencional convencional = new EntregaConvencional(10.0, 2.0, 100.0);
        ModalidadeEntrega expressa = new EntregaExpressa(convencional, 15.0, 1);
        ModalidadeEntrega economica = new EntregaEconomica(convencional, 0.20, 2);

        ControladorLogistico controlador = new ControladorLogistico();
        Pedido pedido = new Pedido("PED-001", "Ana", "Campina Grande", "João Pessoa",
                2.5, 120.0, convencional);
        controlador.registrarPedido(pedido);

        System.out.println("Pedido: " + pedido.getId() + " - " + pedido.getStatus());
        System.out.printf("Entrega %s: R$ %.2f, prazo de %d dia(s)%n",
                pedido.getModalidade().getNome(), pedido.calcularCusto(), pedido.calcularPrazoEmDias());

        controlador.alterarModalidade(pedido.getId(), expressa);
        System.out.printf("Entrega %s: R$ %.2f, prazo de %d dia(s)%n",
                pedido.getModalidade().getNome(), pedido.calcularCusto(), pedido.calcularPrazoEmDias());

        controlador.alterarModalidade(pedido.getId(), economica);
        System.out.printf("Entrega %s: R$ %.2f, prazo de %d dia(s)%n",
                pedido.getModalidade().getNome(), pedido.calcularCusto(), pedido.calcularPrazoEmDias());

        controlador.despacharPedido(pedido.getId());
        System.out.println("Status após despacho: " + pedido.getStatus());
    }
}
