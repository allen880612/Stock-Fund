package android.example.fund_demo;

import android.content.Context;
import android.example.fund_demo.Fund.Fund;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class FundAdapter extends
        RecyclerView.Adapter<FundAdapter.FundViewHolder>{

    private final ArrayList<Fund> fundList;
    private LayoutInflater mInflater;
    private Context mContext;
    private String choice;

    // describe view holder
    class FundViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView codeView;
        public final TextView nameView;
        public final TextView priceView;
        //public final TextView actionView;
        final FundAdapter mAdapter;
        private LayoutInflater mInflater;
        //Constructor
        public FundViewHolder(View itemView, FundAdapter adapter) {
            super(itemView);
            // Initialize view
            codeView = itemView.findViewById(R.id.item_fund);
            nameView = itemView.findViewById(R.id.fund_name);
            priceView = itemView.findViewById(R.id.label_return);
            //actionView = itemView.findViewById(R.id.action);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            // Get the position of the item that was clicked.
//            int pos = getLayoutPosition();
//            Intent intent = new Intent(mContext, Description.class);
//            intent.putExtra("STOCK_INFO", fundList.get(pos));
//            v.getContext().startActivity(intent);
        }
    }

    //Constructor
    public FundAdapter(Context context,ArrayList<Fund> _fundList) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        this.fundList = _fundList;
    }

    @Override
    public int getItemCount() {
        return fundList.size();
    }

    @NonNull
    @Override
    public FundViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View mItemView = mInflater.inflate(R.layout.list_item, parent, false);
        return new FundViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull FundViewHolder holder, int _p) {
        Fund fund = fundList.get(_p);     //ge position and do something
        holder.nameView.setText(fund.GetName());
        holder.codeView.setText(fund.GetCode());
        holder.priceView.setText(fund.GetReturn_1y());
    }

}
