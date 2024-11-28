import java.util.ArrayList;

public class Customer extends Akun{
    public Keranjang keranjang;
    public ArrayList<Invoice> invoiceSelesai;

    public Customer(String id, String username, String password, String role){
        super(id, username, password, role);
    }

    public Keranjang getKeranjang(){
        return keranjang;
    }

    public ArrayList<Invoice> getInvoiceSelesai(){
        return invoiceSelesai;
    }

}
