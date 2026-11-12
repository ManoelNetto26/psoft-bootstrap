/** Entrega de menor custo em troca de um prazo adicional. */
public class EntregaEconomica implements ModalidadeEntrega {
    private final EntregaConvencional tarifaBase;
    private final double descontoPercentual;
    private final int diasAdicionais;

    public EntregaEconomica(EntregaConvencional tarifaBase, double descontoPercentual, int diasAdicionais) {
        if (tarifaBase == null) {
            throw new IllegalArgumentException("tarifa convencional não pode ser nula");
        }
        if (!Double.isFinite(descontoPercentual) || descontoPercentual < 0 || descontoPercentual > 1) {
            throw new IllegalArgumentException("desconto deve estar entre 0 e 1");
        }
        if (diasAdicionais < 0) {
            throw new IllegalArgumentException("dias adicionais devem ser não negativos");
        }
        this.tarifaBase = tarifaBase;
        this.descontoPercentual = descontoPercentual;
        this.diasAdicionais = diasAdicionais;
    }

    @Override
    public String getNome() {
        return "Econômica";
    }

    @Override
    public double calcularCusto(Pedido pedido) {
        return tarifaBase.calcularCusto(pedido) * (1 - descontoPercentual);
    }

    @Override
    public int calcularPrazoEmDias(Pedido pedido) {
        return tarifaBase.calcularPrazoEmDias(pedido) + diasAdicionais;
    }
}
