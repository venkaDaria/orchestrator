package com.globallogic.orchestrator.connector;

import java.io.*;

public class FileSystemConnectorImpl implements FileSystemConnector {

    @Override
    public String read(String fileName) {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader in = new BufferedReader(new FileReader(fileName))){
            String s;
            while ((s = in.readLine()) != null) {
                sb.append(s).append("\n");
            }
        } catch (IOException e) {
            throw new FileProcessException("Problem with file");
        }

        return sb.toString();
    }

    @Override
    public void write(String fileName, String text) {
        try (PrintWriter out = new PrintWriter(fileName)){
            out.print(text);
        } catch (FileNotFoundException e) {
            throw new FileProcessException("File not found");
        }
    }
}
