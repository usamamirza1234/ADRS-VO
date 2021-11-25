package ast.adrs.vo.MainAuxilaries;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.armoomragames.denketa.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ast.adrs.vo.MainAuxilaries.Adapter.InfoWindowAdapter;
import ast.adrs.vo.MainAuxilaries.WebServices.Home_WebHit_Post_GetGPSCordinates;
import ast.adrs.vo.Utils.AppConfig;
import ast.adrs.vo.Utils.IWebCallback;

public class MapFragment extends Fragment implements OnMapReadyCallback
        , GoogleMap.OnInfoWindowClickListener {

    final String DEFAULT_TEXT = "...";
    ArrayList<Home_WebHit_Post_GetGPSCordinates.ResponseModel.Result> lstMaps;
    View frg;
    //Maps
    private GoogleMap mGoogleMap;
    private String mStreetAddress, mArea;
    private Context mContext;

    private Marker marker;


    private Marker myMarker;
    private Dialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        frg = inflater.inflate(R.layout.fragment_map, container, false);


        init();
        bindViews(frg);
        requestPermisions();


        return frg;
    }







    private void init() {
        mContext = getActivity();
        lstMaps = new ArrayList<>();
        setUpMap();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("dateFrom", "1964-10-20T15:50:16.652Z");
        jsonObject.addProperty("dateTo","2030-08-05T09:11:15.664Z");

        jsonObject.addProperty("areaType",1);
        jsonObject.addProperty("areaCode",0);


        requestMap(jsonObject.toString());


//        lstMaps.add(new DModelMap(33.6844, 73.0479));
//        lstMaps.add(new DModelMap(31.5204, 74.3587));
//        lstMaps.add(new DModelMap(34.5204, 72.0479));
//        lstMaps.add(new DModelMap(32.5204, 71.0479));
//        lstMaps.add(new DModelMap(30.5204, 69.0479));
//        lstMaps.add(new DModelMap(29.5204, 68.0479));






        setUpMap();

    }

    private void requestMap(String toString) {
        showProgDialog();
        Home_WebHit_Post_GetGPSCordinates home_webHit_post_getGPSCordinates = new Home_WebHit_Post_GetGPSCordinates();

        home_webHit_post_getGPSCordinates.GetGPSCordinates(getContext(), new IWebCallback() {
            @Override
            public void onWebResult(boolean isSuccess, String strMsg) {
                if (isSuccess) {

                    dismissDialog();
                    if (Home_WebHit_Post_GetGPSCordinates.responseObject != null &&
                            Home_WebHit_Post_GetGPSCordinates.responseObject.getResult() != null) {
                        lstMaps.addAll(Home_WebHit_Post_GetGPSCordinates.responseObject.getResult());

                        PopulateData();
                    }

                } else {
                    dismissDialog();

                    AppConfig.getInstance().showErrorMessage(getContext(), strMsg);
                }
            }

            @Override
            public void onWebException(Exception ex) {
                dismissDialog();
                Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                AppConfig.getInstance().showErrorMessage(getContext(), ex.toString());
            }
        }, toString);
    }

    private void PopulateData()
    {
        for (int i = 0; i < lstMaps.size(); i++) {
            getAddress(lstMaps.get(i).getLatitude(), lstMaps.get(i).getLongitude(),i);
        }
    }

    private void dismissDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    private void showProgDialog() {
        progressDialog = new Dialog(getActivity(), R.style.AppTheme);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.setContentView(R.layout.dialog_progress);

        progressDialog.setCancelable(false);
        progressDialog.show();
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
//        mGoogleMap.setOnMarkerClickListener(this);

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
            getAddress(lstMaps.get(i).getLatitude(), lstMaps.get(i).getLongitude(),i);
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

    public void getAddress(double lat, double lng,int pos) {

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




        } catch (IOException | IndexOutOfBoundsException e) {
//            mStreetAddress = "exception" + e.getMessage();

            e.printStackTrace();

        }


        locatePointOnMap(new LatLng(lat, lng), true,pos);
    }

    @SuppressLint("MissingPermission")
    public void locatePointOnMap(LatLng latLng, boolean _isCameraAnimationrequired,int pos) {
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


//
//
//            mGoogleMap.addMarker(new MarkerOptions()
//
//                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));





            addMarker(mGoogleMap, latLng.latitude, latLng.longitude,
                    (mStreetAddress.length() > 0 ? mStreetAddress : DEFAULT_TEXT),

                    (lstMaps.get(pos).getMouzaName()) + ","+
                    (lstMaps.get(pos).getSpecies()) + ","+
                    (lstMaps.get(pos).getDisease()) + ","+
                    (lstMaps.get(pos).getTotalAnimals()) + ","+
                    (lstMaps.get(pos).getSickAnimals()) + ","+
                    (lstMaps.get(pos).getDeadAnimals()));

            mGoogleMap.setInfoWindowAdapter(new InfoWindowAdapter(getActivity()));
            mGoogleMap.setOnInfoWindowClickListener(this);


        }




    }

//    @Override
//    public boolean onMarkerClick(final Marker marker) {
//        Log.d("googlemarker","myMarker:  " + marker.getPosition());
//        if (marker.equals(myMarker))
//        {
//            //handle click here
//
//        }
//
//        return true;
//    }
    //endregion


    private void addMarker(GoogleMap map, double lat, double lon,
                           String title, String snippet) {
        map.addMarker(new MarkerOptions().position(new LatLng(lat, lon))
                .title(title)
                .snippet(snippet));
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Log.d("googlemarker", "myMarker:  " + marker.getPosition());
        Toast.makeText(getContext(), marker.getTitle(), Toast.LENGTH_LONG).show();
    }

}
