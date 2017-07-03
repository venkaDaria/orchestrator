package com.globallogic.orchestrator.connector.filesystem;

import com.globallogic.orchestrator.connector.exception.FileNotFoundException;
import com.globallogic.orchestrator.connector.exception.FileProcessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

@Service
public class FileSystemConnectorImpl implements FileSystemConnector {

    private static final Logger LOG = LoggerFactory.getLogger(FileSystemConnectorImpl.class);

    @Override
    public String read(final String fileName) {
        StringBuilder sb = new StringBuilder();
        LOG.debug("Start reading from " + fileName);

        try (FileReader fr = new FileReader(fileName); BufferedReader in = new BufferedReader(fr)) {
            String s;
            while ((s = in.readLine()) != null) {
                sb.append(s).append(System.lineSeparator());
            }
        } catch (java.io.FileNotFoundException e) {
            LOG.error(e.getMessage());
            throw new FileNotFoundException();
        } catch (IOException e) {
            LOG.error(e.getMessage());
            throw new FileProcessException();
        }

        LOG.debug("Finish reading from " + fileName);
        return sb.toString();
    }

    @Override
    public void write(final String fileName, final String text) {
        LOG.debug("Start writing to " + fileName);
        LOG.debug("Text -> " + text);

        try (PrintWriter out = new PrintWriter(fileName)) {
            out.print(text);
        } catch (java.io.FileNotFoundException e) {
            LOG.error(e.getMessage());
            throw new FileProcessException();
        }

        LOG.debug("Start writing to " + fileName);
    }
}
