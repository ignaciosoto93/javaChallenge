package com.marb.demo.module.demo.domain.model;

import javax.persistence.*;
import com.marb.framework.api.schema.SchemaUtils;

@Entity
@Table(name = SchemaUtils.DEMO_SCHEMA + "_demo")
public class Demo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;

	protected Demo() {
		//for hibernate
	}

	private Demo(Builder builder) {
		id = builder.id;
		name = builder.name;
	}

	public static Builder builder() {
		return new Builder();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public static final class Builder {
		private Long id;
		private String name;

		private Builder() {
		}

		public Builder withId(Long id) {
			this.id = id;
			return this;
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Demo build() {
			return new Demo(this);
		}
	}
}
