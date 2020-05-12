package com.mobileappsarcade.gps.street.view.live.maps.navigation.route.classes;

public class TopStreetView_Model {
    public String PlaceName;
    public double latitude, longitude;

    TopStreetView_Model(String placeName, double latitude, double longitude)
    {
        PlaceName = placeName;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

