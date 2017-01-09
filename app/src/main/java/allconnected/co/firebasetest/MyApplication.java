package allconnected.co.firebasetest;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;


/**
 * Created by michael on 17/1/9.
 */

public class MyApplication extends Application {

    public static final String TAG="APPLICATION";

    private FirebaseRemoteConfig mFirebaseRemoteConfig;

    @Override
    public void onCreate() {
        super.onCreate();




    }
}
