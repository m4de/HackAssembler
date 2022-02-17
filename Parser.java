import java.io.File;
import java.util.Scanner;

class Parser {

    Scanner scanner;

    String currentInstruction;

    enum InstructionType {
        A_INSTRUCTION,
        C_INSTRUCTION
    }

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
     * Skips over whitespace and comments, if necessary.
     * Reads the next instruction from the input, and makes it the current instruction.
     * This method should be called only if {@link #hasMoreLines() hasMoreLines} is true.
     * Initially there is no current instruction.
     */
    void advance() {
        String line = scanner.nextLine();
        if (!(line.isEmpty() || line.startsWith("//"))) {
            currentInstruction = line;
        } else {
            advance();
        }
    }

    /**
     * Returns the type of the current instruction:
     *
     * @return  {@link InstructionType#A_INSTRUCTION A_INSTRUCTION} for <code>@xxx</code>, where <code>xxx</code> is either a decimal number or a symbol.
     *          {@link InstructionType#C_INSTRUCTION C_INSTRUCTION} for <code>dest=comp;jump</code>
     */
    InstructionType instructionType() {
        return currentInstruction.startsWith("@") ? InstructionType.A_INSTRUCTION : InstructionType.C_INSTRUCTION;
    }

    /**
     * If the current instruction is <code>@xxx</code>, returns decimal <code>xxx</code> (as a string).
     * Should be called only if {@link #instructionType() instructionType} is {@link InstructionType#A_INSTRUCTION A_INSTRUCTION}.
     *
     * @return  the instruction's symbol (string)
     */
    String symbol() {
        return currentInstruction.substring(1);
    }

    /**
     * Returns the symbolic <code>dest</code> part of the current C-instruction (8 possibilities).
     * Should be called only if {@link #instructionType() instructionType} is {@link InstructionType#C_INSTRUCTION C_INSTRUCTION}.
     *
     * @return  the instruction's <code>dest</code> field
     */
    String dest() {
        return currentInstruction.contains("=") ? currentInstruction.substring(0, currentInstruction.indexOf("=")) : null;
    }

    /**
     * Returns the symbolic <code>jump</code> part of the current C-instruction (28 possibilities).
     * Should be called only if {@link #instructionType() instructionType} is {@link InstructionType#C_INSTRUCTION C_INSTRUCTION}.
     *
     * @return  the instruction's <code>comp</code> field
     */
    String comp() {
        if (dest() == null && jump() == null) return currentInstruction;
        if (dest() == null && jump() != null) return currentInstruction.substring(0, currentInstruction.indexOf(";"));
        if (dest() != null && jump() == null) return currentInstruction.substring(currentInstruction.indexOf("=") + 1);
        if (dest() != null && jump() != null) return currentInstruction.substring(currentInstruction.indexOf("="), currentInstruction.indexOf(";"));
        return null;
    }

    /**
     * Returns the symbolic <code>jump</code> part of the current C-instruction (8 possibilities).
     * Should be called only if {@link #instructionType() instructionType} is {@link InstructionType#C_INSTRUCTION C_INSTRUCTION}.
     *
     * @return  the instruction's <code>jump</code> field
     */
    String jump() {
        return currentInstruction.contains(";") ? currentInstruction.substring(currentInstruction.indexOf(";") + 1) : null;
    }
}
