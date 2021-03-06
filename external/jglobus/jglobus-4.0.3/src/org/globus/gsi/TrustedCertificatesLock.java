/**
*============================================================================
*  Copyright The Ohio State University Research Foundation, The University of Chicago - 
*	Argonne National Laboratory, Emory University, SemanticBits LLC, and 
*	Ekagra Software Technologies Ltd.
*
*  Distributed under the OSI-approved BSD 3-Clause License.
*  See http://ncip.github.com/cagrid-general/LICENSE.txt for details.
*============================================================================
**/
package org.globus.gsi;

public class TrustedCertificatesLock {
	private static TrustedCertificatesLock lock;


	private TrustedCertificatesLock() {

	}


	public static synchronized TrustedCertificatesLock getInstance() {
		if (lock == null) {
			lock = new TrustedCertificatesLock();
		}
		return lock;
	}

}
