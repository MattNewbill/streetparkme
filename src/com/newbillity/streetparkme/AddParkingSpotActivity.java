package com.newbillity.streetparkme;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DigitalClock;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.newbillity.objects.ParkingRestriction;

public class AddParkingSpotActivity extends Activity {
	private List<ParkingRestriction> restrictions;
	private int id_index = 0;
	private LinearLayout add_parking_view;
	private ScrollView parent;
	private LayoutInflater inflater;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		parent = (ScrollView) inflater.inflate(R.layout.scrollview,
				null);
		add_parking_view = (LinearLayout) inflater.inflate(
				R.layout.activity_add_parking_spot, null);
		parent.addView(add_parking_view);

		View custom = inflater.inflate(R.layout.single_add_parking_restriction,
				null);
		custom.setId(id_index++);
		add_parking_view.addView(custom);
		setContentView(parent);
		restrictions = new ArrayList<ParkingRestriction>();

		Button save_button = (Button)findViewById(R.id.save_restriction_button);
			save_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				for (int i = 0; i < id_index; i++) {
					ViewGroup viewgroup = (ViewGroup)AddParkingSpotActivity.this.findViewById(i);
					EditText cost_per_hour_view = (EditText)viewgroup.findViewById(R.id.cost_per_hour);
					@SuppressWarnings("deprecation")
					DigitalClock beg_time_restr_view = (DigitalClock)viewgroup.findViewById(R.id.time_restriction_from);
					@SuppressWarnings("deprecation")
					DigitalClock end_time_restr_view = (DigitalClock)viewgroup.findViewById(R.id.time_restriction_to);
					EditText hour_time_limit_view = (EditText)viewgroup.findViewById(R.id.hour_time_limit_text);
					EditText minute_time_limit_view = (EditText)viewgroup.findViewById(R.id.minute_time_limit_text);
					CheckBox permit_required_checkbox = (CheckBox)viewgroup.findViewById(R.id.permit_required_checkbox);
					CheckBox monday_checkbox = (CheckBox)viewgroup.findViewById(R.id.active_monday_checkbox);
					CheckBox tuesday_checkbox = (CheckBox)viewgroup.findViewById(R.id.active_tuesday_checkbox);
					CheckBox wednesday_checkbox = (CheckBox)viewgroup.findViewById(R.id.active_wednesday_checkbox);
					CheckBox thursday_checkbox = (CheckBox)viewgroup.findViewById(R.id.active_thursday_checkbox);
					CheckBox friday_checkbox = (CheckBox)viewgroup.findViewById(R.id.active_friday_checkbox);
					CheckBox saturday_checkbox = (CheckBox)viewgroup.findViewById(R.id.active_saturday_checkbox);
					CheckBox sunday_checkbox = (CheckBox)viewgroup.findViewById(R.id.active_sunday_checkbox);
					
					Time beg_time_restr = new Time();
					Time end_time_restr = new Time();
					
					ParkingRestriction pr = new ParkingRestriction(
							cost_per_hour_view.getText().toString(), beg_time_restr.toString(),
							end_time_restr.toString(), hour_time_limit_view.getText().toString(),
							minute_time_limit_view.getText().toString(), permit_required_checkbox.isChecked(),
							monday_checkbox.isChecked(), tuesday_checkbox.isChecked(), wednesday_checkbox.isChecked(),
							thursday_checkbox.isChecked(), friday_checkbox.isChecked(), saturday_checkbox.isChecked(),
							sunday_checkbox.isChecked());
					restrictions.add(pr);

				}
				System.out.println("Here");
				//send parking spot to DB
				//redirect back to maps page
				Class mainActivity;
				try {
					mainActivity = Class.forName("com.newbillity.streetparkme.MainActivity");
					Intent intent = new Intent(AddParkingSpotActivity.this, mainActivity);
					startActivity(intent);
				
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
			}
		});
		Button add_restriction_button = (Button)findViewById(R.id.add_restriction_button);
		add_restriction_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				View custom = inflater.inflate(R.layout.single_add_parking_restriction,
						null);
				custom.setId(id_index++);
				add_parking_view.addView(custom);
				setContentView(parent);
				
			}
		});
		

	}
}
