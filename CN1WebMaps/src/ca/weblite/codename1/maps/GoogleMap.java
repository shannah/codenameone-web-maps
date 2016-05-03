/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.weblite.codename1.maps;


import com.codename1.components.WebBrowser;
import com.codename1.javascript.JSFunction;
import com.codename1.javascript.JSObject;
import com.codename1.javascript.JavascriptContext;
import com.codename1.maps.BoundingBox;
import com.codename1.maps.Coord;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BorderLayout;
import java.util.Iterator;

/**
 *
 * @author shannah
 */
public class GoogleMap extends Container {
    private BrowserComponent browser;
    private JavascriptContext c;
    private JSObject map, directionsService, directionsDisplay;
    public GoogleMap(){
        this.setLayout(new BorderLayout());
        WebBrowser b = new WebBrowser(){

            @Override
            public void onLoad(String url) {
                System.out.println("Loaded "+url);
                map = (JSObject)c.get("map");
                directionsService = (JSObject)c.get("directionsService");
                directionsDisplay = (JSObject)c.get("directionsDisplay");
                
                GoogleMap.this.onLoad();
            }
            
        };
        browser = (BrowserComponent)b.getInternal();
        c = new JavascriptContext(browser);
        b.setURL("jar:///ca.weblite.codename1.maps.html");
        this.addComponent(BorderLayout.CENTER, b);
    }
    
    protected void onLoad(){
        
    }
    
    private Coord getCoord(JSObject latLng){
        Double lat = (Double)latLng.call("lat", new Object[]{});
        Double lng = (Double)latLng.call("lng", new Object[]{});
        return new Coord(lat.doubleValue(), lng.doubleValue());
    }
    
    private BoundingBox getBounds(JSObject latLngBounds){
        JSObject sw = (JSObject)latLngBounds.call("getSouthWest", new Object[]{});
        JSObject ne = (JSObject)latLngBounds.call("getNorthEast", new Object[]{});
        Coord sw1 = getCoord(sw);
        Coord ne1 = getCoord(ne);
        return new BoundingBox(sw1, ne1);
        
    }
    
    private JSObject getLatLng(Coord coord){
        return (JSObject)c.get("new google.maps.LatLng("+coord.getLatitude()+","+coord.getLongitude()+")");
    }
    
    private JSObject getLatLngBounds(BoundingBox box){
        JSObject sw = getLatLng(box.getSouthWest());
        JSObject ne = getLatLng(box.getNorthEast());
        return (JSObject)c.get("new google.maps.LatLngBounds("+sw.toJSPointer()+","+ne.toJSPointer()+", true");
    }
    
    public void fitMapBounds(BoundingBox bounds){
        map.call("fitBounds", new Object[]{getLatLngBounds(bounds)});
    }
    
    public BoundingBox getMapBounds(){
        return getBounds(map.callObject("getBounds"));
    }
    
    public Coord getCenter(){
        return getCoord(map.callObject("getCenter"));
    }
    
    public double getHeading(){
        return map.callDouble("getHeading");
    }
    
    public void setHeading(double heading){
        map.call("setHeading", new Object[]{new Double(heading)});
    }
    
    public double getZoom(){
        return map.callDouble("getZoom");
    }
    
    public void setZoom(double zoom){
        map.call("setZoom", new Object[]{new Double(zoom)});
    }
    
    
    
    
    
    
    
    private JSObject jsWayPoint(DirectionsRequest.WayPoint pt){
        JSObject jpt = (JSObject)c.get("{}");
        if ( pt.getLocationCoord() != null ){
            jpt.set("location", getLatLng(pt.getLocationCoord()));
        } else {
            jpt.set("location", pt.getLocationName());
        }
        jpt.setBoolean("stopover", pt.isStopover());
        return jpt;
    }
    
