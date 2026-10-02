package edu.psu.se411.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class StackTest {

    private final Stack<String> stringStack = new Stack<>();

    // --- Tests from the handout ---

    @Test
    void pushPushPop_returnsLatest() {
        stringStack.push("Z");
        stringStack.push("A");
        assertEquals("A", stringStack.pop());
    }

    @Test
    void pop_empty_stack() {
        NoSuchElementException thrown = assertThrows(
            NoSuchElementException.class,
            () -> stringStack.pop(),
            "Expected pop from empty Stack to throw, but it didn't"
        );
        assertEquals("Stack is empty, cannot pop", thrown.getMessage());
    }

    @Test
    void pop_returnsElementsInReverseOrder() {
        stringStack.push("A");
        stringStack.push("B");
        stringStack.push("C");
        assertEquals("C", stringStack.pop());
        assertEquals("B", stringStack.pop());
        assertEquals("A", stringStack.pop());
    }

    // --- Extra edge cases ---

    @Test
    void push_null_popReturnsNull() {
        stringStack.push(null);
        assertNull(stringStack.pop());
    }

    @Test
    void pop_afterStackEmptied_throwsAgain() {
        stringStack.push("A");
        assertEquals("A", stringStack.pop());
        assertThrows(NoSuchElementException.class, () -> stringStack.pop());
    }

    @Test
    void interleavedPushAndPop_keepsCorrectState() {
        stringStack.push("A");
        stringStack.push("B");
        assertEquals("B", stringStack.pop());
        stringStack.push("C");
        assertEquals("C", stringStack.pop());
        assertEquals("A", stringStack.pop());
    }

    @Test
    void push_duplicateValues_allPoppedSeparately() {
        stringStack.push("X");
        stringStack.push("X");
        assertEquals("X", stringStack.pop());
        assertEquals("X", stringStack.pop());
        assertThrows(NoSuchElementException.class, () -> stringStack.pop());
    }

    @Test
    void push_beyondInitialCapacity_stillWorks() {
        for (int i = 0; i < 100; i++) {
            stringStack.push("item" + i);
        }
        for (int i = 99; i >= 0; i--) {
            assertEquals("item" + i, stringStack.pop());
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {-5, 0, 1, 100})
    void capacityConstructor_anyValue_stackWorks(int capacity) {
        Stack<String> s = new Stack<>(capacity);
        s.push("A");
        assertEquals("A", s.pop());
        assertThrows(NoSuchElementException.class, () -> s.pop());
    }
}