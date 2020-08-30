package fileio;

import java.io.*;

public class FileIO implements IFileIO {
    private BufferedReader reader;
    private BufferedWriter writer;

    public FileIO(String inputFile, String outputFile) {
        try {
            this.reader = new BufferedReader(new FileReader(inputFile));
            this.writer = new BufferedWriter(new FileWriter(outputFile));
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public String loadLine() throws IOException {
        String line = this.reader.readLine();
        if (line != null)
            return line.toUpperCase();
        return null;
    }

    @Override
    public void saveLine(String line) throws IOException {
        line += "\n";
        this.writer.write(line);
    }

    public void closeInputFile() throws IOException {
        this.reader.close();
    }

    public void closeOutputFile() throws IOException {
        this.writer.close();
    }
}