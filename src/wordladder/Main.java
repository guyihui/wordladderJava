package wordladder;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.io.File;
import java.util.Stack;

public class Main {

    static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public static void main(String[] args) throws FileNotFoundException {
        printHeading();
//        input dictionary name and check
        Scanner scanner = new Scanner(System.in);//console input
        String filepath = getDictionaryPath(scanner);
        while (true) {
//            input target and source
            System.out.print("input word #1(source):");
            String source = scanner.nextLine();
            int len = source.length();
            System.out.print("input word #2(target):");
            String target = scanner.nextLine();
            if (len != target.length()) continue;
//            load words with equal length into set
            Scanner sc = new Scanner(new File(filepath));//file input
            HashSet<String> set = new HashSet<>();
            loadDictionary(len, sc, set);
            if (!(set.contains(source) && set.contains(target))) { //check whether source/target is in the dictionary
                System.out.println("{" + source + "} in dictionary:" + set.contains(source));
                System.out.println("{" + target + "} in dictionary:" + set.contains(target));
                continue;
            }
//            find ladder
            Stack<Stack<String>> ladders = prepareLadderStacks(source);
            Stack<String> result = wordLadder(set, ladders, target);//core logical part
//            output
            System.out.println("ladder from {" + source + "} to {" + target + "}:");
            printLadder(result);
//            continue
            System.out.println("press enter to continue('q' to quit):");
            String quit = scanner.nextLine();
            if (quit.equals("q")) break;
        }
    }

    public static Stack<Stack<String>> prepareLadderStacks(String source) {
        Stack<String> ladder = new Stack<>();
        ladder.push(source);
        Stack<Stack<String>> ladders = new Stack<>();
        ladders.push(ladder);
        return ladders;
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
            String dicPath = scanner.nextLine();
            File file = new File(dicPath);
            if (file.exists()) {
                return dicPath;
            }
            System.out.println("File not found.");
        }
    }

    private static void printHeading() {
        System.out.println("This is a word ladder.");
        System.out.println();
    }

    private static Stack<String> wordLadder(HashSet<String> set, Stack<Stack<String>> ladders, String target)  {
        if (ladders.size() == 0) {
            Stack<String> stk = new Stack<>();
            stk.push("Not found.");
            return stk;
        }
        Stack<Stack<String>> longerLadder = new Stack<>();
        int laddersSize = ladders.size();
        for (int k = 0; k < laddersSize; k++) {
            Stack<String> lad = ladders.pop();
            String top = lad.lastElement();
            for (int i = 0; i < top.length(); i++) {
//                replace the letter on position i
                StringBuilder newWord = new StringBuilder(top);
                for (int j = 0; j < 26; j++) {
                    newWord.setCharAt(i, alphabet[j]);
                    if (newWord.toString().equals(target)) {
                        lad.push(newWord.toString());
                        return lad;
                    } else if (!newWord.toString().equals(top) && set.contains(newWord.toString())) {
                        lad.push(newWord.toString());
                        set.remove(newWord.toString());
                        longerLadder.push((Stack<String>)lad.clone());
                        lad.pop();
                    }
                }
            }
        }
        return wordLadder(set, longerLadder, target);
    }

    private static void printLadder(Stack<String> stk) {
        int len = stk.size();
        for (int i = 0; i < len; i++) {
            System.out.print(stk.elementAt(i) + " ");
        }
        System.out.println();
    }
}
