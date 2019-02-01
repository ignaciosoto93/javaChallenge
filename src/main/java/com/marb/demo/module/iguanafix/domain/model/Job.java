package com.marb.demo.module.iguanafix.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "job")
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String title;

	protected Job() {
		//for hibernate
	}

	private Job(Builder builder) {
		id = builder.id;
		title = builder.title;
	}

	public static Builder builder() {
		return new Builder();
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public static final class Builder {
		private Long id;
		private String title;

		private Builder() {
		}

		public Builder withId(Long id) {
			this.id = id;
			return this;
		}

		public Builder withTitle(String title) {
			this.title = title;
			return this;
		}

		public Job build() {
			return new Job(this);
		}
	}
}
