package com.epam.events.Helpers;

import com.epam.reportportal.message.ReportPortalMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.File;

public class Watcher implements AfterTestExecutionCallback {
    protected static final Logger log = LogManager.getLogger("binary_data_logger");

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        ReportPortalMessage message;

        Boolean testResult = context.getExecutionException().isPresent();

        //false - SUCCESS, true - FAILED
        if(testResult)  {
            Helpers help = new Helpers();
            log.info("Test failed");

            message = new ReportPortalMessage
                    (new File(help.takeScreenshot()), "Failed test screenshot");

            log.info(message);
        } else {
            Helpers help = new Helpers();
            log.info("Test succeed");

            message = new ReportPortalMessage
                    (new File(help.takeScreenshot()), "Succeed test screenshot");
            log.info(message);
        }
    }
}
