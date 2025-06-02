package com.seidor.comerzzia.commons.domain.service;

import org.springframework.scheduling.annotation.Scheduled;

import com.seidor.comerzzia.commons.api.v1.model.CommonsModel;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

public interface ScheduleProcessService {
	
	/*
	 ┌───────────── segundo (0-59)
	 │ ┌───────────── minuto (0 - 59)
	 │ │ ┌───────────── hora (0 - 23)
	 │ │ │ ┌───────────── dia do mês (1 - 31)
	 │ │ │ │ ┌───────────── mês (1 - 12) (ou JAN-DEZ)
	 │ │ │ │ │ ┌───────────── dia da semana (0 - 7)
	 │ │ │ │ │ │ (0 ou 7 é domingo, ou SEG-DOM)
	 │ │ │ │ │ │
	 * * * * * *
	*/
	@Scheduled(cron = "${CONFIG_SCHEDULE}", zone = "${CONFIG_ZONE_SCHEDULE}")
	@SchedulerLock(name = "TaskScheduler_salesIntegration", lockAtLeastFor = "PT2M", lockAtMostFor = "PT4M")
	public CommonsModel startProcess();

}
