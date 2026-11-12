public class Pedido {
    private Integer id;
    private String descricao;
    private ModalidadeEntrega modalidadeEntrega;
    private boolean status;

    public Pedido(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
        this.status = false;
    }

    public void setModalidadeConvencional(float tarifa, float distancia){
        this.modalidadeEntrega = new ModalidadeConvencional(tarifa, distancia);
    }

    public void setModalidadeExpressa(float distancia){
        this.modalidadeEntrega = new ModalidadeExpressa(distancia);
    }

    public void setModalidadeEconomica(float distancia){
        this.modalidadeEntrega = new ModalidadeEconomica(distancia);
    }

    public float getCusto(){
        if (modalidadeEntrega == null)  throw new IllegalStateException("Modalidade de entrega não definida.");
        return modalidadeEntrega.custo();
    }

    public String getPrazo(){
        if (modalidadeEntrega == null)  throw new IllegalStateException("Modalidade de entrega não definida.");
        return modalidadeEntrega.prazo();
    }

    public String getDescricao(){
        return descricao;
    }

    public boolean getStatus(){
        return status;
    }

    public void despachar() {
        if (modalidadeEntrega != null) status = true;
        else throw new IllegalStateException("Modalidade de entrega não definida.");
    }
}
