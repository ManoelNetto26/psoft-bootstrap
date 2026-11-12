/** Entrega com tarifa-base mais uma tarifa por quilômetro percorrido. */
public class EntregaConvencional implements ModalidadeEntrega {
    private final double tarifaBase;
    private final double tarifaPorKm;
    private final double quilometrosPorDia;

    public EntregaConvencional(double tarifaBase, double tarifaPorKm, double quilometrosPorDia) {
        validarNaoNegativo(tarifaBase, "tarifa base");
        validarNaoNegativo(tarifaPorKm, "tarifa por quilômetro");
        if (!Double.isFinite(quilometrosPorDia) || quilometrosPorDia <= 0) {
            throw new IllegalArgumentException("quilômetros por dia deve ser positivo");
        }
        this.tarifaBase = tarifaBase;
        this.tarifaPorKm = tarifaPorKm;
        this.quilometrosPorDia = quilometrosPorDia;
    }

    @Override
    public String getNome() {
        return "Convencional";
    }

    @Override
    public double calcularCusto(Pedido pedido) {
        validarPedido(pedido);
        return tarifaBase + tarifaPorKm * pedido.getDistanciaKm();
    }

    @Override
    public int calcularPrazoEmDias(Pedido pedido) {
        validarPedido(pedido);
        return Math.max(1, (int) Math.ceil(pedido.getDistanciaKm() / quilometrosPorDia));
    }

    private static void validarPedido(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("pedido não pode ser nulo");
        }
    }

    private static void validarNaoNegativo(double valor, String campo) {
        if (!Double.isFinite(valor) || valor < 0) {
            throw new IllegalArgumentException(campo + " deve ser não negativo");
        }
    }
}
