package pages.aem;

public class RenameUploadPage extends AemBasePage {
    private static RenameUploadPage instance;
    public static RenameUploadPage Instance = (instance != null) ? instance : new RenameUploadPage();

    public RenameUploadPage(){ pageURL = "/moveAssets.html"; }
}
