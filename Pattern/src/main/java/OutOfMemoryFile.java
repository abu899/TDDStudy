import java.io.File;
import java.io.IOException;

public class OutOfMemoryFile extends File {
    public OutOfMemoryFile(String path) {
        super(path);
    }
    public boolean createNewFile() throws IOException {
        throw new IOException();
    }
}
