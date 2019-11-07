package com.kfir.reputation.wifi;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Wifi Network
		{
			"id" : "123456",
			"auth": <can be either 'wpa' or 'public'>,
			"avg_throughput": <a float of average bandwidth in mbit/sec >
			"devices":[{"id":"a1b2"},{"id":"c3d4"}]
		}
 *
 */
@Component
public class Wifi {
	
	private String id;
    private String auth ;
    private double avgThroughput;
    private List<Device> devices = new ArrayList<>();
    
    
//    enum Auth{
//    	wpa, Public;
//    }
    

	public Wifi(Device device) {
		this.id = device.getNetwork_id();
		this.auth = device.getAuth();
//		device.setWifi_fk(this);
		this.avgThroughput = device.getThroughput();
		this.devices.add(device);
	}


	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public String getId() 								{	return id;	}
	public void setId(String id) 						{	this.id = id;	}
	public String getAuth() 							{	return auth;	}
	public void setAuth(String auth) 					{	this.auth = auth;	}
	public double getAvgThroughput() 					{	return avgThroughput;	}
	public void setAvgThroughput(double avgThroughput) 	{	this.avgThroughput = avgThroughput;	}
	
	public void addDevice(Device device) {
		this.devices.add(device);
		avg();
	}

    public List<Device> getDevices() {
		return devices;
	}

	public void accumulateThroughput(Long throughput, String deviceId) {
		getDevices().stream()
		.filter( d -> d.getDevice_id().equals(deviceId)).findAny()
		.get().accumulateThroughput(throughput);
		avg();
	}
    
    public void avg() {
    	avgThroughput = devices.stream().mapToInt( d -> d.getThroughput() ).sum() / devices.size();
	}

        
    


	@Override
	public String toString() {
		return "Wifi:\n[ " + super.toString() + ",\n  " + (id != null ? "id=" + id + ",\n  " : "")
				+ (auth != null ? "auth=" + auth + ",\n  " : "") + "avgThroughput=" + avgThroughput + ",\n  "
				+ (devices != null ? "devices=" + devices : "") + "]";
	}
    

}
