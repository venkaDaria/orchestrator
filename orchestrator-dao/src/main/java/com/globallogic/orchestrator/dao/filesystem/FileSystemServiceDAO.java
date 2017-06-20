package com.globallogic.orchestrator.dao.filesystem;

import com.globallogic.orchestrator.dao.ServiceDAO;
import com.globallogic.orchestrator.connector.filesystem.FileSystemConnectorImpl;
import com.globallogic.orchestrator.dao.exception.ServiceConfigurationException;
import com.globallogic.orchestrator.model.valueobject.ImageReference;
import com.globallogic.orchestrator.model.valueobject.Port;
import com.globallogic.orchestrator.model.valueobject.Role;
import com.globallogic.orchestrator.model.valueobject.Volume;
import com.globallogic.orchestrator.model.entity.Service;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FileSystemServiceDAO implements ServiceDAO {
    private static final String SEPARATOR = ";";
    private static final String FILE_NAME = "services.csv";

    @Override
    public void save(final Set<Service> services) {
        StringBuilder sb = new StringBuilder();
        services.forEach(service -> sb.append(getString(service)));

        new FileSystemConnectorImpl().write(FILE_NAME, sb.toString());
    }

    @Override
    public Set<Service> load() {
        String[] lines = new FileSystemConnectorImpl().read(FILE_NAME).split(System.lineSeparator());

        Set<Service> services = new HashSet<>();
        Arrays.stream(lines).forEach(line -> services.add(parse(line)));
        return services;
    }

    private Service parse(String line) {
        Service service = new Service();

        if (StringUtils.isBlank(line)) {
            throw new ServiceConfigurationException();
        }

        String[] values = line.split(SEPARATOR);

        if (values.length < 2) {
            throw new ServiceConfigurationException();
        }

        service.setName(values[0]);
        service.setImage(new ImageReference(values[1]));

        int i = 2;

        Set<Port> ports = new HashSet<>();
        for (; i < values.length && StringUtils.isNotEmpty(values[i]); i++) {
            ports.add(new Port(values[i]));
        }
        service.setPorts(ports);

        i++;

        Set<Role> roles = new HashSet<>();
        for (; i < values.length && StringUtils.isNotEmpty(values[i]); i++) {
            roles.add(new Role(values[i]));
        }
        service.setRoles(roles);

        i++;

        Set<Volume> volumes = new HashSet<>();
        for (; i < values.length; i++) {
            volumes.add(new Volume(values[i]));
        }
        service.setVolumes(volumes);
        return service;
    }

    private String getString(Service service) {
        StringBuilder lineBuilder = new StringBuilder(service.getName());
        lineBuilder.append(SEPARATOR).append(service.getImage().getValue());

        for (Port p : service.getPorts()) {
            lineBuilder.append(SEPARATOR).append(p.getValue());
        }

        lineBuilder.append(SEPARATOR);

        for (Role r : service.getRoles()) {
            lineBuilder.append(SEPARATOR).append(r.getValue());
        }

        lineBuilder.append(SEPARATOR);

        for (Volume v : service.getVolumes()) {
            lineBuilder.append(SEPARATOR).append(v.getValue());
        }
        return lineBuilder.append(System.lineSeparator()).toString();
    }
}
