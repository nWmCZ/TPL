package eu.sn.controller;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import eu.sn.ecp.ECPClient;
import eu.sn.model.Ack;
import eu.sn.model.AckRepository;
import eu.sn.model.Message;
import eu.sn.model.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
public class MainController extends UI {

    Grid<Message> messageGrid = new Grid<>();
    Grid<Ack> ackGrid = new Grid<>();

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
        heading.setStyleName(ValoTheme.LABEL_H1);

        // First line for Message ID
        HorizontalLayout upperLine = new HorizontalLayout();

        Button refreshButton = new Button("Refresh");
        refreshButton.addClickListener(click -> { updateGrid(); });

        Button downloadMessageButton = new Button("Download selected message");
        downloadMessageButton.addClickListener(click -> Notification.show("Not implemented yet"));

        upperLine.addComponents(refreshButton, downloadMessageButton);

        HorizontalLayout ackLine = new HorizontalLayout();

        Button downloadAckButton = new Button("Download selected ACK");
        downloadAckButton.addClickListener(click -> Notification.show("Not implemented yet"));

        ackLine.addComponents(downloadAckButton);

        // Global layout
        VerticalLayout verticalLayout = new VerticalLayout();

        verticalLayout.addComponents(heading, upperLine, messageGrid, ackLine, ackGrid);

        setContent(verticalLayout);

        updateGrid();
    }

    private void updateGrid() {
        messageGrid.setItems(messageRepository.findAll());
        ackGrid.setItems(ackRepository.findAll());
    }
}
