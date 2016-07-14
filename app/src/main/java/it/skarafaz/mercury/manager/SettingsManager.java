package it.skarafaz.mercury.manager;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import it.skarafaz.mercury.MercuryApplication;

public class SettingsManager {
    public static final String PREF_SORT_CMD = "pref_sort_cmd";
    private static SettingsManager instance;
    private SharedPreferences prefs;

    private SettingsManager() {
        prefs = PreferenceManager.getDefaultSharedPreferences(MercuryApplication.getContext());
    }

    public static synchronized SettingsManager getInstance() {
        if (instance == null) {
            instance = new SettingsManager();
        }
        return instance;
    }

    public Boolean getSortCmd() {
        return prefs.getBoolean(PREF_SORT_CMD, false);
    }
}
