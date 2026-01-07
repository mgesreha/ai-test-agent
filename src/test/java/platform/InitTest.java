package platform;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import platform.utils.PropertiesUtils;

public class InitTest {

    // Thread-local storage for thread-specific instances
    private static final ThreadLocal<Playwright> threadLocalPlaywright = new ThreadLocal<>();
    private static final ThreadLocal<Browser> threadLocalBrowser = new ThreadLocal<>();
    private static final ThreadLocal<Page> threadLocalPage = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> threadLocalContext = new ThreadLocal<>();

    private static volatile String domain;

    @BeforeMethod
    protected void createContextAndPage(){
        // Initialize Playwright and Browser for this thread
        Playwright playwright = Playwright.create();
        threadLocalPlaywright.set(playwright);

        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(getHeadless()));
        threadLocalBrowser.set(browser);

        // Create context and page
        BrowserContext context = browser.newContext();
        threadLocalContext.set(context);

        Page page = context.newPage();
        threadLocalPage.set(page);

        page.setViewportSize(1920, 1080);
    }

    @AfterMethod
    protected void closeContext() {
        Page page = threadLocalPage.get();
        if (page != null) {
            page.close();
            threadLocalPage.remove();
        }

        BrowserContext context = threadLocalContext.get();
        if (context != null) {
            context.close();
            threadLocalContext.remove();
        }

        Browser browser = threadLocalBrowser.get();
        if (browser != null) {
            browser.close();
            threadLocalBrowser.remove();
        }

        Playwright playwright = threadLocalPlaywright.get();
        if (playwright != null) {
            playwright.close();
            threadLocalPlaywright.remove();
        }
    }

    private static boolean getHeadless(){
        return PropertiesUtils.getProperties("test.properties").getProperty("headless").equals("true");
    }

    private static void setDomain(String domain){
        InitTest.domain = domain;
    }

    public static String getDomain(){
        return PropertiesUtils.getProperties("test.properties").getProperty("environment");
    }

    public static String getEnvironment() {
        return PropertiesUtils.getProperties("test.properties").getProperty("environment");
    }

    /**
     * Gets the thread-local page instance.
     */
    protected Page getPage() {
        return threadLocalPage.get();
    }

    /**
     * Gets the thread-local context instance.
     */
    protected BrowserContext getContext() {
        return threadLocalContext.get();
    }}


