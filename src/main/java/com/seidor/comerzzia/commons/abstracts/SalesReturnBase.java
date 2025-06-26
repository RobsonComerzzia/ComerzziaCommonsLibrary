package com.seidor.comerzzia.commons.abstracts;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class SalesReturnBase extends XmlBase {

	protected static final String AWS_PREFIX = "AWS";
	protected static final String GOOGLE_PREFIX = "GOOGLE";
	protected static final String FISCAL_PREFIX = "FISCAL";
	protected static final String STANDARD_PREFIX = "STANDARD";
	protected static final String B1_PREFIX = "B1";
	
}
