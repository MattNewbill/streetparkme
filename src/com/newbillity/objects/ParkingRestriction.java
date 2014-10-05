package com.newbillity.objects;

import java.io.Serializable;

import android.R.bool;
import android.text.format.Time;

@SuppressWarnings("serial")
public class ParkingRestriction implements Serializable{
	public final static int NOPARKING = 0;
	public final static int TIMEDPARKING = 1;
	public final static int UNRESTRICTED = 2;
	private int parking_type;
	private int id;
	private double cost_per_hour;
	private Time beg_time_restriction;
	private Time end_time_restriction;
	private int hour_time_restriction;
	private int minute_time_restriction;
	private boolean permit_required;
	private boolean active_monday;
	private boolean active_tuesday;
	private boolean active_wednesday;
	private boolean active_thursday;
	private boolean active_friday;
	private boolean active_saturday;
	private boolean active_sunday;
	
	public ParkingRestriction (String cost_per_hour, String beg_time_restriction, String end_time_restriction,
			String hour_time_restriction, String minute_time_restriction, boolean permit_required, boolean active_monday, boolean active_tuesday,
			boolean active_wednesday, boolean active_thursday, boolean active_friday, boolean active_saturday, boolean active_sunday){
		if(cost_per_hour.equals(""))
			this.cost_per_hour = 0;
		else
			this.cost_per_hour = Double.parseDouble(cost_per_hour);
		
		this.beg_time_restriction = new Time(beg_time_restriction);
		this.end_time_restriction = new Time(end_time_restriction);
		
		if(hour_time_restriction.equals(""))
			this.hour_time_restriction = -1;
		else
			this.hour_time_restriction = Integer.parseInt(hour_time_restriction);
		
		if(minute_time_restriction.equals(""))
			this.minute_time_restriction = -1;
		else 
			this.minute_time_restriction = Integer.parseInt(minute_time_restriction);
		
		this.permit_required = permit_required;
		this.active_monday = active_monday;
		this.active_tuesday = active_tuesday;
		this.active_wednesday = active_wednesday;
		this.active_thursday = active_thursday;
		this.active_friday = active_friday;
		this.active_saturday = active_saturday;
		this.active_sunday = active_sunday;
	}

}
