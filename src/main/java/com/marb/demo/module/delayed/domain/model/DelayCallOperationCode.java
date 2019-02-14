package com.marb.demo.module.delayed.domain.model;

public enum DelayCallOperationCode {

	CREATED(1, "CREATED");

	private int id;
	private String shortCode;

	DelayCallOperationCode(int id, String shortCode) {
		this.id = id;
		this.shortCode = shortCode;
	}

	public static DelayCallOperationCode byId(int id) {
		for (DelayCallOperationCode et : values()) {
			if (et.getId() == id) {
				return et;
			}
		}
		throw new IllegalArgumentException("operation code " + id + " does not exist");
	}

	public static DelayCallOperationCode byCode(String code) {
		for (DelayCallOperationCode et : values()) {
			if (et.getShortCode().equals(code)) {
				return et;
			}
		}
		throw new IllegalArgumentException("operation code " + code + " does not exist");
	}

	public int getId() {
		return this.id;
	}

	public String getShortCode() {
		return shortCode;
	}

	@Override
	public String toString() {
		return "DelayCallOperationCode{" + "id=" + id + ", shortCode='" + shortCode + '\'' + '}';
	}
}
