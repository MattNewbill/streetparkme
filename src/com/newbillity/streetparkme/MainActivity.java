package com.newbillity.streetparkme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.newbillity.objects.ParkingRestriction;
import com.newbillity.objects.ParkingSpot;

//map.animateCamera(CameraUpdateFactory.newCameraPosition(position));

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends Activity {
	private GoogleMap map;
	private List<ParkingSpot> parking_spots;
	private Map<Marker, ParkingSpot> allMarkersMap = new HashMap<Marker, ParkingSpot>();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupMap();
		setupMapListeners();
		setMapParkingSpots();

	}

	private void setMapParkingSpots() {
		List<ParkingSpot> parking_spots_list = MyApp.getParking_spots_list();

		if (parking_spots_list != null)
			for (int i = 0; i < parking_spots_list.size(); i++) {
				MarkerOptions marker_options = new MarkerOptions().position(
						parking_spots_list.get(i).getLatlng()).title(
						"No Parking 11:00am-1:00pm\nMonday-Friday");
				marker_options.draggable(false);
				// marker.getIcon();
				Marker marker = map.addMarker(marker_options);
				allMarkersMap.put(marker, parking_spots_list.get(i));
			}

	}

	private void setupMapListeners() {
		map.setOnMapClickListener(new OnMapClickListener() {

			@Override
			public void onMapClick(LatLng latlng) {
				LatLng click_location = latlng;
				// Create a LatLngBounds that includes Australia.
				// LatLngBounds click_location = new LatLngBounds(new
				// LatLng(-44,
				// 113), new LatLng(-10, 154));

				MarkerOptions marker_options = new MarkerOptions().position(
						click_location).title("Mark Parking Spot Here?");
				marker_options.draggable(true);
				// marker.getIcon();
				map.addMarker(marker_options);

				CameraPosition position = new CameraPosition.Builder()
						.target(click_location).zoom(10).build();
				map.animateCamera(CameraUpdateFactory
						.newCameraPosition(position));

				Class AddParkingActivity;
				try {
					AddParkingActivity = Class
							.forName("com.newbillity.streetparkme.AddParkingSpotActivity");
					Intent intent = new Intent(MainActivity.this,
							AddParkingActivity);
					ParkingSpot ps = new ParkingSpot(click_location,
							new ArrayList<ParkingRestriction>());
					MyApp.setParkingSpot(ps);
					startActivity(intent);

				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void setupMap() {
		if (map == null) {
			map = ((MapFragment) getFragmentManager()
					.findFragmentById(R.id.map)).getMap();
			map.setMyLocationEnabled(true);
			// Check if we were successful in obtaining the map.
			if (map != null) {

				map.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {

					@Override
					public void onMyLocationChange(Location location) {

						CameraPosition position = new CameraPosition.Builder()
								.target(new LatLng(location.getLatitude(),
										location.getLongitude())).zoom(15)
								.build();
						map.animateCamera(CameraUpdateFactory
								.newCameraPosition(position));
					}
				});

			}
		}
	}
}