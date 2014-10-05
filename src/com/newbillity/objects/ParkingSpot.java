package com.newbillity.objects;

import java.io.Serializable;
import java.util.List;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

@SuppressWarnings("serial")
public class ParkingSpot implements Serializable {
	private LatLng latlng;
	public List<ParkingRestriction> restrictions;
	private int rating;

	public ParkingSpot(LatLng latlng, List<ParkingRestriction> restrictions) {
		this.setLatlng(latlng);
		if (!restrictions.isEmpty())
			this.restrictions.addAll(restrictions);
		this.setRating(0);
	}

	public LatLng getLatlng() {
		return latlng;
	}

	public void setLatlng(LatLng latlng) {
		this.latlng = latlng;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

}
