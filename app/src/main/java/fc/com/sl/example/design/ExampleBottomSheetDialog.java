package fc.com.sl.example.design;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;


import fc.com.sl.example.R;

/**
 * Created by rjhy on 16-12-21
 */
public class ExampleBottomSheetDialog extends FcBottomSheetDialog {
    private RecyclerView recyclerView;
    public ExampleBottomSheetDialog(@NonNull Context context) {
        super(context);
        //在此处调用setContentView方法和在onCreate方法里调用setContentView方法  效果不一样
        setContentView(R.layout.dialog_bottom_sheet2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.dialog_bottom_sheet2);
        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        recyclerView.setAdapter(new ButtonSheetRecycleAdapter());
    }
}
