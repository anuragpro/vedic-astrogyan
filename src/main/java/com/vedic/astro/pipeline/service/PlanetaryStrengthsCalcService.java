package com.vedic.astro.pipeline.service;

import org.springframework.scheduling.annotation.Async;

import com.vedic.astro.pipeline.msg.BirthChartMsg;

public class PlanetaryStrengthsCalcService {
	
	@Async
	public BirthChartMsg calculateStrengths(BirthChartMsg msg){
		
		System.out.println("Inside PlanetaryStrengthsCalcService: " + msg);
		
		return new BirthChartMsg("123", "Router test");
		
	}

}
