import java.io.File;
import java.io.FileWriter;

class HackAssembler {

    public static void main(String[] args) throws Exception {
        if (args.length == 1) {
            if (args[0].endsWith(".asm")) {
                FileWriter fileWriter = new FileWriter(args[0].substring(0, args[0].indexOf('.')) + ".hack");
                Parser parser = new Parser(new File(args[0]));
                while (parser.hasMoreLines()) {
                    parser.advance();
                    fileWriter.write(parser.currentInstruction + "\r\n");
                }
                parser.scanner.close();
                fileWriter.close();
            } else {
                System.out.println("Invalid file extension");
            }
        } else {
            System.out.println("Invalid number of arguments");
        }
    }
}
