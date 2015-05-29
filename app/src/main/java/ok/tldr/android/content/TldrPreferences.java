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

    public static boolean getShouldShowLogin(final Context context) {
        return getBoolean(context, Keys.KEY_SHOULD_SHOW_LOGIN, true);
    }

    public static void setShouldShowLogin(final Context context, final boolean isFirstLaunch) {
        putBoolean(context, Keys.KEY_SHOULD_SHOW_LOGIN, isFirstLaunch);
    }

    public static String getUsername(final Context context) {
        return getString(context, Keys.KEY_USERNAME);
    }

    public static void setUsername(final Context context, final String username) {
        putString(context, Keys.KEY_USERNAME, username);
    }

    public static String getAccessToken(final Context context) {
        return getString(context, Keys.KEY_ACCESS_TOKEN);
    }

    public static void setAccessToken(final Context context, final String accessToken) {
        putString(context, Keys.KEY_ACCESS_TOKEN, accessToken);
    }

    private static String getString(final Context context, final String key) {
        final SharedPreferences preferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return preferences.getString(key, "");
    }

    private static void putString(final Context context, final String key, final String value) {
        final SharedPreferences preferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        preferences.edit().putString(key, value).apply();
    }

    private static boolean getBoolean(final Context context, final String key, final boolean defaultValue) {
        final SharedPreferences preferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(key, defaultValue);
    }

    private static void putBoolean(final Context context, final String key, final boolean value) {
        final SharedPreferences preferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        preferences.edit().putBoolean(key, value).apply();
    }
}
