package fileio;

import java.io.IOException;

public interface IFileIO {
    String loadLine() throws IOException;
    void saveLine(String line) throws IOException;
    void closeInputFile() throws IOException;
    void closeOutputFile() throws IOException;
}
