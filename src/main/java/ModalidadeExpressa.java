public class ModalidadeExpressa implements ModalidadeEntrega {
    private float distanciaEmKm;

    public ModalidadeExpressa(float distanciaEmKm){
        this.distanciaEmKm = distanciaEmKm;
    }

    @Override
    public float custo() {
        return distanciaEmKm * 1.5f;
    }

    @Override
    public String prazo() {
        if (distanciaEmKm <= 10) return "Entrega em 12 horas";
        else if (distanciaEmKm <= 100) return "Entrega em 24 horas";
        else return "Entrega em 48 horas";
    }

}
