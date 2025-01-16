
class Pagamento {
    private static int contador = 0;
    private int codigo;
    private String pagamento;
    private String avista;
    private String local;
    private int atend;

    public Pagamento(String pagamento, String avista, String local, int atend) {
        this.codigo = ++contador;
        this.pagamento = pagamento;
        this.avista = avista;
        this.local = local;
        this.atend = atend;
    }

    public String getPagamento() {
        return pagamento;
    }

    public String getAvista() {
        return avista;
    }

    public String getLocal() {
        return local;
    }

    public int getAtend() {
        return atend;
    }

    @Override
    public String toString() {
        return "Pagamento [codigo=" + codigo + ", pagamento=" + pagamento + ", avista=" + avista + ", local=" + local
                + ", atend=" + atend + "]";
    }
}