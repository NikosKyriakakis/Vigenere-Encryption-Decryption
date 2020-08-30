package vigenere;

import fileio.FileIO;
import fileio.IFileIO;

import java.io.IOException;

public class VigenereMain {
    public static void main(String[] args) {
        if (args.length != 4) {
            System.err.println("Usage: program_name" +
                    " <input_file_path> " +
                    " <output_file_path> " +
                    "<key(text)>" +
                    " <mode=(encrypt/decrypt)>");
            System.exit(1);
        }

        String key = args[2].toUpperCase();
        String mode = args[3];
        IFileIO handlerIO = new FileIO(args[0], args[1]);
        Vigenere vigenere = new Vigenere(key);
        IFunction fn = null;

        switch(mode) {
            case "encrypt":
                fn = VigenereMain::executeEncryption;
                break;
            case "decrypt":
                fn = VigenereMain::executeDecryption;
                break;
            default:
                System.err.println("Error in <mode> arg, valid options = (encrypt/decrypt)");
                System.exit(1);
        }

        try {
            String plainText;
            String cipherText;

            while((plainText = handlerIO.loadLine()) != null) {
                cipherText = fn.invoke(vigenere, plainText, key);
                handlerIO.saveLine(cipherText);
            }
            handlerIO.closeInputFile();
            handlerIO.closeOutputFile();
        } catch(IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    private static String executeEncryption(Vigenere vigenere, String text, String key) {
        return vigenere.encrypt(text);
    }
    
    private static String executeDecryption(Vigenere vigenere, String text, String key) {
        return vigenere.decrypt(text);
    }
    
    private interface IFunction {
        String invoke(Vigenere vigenere, String text, String key);
    }
}