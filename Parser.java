import java.io.File;
import java.util.Scanner;

class Parser {

    Scanner scanner;

    String currentInstruction;

    /**
     * Opens the input file/stream and gets ready to parse it.
     *
     * @param file  Input file or stream
     * @throws Exception
     */
    Parser(File file) throws Exception {
        scanner = new Scanner(file);
    }

    /**
     * Are there more lines in the input?
     *
     * @return  <code>true</code> if there are more lines in the input;
     *          <code>false</code> otherwise.
     */
    boolean hasMoreLines() {
        return scanner.hasNextLine();
    }

    /**
     * Reads the next instruction from the input, and makes it the current instruction.
     * This method should be called only if {@link #hasMoreLines() hasMoreLines} is true.
     * Initially there is no current instruction.
     */
    void advance() {
        currentInstruction = scanner.nextLine();
    }
}
