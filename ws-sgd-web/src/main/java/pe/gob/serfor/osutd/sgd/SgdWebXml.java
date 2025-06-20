package pe.gob.serfor.osutd.sgd;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import pe.gob.serfor.osutd.sgd.config.DefaultProfileUtil;



@SpringBootApplication
public class SgdWebXml extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		/**
         * set a default to use when no profile is configured.
         */
        DefaultProfileUtil.addDefaultProfile(application.application());
		return application.sources(SgdApp.class);
	}

}
