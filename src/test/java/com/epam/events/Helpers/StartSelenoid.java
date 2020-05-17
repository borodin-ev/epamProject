package com.epam.events.Helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StartSelenoid {
    public static String start() throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("./src/test/resources/startSelenoid.sh");

        Process process = processBuilder.start();

        StringBuilder output = new StringBuilder();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(output.append(line).append("\n"));
        }

        int exitVal = process.waitFor();
        if (exitVal == 0) {
            System.out.println("Success!");
            System.out.println(output);
            return output.toString().substring
                    (
                            output.toString().lastIndexOf("->") - 4,
                            output.toString().lastIndexOf("->")
                    );

        } else {
            throw new InterruptedException("Something went wrong");
        }
    }
}
