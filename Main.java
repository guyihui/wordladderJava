package wordladder;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.io.File;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        printHeading();
//        input dictionary name and check
        Scanner scanner = new Scanner(System.in);
        String filepath = getDictionaryPath(scanner);

        while (true) {
//            input target and source
            System.out.print("input word #1(source):");
            String source = scanner.nextLine();
            int len = source.length();
            System.out.print("input word #2(target):");
            String target = scanner.nextLine();
            if(len!=target.length()) continue;
//            load words with equal length into set
            Scanner sc = new Scanner(new File(filepath));
            HashSet<String> set = new HashSet<>();
            loadDictionary(len, sc, set);
//            find ladder
            Stack<String> ladder = new Stack<>();
            ladder.push(target);
            Stack<Stack<String>> ladders = new Stack<>();
            ladders.push(ladder);

//            output
            System.out.println("ladder from {" + source + "} to {" + target + "}:");


//            continue
            System.out.println("press enter to continue('q' to quit):");
            String quit = scanner.nextLine();
            if (quit.equals("q")) break;
        }


    }

    private static void loadDictionary(int len, Scanner sc, HashSet<String> set) {
        while (sc.hasNextLine()) {
            String word = sc.nextLine();
            if (word.length() == len) {
                set.add(word);
            }
        }
    }

    private static String getDictionaryPath(Scanner scanner) {
        while (true) {
            System.out.print("input your dictionary(filename):");
            String dic = scanner.nextLine();
            File file = new File(dic);
            if (file.exists()){
                return dic;
            }
            System.out.println("File not found.");
        }
    }

    private static void printHeading() {
        System.out.println("This is a word ladder.");
        System.out.println();
    }

    private static Stack<String> wordLadder(HashSet<String> set, Stack<Stack<String>> ladders, String source) {
        Stack<Stack<String>> longerLadder = new Stack<>();
        for (Stack<String> lad : ladders) {
            String top = lad.lastElement();
            for (int i = 0; i < top.length(); i++) {
//                replace the letter on position i





            }
        }
        return wordLadder(set, longerLadder, source);
    }
}
