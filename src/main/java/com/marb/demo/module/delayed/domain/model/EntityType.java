package com.marb.demo.module.delayed.domain.model;

/**
 * Defines the entity type in the system. An entity type allows to have in the same
 * data-set repeated "ids" of different models (i.e. a customer and a company)
 *
 * @author Andres
 */
public enum EntityType {

	DEMO(1, "DEM");

	private int id;
	private String shortCode;

	private EntityType(int id, String shortCode) {
		this.id = id;
		this.shortCode = shortCode;
	}

	public static EntityType byId(int id) {
		for (EntityType et : values()) {
			if (et.getId() == id) {
				return et;
			}
		}
		throw new IllegalArgumentException("entity type " + id + " does not exist");
	}

	public static EntityType byCode(String code) {
		for (EntityType et : values()) {
			if (et.getShortCode().equals(code)) {
				return et;
			}
		}
		throw new IllegalArgumentException("entity type " + code + " does not exist");
	}

	public int getId() {
		return this.id;
	}

	public String getShortCode() {
		return shortCode;
	}

	@Override
	public String toString() {
		return "EntityType{" + "id=" + id + ", shortCode='" + shortCode + '\'' + '}';
	}
}
