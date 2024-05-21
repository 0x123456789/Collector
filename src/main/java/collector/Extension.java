package collector;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import burp.api.montoya.logging.Logging;
import collector.burp.Proxy;
import collector.gui.CollectorTab;

//Burp will auto-detect and load any class that extends BurpExtension.
public class Extension implements BurpExtension
{
    @Override
    public void initialize(MontoyaApi api)
    {
        Model model = new Model();
        CollectorTab ui = new CollectorTab();

        // set extension name
        api.extension().setName("Collector");
        api.proxy().registerResponseHandler(new Proxy(model, ui));
        api.userInterface().registerSuiteTab("Collector", ui.getUI());
        api.logging().logToOutput("[*] Collector version 0.4");
        api.logging().logToOutput("[+] Collector started");
    }
}