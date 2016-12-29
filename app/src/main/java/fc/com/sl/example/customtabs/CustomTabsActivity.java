package fc.com.sl.example.customtabs;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import fc.com.sl.example.MainActivity;
import fc.com.sl.example.R;
import fc.com.sl.example.customtabs.shared.CustomTabActivityHelper;

/**
 * Created by rjhy on 16-12-20
 */
public class CustomTabsActivity extends AppCompatActivity {
    private CustomTabActivityHelper mCustomTabActivityHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_tabs);

        mCustomTabActivityHelper = new CustomTabActivityHelper();
        mCustomTabActivityHelper.setConnectionCallback(new CustomTabActivityHelper.ConnectionCallback() {
            @Override
            public void onCustomTabsConnected() {
                mCustomTabActivityHelper.mayLaunchUrl(Uri.parse("https://paul.kinlan.me/"), null, null);
            }

            @Override
            public void onCustomTabsDisconnected() {

            }
        });
        findViewById(R.id.bt_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBaseCustomTab();
            }
        });
        findViewById(R.id.bt_change_toolbar_color).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChangeToolbarColor();
            }
        });
        findViewById(R.id.bt_add_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddMenuCustomTabs();
            }
        });
        findViewById(R.id.bt_animation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAnimationCustomTabs();
            }
        });

        findViewById(R.id.bt_advance).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCustomTab();
            }
        });
    }

    private CustomTabsIntent.Builder createBuilder() {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setShowTitle(true);
        builder.addDefaultShareMenuItem();
        builder.setCloseButtonIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_back));
        return builder;
    }

    private void openBaseCustomTab() {
        String url = "https://www.baidu.com/";
        CustomTabsIntent customTabsIntent = createBuilder().build();
        customTabsIntent.launchUrl(CustomTabsActivity.this, Uri.parse(url));
    }

    private CustomTabsIntent.Builder createToolBarColorBuilder() {
        CustomTabsIntent.Builder builder = createBuilder();
        builder.setToolbarColor(getResources().getColor(R.color.colorPrimary));
        builder.setSecondaryToolbarColor(getResources().getColor(R.color.colorPrimary));
        return builder;
    }

    private void openChangeToolbarColor() {
        String url = "https://paul.kinlan.me/";
        CustomTabsIntent.Builder builder = createToolBarColorBuilder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(CustomTabsActivity.this, Uri.parse(url));
    }

    private void openAddMenuCustomTabs() {
        String url = "https://paul.kinlan.me/";
        CustomTabsIntent.Builder builder = createToolBarColorBuilder();

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, url);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        builder.addMenuItem("菜单1", pendingIntent);

        Bitmap icon = BitmapFactory.decodeResource(getResources(),
                android.R.drawable.ic_menu_share);
        builder.setActionButton(icon, "分享", pendingIntent);

        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(CustomTabsActivity.this, Uri.parse(url));
    }

    private void openAnimationCustomTabs() {
        String url = "https://paul.kinlan.me/";
        CustomTabsIntent.Builder builder = createToolBarColorBuilder();
        builder.setStartAnimations(this, android.R.anim.fade_in, android.R.anim.fade_out);
        builder.setExitAnimations(this, android.R.anim.fade_in, android.R.anim.fade_out);
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(CustomTabsActivity.this, Uri.parse(url));
    }

    private void openCustomTab() {
        CustomTabsIntent.Builder builder = createToolBarColorBuilder();
        CustomTabsIntent customTabsIntent = builder.build();
        CustomTabActivityHelper.openCustomTab(this, customTabsIntent, Uri.parse("https://paul.kinlan.me/"), new CustomTabActivityHelper.CustomTabFallback() {
            @Override
            public void openUri(Activity activity, Uri uri) {
                Toast.makeText(activity, "不支持customTabs", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mCustomTabActivityHelper.bindCustomTabsService(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mCustomTabActivityHelper.unbindCustomTabsService(this);
    }
}
