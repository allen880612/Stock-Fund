package android.example.demo;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class StockInfoAdapter extends
        RecyclerView.Adapter<StockInfoAdapter.StockViewHolder>{

    private final ArrayList<StockHolder> stockList;
    private LayoutInflater mInflater;
    private Context mContext;
    private String choice;

    // describe view holder
    class StockViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView codeView;
        public final TextView nameView;
        public final TextView priceView;
        //public final TextView actionView;
        final StockInfoAdapter mAdapter;
        private LayoutInflater mInflater;
        //Constructor
        public StockViewHolder(View itemView, StockInfoAdapter adapter) {
            super(itemView);
            // Initialize view
            codeView = itemView.findViewById(R.id.code);
            nameView = itemView.findViewById(R.id.name);
            priceView = itemView.findViewById(R.id.price);
            //actionView = itemView.findViewById(R.id.action);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            // Get the position of the item that was clicked.
            int pos = getLayoutPosition();
            Intent intent = new Intent(mContext, Description.class);
            intent.putExtra("STOCK_INFO", stockList.get(pos));
            v.getContext().startActivity(intent);
        }
    }

    //Constructor
    public StockInfoAdapter(Context context,ArrayList<StockHolder> _stockList, String _choice) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        choice = _choice;
        this.stockList = _stockList;
    }

    @Override
    public int getItemCount() {
        return stockList.size();
    }

    @NonNull
    @Override
    public StockInfoAdapter.StockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View mItemView = mInflater.inflate(R.layout.stock_item, parent, false);
        return new StockViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull StockViewHolder holder, int _p) {
        StockHolder sh = stockList.get(_p);     //ge position and do something
        holder.nameView.setText(sh.GetName());
        holder.codeView.setText(sh.GetCode());

        String info = sh.GetValueByKey(choice);
        holder.priceView.setText(info);
    }

}
