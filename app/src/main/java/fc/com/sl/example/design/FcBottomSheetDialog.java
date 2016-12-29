package fc.com.sl.example.design;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.view.ViewGroup;

import fc.com.sl.example.R;

/**
 * Created by rjhy on 16-12-21
 */
public class FcBottomSheetDialog extends BottomSheetDialog {
    private BottomSheetBehavior behavior;
    private int peekHeight = -1;
    private int showState = BottomSheetBehavior.STATE_COLLAPSED;

    public FcBottomSheetDialog(@NonNull Context context) {
        super(context);
    }

    public FcBottomSheetDialog(@NonNull Context context, @StyleRes int theme) {
        super(context, theme);
    }

    public FcBottomSheetDialog(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResId) {
        super.setContentView(layoutResId);
        initBehavior();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        initBehavior();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        initBehavior();
    }

    private void initBehavior() {
        behavior = BottomSheetBehavior.from(findViewById(android.support.design.R.id.design_bottom_sheet));
    }

    @Override
    public void show() {
        super.show();
        behavior.setPeekHeight(peekHeight);
        behavior.setState(showState);
    }

    public void setShowState(final @BottomSheetBehavior.State int state) {
        this.showState = state;
    }

    public void setPeekHeight(int peekHeight) {
        this.peekHeight = peekHeight;
    }

    public void setPeekHeightAuto() {
        this.peekHeight = -1;
    }
}
