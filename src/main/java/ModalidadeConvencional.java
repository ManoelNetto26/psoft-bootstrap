public class ModalidadeConvencional implements ModalidadeEntrega {
    private float tarifa;
    private float distanciaEmKm;

    public ModalidadeConvencional(float tarifa, float distanciaEmKm){
        this.tarifa = tarifa;
        this.distanciaEmKm = distanciaEmKm;
    }

    @Override
    public float custo() {
        return tarifa * distanciaEmKm;
    }

    @Override
    public String prazo() {
        if (distanciaEmKm <= 10) return "Entrega em 1 dia";
        else if (distanciaEmKm <= 100) return "Entrega em 3 dias";
        else return "Entrega em 7 dias";
    }
}
