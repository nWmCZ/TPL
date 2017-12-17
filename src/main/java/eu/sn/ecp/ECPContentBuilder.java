package eu.sn.ecp;

import eu.sn.model.Ack;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@Service
public class ECPContentBuilder {

    Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);


    public ECPContentBuilder() throws Exception {
        cfg.setDirectoryForTemplateLoading(new File("/Work/git-sources-idea/TPL/src/main/resources/templates"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
    }

    public byte[] createECPContent(Ack ack) throws Exception {

        Map root = new HashMap();
        root.put("mRID", ack.getmRID());
        root.put("createdDateTime", ack.getCreatedDateTime());
        root.put("marketDocumentMRID", ack.getMessage().getDocumentId());
        root.put("marketDocumentVersion", ack.getMessage().getVersion());
        root.put("marketDocumentCreatedDateTime", ack.getMessage().getCreatedDateTime());
        root.put("reason", ack.getReasonCode());

        if (ack.getReasonText() != null) {
            root.put("reasonText", ack.getReasonText());
        }

        /* Get the template (uses cache internally) */
        Template temp = cfg.getTemplate("ack.ftl");

        /* Merge data-model with template */
        Writer out = new StringWriter();
        temp.process(root, out);

        return out.toString().getBytes("UTF-8");
    }

}
