package eu.sn.ecp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ECPClientConfiguration {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("eu.sn.ecp");
        return marshaller;
    }

    @Bean
    public ECPClient ecpClient(Jaxb2Marshaller marshaller) {
        ECPClient client = new ECPClient();
        client.setDefaultUri("http://10.2.1.10:10002/ECP_MODULE/services/ECPEndpointService");

        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);

        return client;
    }
}
