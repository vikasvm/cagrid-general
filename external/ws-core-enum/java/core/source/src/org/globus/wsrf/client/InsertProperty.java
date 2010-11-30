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
package org.globus.wsrf.client;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.rpc.Stub;

import org.apache.axis.message.MessageElement;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
import org.globus.wsrf.utils.FaultHelper;
import org.globus.wsrf.utils.XmlUtils;
import org.oasis.wsrf.properties.InsertType;
import org.oasis.wsrf.properties.SetResourceProperties_Element;
import org.oasis.wsrf.properties.SetResourceProperties_PortType;
import org.oasis.wsrf.properties.WSResourcePropertiesServiceAddressingLocator;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class InsertProperty extends BaseClient {

    public static void main(String[] args) {
        InsertProperty client = new InsertProperty();
        client.setCustomUsage("propertyValueFile");

        Element values = null;
        FileInputStream in = null;
        try {
            CommandLine line = client.parse(args);

            List options = line.getArgList();
            if (options == null || options.isEmpty()) {
                throw new ParseException("Expected property value file");
            }
            in = new FileInputStream((String)options.get(0));
            values = XmlUtils.newDocument(in).getDocumentElement();
        } catch(ParseException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(COMMAND_LINE_ERROR);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(COMMAND_LINE_ERROR);
        } finally {
            if (in != null) {
                try { in.close(); } catch (Exception ee) {}
            }
        }

        ArrayList elementList = new ArrayList();
        NodeList list = values.getChildNodes();
        for (int i=0;i<list.getLength();i++) {
            Node n = list.item(i);
            if (n instanceof Element) {
                elementList.add(new MessageElement((Element)n));
            }
        }

        WSResourcePropertiesServiceAddressingLocator locator =
            new WSResourcePropertiesServiceAddressingLocator();

        try {
            SetResourceProperties_PortType port =
                locator.getSetResourcePropertiesPort(client.getEPR());
	    client.setOptions((Stub)port);
            
            InsertType insert = new InsertType();
            MessageElement [] elements = 
                (MessageElement[])elementList.toArray(new MessageElement[]{});
            insert.set_any(elements);
            
            SetResourceProperties_Element request = 
                new SetResourceProperties_Element();
            request.setInsert(insert);

            port.setResourceProperties(request);

        } catch(Exception e) {
            if (client.isDebugMode()) {
                FaultHelper.printStackTrace(e);
            } else {
                System.err.println("Error: " + FaultHelper.getMessage(e));
            }
            System.exit(APPLICATION_ERROR);
        }
    }

}
