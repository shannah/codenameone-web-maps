/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.weblite.codename1.maps;

import java.util.List;

/**
 *
 * @author shannah
 */
public class DirectionsResult {
    
    public static final String STATUS_INVALID_REQUEST = "INVALID_REQUEST";
    public static final String STATUS_MAX_WAYPOINTS_EXCEEDED = "MAX_WAYPOINTS_EXCEEDED";
    public static final String STATUS_NOT_FOUND = "NOT_FOUND";
    public static final String STATUS_OK = "OK";
    public static final String STATUS_OVER_QUERY_LIMIT="OVER_QUERY_LIMIT";
    public static final String STATUS_REQUEST_DENIED = "REQUEST_DENIED";
    public static final String STATUS_UNKNOWN_ERROR = "UNKNOWN_ERROR";
    public static final String STATUS_ZERO_RESULTS = "ZERO_RESULTS";
    
    private List<DirectionsRoute> routes;
    private String status;

    /**
     * @return the routes
     */
    public List<DirectionsRoute> getRoutes() {
        return routes;
    }

    /**
     * @param routes the routes to set
     */
    public void setRoutes(List<DirectionsRoute> routes) {
        this.routes = routes;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
}
