package com.marb.demo.module.delayed.domain.model;

public enum DelayCallSystemCode {

	EXAMPLE_NOTIFICATION(1, "EXAMPLE_NOTIF");

	private int id;
	private String shortCode;

	DelayCallSystemCode(int id, String shortCode) {
		this.id = id;
		this.shortCode = shortCode;
	}

	public static DelayCallSystemCode byId(int id) {
		for (DelayCallSystemCode et : values()) {
			if (et.getId() == id) {
				return et;
			}
		}
		throw new IllegalArgumentException("system code " + id + " does not exist");
	}

	public static DelayCallSystemCode byCode(String code) {
		for (DelayCallSystemCode et : values()) {
			if (et.getShortCode().equals(code)) {
				return et;
			}
		}
		throw new IllegalArgumentException("system code " + code + " does not exist");
	}

	public int getId() {
		return this.id;
	}

	public String getShortCode() {
		return shortCode;
	}

	@Override
	public String toString() {
		return "DelayCallSystemCode{" + "id=" + id + ", shortCode='" + shortCode + '\'' + '}';
	}
}
