package com.vedic.astro.domain;

import com.vedic.astro.enums.NakDashaSystem;


public class DashaPeriodSnapshot {
	
	private String pid;
	private NakDashaSystem dashaSystem;
	private DashaTimePeriod dashaPeriod = null;
	private DashaCombination dashaCombination = null;
	private Long seq = null;

	
	public DashaPeriodSnapshot() {}
	
	public DashaPeriodSnapshot(String pid, Long seq, NakDashaSystem dashaSystem,
			DashaTimePeriod dashaPeriod, DashaCombination dashaCombination) {
		super();
		this.pid = pid;
		this.dashaSystem = dashaSystem;
		this.dashaPeriod = dashaPeriod;
		this.dashaCombination = dashaCombination;
		this.seq = seq;
	}

	public DashaTimePeriod getDashaPeriod() {
		return dashaPeriod;
	}

	public void setDashaPeriod(DashaTimePeriod dashaPeriod) {
		this.dashaPeriod = dashaPeriod;
	}

	public DashaCombination getDashaCombination() {
		return dashaCombination;
	}

	public void setDashaCombination(DashaCombination dashaCombination) {
		this.dashaCombination = dashaCombination;
	}
	
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public NakDashaSystem getDashaSystem() {
		return dashaSystem;
	}

	public void setDashaSystem(NakDashaSystem dashaSystem) {
		this.dashaSystem = dashaSystem;
	}
	
	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	@Override
	public String toString() {
		return "DashaPeriodSnapshot [pid=" + pid + ", dashaSystem="
				+ dashaSystem + ", dashaPeriod=" + dashaPeriod
				+ ", dashaCombination=" + dashaCombination + ", seq=" + seq
				+ "]";
	}
}
