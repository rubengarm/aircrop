package com.aircrop.backend.response;

public class SectorResponseRest extends ResponseRest{
	
	private SectorResponse sectorResponse = new SectorResponse();
	
	public SectorResponse getSectorResponse() {
		return sectorResponse;
	}
	
	public void setSectorResponse(SectorResponse sectorResponse) {
		this.sectorResponse=sectorResponse;
	}

}
