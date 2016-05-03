/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.weblite.codename1.maps;

import com.codename1.maps.Coord;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author shannah
 */
public class DirectionsRequest {
    public static final String TRAVEL_MODE_BICYCLING = "BICYCLING";
    public static final String TRAVEL_MODE_DRIVING = "DRIVING";
    public static final String TRAVEL_MODE_TRANSIT = "TRANSIT";
    public static final String TRAVEL_MODE_WALKING = "WALKING";
    
    public static final String UNIT_SYSTEM_IMPERIAL="IMPERIAL";
    public static final String UNIT_SYSTEM_METRIC="IMPERIAL";
    
    private boolean avoidHighways, avoidTolls, optimizeWayPoints, provideRouteAlternatives;
    private Coord destinationCoord, originCoord;
    private String destinationName, originName, region;
    private String travelMode, unitSystem;
    private Date transitArrivalTime, transitDepartureTime;
    
    private List<WayPoint> wayPoints = new ArrayList<WayPoint>();

    /**
     * @return the avoidHighways
     */
    public boolean isAvoidHighways() {
        return avoidHighways;
    }

    /**
     * @param avoidHighways the avoidHighways to set
     */
    public void setAvoidHighways(boolean avoidHighways) {
        this.avoidHighways = avoidHighways;
    }

    /**
     * @return the avoidTolls
     */
    public boolean isAvoidTolls() {
        return avoidTolls;
    }

    /**
     * @param avoidTolls the avoidTolls to set
     */
    public void setAvoidTolls(boolean avoidTolls) {
        this.avoidTolls = avoidTolls;
    }

    /**
     * @return the optimizeWayPoints
     */
    public boolean isOptimizeWayPoints() {
        return optimizeWayPoints;
    }

    /**
     * @param optimizeWayPoints the optimizeWayPoints to set
     */
    public void setOptimizeWayPoints(boolean optimizeWayPoints) {
        this.optimizeWayPoints = optimizeWayPoints;
    }

    /**
     * @return the provideRouteAlternatives
     */
    public boolean isProvideRouteAlternatives() {
        return provideRouteAlternatives;
    }

    /**
     * @param provideRouteAlternatives the provideRouteAlternatives to set
     */
    public void setProvideRouteAlternatives(boolean provideRouteAlternatives) {
        this.provideRouteAlternatives = provideRouteAlternatives;
    }

    /**
     * @return the destinationCoord
     */
    public Coord getDestinationCoord() {
        return destinationCoord;
    }

    /**
     * @param destinationCoord the destinationCoord to set
     */
    public void setDestinationCoord(Coord destinationCoord) {
        this.destinationCoord = destinationCoord;
    }

    /**
     * @return the originCoord
     */
    public Coord getOriginCoord() {
        return originCoord;
    }

    /**
     * @param originCoord the originCoord to set
     */
    public void setOriginCoord(Coord originCoord) {
        this.originCoord = originCoord;
    }

    /**
     * @return the destinationName
     */
    public String getDestinationName() {
        return destinationName;
    }

    /**
     * @param destinationName the destinationName to set
     */
    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    /**
     * @return the originName
     */
    public String getOriginName() {
        return originName;
    }

    /**
     * @param originName the originName to set
     */
    public void setOriginName(String originName) {
        this.originName = originName;
    }

    /**
     * @return the region
     */
    public String getRegion() {
        return region;
    }

    /**
     * @param region the region to set
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * @return the travelMode
     */
    public String getTravelMode() {
        return travelMode;
    }

    /**
     * @param travelMode the travelMode to set
     */
    public void setTravelMode(String travelMode) {
        this.travelMode = travelMode;
    }

    /**
     * @return the unitSystem
     */
    public String getUnitSystem() {
        return unitSystem;
    }

    /**
     * @param unitSystem the unitSystem to set
     */
    public void setUnitSystem(String unitSystem) {
        this.unitSystem = unitSystem;
    }

    /**
     * @return the transitArrivalTime
     */
    public Date getTransitArrivalTime() {
        return transitArrivalTime;
    }

    /**
     * @param transitArrivalTime the transitArrivalTime to set
     */
    public void setTransitArrivalTime(Date transitArrivalTime) {
        this.transitArrivalTime = transitArrivalTime;
    }

    /**
     * @return the transitDepartureTime
     */
    public Date getTransitDepartureTime() {
        return transitDepartureTime;
    }

    /**
     * @param transitDepartureTime the transitDepartureTime to set
     */
    public void setTransitDepartureTime(Date transitDepartureTime) {
        this.transitDepartureTime = transitDepartureTime;
    }
    
    public List<WayPoint> getWayPoints(){
        return wayPoints;
    }
    
    public static class WayPoint {
        private Coord locationCoord;
        private String locationName;
        private boolean stopover = true;

        /**
         * @return the location
         */
        public Coord getLocationCoord() {
            return locationCoord;
        }

        /**
         * @param location the location to set
         */
        public void setLocationCoord(Coord location) {
            this.locationCoord = location;
        }
        
        public String getLocationName(){
            return locationName;
        }
        
        public void setLocationName(String name){
            this.locationName = name;
        }

        /**
         * @return the stopover
         */
        public boolean isStopover() {
            return stopover;
        }

        /**
         * @param stopover the stopover to set
         */
        public void setStopover(boolean stopover) {
            this.stopover = stopover;
        }
    }
    
    
}
