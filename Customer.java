public class Customer {
    private String username;
    private String id;
    private Keranjang keranjang;

    public Customer(String id) {
        this.id = id;
        this.username = username;
        this.keranjang = new Keranjang();
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Keranjang getKeranjang() {
        return keranjang;
    }

    @Override
    public String toString() {
        return "Customer{id='" + id + "', keranjang=" + keranjang + "}";
    }
}
