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


        holder.txv_BRMI.setText(mData.get(position).getName());
        holder.txv_totalreforms.setText(mData.get(position).getDescription());
        holder.txv_implemented.setText(mData.get(position).getProposedChange());
        holder.txv_partitallyimplemented.setText(mData.get(position).getApprovingAuthority());
        holder.txv_underimplementation.setText(mData.get(position).getDepartment());
        holder.txv_furtherdelibrations.setText(mData.get(position).getDetails());
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



        TextView txv_BRMI,txv_totalreforms,txv_implemented,txv_partitallyimplemented,
                txv_underimplementation,txv_furtherdelibrations,txv_disagreed,txv_doesnotpertain,txv_sr;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txv_BRMI = itemView.findViewById(R.id.lay_issues_list_txv_brmi);
            txv_totalreforms = itemView.findViewById(R.id.lay_issues_list_txv_totalreforms);
            txv_implemented = itemView.findViewById(R.id.lay_issues_list_txv_implemented);
            txv_partitallyimplemented = itemView.findViewById(R.id.lay_issues_list_txv_partitallyimplemented);
            txv_underimplementation = itemView.findViewById(R.id.lay_issues_list_txv_underimplementation);
            txv_furtherdelibrations = itemView.findViewById(R.id.lay_issues_list_txv_furtherdelibrations);
            txv_disagreed = itemView.findViewById(R.id.lay_issues_list_txv_disagreed);
            txv_doesnotpertain = itemView.findViewById(R.id.lay_issues_list_txv_doesnotpertain);
            txv_sr = itemView.findViewById(R.id.lay_issues_list_txv_sr);

        }

    }

}
