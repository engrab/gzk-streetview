package com.megaappsinc.gps.street.view.live.maps.navigation.route.model;

public class TopStreetViewModel {
    public String PlaceName;
    public double latitude, longitude;

    public TopStreetViewModel(String placeName, double latitude, double longitude)
    {
        PlaceName = placeName;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

