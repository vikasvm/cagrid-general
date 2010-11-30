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
package org.globus.wsrf.impl.notification;

import java.rmi.RemoteException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.globus.wsrf.Subscription;
import org.globus.wsrf.ResourceContext;
import org.globus.wsrf.NoSuchResourceException;
import org.globus.wsrf.utils.Resources;
import org.globus.wsrf.utils.FaultHelper;
import org.globus.util.I18n;

import org.oasis.wsn.ResourceUnknownFaultType;
import org.oasis.wsn.ResumeFailedFaultType;
import org.oasis.wsn.ResumeSubscription;
import org.oasis.wsn.ResumeSubscriptionResponse;

public class ResumeSubscriptionProvider
{
    static Log logger =
        LogFactory.getLog(ResumeSubscriptionProvider.class.getName());
    private static I18n i18n = I18n.getI18n(Resources.class.getName());

    /**
     * Provider for the resume operation of the subscription manager porttype
     *
     * @param request
     * @return null
     * @throws RemoteException
     * @throws ResourceUnknownFaultType
     * @throws ResumeFailedFaultType
     */
    public ResumeSubscriptionResponse resumeSubscription(ResumeSubscription request)
        throws RemoteException,
               ResourceUnknownFaultType,
               ResumeFailedFaultType
    {
        Object resource = null;
        try
        {
            resource = ResourceContext.getResourceContext().getResource();
        }
        catch(NoSuchResourceException e)
        {
            ResourceUnknownFaultType fault = new ResourceUnknownFaultType();
            FaultHelper faultHelper = new FaultHelper(fault);
            faultHelper.setDescription(
                          i18n.getMessage("resourceDisoveryFailed")
            );
            faultHelper.addFaultCause(e);
            throw fault;
        }
        catch(Exception e)
        {
            throw new RemoteException(
                i18n.getMessage("resourceDisoveryFailed"), e);
        }

        try
        {
            // FIXME: cast
            ((Subscription) resource).resume();
        }
        catch(Exception e)
        {
            ResumeFailedFaultType fault = new ResumeFailedFaultType();
            FaultHelper faultHelper = new FaultHelper(fault);
            faultHelper.addFaultCause(e);
            throw fault;
        }

        return new ResumeSubscriptionResponse();
    }
}