package vigenere;

import java.util.ArrayList;
import java.util.List;

public class Vigenere {
    private final static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final static int alphabetSize = alphabet.length();
    private final List<Character> bufferList;
    private char encryptedLetter;
    private char plainLetter;
    private char keyLetter;

    public Vigenere() {
        bufferList = new ArrayList<>();
    }

    public String encrypt(String text, String key) {
        final int textSize = text.length();
        final int keySize = key.length();
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

    public String decrypt(String text, String key) {
        final int textSize = text.length();
        final int keySize = key.length();
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