public class Akun {
    private String id;
    private String username;
    private String password;
    private String role;

    public Akun(){
        username = "Customer";
    }

    public Akun(String id, String username, String password, String role){
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getId(){
        return id;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getRole(){
        return role;
    }

    public void setUsername(String username){
        this.username = username;
    }

    @Override
    public String toString(){
        return id + ":" + username + ":" + password + ":" + role;
    }
}
 