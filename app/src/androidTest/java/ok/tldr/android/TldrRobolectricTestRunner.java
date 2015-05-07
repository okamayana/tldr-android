package ok.tldr.android;

import org.junit.runners.model.InitializationError;
import org.robolectric.AndroidManifest;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.res.Fs;

public class TldrRobolectricTestRunner extends RobolectricTestRunner {

    public TldrRobolectricTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    @Override
    protected AndroidManifest getAppManifest(Config config) {
        final String appPath = TldrRobolectricTestRunner.class
                .getProtectionDomain().getCodeSource().getLocation().getPath();

        final String manifestPath = appPath + "../../../manifests/test/debug/AndroidManifest.xml";
        final String resPath = appPath + "../../../res/test/debug";
        final String assetsPath = appPath + "../../../assets/test/debug";

        return createAppManifest(Fs.fileFromPath(manifestPath), Fs.fileFromPath(resPath), Fs.fileFromPath(assetsPath));
    }
}
