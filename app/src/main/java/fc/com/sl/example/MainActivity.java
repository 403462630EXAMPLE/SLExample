package fc.com.sl.example;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;

import fc.com.sl.example.customtabs.CustomTabsActivity;
import fc.com.sl.example.design.ButtomSheetActivity;
import fc.com.sl.example.jobDispatcher.FireBaseJobService;
import fc.com.sl.example.jobDispatcher.JobActivity;
import fc.com.sl.example.jobDispatcher.MyJobService;

public class MainActivity extends AppCompatActivity {
    private int jobId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.bt_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CustomTabsActivity.class));
            }
        });

        findViewById(R.id.bt_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ButtomSheetActivity.class));
            }
        });

        findViewById(R.id.bt_3).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                JobScheduler js =
                        (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
                JobInfo job = new JobInfo.Builder(
                        jobId ++,
                        new ComponentName(getApplicationContext(), MyJobService.class))
//                        .setRequiresCharging(true)

                        .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
//                        .setPersisted(true)
//                        .setPeriodic(3000)
                        .build();
                int jobId = js.schedule(job);
                if (jobId < 0) {
                    Toast.makeText(getApplicationContext(), "jobId是 " + jobId + "的任务失败", Toast.LENGTH_SHORT).show();
                }
//                startActivity(new Intent(MainActivity.this, JobActivity.class));
            }
        });

        findViewById(R.id.bt_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(getApplicationContext()));
                Job myJob = dispatcher.newJobBuilder()
                        .setService(FireBaseJobService.class) // the JobService that will be called
                        .setTag("my-unique-tag")        // uniquely identifies the job
                        .setConstraints(Constraint.ON_ANY_NETWORK)
                        .build();
                dispatcher.mustSchedule(myJob);
            }
        });

        findViewById(R.id.bt_5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, JobActivity.class));
            }
        });
    }
}
