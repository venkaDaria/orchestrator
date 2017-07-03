package com.globallogic.orchestrator.dao.filesystem;

import com.globallogic.orchestrator.connector.filesystem.FileSystemConnector;
import com.globallogic.orchestrator.dao.SeparatorHolder;
import com.globallogic.orchestrator.dao.dto.ServiceDto;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class FileSystemServiceDAOImpl implements FileSystemServiceDAO {

    private static final Logger LOG = LoggerFactory.getLogger(FileSystemServiceDAOImpl.class);

    private final String SEPARATOR;

    private static final String FILE_NAME = "services.csv";

    public FileSystemServiceDAOImpl() {
        SEPARATOR = SeparatorHolder.getSeparatorString();
    }

    @Autowired
    private FileSystemConnector connector;

    @Override
    public void save(final Set<ServiceDto> services) {
        LOG.debug("Save services -> " + services);

        StringBuilder sb = new StringBuilder();
        services.forEach(service -> sb.append(getString(service)));

        connector.write(FILE_NAME, sb.toString());
    }

    @Override
    public Set<ServiceDto> load() {
        LOG.debug("Load services");
        String[] lines = connector.read(FILE_NAME).split(System.lineSeparator());

        return Arrays.stream(lines).map(this::getDTO).collect(Collectors.toSet());
    }

    @Override
    public ServiceDto getByName(final String name) {
        LOG.debug("Get a service by name -> " + name);

        String[] lines = connector.read(FILE_NAME).split(System.lineSeparator());

        return getDTO(Arrays.stream(lines).filter(line -> line.split(SEPARATOR)[0].equals(name)).findAny().orElse(null));
    }

    @Override
    public void remove(final String id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(final String name, final String image, final List<String> roles, final List<String> ports, final List<String> volumes) {
        throw new UnsupportedOperationException();
    }

    private String getString(final ServiceDto service) {
        LOG.debug("Get a String from ServiceDto -> " + service);
        return service.getName() + SEPARATOR + service.getImage() +
                SEPARATOR + getString(service.getPorts()) + SEPARATOR +
                SEPARATOR + getString(service.getRoles()) + SEPARATOR +
                SEPARATOR + getString(service.getVolumes()) +
                System.lineSeparator();
    }

    private String getString(final Set<String> set) {
        StringBuilder sb = new StringBuilder();
        set.forEach(el -> sb.append(el).append(SeparatorHolder.getSeparatorString()));
        return sb.length() > 1 ? sb.substring(0, sb.length() - 1) : sb.toString();
    }

    private ServiceDto getDTO(final String line) {
        LOG.debug("Get a ServiceDto from String -> " + line);
        ServiceDto service = new ServiceDto();

        String regex = "^(.+?)" + SEPARATOR + "(.+?)" + SEPARATOR + "(.*?)" + SEPARATOR + SEPARATOR + "(.*?)"
                + SEPARATOR + SEPARATOR + "(.*?)$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(line);

        if (!m.matches()) {
            return service;
        }

        service.setName(m.group(1));
        service.setImage(m.group(2));

        if (StringUtils.isNotEmpty(m.group(3))) {
            service.setPorts(new HashSet<>(Arrays.asList(m.group(3).split(SEPARATOR))));
        } else {
            service.setPorts(new HashSet<>());
        }

        if (StringUtils.isNotEmpty(m.group(4))) {
            service.setRoles(new HashSet<>(Arrays.asList(m.group(4).split(SEPARATOR))));
        } else {
            service.setRoles(new HashSet<>());
        }

        if (StringUtils.isNotEmpty(m.group(5))) {
            service.setVolumes(new HashSet<>(Arrays.asList(m.group(5).split(SEPARATOR))));
        } else {
            service.setVolumes(new HashSet<>());
        }

        LOG.debug("Return a ServiceDto -> " + service);
        return service;
    }
}
