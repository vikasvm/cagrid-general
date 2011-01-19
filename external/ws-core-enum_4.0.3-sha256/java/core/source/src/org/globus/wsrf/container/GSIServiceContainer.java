/*
 * Portions of this file Copyright 1999-2005 University of Chicago
 * Portions of this file Copyright 1999-2005 The University of Southern California.
 *
 * This file or a portion of this file is licensed under the
 * terms of the Globus Toolkit Public License, found at
 * http://www.globus.org/toolkit/download/license.html.
 * If you redistribute this file, with or without
 * modifications, you must include this notice in the file.
 */
package org.globus.wsrf.container;

import java.util.Map;

import javax.security.auth.Subject;

import org.apache.axis.AxisEngine;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.globus.axis.util.Util;
import org.globus.gsi.gssapi.JaasGssUtil;
import org.globus.wsrf.impl.security.descriptor.ContainerSecurityConfig;
import org.ietf.jgss.GSSCredential;

public class GSIServiceContainer extends ServiceContainer {

    private static Log logger =
        LogFactory.getLog(GSIServiceContainer.class.getName());

    static {
        Util.registerTransport();
    }

    public GSIServiceContainer(Map properties) throws ContainerException {
        super(properties);
    }

    protected ServiceDispatcher createServiceDispatcher() throws Exception {
        return new GSIServiceDispatcher(this.properties);
    }

    public String getProtocol() {
        return "https";
    }

}


class GSIServiceDispatcher extends ServiceDispatcher {
    private GSSCredential credentials;

    public GSIServiceDispatcher(Map properties) throws Exception {
        super(properties);
    }

    protected void setupThreadPool() throws Exception {

        Subject subject = null;        
        if (this.containerDescriptor != null) {
            subject = this.containerDescriptor.getSubject();
            ContainerSecurityConfig.getConfig().setSubject(subject);
        } else if (subject == null) {
            ContainerSecurityConfig config =
                ContainerSecurityConfig.getConfig();
            config.refresh();
            subject = config.getSubject();
        }

        if (subject == null) {
            throw new Exception(i18n.getMessage("noValidCreds"));
        }
        this.credentials = JaasGssUtil.getCredential(subject);
        this.threadPool = new GSIServiceThreadPool(this.queue, this.engine);
    }
}


class GSIServiceThreadPool extends ServiceThreadPool {
    private GSSCredential credentials;

    public GSIServiceThreadPool(
        ServiceRequestQueue queue,
        AxisEngine engine) {
        super(queue, engine);
    }

    protected ServiceThread createThread() {
        return new GSIServiceThread(this.queue, this, this.engine);
    }
}