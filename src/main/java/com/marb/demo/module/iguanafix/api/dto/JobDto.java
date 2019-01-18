package com.marb.demo.module.iguanafix.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.marb.framework.api.dto.Dto;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobDto extends Dto {

	@JsonProperty
	private Long id;
	@JsonProperty
	private String title;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
