package com.seidor.comerzzia.commons.api.v1.model;

import org.json.JSONObject;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class XmlModel {
	
	private String contentString;
	
	private JSONObject jsonObect;

}
