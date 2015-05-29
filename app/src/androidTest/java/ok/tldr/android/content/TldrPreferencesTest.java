package ok.tldr.android.content;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import ok.tldr.android.TldrRobolectricTestRunner;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(TldrRobolectricTestRunner.class)
public class TldrPreferencesTest {

    private Context context;

    @Before
    public void setup() {
        context = Robolectric.getShadowApplication().getApplicationContext();
    }

    @After
    public void teardown() {
        context = null;
    }

    @Test
    public void getAttribute_shouldShowLogin_shouldReturnTrue() {
        final boolean shouldShowLogin = TldrPreferences.getShouldShowLogin(context);
        assertThat(shouldShowLogin).isTrue();
    }

    @Test
    public void setAttribute_shouldShowLogin_shouldPersist() {
        TldrPreferences.setShouldShowLogin(context, false);
        assertThat(TldrPreferences.getShouldShowLogin(context)).isFalse();

        TldrPreferences.setShouldShowLogin(context, true);
        assertThat(TldrPreferences.getShouldShowLogin(context)).isTrue();
    }

    @Test
    public void getAttribute_username_shouldReturnEmptyString() {
        final String username = TldrPreferences.getUsername(context);
        assertThat(username).isNotNull();
        assertThat(username).isEqualTo("");
    }

    @Test
    public void setAttribute_username_shouldPersist() {
        TldrPreferences.setUsername(context, "username");

        final String username = TldrPreferences.getUsername(context);
        assertThat(username).isNotNull();
        assertThat(username).isEqualTo("username");
    }

    @Test
    public void getAttribute_accessToken_shouldReturnEmptyString() {
        final String accessToken = TldrPreferences.getAccessToken(context);
        assertThat(accessToken).isNotNull();
        assertThat(accessToken).isEqualTo("");
    }

    @Test
    public void setAttribute_accessToken_shouldPersist() {
        TldrPreferences.setAccessToken(context, "access_token");

        final String accessToken = TldrPreferences.getAccessToken(context);
        assertThat(accessToken).isNotNull();
        assertThat(accessToken).isEqualTo("access_token");
    }
}
