package com.globallogic.orchestrator.connector.filesystem;

import com.globallogic.orchestrator.connector.exception.FileNotFoundException;
import com.globallogic.orchestrator.connector.exception.FileProcessException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

@Service
public class FileSystemConnectorImpl implements FileSystemConnector {

    @Override
    public String read(final String fileName) {
        StringBuilder sb = new StringBuilder();

        try (FileReader fr = new FileReader(fileName); BufferedReader in = new BufferedReader(fr)) {
            String s;
            while ((s = in.readLine()) != null) {
                sb.append(s).append(System.lineSeparator());
            }
        } catch (java.io.FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException e) {
            throw new FileProcessException();
        }

        return sb.toString();
    }

    @Override
    public void write(final String fileName, final String text) {
        try (PrintWriter out = new PrintWriter(fileName)) {
            out.print(text);
        } catch (java.io.FileNotFoundException e) {
            throw new FileProcessException();
        }
    }
}
