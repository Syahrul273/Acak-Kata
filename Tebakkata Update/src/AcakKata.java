import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;

public class AcakKata {
    private static final String ANS_TRUE = "BENAR!";
    private static final String ANS_FALSE = "SALAH!";
    private static final String QUERY = "Jawab: ";
    private static final String TEBAK = "TEBAK kata: \n";
    private static final String EXIT = "EXIT";
    private static final String INPUT = "INPUT";
    private static final String MAIN = "MAIN";
    private ArrayList<String> listKata;

    int nilai1 = 0,nilai2 = 0,ulang = 0, hasil = 0;
    String Player1, Player2;
    BufferedReader in = new BufferedReader(new InputStreamReader (System.in));


    public AcakKata() {
        listKata = new ArrayList<String>();
        listKata.add("buku");
        listKata.add("kucing");
        listKata.add("gajah");
    }


    public void addKata(String kata) {
        listKata.add(kata);
    }


    public String getKata(int i) {
        return listKata.get(i);
    }


    public int genRandom() {
        Random seed = new Random();
        int i =  seed.nextInt(listKata.size());
        return i;
    }


    public String randomKata(String init) {
        StringBuilder build = new StringBuilder();
        String tmp = init;

        while (tmp.equals(init)) {
            boolean flag[] = new boolean[init.length()];

            int i = genRandom();

            for (int j=0; j<init.length(); j++) {
                if(!flag[i]) {
                    build.append(init.charAt(i));
                    flag[i] = true;
                }
                i = genRandom();
            }

            for(int j =0; j<init.length(); j++)
                if(!flag[j]) build.append(init.charAt(j));

            tmp = build.toString();
        }
        return tmp;
    }

    public String randomKata2(String init2) {
        StringBuilder build = new StringBuilder();
        String tmp2 = init2;

        while (tmp2.equals(init2)) {
            boolean flag[] = new boolean[init2.length()];

            int i = genRandom();

            for (int j=0; j<init2.length(); j++) {
                if(!flag[i]) {
                    build.append(init2.charAt(i));
                    flag[i] = true;
                }
                i = genRandom();
            }

            for(int j =0; j<init2.length(); j++)
                if(!flag[j]) build.append(init2.charAt(j));

            tmp2 = build.toString();
        }
        return tmp2;
    }


    public void getOption() {
        String[] choices = {"Masukkan pilihan", "===================================", "", "Ketik [INPUT] untuk memasukkan kata", "Ketik [MAIN] untuk bermain", "Ketik [EXIT] untuk keluar"};
        for(int i =0; i<choices.length; i++)
            System.out.println(choices[i]);
        System.out.println();
    }


    public void startANewGame() throws IOException{
        Scanner scan = new Scanner(System.in);
        System.out.println("=> Player 1");
        System.out.println("Masukkan Nama Anda : ");
        Player1 = scan.nextLine();

        System.out.println("=> Player 2");
        System.out.println("Masukkan Nama Anda : ");
        Player2 = scan.nextLine();


        do{
            String init = getKata(genRandom());
            String curr = randomKata(init);
            System.out.println("Player 1 : " + Player1);
            System.out.println();
            System.out.println(TEBAK + curr);
            System.out.println(QUERY);
            String ans = in.readLine();
            if((ans).equalsIgnoreCase(init)) {
                System.out.println(ANS_TRUE);
                nilai1 = nilai1 + 10;
                System.out.println("Skor Player 1 : " + nilai1 + "\n");
            }else if(!(ans).equalsIgnoreCase(init)){
                System.out.println(ANS_FALSE);
            }

            String curi = randomKata2(init);
            System.out.println("Player 2 : " + Player2);
            System.out.println();
            System.out.println(TEBAK + curi);
            System.out.println(QUERY);
            ans = in.readLine();
            if((ans).equalsIgnoreCase(init)) {
                System.out.println(ANS_TRUE);
                nilai2 = nilai2 + 10;
                System.out.println("Skor Player 2 : " + nilai2 + "\n");
            }else if(!(ans).equalsIgnoreCase(init)){
                System.out.println(ANS_FALSE);
            }

            ulang = ulang + 1;

        }while (ulang < 5);

        if(nilai1 > nilai2){
            System.out.println("Player 1 MENANG");
            System.out.println("Skor : " + nilai1);
        } else if (nilai2 > nilai1) {
            System.out.println("Player 2 MENANG");
            System.out.println("Skor : " + nilai2);
        } else {
            System.out.println("Hasil Skor Seri");
            System.out.println("Skor : " + nilai1);
            System.out.println("Skor : " + nilai2);
        }


    }

    public void play() throws IOException {
        getOption();
        String choice = in.readLine();
        while(true) {
            if(choice.equalsIgnoreCase(MAIN)) {
                startANewGame();
            } else if(choice.equalsIgnoreCase(INPUT)) {
                System.out.println("Silahkan masukkan kata baru");
                String newKata = in.readLine();
                addKata(newKata);
            } else if(choice.equalsIgnoreCase(EXIT)) {
                System.exit(0);
            } else {
                System.out.println("Maaf silahkan masukkan pilihan Anda!");
            }
            System.out.println();
            getOption();
            choice=in.readLine();
        }

    }


    public static void main(String args[]) throws IOException {
        AcakKata hm = new AcakKata();
        hm.play();
    }
}
