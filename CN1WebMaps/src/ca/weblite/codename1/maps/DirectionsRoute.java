/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.weblite.codename1.maps;

import com.codename1.maps.BoundingBox;
import com.codename1.maps.Coord;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shannah
 */
public class DirectionsRoute {
    private BoundingBox bounds;
    private String copyrights;
    private List<Leg> legs = new ArrayList<Leg>();

    /**
     * @return the bounds
     */
    public BoundingBox getBounds() {
        return bounds;
    }

    /**
     * @param bounds the bounds to set
     */
    public void setBounds(BoundingBox bounds) {
        this.bounds = bounds;
    }

    /**
     * @return the copyrights
     */
    public String getCopyrights() {
        return copyrights;
    }

    /**
     * @param copyrights the copyrights to set
     */
    public void setCopyrights(String copyrights) {
        this.copyrights = copyrights;
    }

    /**
     * @return the legs
     */
    public List<Leg> getLegs() {
        return legs;
    }

    /**
     * @param legs the legs to set
     */
    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }
    
    
    public static class Path {
        private long arrivalTimeMillis, departureTimeMillis;
        
        private int distanceInMeters, durationSeconds;
        private String startAddress, endAddress;
        private Coord startLocation, endLocation;
        
        
        
    }
    
    public static class Leg extends Path {
        private List<DirectionsRequest.WayPoint> viaWayPoints = new ArrayList<DirectionsRequest.WayPoint>();
    }
    
    public static class Step {
        private List<Coord> path = new ArrayList<Coord>();
        private int travelMode;
                
        
    }
}
