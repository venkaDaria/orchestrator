package model;

public class Port {
    private Protocol protocol;
    private Integer local;
    private Integer remote;

    public Port(final String portLine) {
        String[] ports = portLine.split(":");
        if (ports.length != 3 && ports.length != 2 || ports[0].equals("") || ports[1].equals("")) {
            throw new IllegalArgumentException(
                    "Port must be: \"protocol:int:int\" or \"protocol:int\"");
        }
        protocol = new Protocol(ports[0]);
        local = Integer.valueOf(ports[1]);
        if (ports.length == 3) {
            remote = Integer.valueOf(ports[2]);
        }
    }

    private Port(final Protocol protocol, final Integer local, final Integer remote) {
        this.protocol = protocol;
        this.local = local;
        this.remote = remote;
    }
    
    public Protocol getProtocol() {
        return protocol;
    }

    public boolean hasProtocol() {
        return protocol != null;
    }

    public Integer getLocal() {
        return local;
    }

    public boolean hasLocal() {
        return local != null;
    }

    public Integer getRemote() {
        return remote;
    }

    public boolean hasRemote() {
        return remote != null;
    }

    public Port copy() {
        return new Port(protocol, local, remote);
    }

    @Override
    public String toString() {
        String line = protocol.getValue() + ":" + local; 
        if (hasRemote()) {
            line += ":" + remote;
        }
        return line;
    }
}