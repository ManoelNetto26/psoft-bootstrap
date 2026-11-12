/** Entrega prioritária: conserva a tarifa convencional e cobra um adicional. */
public class EntregaExpressa implements ModalidadeEntrega {
    private final EntregaConvencional tarifaBase;
    private final double adicional;
    private final int reducaoPrazoEmDias;

    public EntregaExpressa(EntregaConvencional tarifaBase, double adicional, int reducaoPrazoEmDias) {
        if (tarifaBase == null) {
            throw new IllegalArgumentException("tarifa convencional não pode ser nula");
        }
        if (!Double.isFinite(adicional) || adicional < 0) {
            throw new IllegalArgumentException("adicional deve ser não negativo");
        }
        if (reducaoPrazoEmDias < 0) {
            throw new IllegalArgumentException("redução de prazo deve ser não negativa");
        }
        this.tarifaBase = tarifaBase;
        this.adicional = adicional;
        this.reducaoPrazoEmDias = reducaoPrazoEmDias;
    }

    @Override
    public String getNome() {
        return "Expressa";
    }

    @Override
    public double calcularCusto(Pedido pedido) {
        return tarifaBase.calcularCusto(pedido) + adicional;
    }

    @Override
    public int calcularPrazoEmDias(Pedido pedido) {
        return Math.max(1, tarifaBase.calcularPrazoEmDias(pedido) - reducaoPrazoEmDias);
    }
}
