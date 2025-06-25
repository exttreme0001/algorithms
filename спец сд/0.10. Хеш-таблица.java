import java.util.*;
import java.io.*;


    public class Main implements Runnable {
    public static void main(String[] args)throws FileNotFoundException {
        new Thread(null, new Main(), "", 256 * 1024 * 1024).start();
    }

    @Override
    public void run() {
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            PrintWriter pw = new PrintWriter(new File("output.txt"));

                    int m = sc.nextInt();
                    int c = sc.nextInt();
                    int n = sc.nextInt();

                    int[] table = new int[m];
                    Arrays.fill(table, -1);

                    Set<Integer> insertedKeys = new HashSet<>(); // чтобы отслеживать дубликаты

                    for (int i = 0; i < n; i++) {
                        int key = sc.nextInt();
                        if (insertedKeys.contains(key)) continue; // избегаем дубликатов
                        insertedKeys.add(key);

                        int attempt = 0;
                        int index;
                        while (true) {
                            index = ((key % m) + c * attempt) % m;
                            if (table[index] == -1) {
                                table[index] = key;
                                break;
                            }
                            attempt++;
                        }
                    }

                    for (int value : table) {
                        pw.print(value+" ");
                    }

            pw.flush();
            pw.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        }
    }