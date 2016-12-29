package fc.com.sl.example.design;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fc.com.sl.example.R;

/**
 * Created by rjhy on 16-12-21
 */
public class ButtonSheetRecycleAdapter extends RecyclerView.Adapter {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ButtomSheetViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_buttom_sheet, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ButtomSheetViewHolder viewHolder = (ButtomSheetViewHolder) holder;
        viewHolder.textView.setText("item" + (position + 1));
        if (position % 2 == 0) {
            viewHolder.textView.setBackgroundColor(Color.parseColor("#dddddd"));
        } else {
            viewHolder.textView.setBackgroundColor(Color.parseColor("#ffffff"));
        }
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    static class ButtomSheetViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ButtomSheetViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_text);
        }
    }
}
