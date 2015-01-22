package com.genie.commons.vertx;

import org.vertx.java.core.logging.Logger;

/**
 * Created by d032459 on 15/1/6.
 */
public class DummyLogger {
    final org.vertx.java.core.logging.Logger logger;
    public DummyLogger(org.vertx.java.core.logging.Logger logger) {
        this.logger = logger;
    }
    public DummyLogger() {
        logger = null;
    }

    public void error(String msg, Throwable e) {
        if(logger != null)
            logger.error(msg, e);
    }

}
