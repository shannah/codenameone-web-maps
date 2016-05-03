# Google Maps Inside a Web View for Codename One

This library is more of a proof of concept than anything else.  It provides a google map via a web view inside a Codename One application.  It also includes Java API wrappers for Google's direction service.

## Video Demo

[Screencast](https://youtu.be/sRR2tej5LCE)

## Requirements

[Codename One](http://www.codenameone.com)

## Installation

1. Copy the [CN1WebMaps.cn1lib](bin/CN1WebMaps.cn1lib) file into your Codename One project's `lib` directory.
2. Refresh libs

## Sample Usage

(From the [demo](demo) project)

````java
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
````

## Building the Demo

You can try out the demo app by building it from source.

~~~~
$ git clone https://github.com/shannah/codenameone-web-maps.git
$ cd codenameone-web-maps
$ ant
~~~~

Open the demo project in NetBeans

## Building from Source

~~~~
$ git clone https://github.com/shannah/codenameone-web-maps.git
$ cd codenameone-web-maps
$ ant
~~~~

You'll find the library in bin/CN1WebMaps.cn1lib


## License

Apache 2.0


## Credits

* Created By [Steve Hannah](http://sjhannah.com)