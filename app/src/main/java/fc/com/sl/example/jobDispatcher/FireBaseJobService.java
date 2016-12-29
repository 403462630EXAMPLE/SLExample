package fc.com.sl.example.jobDispatcher;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

/**
 * Created by rjhy on 16-12-23
 */
public class FireBaseJobService extends JobService {
    private static final String TAG = "FireBaseJobService";

    private Handler mJobHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Toast.makeText(getApplicationContext(), "FireBase JobService task running", Toast.LENGTH_SHORT).show();
            jobFinished((JobParameters) msg.obj, false);
            return true;
        }
    });

    @Override
    public boolean onStartJob(JobParameters job) {
        // Do some work here
        Log.i(TAG, "---------------onStartJob: " + job.getTag());
        mJobHandler.sendMessage(Message.obtain(mJobHandler, 2, job));
        return true; // Answers the question: "Is there still work going on?"
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        Log.i(TAG, "---------------onStopJob: " + job.getTag());
        mJobHandler.removeMessages(2);
        return false; // Answers the question: "Should this job be retried?"
    }
}
