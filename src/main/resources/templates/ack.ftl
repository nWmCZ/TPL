<?xml version="1.0" encoding="UTF-8"?>
<Acknowledgement_MarketDocument xmlns="urn:iec62325.351:tc57wg16:451-1:acknowledgementdocument:7:0">

    <mRID>${mRID}</mRID>
    <createdDateTime>${createdDateTime}</createdDateTime>

    <sender_MarketParticipant.mRID codingScheme="A01">10X1001A1001A450</sender_MarketParticipant.mRID>
    <sender_MarketParticipant.marketRole.type>A32</sender_MarketParticipant.marketRole.type>

    <receiver_MarketParticipant.mRID codingScheme="A01">10X1001A1001A57U</receiver_MarketParticipant.mRID>
    <receiver_MarketParticipant.marketRole.type>A07</receiver_MarketParticipant.marketRole.type>

    <received_MarketDocument.mRID>${marketDocumentMRID}</received_MarketDocument.mRID>
    <received_MarketDocument.revisionNumber>${marketDocumentVersion}</received_MarketDocument.revisionNumber>

    <received_MarketDocument.createdDateTime>${marketDocumentCreatedDateTime}</received_MarketDocument.createdDateTime>

    <Reason>
        <code>${reason}</code>
        <#if reasonText??>
        <text>${reasonText}</text>
        </#if>
    </Reason>

</Acknowledgement_MarketDocument>
