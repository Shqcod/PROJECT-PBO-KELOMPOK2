public abstract class Pembayaran {
    protected String id;

    public Pembayaran(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public abstract String getTipePembayaran();
}





