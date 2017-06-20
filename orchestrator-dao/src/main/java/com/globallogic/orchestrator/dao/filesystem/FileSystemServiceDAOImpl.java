package com.globallogic.orchestrator.dao.filesystem;

import com.globallogic.orchestrator.connector.filesystem.FileSystemConnectorImpl;
import com.globallogic.orchestrator.dao.ServiceDAO;
import com.globallogic.orchestrator.dao.dto.ServiceDTO;
import com.globallogic.orchestrator.dao.exception.ServiceConfigurationException;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileSystemServiceDAOImpl implements ServiceDAO {
    private final String SEPARATOR;
    private static final String FILE_NAME = "services.csv";

    public FileSystemServiceDAOImpl(final LocaleSeparator separator) {
        SEPARATOR = separator.toString();
    }

    @Override
    public void save(final Set<ServiceDTO> services) {
        StringBuilder sb = new StringBuilder();
        services.forEach(service -> sb.append(getString(service)));

        new FileSystemConnectorImpl().write(FILE_NAME, sb.toString());
    }

    @Override
    public Set<ServiceDTO> load() {
        String[] lines = new FileSystemConnectorImpl().read(FILE_NAME).split(System.lineSeparator());

        Set<ServiceDTO> services = new HashSet<>();
        Arrays.stream(lines).forEach(line -> services.add(getDTO(line)));
        return services;
    }

    private String getString(final ServiceDTO service) {
        if (service == null) {
            throw new ServiceConfigurationException();
        }
        return service.getName() + SEPARATOR + service.getImage() +
                SEPARATOR + service.getPorts() +
                SEPARATOR + service.getRoles() +
                SEPARATOR + service.getVolumes() +
                System.lineSeparator();
    }

    private ServiceDTO getDTO(final String line) {
        ServiceDTO service = new ServiceDTO();

        if (StringUtils.isBlank(line)) {
            throw new ServiceConfigurationException();
        }

        String regex = "^(.+?)" + SEPARATOR + "(.+?)" + SEPARATOR + "(.*?)" + SEPARATOR + "(.*?)"
                + SEPARATOR + "(.*?)" + SEPARATOR + "$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(line);

        if (!m.matches()) {
            throw new ServiceConfigurationException();
        }

        service.setName(m.group(1));
        service.setImage(m.group(2));
        service.setPorts(m.group(3));
        service.setRoles(m.group(4));
        service.setVolumes(m.group(5));

        return service;
    }
}
