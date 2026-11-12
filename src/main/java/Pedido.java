/** Pedido de entrega com modalidade e ciclo de vida próprios. */
public class Pedido {
    public enum Status {
        REGISTRADO,
        DESPACHADO,
        ENTREGUE
    }

    private final String id;
    private final String cliente;
    private final String enderecoOrigem;
    private final String enderecoDestino;
    private final double pesoKg;
    private final double distanciaKm;
    private ModalidadeEntrega modalidade;
    private Status status;

    public Pedido(String id, String cliente, String enderecoOrigem, String enderecoDestino,
                  double pesoKg, double distanciaKm, ModalidadeEntrega modalidade) {
        this.id = validarTexto(id, "id");
        this.cliente = validarTexto(cliente, "cliente");
        this.enderecoOrigem = validarTexto(enderecoOrigem, "endereço de origem");
        this.enderecoDestino = validarTexto(enderecoDestino, "endereço de destino");
        if (!Double.isFinite(pesoKg) || pesoKg <= 0) {
            throw new IllegalArgumentException("peso deve ser positivo");
        }
        if (!Double.isFinite(distanciaKm) || distanciaKm < 0) {
            throw new IllegalArgumentException("distância deve ser não negativa");
        }
        if (modalidade == null) {
            throw new IllegalArgumentException("modalidade não pode ser nula");
        }
        this.pesoKg = pesoKg;
        this.distanciaKm = distanciaKm;
        this.modalidade = modalidade;
        this.status = Status.REGISTRADO;
    }

    public String getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public String getEnderecoOrigem() {
        return enderecoOrigem;
    }

    public String getEnderecoDestino() {
        return enderecoDestino;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public ModalidadeEntrega getModalidade() {
        return modalidade;
    }

    public Status getStatus() {
        return status;
    }

    public double calcularCusto() {
        return modalidade.calcularCusto(this);
    }

    public int calcularPrazoEmDias() {
        return modalidade.calcularPrazoEmDias(this);
    }

    public void alterarModalidade(ModalidadeEntrega novaModalidade) {
        if (status != Status.REGISTRADO) {
            throw new IllegalStateException("a modalidade só pode ser alterada antes do despacho");
        }
        if (novaModalidade == null) {
            throw new IllegalArgumentException("modalidade não pode ser nula");
        }
        modalidade = novaModalidade;
    }

    public void despachar() {
        if (status != Status.REGISTRADO) {
            throw new IllegalStateException("somente pedidos registrados podem ser despachados");
        }
        status = Status.DESPACHADO;
    }

    public void marcarComoEntregue() {
        if (status != Status.DESPACHADO) {
            throw new IllegalStateException("somente pedidos despachados podem ser entregues");
        }
        status = Status.ENTREGUE;
    }

    private static String validarTexto(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " não pode ser vazio");
        }
        return valor.trim();
    }
}
