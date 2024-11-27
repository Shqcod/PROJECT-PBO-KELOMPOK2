import java.io.*;
import java.util.ArrayList;

public class FileCustomer {
    public static ArrayList<Akun> bacaAkunDariFile(String filePath) {
        ArrayList<Akun> akunList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 4) {
                    akunList.add(new Akun(parts[0], parts[1], parts[2], parts[3]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return akunList;
    }

    public static void simpanAkunKeFile(Akun akun, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(akun.toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
