package ok.tldr.android.content;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.shadows.ShadowApplication;

import ok.tldr.android.TldrRobolectricTestRunner;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(TldrRobolectricTestRunner.class)
public class TldrPreferencesTest {

    private Context context;

    @Before
    public void setup() {
        context = ShadowApplication.getInstance().getApplicationContext();
        TldrPreferences.init(context);
    }

    @After
    public void teardown() {
        TldrPreferences.getInstance().clear();
        context = null;
    }

    @Test
    public void getAttribute_shouldShowLogin_shouldReturnTrue() {
        final boolean shouldShowLogin = TldrPreferences.getInstance().getShouldShowLogin();
        assertThat(shouldShowLogin).isTrue();
    }

    @Test
    public void setAttribute_shouldShowLogin_shouldPersist() {
        TldrPreferences.getInstance().setShouldShowLogin(false);
        assertThat(TldrPreferences.getInstance().getShouldShowLogin()).isFalse();

        TldrPreferences.getInstance().setShouldShowLogin(true);
        assertThat(TldrPreferences.getInstance().getShouldShowLogin()).isTrue();
    }

    @Test
    public void getAttribute_username_shouldReturnEmptyString() {
        final String username = TldrPreferences.getInstance().getUsername();
        assertThat(username).isNotNull();
        assertThat(username).isEqualTo("");
    }

    @Test
    public void setAttribute_username_shouldPersist() {
        TldrPreferences.getInstance().setUsername("username");

        final String username = TldrPreferences.getInstance().getUsername();
        assertThat(username).isNotNull();
        assertThat(username).isEqualTo("username");
    }

    @Test
    public void getAttribute_accessToken_shouldReturnEmptyString() {
        final String accessToken = TldrPreferences.getInstance().getAccessToken();
        assertThat(accessToken).isNotNull();
        assertThat(accessToken).isEqualTo("");
    }

    @Test
    public void setAttribute_accessToken_shouldPersist() {
        TldrPreferences.getInstance().setAccessToken("access_token");

        final String accessToken = TldrPreferences.getInstance().getAccessToken();
        assertThat(accessToken).isNotNull();
        assertThat(accessToken).isEqualTo("access_token");
    }
}
