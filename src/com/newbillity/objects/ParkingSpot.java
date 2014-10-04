package com.newbillity.objects;

import java.util.List;

import com.google.android.gms.maps.model.LatLng;

public class ParkingSpot {
	private LatLng latlng;
	private List<ParkingRestriction> restrictions;
	private int rating;
	
	public ParkingSpot (LatLng latlng, List<ParkingRestriction> restrictions) {
		this.latlng = latlng;
		this.restrictions.addAll(restrictions);
		this.rating = 0;
	}
	

}
