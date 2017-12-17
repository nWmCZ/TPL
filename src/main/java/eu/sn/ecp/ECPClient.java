package eu.sn.ecp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

@Service
public class ECPClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(ECPClient.class);

    public SendMessageResponse sendMessage(SentMessage message) {

        log.info("Sending message " + message);

        SendMessageRequest request = new SendMessageRequest();
        request.setMessage(message);

        SendMessageResponse response = (SendMessageResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://10.2.1.10:10002/ECP_MODULE/services/ECPEndpointService/",
                        request,
                        new SoapActionCallback("http://ecp.entso-e.eu/SendMessageResponse"));

        return response;
    }
}
