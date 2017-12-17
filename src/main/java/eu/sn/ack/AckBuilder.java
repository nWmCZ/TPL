package eu.sn.ack;

import eu.sn.model.Ack;
import eu.sn.model.Message;

import java.time.LocalDateTime;

public class AckBuilder {

    public static Ack createAck(Message message, ReasonCode reasonCode, String reasonText) {
        Ack ack = new Ack();
        StringBuilder mrid = new StringBuilder("ACK_");
        mrid.append(reasonCode.toString()).append("_").append(message.getDocumentId());
        ack.setmRID(mrid.toString());
        ack.setCreatedDateTime(LocalDateTime.now());
        ack.setMessage(message);
        ack.setReasonCode(reasonCode);
        ack.setReasonText(reasonText);

        return ack;
    }
}
