package it.skarafaz.mercury.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import it.skarafaz.mercury.R;
import it.skarafaz.mercury.adapter.ServerPagerAdapter;
import it.skarafaz.mercury.data.InitTaskResult;
import it.skarafaz.mercury.manager.ConfigManager;


public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new AsyncTask<Void, Void, InitTaskResult>() {
            @Override
            protected InitTaskResult doInBackground(Void... params) {
                return ConfigManager.getInstance().init();
            }

            @Override
            protected void onPostExecute(InitTaskResult result) {
                postInit(result);
            }
        }.execute();
    }

    private void postInit(InitTaskResult result) {
        if (ConfigManager.getInstance().getServers().size() > 0) {
            ServerPagerAdapter adapter = new ServerPagerAdapter(getSupportFragmentManager(), ConfigManager.getInstance().getServers());
            ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
            viewPager.setAdapter(adapter);
            viewPager.setVisibility(View.VISIBLE);
        } else {
            TextView message = (TextView) findViewById(R.id.message);
            message.setText(getEmptyMessage(result));
            LinearLayout empty = (LinearLayout) findViewById(R.id.empty);
            empty.setVisibility(View.VISIBLE);
        }
        ProgressBar progress = (ProgressBar) findViewById(R.id.progress);
        progress.setVisibility(View.INVISIBLE);
    }

    private int getEmptyMessage(InitTaskResult result) {
        int message = -1;
        switch (result) {
            case SUCCESS:
                message = R.string.no_servers;
                break;
            case CANNOT_READ_EXT_STORAGE:
                message = R.string.cannot_read_ext_storage;
                break;
            case BAD_CONFIG_DIR:
                message = R.string.bad_config_dir;
                break;
        }
        return message;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
