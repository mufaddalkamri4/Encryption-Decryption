import java.util.*;

class AsciiCharSequence implements java.lang.CharSequence {
    // implementation
    byte[] chars;

    public AsciiCharSequence(byte[] chars) {
        this.chars = chars.clone();
    }

    @Override
    public int length() {
        return chars.length;
    }

    @Override
    public char charAt (int index) {
        return (char) chars[index];
    }

    @Override
    public AsciiCharSequence subSequence(int start, int end) {
        return new AsciiCharSequence(Arrays.copyOfRange(chars, start, end));
    }

    @Override
    public String toString() {
        return new String(chars);
    }
}