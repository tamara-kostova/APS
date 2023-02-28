import java.util.LinkedList;
import java.util.Scanner;
import java.util.Queue;
class Gragjanin{
    private String ime;
    private int licnaKarta;
    private int pasos;
    private int vozacka;
    public String getIme() {
        return ime;
    }
    public int getlicnaKarta() {
        return licnaKarta;
    }
    public int getPasos() {
        return pasos;
    }
    public int getVozacka() {
        return vozacka;
    }
    public Gragjanin(String ime, int licnaKarta, int pasos, int vozacka) {
        this.ime = ime;
        this.licnaKarta = licnaKarta;
        this.pasos = pasos;
        this.vozacka = vozacka;
    }
}
public class MVR {

    public static void main(String[] args) {

        Scanner br = new Scanner(System.in);

        int N = Integer.parseInt(br.nextLine());
        Queue<Gragjanin> licnikarti = new LinkedList<>();
        Queue<Gragjanin> pasosi = new LinkedList<>();
        Queue<Gragjanin> vozacki = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            String imePrezime = br.nextLine();
            int lKarta = Integer.parseInt(br.nextLine());
            int pasos = Integer.parseInt(br.nextLine());
            int vozacka = Integer.parseInt(br.nextLine());
            Gragjanin covek = new Gragjanin(imePrezime, lKarta, pasos, vozacka);
            if (covek.getlicnaKarta() == 1)
                licnikarti.add(covek);
            else if (covek.getPasos() == 1)
                pasosi.add(covek);
            else
                vozacki.add(covek);
        }
        while (!(licnikarti.isEmpty())) {
            Gragjanin curr = licnikarti.poll();
            if (curr.getPasos() == 1)
                pasosi.add(curr);
            else if (curr.getVozacka() == 1)
                vozacki.add(curr);
            else
                System.out.println(curr.getIme());
        }
        while (!(pasosi.isEmpty())) {
            Gragjanin curr = pasosi.poll();
            if (curr.getVozacka() == 1)
                vozacki.add(curr);
            else
                System.out.println(curr.getIme());
        }
        while (!(vozacki.isEmpty())) {
            Gragjanin curr = vozacki.poll();
            System.out.println(curr.getIme());
        }
    }
}
