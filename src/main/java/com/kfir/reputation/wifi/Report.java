package com.kfir.reputation.wifi;

public class Report {
	
	private String network_id;
    private String device_id ;
    private long throughput;

	public String getNetwork_id() 				{	return network_id;	}
	public void setNetwork_id(String network_id){	this.network_id = network_id;	}
	public String getDevice_id() 				{	return device_id;	}
	public void setDevice_id(String device_id) 	{	this.device_id = device_id;	}
	public long getThroughput() 				{	return throughput;	}
	public void setThroughput(long throughput) 	{	this.throughput = throughput;	}

	@Override
	public String toString() {
		return "Report:\n[ " + super.toString() + ",\n  "
				+ (network_id != null ? "network_id=" + network_id + ",\n  " : "")
				+ (device_id != null ? "device_id=" + device_id + ",\n  " : "") + "throughput=" + throughput + "]";
	}
    
    
}
