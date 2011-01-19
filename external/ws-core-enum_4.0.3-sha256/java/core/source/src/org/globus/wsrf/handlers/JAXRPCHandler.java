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
package org.globus.wsrf.handlers;

import javax.xml.rpc.soap.SOAPFaultException;

import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;

/**
 * A handler that converts SOAPFaultException into AxisFault so that
 * onFault() method of previous handlers is properly executed
 */
public class JAXRPCHandler extends org.apache.axis.handlers.JAXRPCHandler {
    
    public void invoke(MessageContext msgContext) throws AxisFault {
        try {
            super.invoke(msgContext);
        } catch (SOAPFaultException e) {
            throw AxisFault.makeFault(e);
        }
    }
    
}