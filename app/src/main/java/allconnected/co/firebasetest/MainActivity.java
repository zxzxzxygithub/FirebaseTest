/*
 * Copyright Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * For more information on setting up and running this sample code, see
 * https://firebase.google.com/docs/remote-config/android
 */

package allconnected.co.firebasetest;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import co.allconnected.ad.AdConfigManager;
import co.allconnected.ad.bean.AdConfigBean;

/**
 * firebase测试类，firebase使用需要翻墙
 *
 * @author michael
 * @time 17/1/9 下午8:39
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    // Remote Config keys
    private static final String LOADING_PHRASE_CONFIG_KEY = "loading_phrase";
    private static final String CONNECTED = "connected";
    private static final String WELCOME_MESSAGE_CAPS_KEY = "welcome_message_caps";

    private TextView mWelcomeTextView;
    private AlertDialog mAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWelcomeTextView = (TextView) findViewById(R.id.welcomeTextView);

        Button fetchButton = (Button) findViewById(R.id.fetchButton);
        fetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                fetchWelcome();
                displayWelcomeMessage();

            }
        });


        fetchWelcome();
    }

    /**
     * Fetch welcome message from server.
     */
    private void fetchWelcome() {
        String textStr=null;
        mWelcomeTextView.setText(textStr);
        AdConfigManager.fetchAdConfig(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Fetch succeeded", Toast.LENGTH_SHORT).show();

                }else{

                    Toast.makeText(MainActivity.this, "Fetch failed", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    /**
     * Display welcome message in all caps if welcome_message_caps is set to true. Otherwise
     * display welcome message as fetched from welcome_message.
     */
    // [START display_welcome_message]
    private void displayWelcomeMessage() {
        String connectedStr=null;
        // [START get_config_values]
        connectedStr = AdConfigManager.getAdConfigStr(CONNECTED);
        AdConfigBean adConfigBean = AdConfigManager.getAdConfigBean(CONNECTED);
        // [END get_config_values]
        boolean setAllCaps = false;
        if (setAllCaps) {
            mWelcomeTextView.setAllCaps(true);
        } else {
            mWelcomeTextView.setAllCaps(false);
        }

        mWelcomeTextView.setText(connectedStr);
    }
    // [END display_welcome_message]

    int mPressbackCount = 0;

    @Override
    public void onBackPressed() {
        mPressbackCount++;
        if (mPressbackCount == 1) {
            mAlertDialog = new AlertDialog.Builder(this).setMessage("Are you sure to exit?").setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finish();
                }
            }).setNegativeButton("Cancel", null).create();
            mAlertDialog.show();
            mAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    finish();
                }
            });
        }
    }
}
