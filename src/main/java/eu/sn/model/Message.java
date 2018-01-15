package eu.sn.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
public class Message {
    @Id
    @GeneratedValue
    private Long id;
    private String documentId;
    private int version;
    private LocalDateTime createdDateTime;
    @OneToOne
    private Ack ack;
    private String senderMarketParticipant;
    private String senderMarketParticipantType;
    private String receiverMarketParticipant;
    private String receiverMarketParticipantType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public Ack getAck() {
        return ack;
    }

    public void setAck(Ack ack) {
        this.ack = ack;
    }

    public String getSenderMarketParticipant() {
        return senderMarketParticipant;
    }

    public void setSenderMarketParticipant(String senderMarketParticipant) {
        this.senderMarketParticipant = senderMarketParticipant;
    }

    public String getSenderMarketParticipantType() {
        return senderMarketParticipantType;
    }

    public void setSenderMarketParticipantType(String senderMarketParticipantType) {
        this.senderMarketParticipantType = senderMarketParticipantType;
    }

    public String getReceiverMarketParticipant() {
        return receiverMarketParticipant;
    }

    public void setReceiverMarketParticipant(String receiverMarketParticipant) {
        this.receiverMarketParticipant = receiverMarketParticipant;
    }

    public String getReceiverMarketParticipantType() {
        return receiverMarketParticipantType;
    }

    public void setReceiverMarketParticipantType(String receiverMarketParticipantType) {
        this.receiverMarketParticipantType = receiverMarketParticipantType;
    }

    public Message() {
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", documentId='" + documentId + '\'' +
                ", version=" + version +
                ", createdDateTime=" + createdDateTime +
                ", ack=" + ack +
                ", senderMarketParticipant='" + senderMarketParticipant + '\'' +
                ", senderMarketParticipantType='" + senderMarketParticipantType + '\'' +
                ", receiverMarketParticipant='" + receiverMarketParticipant + '\'' +
                ", receiverMarketParticipantType='" + receiverMarketParticipantType + '\'' +
                '}';
    }
}
