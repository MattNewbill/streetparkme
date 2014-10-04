package com.newbillity.streetparkme;

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
import com.google.android.gms.maps.model.MarkerOptions;
import com.newbillity.objects.ParkingSpot;

//map.animateCamera(CameraUpdateFactory.newCameraPosition(position));

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends Activity {
	private GoogleMap map;
	private ParkingSpot[] parking_spots;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupMap();

		map.setOnMapClickListener(new OnMapClickListener() {

			@Override
			public void onMapClick(LatLng latlng) {
				LatLng click_location = latlng;
				// Create a LatLngBounds that includes Australia.
				//LatLngBounds click_location = new LatLngBounds(new LatLng(-44,
				//		113), new LatLng(-10, 154));

				
				MarkerOptions marker_options = new MarkerOptions().position(
						click_location).title("Mark Parking Spot Here?");
				marker_options.draggable(true);
				//marker.getIcon();
				map.addMarker(marker_options);
				
				CameraPosition position = new CameraPosition.Builder()
						.target(click_location).zoom(10).build();
				map.animateCamera(CameraUpdateFactory
						.newCameraPosition(position));
				
				Class sensorToBeLaunched;
				try {
					sensorToBeLaunched = Class.forName("com.newbillity.streetparkme.AddParkingSpotActivity");
					Intent intent = new Intent(MainActivity.this, sensorToBeLaunched);
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