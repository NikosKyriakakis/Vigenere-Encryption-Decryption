package vigenere;

import java.util.ArrayList;
import java.util.List;

public class Vigenere {
    private final static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final static int alphabetSize = alphabet.length();
    private final List<Character> bufferList;
    private final String key;
    private final int keySize;
    private char keyLetter;
    private char encryptedLetter;
    private char plainLetter;

    public Vigenere(String key) {
        bufferList = new ArrayList<>();
        this.key = key;
        this.keySize = this.key.length();
    }

    public String encrypt(String text) {
        final int textSize = text.length();
        int encryptedIndex;

        bufferList.clear();
        for (int letterIndex = 0, keyIndex = 0; letterIndex < textSize; letterIndex++) {
            plainLetter = text.charAt(letterIndex);
            if (alphabet.indexOf(plainLetter) == -1) {
                encryptedLetter = plainLetter;
            } else {
                keyLetter = key.charAt(keyIndex);
                encryptedIndex = ((plainLetter + keyLetter) % alphabetSize);
                encryptedLetter = alphabet.charAt(encryptedIndex);
                if (++keyIndex == keySize)
                    keyIndex %= keySize;
            }

            bufferList.add(encryptedLetter);
        }

        return unifiedString();
    }

    public String decrypt(String text) {
        final int textSize = text.length();
        int plainIndex;

        bufferList.clear();
        for (int letterIndex = 0, keyIndex = 0; letterIndex < textSize; letterIndex++) {
            encryptedLetter = text.charAt(letterIndex);
            if (alphabet.indexOf(encryptedLetter) == -1) {
                plainLetter = encryptedLetter;
            } else {
                keyLetter = key.charAt(keyIndex);
                plainIndex = ((encryptedLetter - keyLetter + alphabetSize) % alphabetSize);
                plainLetter = alphabet.charAt(plainIndex);
                if (++keyIndex == keySize)
                    keyIndex %= keySize;
            }

            bufferList.add(plainLetter);
        }

        return unifiedString();
    }

    private String unifiedString() {
        StringBuilder builder = new StringBuilder(bufferList.size());
        for (Character c : bufferList)
            builder.append(c);
        return builder.toString();
    }
}