package com.seidor.comerzzia.commons.api.v1.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommonsModel {
	
	private String protocol;
	
	private LocalDateTime dateTimeRequest;

}
