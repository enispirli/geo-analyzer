package com.interview.geoanalyzer.thirdparty;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.interview.geoanalyzer.thirdparty.dto.GeoDataOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class GoogleMapsService {

    private final GeoApiContext geoApiContext;

    public GeoDataOutput reverseGeocode(Double lat, Double lng) throws ApiException, InterruptedException, IOException {
        GeocodingResult[] results = GeocodingApi.reverseGeocode(geoApiContext, new LatLng(lat, lng)).await();

        GeoDataOutput output = new GeoDataOutput();
        if (results.length > 0) {

            for (AddressComponent component : results[0].addressComponents) {
                for (AddressComponentType type : component.types) {
                    switch (type) {
                        case COUNTRY:
                            output.setCountry(component.longName);
                            break;
                        case ADMINISTRATIVE_AREA_LEVEL_1:
                            output.setCity(component.longName);
                            break;
                        case ADMINISTRATIVE_AREA_LEVEL_2:
                            output.setDistrict(component.longName);
                            break;
                    }
                }
            }

            return output;
        }
        throw new IllegalArgumentException("Data not found");
    }
}
