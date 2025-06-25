import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter output = new BufferedWriter(new FileWriter("output.txt"));

        char first = (char) input.read();
        input.readLine();
        String sequence = input.readLine();
        input.close();

        Deque<Character> deque = new ArrayDeque<>();
        deque.add(first);
        char currentFirst = first;
        Character currentDifferent = null;

        for (int i = 0; i < sequence.length(); i++) {
            char c = sequence.charAt(i);

            if (c < currentFirst) {
                deque.addFirst(c);
                currentDifferent = currentFirst;
                currentFirst = c;
            } else if (c > currentFirst) {
                deque.addLast(c);
                if (currentDifferent == null) {
                    currentDifferent = c;
                }
            } else {
                if (currentDifferent == null) {
                    deque.addFirst(c);
                } else {
                    if (c < currentDifferent) {
                        deque.addFirst(c);
                    } else {
                        deque.addLast(c);
                    }
                }
            }
        }

        for (char ch : deque) {
            output.write(ch);
        }
        output.close();
    }
}