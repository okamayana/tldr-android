package ok.tldr.android;

import org.junit.runners.model.InitializationError;
import org.robolectric.AndroidManifest;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.res.Fs;

public class TldrRobolectricTestRunner extends RobolectricTestRunner {

    private static final String MANIFEST_PATH = "../../../manifests/test/debug/AndroidManifest.xml";
    private static final String RES_PATH = "../../../res/test/debug";
    private static final String ASSETS_PATH = "../../../assets/test/debug";

    public TldrRobolectricTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    @Override
    protected AndroidManifest getAppManifest(Config config) {
        final String appPath = TldrRobolectricTestRunner.class
                .getProtectionDomain().getCodeSource().getLocation().getPath();

        final String manifestPath = appPath + MANIFEST_PATH;
        final String resPath = appPath + RES_PATH;
        final String assetsPath = appPath + ASSETS_PATH;

        return createAppManifest(Fs.fileFromPath(manifestPath), Fs.fileFromPath(resPath),
                Fs.fileFromPath(assetsPath));
    }
}
