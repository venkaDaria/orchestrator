package com.globallogic.orchestrator.dao.dto;

public class ServiceDTO {
	private String name;
	private String image;
	private String roles;
	private String ports;
	private String volumes;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

    public String getImage() {
        return image;
    }

    public void setImage(final String image) {
        this.image = image;
    }

	public String getRoles() {
        return roles;
    }

    public void setRoles(final String roles) {
        this.roles = roles;
    }

    public String getPorts() {
        return ports;
    }

    public void setPorts(final String ports) {
        this.ports = ports;
    }

    public String getVolumes() {
        return volumes;
    }

    public void setVolumes(final String volumes) {
        this.volumes = volumes;
    }
}
