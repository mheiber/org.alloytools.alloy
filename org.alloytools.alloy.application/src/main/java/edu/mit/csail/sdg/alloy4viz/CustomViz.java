package edu.mit.csail.sdg.alloy4viz;

import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomViz {
    private static String lastSrcFileStr = null;
    public static void runNext(String tupleOutput) {
        if (CustomViz.lastSrcFileStr != null) {
            CustomViz.run(lastSrcFileStr, tupleOutput);
        }
    }
    public static void run(String srcFileStr, String tupleOutput) {
        CustomViz.lastSrcFileStr = srcFileStr;
        File srcFile = new File(srcFileStr);
        try {
            File tmpFile = File.createTempFile("temp", ".tmp");
            writeStringToFile(tmpFile, tupleOutput);
            
            Pattern pattern = Pattern.compile("@custom_visualization: (.*)");
            
            BufferedReader reader = new BufferedReader(new FileReader(srcFile));
            String line;
            String customVisualizationLine = null;
            while ((line = reader.readLine()) != null) {
                if (line.contains("@custom_visualization")) {
                    customVisualizationLine = line;
                    break;
                }
            }
            reader.close();
            
            if (customVisualizationLine != null) {
                Matcher matcher = pattern.matcher(customVisualizationLine);
                if (matcher.find()) {
                    String capturedCommand = matcher.group(1);
                    List<String> commandParts = new ArrayList<>(Arrays.asList(capturedCommand.split("\\s+")));
                    
                    String programPath = commandParts.get(0);
                    commandParts.set(0, programPath);
                    
                    commandParts.add(tmpFile.getAbsolutePath());
                    
                    ProcessBuilder processBuilder = new ProcessBuilder(commandParts);
                    processBuilder.directory(srcFile.getParentFile());
                    Process process = processBuilder.start();
                    
                    int exitCode = process.waitFor();
                    if (exitCode != 0) {
                        throw new IOException("bad exit code " + exitCode + " for " + commandParts.toString());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void writeStringToFile(File file, String content) throws IOException {
        FileWriter writer = new FileWriter(file);
        writer.write(content);
        writer.close();
    }
}
