package io.github.eduardojm.calculator.core;

public final class CharStream {
    private String buffer;
    private int position;

    public CharStream(String input) {
        this.buffer = input;
        this.position = 0;
    }

    public int getPosition() {
        return this.position;
    }

    public boolean eof() {
        return this.position >= this.buffer.length();
    }

    public char peek() {
        return this.buffer.charAt(this.position);
    }

    public char next() {
        char chr = this.buffer.charAt(this.position);
        this.position = this.position + 1;
        return chr;
    }
}
