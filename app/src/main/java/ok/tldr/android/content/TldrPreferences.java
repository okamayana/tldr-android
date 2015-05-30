package ok.tldr.android.content;

import android.content.Context;
import android.content.SharedPreferences;

public class TldrPreferences {

    private static class Keys {
        public static final String KEY_SHOULD_SHOW_LOGIN = "should_show_login";
        public static final String KEY_USERNAME = "username";
        public static final String KEY_ACCESS_TOKEN = "cookie";
    }

    private static final String NAME = "tldr_preferences";

    private static TldrPreferences instance;
    private SharedPreferences preferences;

    private TldrPreferences(final Context context) {
        preferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public static void init(final Context context) {
        if (instance == null) {
            instance = new TldrPreferences(context);
        }
    }

    public static TldrPreferences getInstance() {
        return instance;
    }

    public void clear() {
        preferences.edit().clear().apply();
    }

    public boolean getShouldShowLogin() {
        return getBoolean(Keys.KEY_SHOULD_SHOW_LOGIN, true);
    }

    public void setShouldShowLogin(final boolean isFirstLaunch) {
        putBoolean(Keys.KEY_SHOULD_SHOW_LOGIN, isFirstLaunch);
    }

    public String getUsername() {
        return getString(Keys.KEY_USERNAME);
    }

    public void setUsername(final String username) {
        putString(Keys.KEY_USERNAME, username);
    }

    public String getAccessToken() {
        return getString(Keys.KEY_ACCESS_TOKEN);
    }

    public void setAccessToken(final String accessToken) {
        putString(Keys.KEY_ACCESS_TOKEN, accessToken);
    }

    private String getString(final String key) {
        return preferences.getString(key, "");
    }

    private void putString(final String key, final String value) {
        preferences.edit().putString(key, value).apply();
    }

    private boolean getBoolean(final String key, final boolean defaultValue) {
        return preferences.getBoolean(key, defaultValue);
    }

    private void putBoolean(final String key, final boolean value) {
        preferences.edit().putBoolean(key, value).apply();
    }
}
