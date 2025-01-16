
class Pedido {
    private static int contador = 0;
    private int codigo;
    private String pedido;
    private String adicionais;
    private String cobertura;
    private String item;

    public Pedido(String pedido, String adicionais, String cobertura, String item) {
        this.codigo = ++contador;
        this.pedido = pedido;
        this.adicionais = adicionais;
        this.cobertura = cobertura;
        this.item = item;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    @Override
    public String toString() {
        return "Pedido [codigo=" + codigo + ", pedido=" + pedido + ", adicionais=" + adicionais + ", cobertura="
                + cobertura + ", item" + item + "]";
    }
}