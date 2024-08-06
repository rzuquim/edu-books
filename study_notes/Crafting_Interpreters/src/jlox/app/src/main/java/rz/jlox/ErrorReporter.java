package rz.jlox;

public class ErrorReporter {
    private boolean anyErrors;

    public boolean anyErrors() {
        return this.anyErrors;
    }

    public void report(int currLine, String message, Object... args) {
        var finalMessage = String.format("[Line %d] ", currLine) + String.format(message, args);
        System.err.println(finalMessage);
        this.anyErrors = true;
    }
}
