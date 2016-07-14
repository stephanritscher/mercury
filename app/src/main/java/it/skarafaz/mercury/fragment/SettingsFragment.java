package it.skarafaz.mercury.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import it.skarafaz.mercury.R;

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
