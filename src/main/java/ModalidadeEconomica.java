public class ModalidadeEconomica implements ModalidadeEntrega{
    private float distanciaEmKm;

    public ModalidadeEconomica(float distanciaEmKm){
        this.distanciaEmKm = distanciaEmKm;
    }

    @Override
    public float custo() {
        return distanciaEmKm * 0.8f; 
    }

    @Override
    public String prazo() {
        if (distanciaEmKm <= 10) return "Entrega em 12 dias";
        else if (distanciaEmKm <= 100) return "Entrega em 20 dias";
        else return "Entrega em 1 mês";
    }

}
