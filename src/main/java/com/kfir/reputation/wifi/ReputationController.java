package com.kfir.reputation.wifi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ReputationController {

        
    private static final Map<String, Wifi> networksRepository = new HashMap<>();
    //private static final Map<String, Device> devicesRepository = new HashMap<>();
    
    static {
    	final String firstNetworkId = "Net1";
    	final String scndNetworkId = "Net2";
    	
		networksRepository.put(firstNetworkId, new Wifi(new Device("d1", firstNetworkId, "wpa" )));
		
    	networksRepository.get(firstNetworkId).addDevice(new Device("d2", firstNetworkId, "wpa" ));
    	networksRepository.get(firstNetworkId).addDevice(new Device("d3", firstNetworkId, "Pubflic" ));
    	networksRepository.get(firstNetworkId).addDevice(new Device("d4", firstNetworkId, "Publddsic" ));
    	
		networksRepository.put(scndNetworkId, new Wifi(new Device("d5", scndNetworkId, "wpa" )));
		
    	networksRepository.get(scndNetworkId).addDevice(new Device("d6", firstNetworkId, "wpa" ));
    	networksRepository.get(scndNetworkId).addDevice(new Device("d7", firstNetworkId, "Pubflic" ));
    	networksRepository.get(scndNetworkId).addDevice(new Device("d8", firstNetworkId, "Publddsic" ));
    }
    
    
    // Save or update
    /**
     * 	Request: 
	PUT http://my-service/api/network/connect
	{
		"device_id": "a1b2",
		"network_id": "123456",
		"auth" : "wpa"
	}
     */
    @PutMapping("api/network/connect")
    void saveOrUpdate(@RequestBody Device device) {

    	final String networkId = device.getNetwork_id();
    	Wifi network = networksRepository.get(networkId);

    	if(network == null) {
    		networksRepository.put( networkId,  new Wifi(device));
    	}
    	else {
    		network.addDevice(device);
    	}
    	
    }

    //get
    @GetMapping("api/network")
    Wifi get(@RequestParam String id) {
    	return networksRepository.get(id);
    }
    //getAll    
    @GetMapping("api/network/")
    List<Wifi> getAll() {
    	return new ArrayList<>(networksRepository.values());
    }
    
    
    @PostMapping("api/network/report")
    void newBook(@RequestBody Report report) {
        networksRepository.get(report.getNetwork_id())
        		.accumulateThroughput(report.getThroughput(), report.getDevice_id());

    }

}
