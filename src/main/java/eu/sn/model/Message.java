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

    public Message() {
    }

    public Message(String documentId, int version, LocalDateTime createdDateTime) {
        this.documentId = documentId;
        this.version = version;
        this.createdDateTime = createdDateTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", documentId='" + documentId + '\'' +
                ", version=" + version +
                ", createdDateTime=" + createdDateTime +
                ", ack=" + ack +
                '}';
    }
}
