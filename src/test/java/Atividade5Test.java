/** Testes executáveis sem dependências externas: java -ea Atividade5Test. */
public class Atividade5Test {
    public static void main(String[] args) {
        EntregaConvencional convencional = new EntregaConvencional(10.0, 2.0, 100.0);
        EntregaExpressa expressa = new EntregaExpressa(convencional, 15.0, 1);
        EntregaEconomica economica = new EntregaEconomica(convencional, 0.20, 2);
        Pedido pedido = new Pedido("P1", "Ana", "Origem", "Destino", 1.5, 120.0, convencional);

        conferir(pedido.calcularCusto(), 250.0, "custo convencional");
        conferir(pedido.calcularPrazoEmDias(), 2, "prazo convencional");
        conferir(expressa.calcularCusto(pedido), 265.0, "custo expresso");
        conferir(expressa.calcularPrazoEmDias(pedido), 1, "prazo expresso mínimo");
        conferir(economica.calcularCusto(pedido), 200.0, "custo econômico com desconto");
        conferir(economica.calcularPrazoEmDias(pedido), 4, "prazo econômico adicional");

        ControladorLogistico controlador = new ControladorLogistico();
        controlador.registrarPedido(pedido);
        controlador.alterarModalidade("P1", economica);
        if (pedido.getModalidade() != economica || controlador.listarPedidos().size() != 1) {
            throw new AssertionError("pedido não registrado ou modalidade não alterada");
        }
        esperarExcecao(IllegalArgumentException.class, () -> controlador.registrarPedido(pedido));

        controlador.despacharPedido("P1");
        esperarExcecao(IllegalStateException.class, () -> controlador.alterarModalidade("P1", expressa));
        controlador.confirmarEntrega("P1");
        if (pedido.getStatus() != Pedido.Status.ENTREGUE) {
            throw new AssertionError("status final incorreto");
        }
        esperarExcecao(IllegalStateException.class, () -> controlador.despacharPedido("P1"));
        esperarExcecao(IllegalArgumentException.class,
                () -> new Pedido(" ", "Ana", "Origem", "Destino", 1, 1, convencional));

        System.out.println("Atividade5Test: todos os testes passaram.");
    }

    private static void conferir(double atual, double esperado, String descricao) {
        if (Math.abs(atual - esperado) > 0.000001) {
            throw new AssertionError(descricao + ": esperado " + esperado + ", obtido " + atual);
        }
    }

    private static void conferir(int atual, int esperado, String descricao) {
        if (atual != esperado) {
            throw new AssertionError(descricao + ": esperado " + esperado + ", obtido " + atual);
        }
    }

    private static void esperarExcecao(Class<? extends Exception> esperada, Runnable acao) {
        try {
            acao.run();
        } catch (Exception excecao) {
            if (esperada.isInstance(excecao)) {
                return;
            }
            throw new AssertionError("exceção inesperada", excecao);
        }
        throw new AssertionError("era esperada a exceção " + esperada.getSimpleName());
    }
}
