package com.seidor.comerzzia.commons.api.v1.controller;

import org.springframework.web.bind.annotation.PathVariable;

public abstract class CreditLimitCommonsController {

	public abstract <T> T getCreditLimitBalance(@PathVariable String cardCode);
}
