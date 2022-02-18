import java.util.HashMap;

class SymbolTable {

    private HashMap<String, Integer> symbolTable;

    /**
     * Creates a new empty symbol table.
     */
    SymbolTable() {
        symbolTable = new HashMap<>();
    }

    /**
     * Adds <code>&lt;symbol,address&gt;</code> to the table.
     *
     * @param symbol    the symbol to be added
     * @param address   the address of the symbol
     */
    void addEntry(String symbol, int address) {
        symbolTable.put(symbol, address);
    }
}
