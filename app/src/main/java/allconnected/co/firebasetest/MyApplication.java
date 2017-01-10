package allconnected.co.firebasetest;

import android.app.Application;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import co.allconnected.ad.AdConfigManager;


/**
 * application类，初始化config
 *
 * @author michael
 * @time 17/1/10 下午4:43
 */
public class MyApplication extends Application {

    public static final String TAG = "APPLICATION";

    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    /**
     * 缓存时间，这里设置为1个小时
     */
    private static final long CACHEEXPIRATION = 3600;
    private static final int DEFAULT_CONFIG_FILE_ID = R.xml.remote_config_defaults;

    @Override
    public void onCreate() {
        super.onCreate();
        AdConfigManager.init(DEFAULT_CONFIG_FILE_ID, CACHEEXPIRATION);
    }
}
