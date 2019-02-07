package com.marb.demo.module.delayed.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import com.marb.demo.module.delayed.domain.api.dto.DelayedCallRequestDto;

@Entity
@Table(name = "delayed_call")
public class DelayedCall {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private DelayedCallStatus status;
	@CreationTimestamp
	@Column(name = "created_on")
	private Date createdOn;
	@Column(name = "retries")
	private Integer retries = 0;
	@Column(name = "system_code")
	private String systemCode;
	@Column(name = "operation_code")
	private String operationCode;
	@Column(name = "payload")
	private String payload;
	@Column(name = "entity_type")
	private EntityType mainEntityType;
	@Column(name = "entity_id")
	private Long mainEntityId;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "delayed_call_id", nullable = false)
	private List<DelayedCallLog> delayedCallLogs = new ArrayList<>();

	public DelayedCall() {
	}

	public Long getId() {
		return id;
	}

	public DelayedCallStatus getStatus() {
		return status;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public int getRetries() {
		return retries;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public String getOperationCode() {
		return operationCode;
	}

	public String getPayload() {
		return payload;
	}

	public EntityType getMainEntityType() {
		return mainEntityType;
	}

	public long getMainEntityId() {
		return mainEntityId;
	}

	public List<DelayedCallLog> getDelayedCallLogs() {
		return delayedCallLogs;
	}

	private DelayedCall(Builder builder) {
		id = builder.id;
		status = builder.status;
		createdOn = builder.createdOn;
		retries = builder.retries;
		systemCode = builder.systemCode;
		operationCode = builder.operationCode;
		payload = builder.payload;
		mainEntityType = builder.mainEntityType;
		mainEntityId = builder.mainEntityId;
		delayedCallLogs = builder.delayedCallLogs;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private Long id;
		private DelayedCallStatus status;
		private Date createdOn;
		private Integer retries = 0;
		private String systemCode;
		private String operationCode;
		private String payload;
		private EntityType mainEntityType;
		private Long mainEntityId;
		private List<DelayedCallLog> delayedCallLogs = new ArrayList<>();

		private Builder() {
		}

		public Builder withId(Long id) {
			this.id = id;
			return this;
		}

		public Builder withDelayedCallLogs(List<DelayedCallLog> delayedCallLogs) {
			this.delayedCallLogs = delayedCallLogs;
			return this;
		}

		public Builder withStatus(DelayedCallStatus status) {
			this.status = status;
			return this;
		}

		public Builder withCreatedOn(Date createdOn) {
			this.createdOn = createdOn;
			return this;
		}

		public Builder withSystemCode(String systemCode) {
			this.systemCode = systemCode;
			return this;
		}

		public Builder withOperationCode(String operationCode) {
			this.operationCode = operationCode;
			return this;
		}

		public Builder withPayload(String payload) {
			this.payload = payload;
			return this;
		}

		public Builder withMainEntityType(EntityType mainEntityType) {
			this.mainEntityType = mainEntityType;
			return this;
		}

		public Builder withMainEntityId(Long mainEntityId) {
			this.mainEntityId = mainEntityId;
			return this;
		}

		public Builder addRetryAttempt() {
			this.retries++;
			return this;
		}

		public Builder addDelayedCallLog(DelayedCallLog delayedCallLog) {
			if (this.delayedCallLogs == null){
				this.delayedCallLogs = new ArrayList<>();
			}
			this.delayedCallLogs.add(delayedCallLog);
			return this;
		}

		public Builder fromDelayedCall(DelayedCall delayedCall) {
			this.delayedCallLogs = delayedCall.getDelayedCallLogs();
			this.id = delayedCall.getId();
			this.createdOn = delayedCall.getCreatedOn();
			this.status = delayedCall.getStatus();
			this.systemCode = delayedCall.getSystemCode();
			this.operationCode = delayedCall.getOperationCode();
			this.payload = delayedCall.getPayload();
			this.mainEntityType = delayedCall.getMainEntityType();
			this.mainEntityId = delayedCall.getMainEntityId();
			this.retries = delayedCall.getRetries();
			return this;
		}

		public Builder fromDelayedCallRequestDto(DelayedCallRequestDto delayedCallRequestDto) {
			this.createdOn = new Date();
			this.status = DelayedCallStatus.PENDING;
			this.systemCode = delayedCallRequestDto.getSystemCode();
			this.operationCode = delayedCallRequestDto.getOperationCode();
			this.payload = delayedCallRequestDto.getPayload();
			this.mainEntityType = delayedCallRequestDto.getMainEntityType();
			this.mainEntityId = delayedCallRequestDto.getMainEntityId();
			return this;
		}

		public DelayedCall build() {
			return new DelayedCall(this);
		}
	}

	@Override
	public String toString() {
		return "DelayedCall{" + "id=" + id + ", status=" + status + ", createdOn=" + createdOn + ", retries=" + retries
				+ ", systemCode='" + systemCode + '\'' + ", operationCode='" + operationCode + '\'' + ", payload='"
				+ payload + '\'' + ", mainEntityType=" + mainEntityType + ", mainEntityId=" + mainEntityId
				+ ", delayedCallLogs=" + delayedCallLogs + '}';
	}
}
