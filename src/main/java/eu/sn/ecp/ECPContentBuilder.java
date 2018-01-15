package eu.sn.ecp;

import eu.sn.ack.ACKJAXB;
import eu.sn.configuration.Configuration;
import eu.sn.model.Ack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.StringWriter;
import java.io.Writer;

@SuppressWarnings("unchecked")
@Service
public class ECPContentBuilder {

    @Autowired
    Configuration configuration;

    public byte[] createECPContent(Ack ack) throws Exception {

        ACKJAXB ackjaxb = new ACKJAXB();

        ackjaxb.setmRID(ack.getmRID());
        ackjaxb.setCreatedDateTime(ack.getCreatedDateTime().toString().substring(0,19).concat("Z"));
        ackjaxb.setReceiver(ack.getMessage().getSenderMarketParticipant());
        ackjaxb.setReceiverType(ack.getMessage().getSenderMarketParticipantType());
        ackjaxb.setSender(ack.getMessage().getReceiverMarketParticipant());
        ackjaxb.setSenderType(ack.getMessage().getReceiverMarketParticipantType());
        ackjaxb.setReceivedMRID(ack.getMessage().getDocumentId());
        ackjaxb.setReceivedVersion(ack.getMessage().getVersion());
        ackjaxb.setReceivedCreatedDateTime(ack.getMessage().getCreatedDateTime().toString().substring(0,19).concat("Z"));
        ackjaxb.setReasonCode(ack.getReasonCode().toString());

        if (ack.getReasonText() != null) {
            ackjaxb.setReasonText(ack.getReasonText());
        }

        Writer out = new StringWriter();

        try {
            File file = new File(configuration.getOutAckDir());
            JAXBContext jaxbContext = JAXBContext.newInstance(ACKJAXB.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(ackjaxb, out);
            jaxbMarshaller.marshal(ackjaxb, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return out.toString().getBytes("UTF-8");
    }

}
