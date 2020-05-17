package com.megaappsinc.gps.street.view.live.maps.navigation.route.model;

public class StreetViewModel {
    private String PlaceName;
    private double latitude, longitude;

    public void setPlaceName(String placeName) {
        PlaceName = placeName;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getPlaceName() {
        return PlaceName;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public StreetViewModel(String placeName, double latitude, double longitude)
    {
        PlaceName = placeName;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
