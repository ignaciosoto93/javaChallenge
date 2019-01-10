package com.marb.demo.module.demo.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.marb.framework.api.dto.Dto;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DemoDto extends Dto {

	@JsonProperty
	private Long id;
	@JsonProperty
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
