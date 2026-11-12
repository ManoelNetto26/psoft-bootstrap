import java.util.Hashtable;

public class CentralDeLogistica {
    private static CentralDeLogistica instance;
    private Hashtable<Integer, Pedido> pedidos;

    private CentralDeLogistica() {
        // Construtor privado para evitar instanciamento externo
        pedidos = new Hashtable<>();
    }

    public static CentralDeLogistica getInstance(){
        if (instance == null) {
            instance = new CentralDeLogistica();
        }
        return instance;
    }

    public void adicionarPedido(int id, String descricao){
        pedidos.put(id, new Pedido(id, descricao));
    }

    public boolean getStatusDePedido(int id){
        Pedido pedido = pedidos.get(id);
        if (pedido == null) throw new IllegalArgumentException("O pedido não existe no sistema.");
        else return pedido.getStatus(); 
    }

    public float getCustoDePedido(int id){
        Pedido pedido = pedidos.get(id);
        if (pedido == null) throw new IllegalArgumentException("O pedido não existe no sistema.");
        else return pedido.getCusto();
    }

     public String getPrazoDePedido(int id){
        Pedido pedido = pedidos.get(id);
        if (pedido == null) throw new IllegalArgumentException("O pedido não existe no sistema.");
        else return pedido.getPrazo();
    }

    public void despacharPedido(int id){
        Pedido pedido = pedidos.get(id);
        if (pedido == null) throw new IllegalArgumentException("O pedido não existe no sistema.");
        pedido.despachar();
    }

    public void setModalidadeExpressaEmPedido(int id, float distancia){
        Pedido pedido = pedidos.get(id);

        if (pedido != null) 
            if (!pedido.getStatus()) pedido.setModalidadeExpressa(distancia);
            else throw new IllegalArgumentException("O pedido já foi despachado");
        else throw new IllegalArgumentException("O pedido não existe no sistema.");
    }

    public void setModalidadeConvencionalEmPedido(int id, float tarifa, float distancia){
        Pedido pedido = pedidos.get(id);
        
        if (pedido != null) 
            if (!pedido.getStatus()) pedido.setModalidadeConvencional(tarifa, distancia);
            else throw new IllegalArgumentException("O pedido já foi despachado");
        else throw new IllegalArgumentException("O pedido não existe no sistema.");
    }

    public void setModalidadeEconomicaEmPedido(int id, float distancia){
        Pedido pedido = pedidos.get(id);
        
        if (pedido != null) 
            if (!pedido.getStatus()) pedido.setModalidadeEconomica(distancia);
            else throw new IllegalArgumentException("O pedido já foi despachado");
        else throw new IllegalArgumentException("O pedido não existe no sistema.");
    }
}
