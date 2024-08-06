package rz.jlox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Run {
    public static void main(final String[] args) throws IOException {
        if (args.length > 1) {
            System.err.println("Usage: jlox [script]");
            // NOTE: error codes based on "sysexits.h" header
            System.exit(64);
        }

        if (args.length == 0) {
            startREPL();
        } else {
            interpretFile(args[0]);
        }
    }

    private static void startREPL() throws IOException {
        var stdin = new InputStreamReader(System.in);
        var reader = new BufferedReader(stdin);

        while (true) {
            var error = new ErrorReporter();
            System.out.print("> ");
            var line = reader.readLine();
            if (line == null) {
                return;
            }

            parse(line, error);
        }
    }

    private static void interpretFile(final String filePath) throws IOException {
        var error = new ErrorReporter();
        var fileBytes = Files.readAllBytes(Paths.get(filePath));
        var fileContent = new String(fileBytes, Charset.defaultCharset());
        parse(fileContent, error);
        if (error.anyErrors()) {
            System.exit(65);
        }
    }

    private static void parse(String fileContent, ErrorReporter error) {
        var scanner = new Scanner(error);
        var tokens = scanner.scanTokens(fileContent);

        for (var token : tokens) {
            System.out.println(token);
        }
    }
}
