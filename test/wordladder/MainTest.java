package wordladder;

import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    Main main = new Main();

    @Test
    public void testPrepareLadderStacks() {
        Stack<String> ladder = new Stack<>();
        ladder.push("source");
        Stack<Stack<String>> ladders = new Stack<>();
        ladders.push(ladder);
        assertEquals(ladders, main.prepareLadderStacks("source"));
    }

}