package com.globallogic.orchestrator.dao.filesystem;

import com.globallogic.orchestrator.dao.ServiceDAO;
import com.globallogic.orchestrator.dao.connector.FileSystemConnectorImpl;
import com.globallogic.orchestrator.dao.exception.ServiceConfigurationException;
import com.globallogic.orchestrator.dao.model.valueobject.ImageReference;
import com.globallogic.orchestrator.dao.model.valueobject.Port;
import com.globallogic.orchestrator.dao.model.valueobject.Role;
import com.globallogic.orchestrator.dao.model.valueobject.Volume;
import com.globallogic.orchestrator.dao.model.entity.Service;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FileSystemServiceDAO implements ServiceDAO {
    private static final String SEP = ";";
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

        String[] values = line.split(SEP);

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
        lineBuilder.append(SEP).append(service.getImage().getValue());

        for (Port p : service.getPorts()) {
            lineBuilder.append(SEP).append(p.getValue());
        }

        lineBuilder.append(SEP);

        for (Role r : service.getRoles()) {
            lineBuilder.append(SEP).append(r.getValue());
        }

        lineBuilder.append(SEP);

        for (Volume v : service.getVolumes()) {
            lineBuilder.append(SEP).append(v.getValue());
        }
        return lineBuilder.append(System.lineSeparator()).toString();
    }
}
