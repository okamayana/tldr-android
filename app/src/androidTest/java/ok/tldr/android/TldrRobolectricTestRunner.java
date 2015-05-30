package ok.tldr.android;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricTestRunner;

public class TldrRobolectricTestRunner extends RobolectricTestRunner {

    private static final String KEY_PACKAGE = "android.package";
    private static final String KEY_MANIFEST = "android.manifest";
    private static final String KEY_RESOURCES = "android.resources";
    private static final String KEY_ASSETS = "android.assets";

    private static final String PATH_MANIFEST = "build/intermediates/manifests/full/";
    private static final String PATH_RESOURCES = "build/intermediates/res/";
    private static final String PATH_ASSETS = "build/intermediates/assets/";

    public TldrRobolectricTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);

        final String buildVariant = BuildConfig.BUILD_TYPE + (BuildConfig.FLAVOR.isEmpty()? "" : "/" + BuildConfig.FLAVOR);

        System.setProperty(KEY_PACKAGE, BuildConfig.APPLICATION_ID);
        System.setProperty(KEY_MANIFEST, PATH_MANIFEST + buildVariant + "/AndroidManifest.xml");
        System.setProperty(KEY_RESOURCES, PATH_RESOURCES + buildVariant);
        System.setProperty(KEY_ASSETS, PATH_ASSETS + buildVariant);
    }
}
