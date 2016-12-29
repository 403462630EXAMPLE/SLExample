package fc.com.sl.example.design;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import fc.com.sl.example.R;

/**
 * Created by rjhy on 16-12-20
 */
public class ButtomSheetActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BottomSheetBehavior bottomSheetBehavior;
    private ExampleBottomSheetDialogFragment fragment;
    private ExampleBottomSheetDialog dialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_sheet);
        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        recyclerView.setAdapter(new ButtonSheetRecycleAdapter());

        bottomSheetBehavior = BottomSheetBehavior.from(recyclerView);
        findViewById(R.id.expanded).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        findViewById(R.id.collapsed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        findViewById(R.id.hidden).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });

        findViewById(R.id.dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //有bug
//                BottomSheetDialog dialog = new BottomSheetDialog(ButtomSheetActivity.this);
//                dialog.setContentView(R.layout.dialog_bottom_sheet);
//                dialog.show();

//                FcBottomSheetDialog dialog = new FcBottomSheetDialog(ButtomSheetActivity.this);
//                dialog.setContentView(R.layout.dialog_bottom_sheet);
//                dialog.setPeekHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics()));
//                dialog.show();

                if (dialog == null) {
                    dialog = new ExampleBottomSheetDialog(ButtomSheetActivity.this);
//                    dialog.setPeekHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics()));
                }
                dialog.show();
            }
        });

        findViewById(R.id.fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fragment == null) {
                    fragment = new ExampleBottomSheetDialogFragment();
                }
                fragment.show(getSupportFragmentManager(), "dialog");
            }
        });
    }
}
