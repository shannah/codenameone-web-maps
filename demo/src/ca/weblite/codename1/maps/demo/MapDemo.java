package ca.weblite.codename1.maps.demo;


import ca.weblite.codename1.maps.DirectionsRequest;
import ca.weblite.codename1.maps.DirectionsResult;
import ca.weblite.codename1.maps.DirectionsRouteListener;
import ca.weblite.codename1.maps.GoogleMap;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.ui.Container;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import java.io.IOException;

public class MapDemo {

    private Form current;
    private Resources theme;

    public void init(Object context) {
        theme = UIManager.initFirstTheme("/theme");

        // Pro only feature, uncomment if you have a pro subscription
        // Log.bindCrashProtection(true);
    }
    
    public void start() {
        Form hi = new Form("Google Direction Service");
        
        final GoogleMap map = new GoogleMap();
        hi.setLayout(new BorderLayout());
        
        final TextField start = new TextField();
        start.setHint("Start location");
        
        
        final TextField end = new TextField();
        end.setHint("Destination");
        
        Container form = new Container();
        form.setLayout(new BorderLayout());
        form.addComponent(BorderLayout.NORTH, start);
        form.addComponent(BorderLayout.SOUTH, end);
        
        ActionListener routeListener = new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                if ( !"".equals(start.getText()) && !"".equals(end.getText())){
                    DirectionsRequest req = new DirectionsRequest();
                    req.setTravelMode(DirectionsRequest.TRAVEL_MODE_DRIVING);
                    req.setOriginName(start.getText());
                    req.setDestinationName(end.getText());
                    map.route(req, new DirectionsRouteListener(){

                        public void routeCalculated(DirectionsResult result) {
                            System.out.println("Successfully mapped route");
                        }
                        
                    });
                }
            }
            
        };
        
        start.addActionListener(routeListener);
        end.addActionListener(routeListener);
        
        hi.addComponent(BorderLayout.NORTH, form);
        
        hi.addComponent(BorderLayout.CENTER, map);
        hi.show();
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
    }
    
    public void destroy() {
    }

}
