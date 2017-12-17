package eu.sn.model;

import eu.sn.ack.ReasonCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
public class Ack {
    @Id
    @GeneratedValue
    private Long id;
    private String mRID;
    private LocalDateTime createdDateTime;
    @OneToOne
    private Message message;
    private ReasonCode reasonCode;
    private String reasonText;
    private boolean sent;

    public Ack() {
    }

    public Ack(String mRID, LocalDateTime ackCreatedDateTime, Message message) {
        this.mRID = mRID;
        this.createdDateTime = ackCreatedDateTime;
        this.message = message;
    }

    public ReasonCode getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(ReasonCode reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getReasonText() {
        return reasonText;
    }

    public void setReasonText(String reasonText) {
        this.reasonText = reasonText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getmRID() {
        return mRID;
    }

    public void setmRID(String mRID) {
        this.mRID = mRID;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    @Override
    public String toString() {
        return "Ack{" +
                "id=" + id +
                ", mRID='" + mRID + '\'' +
                ", createdDateTime=" + createdDateTime +
                ", message=" + message +
                ", reasonCode=" + reasonCode +
                ", reasonText='" + reasonText + '\'' +
                ", sent=" + sent +
                '}';
    }
}
