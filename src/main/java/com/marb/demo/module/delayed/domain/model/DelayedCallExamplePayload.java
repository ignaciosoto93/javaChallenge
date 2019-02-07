package com.marb.demo.module.delayed.domain.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = DelayedCallExamplePayload.Builder.class)
public class DelayedCallExamplePayload {
	private String name;
	private String value;

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	private DelayedCallExamplePayload(Builder builder) {
		name = builder.name;
		value = builder.value;
	}

	public static Builder builder() {
		return new Builder();
	}

	@JsonPOJOBuilder
	public static final class Builder {
		private String name;
		private String value;

		private Builder() {
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withValue(String value) {
			this.value = value;
			return this;
		}

		public DelayedCallExamplePayload build() {
			return new DelayedCallExamplePayload(this);
		}
	}

	@Override
	public String toString() {
		return "DelayedCallExamplePayload{" + "name='" + name + '\'' + ", value='" + value + '\'' + '}';
	}
}
