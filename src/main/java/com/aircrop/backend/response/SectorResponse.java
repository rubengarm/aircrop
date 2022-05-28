package com.aircrop.backend.response;

import java.util.List;

import com.aircrop.backend.model.Sector;

public class SectorResponse {
	
	private List<Sector> sector;
	
	public List<Sector> getSector(){
		return sector;
	}
	
	public void setSector(List<Sector> sector) {
		this.sector=sector;
	}

}
