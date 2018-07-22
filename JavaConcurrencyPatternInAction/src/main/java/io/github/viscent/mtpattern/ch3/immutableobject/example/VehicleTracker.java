package io.github.viscent.mtpattern.ch3.immutableobject.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class VehicleTracker {

    private Map<String,Location> locMap=new ConcurrentHashMap<String,Location>();

    public void updateLocation(String vehichlId,Location newLocation){
        locMap.put(vehichlId,newLocation);
    }

}
