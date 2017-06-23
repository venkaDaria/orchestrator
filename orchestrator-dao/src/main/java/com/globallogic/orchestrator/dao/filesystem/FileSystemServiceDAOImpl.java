package com.globallogic.orchestrator.dao.filesystem;

import com.globallogic.orchestrator.connector.filesystem.FileSystemConnectorImpl;
import com.globallogic.orchestrator.dao.SeparatorHolder;
import com.globallogic.orchestrator.dao.dto.ServiceDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class FileSystemServiceDAOImpl implements FileSystemServiceDAO {

    private final String SEPARATOR;

    private static final String FILE_NAME = "services.csv";

    public FileSystemServiceDAOImpl() {
        SEPARATOR = SeparatorHolder.getSeparatorString();
    }

    @Override
    public void save(final Set<ServiceDto> services) {
        StringBuilder sb = new StringBuilder();
        services.forEach(service -> sb.append(getString(service)));

        new FileSystemConnectorImpl().write(FILE_NAME, sb.toString());
    }

    @Override
    public Set<ServiceDto> load() {
        String[] lines = new FileSystemConnectorImpl().read(FILE_NAME).split(System.lineSeparator());

        return Arrays.stream(lines).map(this::getDTO).collect(Collectors.toSet());
    }

    private String getString(final ServiceDto service) {
        return service.getName() + SEPARATOR + service.getImage() +
                SEPARATOR + getString(service.getPorts()) + SEPARATOR +
                SEPARATOR + getString(service.getRoles()) + SEPARATOR +
                SEPARATOR + getString(service.getVolumes()) +
                System.lineSeparator();
    }

    private String getString(Set<String> set) {
        StringBuilder sb = new StringBuilder();
        set.forEach(el -> sb.append(el).append(SeparatorHolder.getSeparatorString()));
        return sb.length() > 1 ? sb.substring(0, sb.length() - 1) : sb.toString();
    }

    private ServiceDto getDTO(final String line) {
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

        return service;
    }
}
