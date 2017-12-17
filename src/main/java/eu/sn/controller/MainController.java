package eu.sn.controller;

import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import eu.sn.ack.ReasonCode;
import eu.sn.ecp.ECPClient;
import eu.sn.ecp.ECPContentBuilder;
import eu.sn.model.Ack;
import eu.sn.model.AckRepository;
import eu.sn.model.Message;
import eu.sn.model.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static eu.sn.ack.AckBuilder.createAck;

@SpringUI
public class MainController extends UI {

    Grid<Message> messageGrid = new Grid<>();
    Grid<Ack> ackGrid = new Grid<>();

    FileDownloader fileDownloader;

    @Autowired
    ECPContentBuilder ecpContentBuilder;

    @Autowired
    ECPClient ecpClient;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    AckRepository ackRepository;

    @Override
    protected void init(VaadinRequest request) {

        messageGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        messageGrid.addColumn(Message::getDocumentId).setCaption("Document ID");
        messageGrid.addColumn(Message::getVersion).setCaption("Version");
        messageGrid.addColumn(Message::getCreatedDateTime).setCaption("Created");
        messageGrid.setWidth("100%");

        ackGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        ackGrid.addColumn(Ack::getmRID).setCaption("ACK ID");
        ackGrid.addColumn(Ack::getReasonCode).setCaption("Reason Code");
        ackGrid.addColumn(Ack::getReasonText).setCaption("Reason Text");
        ackGrid.addColumn(Ack::getMessage).setCaption("Original message");
        ackGrid.addColumn(Ack::getCreatedDateTime).setCaption("ACK created");
        ackGrid.setWidth("100%");

        Label heading = new Label("Transparency Platform Lite");

        // First line for Message ID
        HorizontalLayout upperLine = new HorizontalLayout();

        TextField documentIdTextField = new TextField();

        DateTimeField dateTimeField = new DateTimeField();

        Button addMessageButton = new Button("Add message");
        addMessageButton.addClickListener(click -> {
            if (documentIdTextField.getValue() == null || dateTimeField.getValue() == null) {
                Notification.show("All input fields must be fulfilled");
            } else {
                Message m = new Message(documentIdTextField.getValue(), 1, dateTimeField.getValue());
                messageRepository.save(m);
                updateGrid();
            }
        });

        Button downloadMessageButton = new Button("Download selected message");
        downloadMessageButton.addClickListener(click -> Notification.show("Not implemented yet"));

        upperLine.addComponents(documentIdTextField, dateTimeField, addMessageButton, downloadMessageButton);

        HorizontalLayout ackLine = new HorizontalLayout();

        NativeSelect<ReasonCode> reasonCodeNativeSelect = new NativeSelect<>();
        reasonCodeNativeSelect.setItems(ReasonCode.A01, ReasonCode.A02);

        TextField ackReasonText = new TextField();
        ackReasonText.setDescription("Optional Reason");

        Button sendAckButton = new Button("Send ACK");
        sendAckButton.addClickListener(event -> {

            if (messageGrid.getSelectionModel().getFirstSelectedItem().isPresent()) {
                Message selectedMessage = messageGrid.getSelectionModel().getFirstSelectedItem().get();

                Ack ack; // = new Ack();

                if (reasonCodeNativeSelect.getValue() == ReasonCode.A01) {
                    ack = createAck(selectedMessage, ReasonCode.A01, ackReasonText.getValue().length() > 0 ? ackReasonText.getValue() : null);
                    ackRepository.save(ack);
                    updateGrid();
                } else if (reasonCodeNativeSelect.getValue() == ReasonCode.A02 && ackReasonText.getValue() != null && ackReasonText.getValue().length() > 0) {
                    ack = createAck(selectedMessage, ReasonCode.A02, ackReasonText.getValue());
                    ackRepository.save(ack);
                    updateGrid();
                } else {
                    Notification.show("All input fields must be fulfilled");
                }

            } else {
                Notification.show("No Message selected");
            }
        });

        Button downloadAckButton = new Button("Download selected ACK");
        downloadAckButton.addClickListener(click -> {
            if (ackGrid.getSelectionModel().getFirstSelectedItem().isPresent()) {
                Ack selectedAck = ackGrid.getSelectionModel().getFirstSelectedItem().get();

                StreamResource myResource = createResource(selectedAck);
                fileDownloader = new FileDownloader(myResource);
                fileDownloader.extend(downloadAckButton);

            } else {
                Notification.show("No ACK selected");
            }
        });

        ackLine.addComponents(reasonCodeNativeSelect, ackReasonText, sendAckButton, downloadAckButton);

        // Global layout
        VerticalLayout verticalLayout = new VerticalLayout();

        verticalLayout.addComponents(heading, upperLine, messageGrid, ackLine, ackGrid);

        setContent(verticalLayout);

        updateGrid();
    }

    private StreamResource createResource(Ack ack) {

        return new StreamResource(new StreamResource.StreamSource() {
            @Override
            public InputStream getStream() {

                try {
                    return new ByteArrayInputStream(ecpContentBuilder.createECPContent(ack));
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }

            }
        }, ack.getmRID() + ".xml");
    }

    private void updateGrid() {
        messageGrid.setItems(messageRepository.findAll());
        ackGrid.setItems(ackRepository.findAll());
    }
}