    private JSObject jsDirectionsRequest(DirectionsRequest req){
        System.out.println("here");
        JSObject jreq = (JSObject)c.get("{}");
        jreq.set("avoidHighways", req.isAvoidHighways());
        jreq.set("avoidTolls", req.isAvoidTolls());
        if ( req.getDestinationCoord() != null ){
            jreq.set("destination", getLatLng(req.getDestinationCoord()));
        } else {
            jreq.set("destination", req.getDestinationName());
        }
        System.out.println("here2");
        if ( req.getOriginCoord() != null ){
            jreq.set("origin", getLatLng(req.getOriginCoord()));
        } else {
            jreq.set("origin", req.getOriginName());
        }
        
        jreq.set("provideRouteAlternatives", req.isProvideRouteAlternatives());
        if ( req.getRegion() != null ){
            jreq.set("region", req.getRegion());
        }
        
        if ( req.getTransitArrivalTime() != null && req.getTransitDepartureTime() != null ){
            JSObject atime = (JSObject)c.get("new Date("+req.getTransitArrivalTime().getTime()+")");
            JSObject dtime = (JSObject)c.get("new Date("+req.getTransitDepartureTime().getTime()+")");
            JSObject transitOptions = (JSObject)c.get("{}");
            transitOptions.set("arrivalTime", atime);
            transitOptions.set("departureTime", dtime);
            jreq.set("transitOptions",transitOptions);
        }
        if( req.getTravelMode() != null){
            jreq.set("travelMode",req.getTravelMode());
        }
        if ( req.getUnitSystem() != null ){
            jreq.set("unitSystem", req.getUnitSystem());
        }
        System.out.println("here3");
        int numWayPoints = req.getWayPoints().size();
        if ( numWayPoints > 0 ){
            
            JSObject jWaypoints = (JSObject)c.get("[]");
            Iterator<DirectionsRequest.WayPoint> waypoints = req.getWayPoints().iterator();

            while (waypoints.hasNext() ){
                DirectionsRequest.WayPoint pt = waypoints.next();
                jWaypoints.call("push", new Object[]{ jsWayPoint(pt)});

            }
            
            jreq.set("waypoints", jWaypoints);
        }
        System.out.println("here4");
        return jreq;
        
    }
    
    
    private DirectionsResult fromJSDirectionsResult(JSObject jres){
        JSObject routes = jres.getObject("routes");
        int len = routes.getInt("length");
        DirectionsResult res = new DirectionsResult();
        for ( int i=0; i<len; i++){
            res.getRoutes().add(fromJSDirectionsRoute(routes.getObject(i)));
            
        }
        return res;
    }
    
    private DirectionsRoute fromJSDirectionsRoute(JSObject jrte){
        
        DirectionsRoute rte = new DirectionsRoute();
        rte.setBounds(getBounds(jrte.getObject("bounds")));
        rte.setCopyrights(jrte.getString("copyrights"));
        
        JSObject legs = jrte.getObject("legs");
        int len = legs.getInt("length");
        // TODO Complete the conversion...
        
        return rte;
    }
    public void route(DirectionsRequest request, DirectionsRouteListener resultListener){
        
        System.out.println("DirectionsService is "+directionsService.toJSPointer());
        
        directionsService.call("route", new Object[]{ jsDirectionsRequest(request), new JSFunction(){

                public void apply(JSObject self, Object[] args) {
                    
                    System.out.println("In response");
                    JSObject result = (JSObject)args[0];
                    String status = (String)args[1];
                    
                   //String statusStr = (String)c.get(status.toJSPointer()+"+''");
                    System.out.println("Status is "+status);
                    if ( DirectionsResult.STATUS_OK.equals(status)){
                        directionsDisplay.call("setDirections", new Object[]{result});
                    }
                    
                }

            }
            
        });
        
        //System.out.println((String)c.get("typeof("+directionsService.toJSPointer()+")"));
        //System.out.println((String)c.get("typeof("+directionsService.toJSPointer()+"."+JSObject.ID_KEY+")"));
        //directionsService.call("route", new Object[]{ c.get("{origin:'Langley,BC','destination':'Surrey,BC'}"), c.get("function(){}")});
            
        
        
        
    }
}
