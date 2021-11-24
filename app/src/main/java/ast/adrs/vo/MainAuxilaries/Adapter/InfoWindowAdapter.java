package ast.adrs.vo.MainAuxilaries.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.armoomragames.denketa.R;
import com.google.android.gms.maps.GoogleMap;

import java.util.Arrays;
import java.util.List;

public class InfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private final View view;

    private final Context myContext;

    public InfoWindowAdapter(Context aContext) {
        this.myContext = aContext;

        LayoutInflater inflater = (LayoutInflater) myContext.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.lay_popup_map_,
                null);
    }


    @Override
    public View getInfoWindow(com.google.android.gms.maps.model.Marker marker) {


        final String title = marker.getTitle();
        final String snippet = marker.getSnippet();


        final TextView txv_Title = ((TextView) view.findViewById(R.id.lay_popup_map_txv_title));
        final TextView txv_mouza = ((TextView) view.findViewById(R.id.lay_popup_map_txv_mouza));
        final TextView txv_Spice = ((TextView) view.findViewById(R.id.lay_popup_map_txv_Spice));
        final TextView txv_disease = ((TextView) view.findViewById(R.id.lay_popup_map_txv_disease));
        final TextView txv_total = ((TextView) view.findViewById(R.id.lay_popup_map_txv_total));
        final TextView txv_sick = ((TextView) view.findViewById(R.id.lay_popup_map_txv_sick));
        final TextView txv_dead = ((TextView) view.findViewById(R.id.lay_popup_map_txv_dead));


        if (title != null) {
            txv_Title.setText(title);
        } else {
            txv_Title.setText("");
            txv_Title.setVisibility(View.GONE);
        }


        if (snippet != null) {
            List<String> items = Arrays.asList(snippet.split("\\s*,\\s*"));
            txv_mouza.setText("Mouza: " + items.get(0));
            txv_Spice.setText("Spice: " + items.get(1));
            txv_disease.setText("Disease: " + items.get(2));
            txv_total.setText("Total: " + items.get(3));
            txv_sick.setText("Sick: " + items.get(4));
            txv_dead.setText("Dead: " + items.get(5));

        } else {
            txv_mouza.setText("");
            txv_Spice.setText("");
            txv_disease.setText("");
            txv_total.setText("");
            txv_sick.setText("");
            txv_dead.setText("");
        }

        return view;
    }

    @Override
    public View getInfoContents(com.google.android.gms.maps.model.Marker marker) {
        if (marker != null
                && marker.isInfoWindowShown()) {
            marker.hideInfoWindow();
            marker.showInfoWindow();
        }
        return null;
    }
}