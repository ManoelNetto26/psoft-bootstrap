import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/** Ponto central para registrar pedidos e acompanhar as operações de entrega. */
public class ControladorLogistico {
    private final Map<String, Pedido> pedidos = new LinkedHashMap<>();

    public void registrarPedido(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("pedido não pode ser nulo");
        }
        if (pedidos.containsKey(pedido.getId())) {
            throw new IllegalArgumentException("já existe um pedido com o id " + pedido.getId());
        }
        pedidos.put(pedido.getId(), pedido);
    }

    public Pedido buscarPedido(String id) {
        Pedido pedido = pedidos.get(id);
        if (pedido == null) {
            throw new IllegalArgumentException("pedido não encontrado: " + id);
        }
        return pedido;
    }

    public Collection<Pedido> listarPedidos() {
        return Collections.unmodifiableList(new ArrayList<>(pedidos.values()));
    }

    public void alterarModalidade(String idPedido, ModalidadeEntrega modalidade) {
        buscarPedido(idPedido).alterarModalidade(modalidade);
    }

    public void despacharPedido(String idPedido) {
        buscarPedido(idPedido).despachar();
    }

    public void confirmarEntrega(String idPedido) {
        buscarPedido(idPedido).marcarComoEntregue();
    }
}
