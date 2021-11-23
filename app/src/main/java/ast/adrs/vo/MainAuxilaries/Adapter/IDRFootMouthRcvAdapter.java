package ast.adrs.vo.MainAuxilaries.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.armoomragames.denketa.R;

import java.util.ArrayList;

import ast.adrs.vo.MainAuxilaries.DModels.DModelIDRFootMouth;
import ast.adrs.vo.Utils.IAdapterCallback;


//
public class IDRFootMouthRcvAdapter extends RecyclerView.Adapter<IDRFootMouthRcvAdapter.ViewHolder> {

    private final ArrayList<DModelIDRFootMouth> mData;
    private final Context mContext;
    private final IAdapterCallback iAdapterCallback;


    public IDRFootMouthRcvAdapter(Context mContext, ArrayList<DModelIDRFootMouth> mData,
                                  IAdapterCallback iAdapterCallback) {
        this.mContext = mContext;
        this.mData = mData;

        this.iAdapterCallback = iAdapterCallback;


    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lay_item_idr_footmouth, null);


        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {


        holder.txv_farmer.setText(mData.get(position).getName());
        holder.txv_sick.setText(mData.get(position).getDescription());
        holder.txv_dead.setText(mData.get(position).getProposedChange());
        holder.txv_risk.setText(mData.get(position).getApprovingAuthority());
        holder.txv_sr.setText((position+1)+"");





    }


    @Override
    public int getItemCount() {
        return mData.size();
    }




    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolder extends RecyclerView.ViewHolder {



        TextView txv_sr, txv_farmer, txv_sick, txv_dead,  txv_risk;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txv_sr = itemView.findViewById(R.id.lay_foot_mouth_txv_sr);
            txv_farmer = itemView.findViewById(R.id.lay_foot_mouth_txv_farmer);
            txv_sick = itemView.findViewById(R.id.lay_foot_mouth_txv_sick);
            txv_dead= itemView.findViewById(R.id.lay_foot_mouth_txv_dead);
            txv_risk = itemView.findViewById(R.id.lay_foot_mouth_txv_risk);


        }

    }

}
