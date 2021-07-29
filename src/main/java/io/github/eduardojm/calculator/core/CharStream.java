package io.github.eduardojm.calculator.core;

/**
 * A class that implements a character stream for strings.
 */
public final class CharStream {
    /**
     * The input buffer string.
     */
    private final String buffer;
    /**
     * The current position in the buffer string.
     */
    private int position;

    /**
     * Creates a new instance of the CharStream.
     * @param input The input buffer string.
     */
    public CharStream(String input) {
        this.buffer = input;
        this.position = 0;
    }

    /**
     * Gets a boolean value indicating if is the end of the stream.
     * @return Returns true if the stream was ended, false otherwise.
     */
    public boolean eof() {
        return this.position >= this.buffer.length();
    }

    /**
     * Get the current character and not jump the position to the next character.
     * @return Returns the current character.
     */
    public char peek() {
        return this.buffer.charAt(this.position);
    }

    /**
     * Gets the current character and jump the position to the next character.
     * @return Returns the current character.
     */
    public char next() {
        char chr = this.buffer.charAt(this.position);
        this.position = this.position + 1;
        return chr;
    }

    /**
     * Gets the current position of the buffer string.
     * @return Returns the current position of the buffer string.
     */
    public int getPosition() {
        return position;
    }
}
