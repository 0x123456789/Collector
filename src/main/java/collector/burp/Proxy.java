package collector.burp;

import burp.api.montoya.http.message.params.ParsedHttpParameter;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.proxy.http.InterceptedResponse;
import burp.api.montoya.proxy.http.ProxyResponseHandler;
import burp.api.montoya.proxy.http.ProxyResponseReceivedAction;
import burp.api.montoya.proxy.http.ProxyResponseToBeSentAction;

import collector.Model;
import collector.gui.CollectorTab;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;
import static collector.Ruler.isNeeded;

public class Proxy implements ProxyResponseHandler {
    private final CollectorTab ui;
    private final Model model;
    public Proxy(Model model, CollectorTab ui) {
        this.model = model;
        this.ui = ui;

        this.ui.getTree().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String[] listData = model.getListByPath(ui.getSelectionPath());
                ui.updateList(listData);
            }
        });

        this.ui.getCopyListButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ui.copyAllListItems();
            }
        });
    }

    @Override
    public ProxyResponseReceivedAction handleResponseReceived(InterceptedResponse interceptedResponse) {
        HttpRequest request = interceptedResponse.initiatingRequest();
        // get data from request
        String host = request.httpService().toString();
        List<ParsedHttpParameter> params = request.parameters();

        for (ParsedHttpParameter parameter : params) {
            if (isNeeded(parameter.name())) {
                // if new host added to model we add new element to root
                if (model.addUnique(host, parameter.name(), parameter.value())) {
                    ui.updateTree(host, parameter.name(), parameter.value());
                }
            }
        }
        return ProxyResponseReceivedAction.continueWith(interceptedResponse);
    }

    @Override
    public ProxyResponseToBeSentAction handleResponseToBeSent(InterceptedResponse interceptedResponse) {
        return ProxyResponseToBeSentAction.continueWith(interceptedResponse);
    }
}
