package com.kfir.reputation.wifi;


import org.springframework.stereotype.Component;


/**
 * 			
 * "device_id": "a1b2",
			"network_id": "123456",
			"auth" : "wpa"
 *
 */
@Component
public class Device {

	private String device_id;
	private String network_id;
	//private Wifi wifi_fk;
	private String auth;
	private int throughput = 0;
	
	
	public Device() {	}

	public Device(String deviceId, String networkId, String auth) {
		this.device_id = deviceId;
		this.network_id = networkId;
		this.auth = ("wpa".equals(auth)) ? "wpa" : "public";
	}
	

	public String getDevice_id() 				{	return device_id;	}	
	public void setDevice_id(String device_id) 	{	this.device_id = device_id;	}	
	public String getNetwork_id() 				{	return network_id;	}	
	public void setNetwork_id(String network_id){	this.network_id = network_id;	}	
	public String getAuth() 					{	return auth;	}	
	public void setAuth(String auth) 			{	this.auth = auth;	}		
	public int getThroughput() 					{	return throughput;	}
	
	public void accumulateThroughput(Long throughput) {
		this.throughput += throughput;
	}
	
	@Override
	public String toString() {
		return "Device:\n[ " + super.toString() + ",\n  "
				+ (device_id != null ? "device_id=" + device_id + ",\n  " : "")
				+ (network_id != null ? "network_id=" + network_id + ",\n  " : "")
				+ (auth != null ? "auth=" + auth : "") + "]";
	}

	
}
