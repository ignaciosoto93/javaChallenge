package com.marb.demo.module.delayed.domain.model;

import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "delayed_call_log")
public class DelayedCallLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@CreationTimestamp
	@Column(name = "created_on")
	private Date createdOn;
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private DelayedCallStatus status;
	@Column(name = "description")
	private String description;

	public DelayedCallLog() {
	}

	public Long getId() {
		return id;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public DelayedCallStatus getStatus() {
		return status;
	}

	public String getDescription() {
		return description;
	}

	private DelayedCallLog(Builder builder) {
		id = builder.id;
		createdOn = builder.createdOn;
		status = builder.status;
		description = builder.description;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private Long id;
		private Date createdOn;
		private DelayedCallStatus status;
		private String description;

		private Builder() {
		}

		public Builder withId(Long id) {
			this.id = id;
			return this;
		}

		public Builder withCreatedOn(Date createdOn) {
			this.createdOn = createdOn;
			return this;
		}

		public Builder withStatus(DelayedCallStatus status) {
			this.status = status;
			return this;
		}

		public Builder withDescription(String description) {
			this.description = description;
			return this;
		}

		public DelayedCallLog build() {
			return new DelayedCallLog(this);
		}
	}

	@Override
	public String toString() {
		return "DelayedCallLog{" + "id=" + id + ", createdOn=" + createdOn + ", status=" + status + ", description='"
				+ description + '\'' + '}';
	}
}
