package com.android.settings.display;

import android.content.Context;
import android.provider.Settings;
import androidx.preference.TwoStatePreference;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.settings.R;
import com.android.settings.Utils;
import androidx.preference.Preference;
import com.android.settings.core.BasePreferenceController;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceCategory;
import android.app.settings.SettingsEnums;
import com.android.settings.network.GlobalSettingsChangeListener;
import androidx.preference.PreferenceScreen;
import android.provider.Settings.SettingNotFoundException;

public class LockscreenWeatherPreferenceController extends BasePreferenceController implements Preference.OnPreferenceChangeListener {

    static final int SETTING_VALUE_OFF = 0;
    public Preference toggle;
    static final int SETTING_VALUE_ON = 1;


    public LockscreenWeatherPreferenceController(Context context, String preferenceKey) {
        super(context, preferenceKey);
    }

    @Override
    public void displayPreference(PreferenceScreen screen) {
        super.displayPreference(screen);
        toggle = screen.findPreference("weathertoggle");
    }

    @Override
    public void updateState(Preference preference) {;
        if (preference == toggle) {
            int mode = Settings.System.getInt(mContext.getContentResolver(),
                    "lockscreen_weather_enabled", SETTING_VALUE_OFF);
            ((TwoStatePreference) preference).setChecked(mode != SETTING_VALUE_OFF);
        } else {
            int mode = Settings.System.getInt(mContext.getContentResolver(),
                    "lockscreen_weather_text", SETTING_VALUE_OFF);
            ((TwoStatePreference) preference).setChecked(mode != SETTING_VALUE_OFF);
        }
    }

    @Override
    public int getAvailabilityStatus() {
        return AVAILABLE;
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {;
        final boolean isEnabled = (Boolean) newValue;
        if (preference == toggle) {
        Settings.System.putInt(mContext.getContentResolver(),
                "lockscreen_weather_enabled",
                isEnabled ? SETTING_VALUE_ON : SETTING_VALUE_OFF);
        return true;
        } else {
        Settings.System.putInt(mContext.getContentResolver(),
                "lockscreen_weather_text",
                isEnabled ? SETTING_VALUE_ON : SETTING_VALUE_OFF);
                return true;
        }
    }
}

