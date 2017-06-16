package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.connector.FileSystemConnectorImpl;
import com.globallogic.orchestrator.model.entity.Service;
import com.globallogic.orchestrator.model.valueobject.ImageReference;
import com.globallogic.orchestrator.model.valueobject.Port;
import com.globallogic.orchestrator.model.valueobject.Role;
import com.globallogic.orchestrator.model.valueobject.Volume;
import com.globallogic.orchestrator.service.exception.ServiceServiceException;
import org.apache.commons.lang.StringUtils;

import java.util.HashSet;
import java.util.Set;

public class ServiceService {
    private static final String FILE_NAME = "service.csv";

    private static final String SEP = ";";

    public void write(Service service) {
        write(FILE_NAME, service);
    }

    public void write(String fileName, Service service) {
        new FileSystemConnectorImpl().write(fileName, getStringCsv(service));
    }

    public Service read() {
        return read(FILE_NAME);
    }

    public Service read(String fileName) {
        return parse(new FileSystemConnectorImpl().read(fileName));
    }

    public static Service parse(String line) {
        Service service = new Service();

        if (StringUtils.isBlank(line)) {
            throw new ServiceServiceException();
        }

        String[] values = line.split(SEP);

        if (values.length < 2) {
            throw new ServiceServiceException();
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

    public static String getStringCsv(Service service) {
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
