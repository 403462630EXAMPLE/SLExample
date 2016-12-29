package fc.com.sl.example.jobDispatcher;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;


@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MyJobService extends JobService {
    private static final String TAG = "MyJobService";

    private Handler mJobHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Toast.makeText(getApplicationContext(), "JobService task running", Toast.LENGTH_SHORT).show();
            jobFinished((JobParameters) msg.obj, false);
            return true;
        }
    });

    @Override
    public boolean onStartJob(JobParameters job) {
        // Do some work here
        Log.i(TAG, "---------------onStartJob: " + job.getJobId());
        mJobHandler.sendMessage(Message.obtain(mJobHandler, 1, job));
        return true; // Answers the question: "Is there still work going on?"
    }

    /**
     * 1. 如果onStartJob 返回false，则不会执行onStopJob
     * 2. 如果onStartJob返回true，如果调用了jobFinished方法， 则也不会执行onStopJob;
     * 3. 如果onStartJob返回true，如果调用了
     *      JobScheduler tm = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
     *      tm.cancelAll();
     *    才会执行onStopJob
     */
    @Override
    public boolean onStopJob(JobParameters job) {
        Log.i(TAG, "---------------onStopJob: " + job.getJobId());
        Toast.makeText(getApplicationContext(), "JobService task onStopJob", Toast.LENGTH_SHORT).show();
        mJobHandler.removeMessages(1);
        return false; // Answers the question: "Should this job be retried?"
    }
}