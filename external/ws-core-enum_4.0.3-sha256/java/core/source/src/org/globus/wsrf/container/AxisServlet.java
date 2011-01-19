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

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.axis.AxisFault;
import org.apache.axis.AxisProperties;
import org.apache.axis.Constants;
import org.apache.axis.EngineConfigurationFactory;
import org.apache.axis.MessageContext;
import org.apache.axis.configuration.EngineConfigurationFactoryFinder;
import org.apache.axis.server.AxisServer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.globus.axis.util.Util;
import org.globus.wsrf.config.ContainerConfig;
import org.globus.wsrf.container.usage.ContainerUsageBasePacket;

public class AxisServlet extends org.apache.axis.transport.http.AxisServlet {
    
    private static Log logger =
        LogFactory.getLog(AxisServlet.class.getName());

    static {
        // a hack - forces the class to load and initialize the AxisProperties
        Class c = EngineConfigurationFactoryFinder.class;
        // this then overwires the properties with our own cfg class
        AxisProperties.setClassDefaults(
           EngineConfigurationFactory.class,
           new String[] {
            "org.globus.axis.configuration.EngineConfigurationFactoryServlet",
            "org.apache.axis.configuration.EngineConfigurationFactoryServlet",
            "org.apache.axis.configuration.EngineConfigurationFactoryDefault"}
        );

        Util.registerTransport();
    }

    public void init() throws ServletException {
        super.init();

        AxisServer server = null;
        try {
            server = getEngine();
        } catch (AxisFault e) {
            throw new ServletException(e);
        }

        ContainerConfig config = ContainerConfig.getConfig(server);
        ServletConfig servletConfig = getServletConfig();
        ServletContext context = servletConfig.getServletContext();

        // XXX: always overwrite web context
        File rootFile = new File(context.getRealPath ("/"));
        String rootContext = rootFile.getName();
            
        config.setOption(ContainerConfig.WEB_CONTEXT, rootContext);

        String webInfPath = getWebInfPath();
        String homeDir = getHomeDir();

        BaseContainerConfig.setEngine(server);
        BaseContainerConfig.setBaseDirectory(webInfPath);
        BaseContainerConfig.setSchemaDirectory(homeDir);

        // set basic msg properties
        MessageContext msgContext = new MessageContext(server);
        msgContext.setProperty(Constants.MC_CONFIGPATH, webInfPath);
        msgContext.setProperty(Constants.MC_HOME_DIR, homeDir);

        // set config profile info
        String configProfile = 
            servletConfig.getInitParameter(ContainerConfig.CONFIG_PROFILE);
        if (configProfile != null) {
            msgContext.setProperty(ContainerConfig.CONFIG_PROFILE,
                                   configProfile);
        }

        // set container type
        UsageConfig.setContainerType(
                      ContainerUsageBasePacket.SERVLET_CONTAINER);

        // ensure host info is ok
        String host = null;
        try {
            host = ServiceHost.getHost(config);
        } catch (IOException e) {
            throw new ServletException(e);
        }

        // configure default protocol & port if needed by
        // services during initialization on startup
        String protocol = servletConfig.getInitParameter("defaultProtocol");
        String port = servletConfig.getInitParameter("defaultPort");
        if (protocol != null && port != null) {
            ServiceHost.setDefaults(protocol, host, Integer.parseInt(port));
        }
        
        // set serverID - step 1) lookup system property
        String serverID = 
            System.getProperty(ContainerConfig.CONTAINER_ID_PROPERTY);
        if (serverID == null) {
            // set serverID - step 2) get it from configuration
            serverID = config.getOption(ContainerConfig.CONTAINER_ID);
            if (serverID == null) {
                // set serverID - step 3) default to host-webapp
                serverID = host + "-" + rootContext;
            }
        }
        BaseContainerConfig.setContainerID(serverID);

        try {
            ServiceManager.getServiceManager(server).start(msgContext);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    public void destroy() {
        AxisServer server = null;
        try {
            server = getEngine();
        } catch (AxisFault e) {
            logger.warn("", e);
            return;
        }
        ServiceManager.getServiceManager(server).stop();
        super.destroy();
    }
    
}