package ast.adrs.vo.MainAuxilaries;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.armoomragames.denketa.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ast.adrs.vo.MainAuxilaries.DModels.DModelMap;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    //Maps
    private GoogleMap mGoogleMap;
    private String mStreetAddress, mArea;
    final String DEFAULT_TEXT = "...";
    ArrayList<DModelMap> lstMaps;
    private Context mContext;
    private Marker marker;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View frg = inflater.inflate(R.layout.fragment_map, container, false);


        init();
        bindViews(frg);
        requestPermisions();



        return frg;
    }







    private void init() {
        mContext = getActivity();
        lstMaps = new ArrayList<>();


        lstMaps.add(new DModelMap(33.6844, 73.0479));
        lstMaps.add(new DModelMap(31.5204, 74.3587));
        lstMaps.add(new DModelMap(34.5204, 72.0479));
        lstMaps.add(new DModelMap(32.5204, 71.0479));
        lstMaps.add(new DModelMap(30.5204, 69.0479));
        lstMaps.add(new DModelMap(29.5204, 68.0479));




        setUpMap();

    }

    private void bindViews(View frg) {

    }


    //region Map
    private void requestPermisions() {
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        }
//        else {
//            if (GPSTracker.getInstance(getContext()).getLatitude() != 0) {
//
//                getAddress(GPSTracker.getInstance(getContext()).getLatitude(),
//                        GPSTracker.getInstance(getContext()).getLongitude());
//
//                locatePointOnMap(new LatLng(GPSTracker.getInstance(getContext()).getLatitude(),
//                        GPSTracker.getInstance(getContext()).getLongitude()), true);
//            } else {
//                CustomToast.showToastMessage(getActivity(), "Please wait, while your GPS is trying to get your location.",
//                        Toast.LENGTH_SHORT);
//            }
//        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mGoogleMap = googleMap;


//        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(LatLng latLng) {
//               // getAddress(latLng.latitude, latLng.longitude);
//
//                Toast.makeText(getContext(), "No Data ", Toast.LENGTH_SHORT).show();
//
//            }
//        });

        for (int i = 0; i < lstMaps.size(); i++) {
            getAddress(lstMaps.get(i).getLat(), lstMaps.get(i).getLon());
        }

    }


    private void setUpMap() {


        if (mGoogleMap == null) {
            try {
                SupportMapFragment mapFragment = ((SupportMapFragment) getChildFragmentManager()
                        .findFragmentById(R.id.frg_location_mapview));
                mapFragment.getMapAsync(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



    }

    public void getAddress(double lat, double lng) {

        mStreetAddress = "";

        try {
            Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            Address objAddress = addresses.get(0);
            String currAddress = objAddress.getAddressLine(0);

            if (objAddress.getSubLocality() != null && objAddress.getSubLocality().length() > 0) {
                mArea = objAddress.getSubLocality();
            } else if (objAddress.getLocality() != null && objAddress.getLocality().length() > 0) {
                mArea = objAddress.getLocality();
            } else if (objAddress.getAdminArea() != null && objAddress.getAdminArea().length() > 0) {
                mArea = objAddress.getAdminArea();
            } else {
                mArea = "";
            }

            mStreetAddress = currAddress;

        } catch (IOException e) {
//            mStreetAddress = "exception" + e.getMessage();
            e.printStackTrace();

        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }


        locatePointOnMap(new LatLng(lat, lng), true);
    }

    @SuppressLint("MissingPermission")
    public void locatePointOnMap(LatLng latLng, boolean _isCameraAnimationrequired) {
        if (latLng != null) {

         //   to clear marker
//            if (mGoogleMap != null)
//            {
//                mGoogleMap.clear();
//            }


            try {
                if (_isCameraAnimationrequired) {

                    CameraPosition cameraPosition = new CameraPosition.Builder()
                            .target(latLng)     // Sets the center of the map to location user
                            .zoom(05.8f)        // Sets the zoom
                            .bearing(0)         // Sets the orientation of the camera (90 => east, 0 => North)
                            .tilt(20)           // Sets the tilt of the camera to 20 degrees
                            .build();           // Creates a CameraPosition from the builder
                    mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                    mGoogleMap.setMyLocationEnabled(true);


//                    Marker marker = mGoogleMap.addMarker(new MarkerOptions()
//                            .position(latLng)
//                            .title("Diseases")
//
//                    );

                }
            } catch (Exception e) {

            }
            mGoogleMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title("Diseases")
                    .snippet("This is my spot!")

                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

            mGoogleMap.addMarker(new MarkerOptions().position(latLng).title(mStreetAddress.length() > 0 ? mStreetAddress : DEFAULT_TEXT));


    }



    }
    //endregion


}
