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
package org.globus.gatekeeper;

/**
 * BadRequestException indicates that a client made a bad request to the
 * server.  A bad request consist of syntax error, or while executing the
 * service, it returned a failured, not associated with security rights.
 */
public class BadRequestException extends GateKeeperException {

}
