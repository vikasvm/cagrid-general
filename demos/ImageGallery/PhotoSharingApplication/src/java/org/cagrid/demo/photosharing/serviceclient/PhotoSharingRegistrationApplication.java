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
package org.cagrid.demo.photosharing.serviceclient;

import org.cagrid.demos.photoservicereg.client.PhotoSharingRegistrationClient;

public class PhotoSharingRegistrationApplication {

	public static void usage(){
		System.out.println(PhotoSharingRegistrationApplication.class.getName() + " -url <service url>");
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Running the Photo Sharing Registration Application");
		try{
			if(!(args.length < 2)){
				if(args[0].equals("-url")){
					String photoSharingRegistrationServiceURL = args[1];
					
					PhotoSharingRegistrationClient registrationClient = new PhotoSharingRegistrationClient(photoSharingRegistrationServiceURL);
					
					String hostIdentity = "/O=caBIG/OU=caGrid/OU=Training/OU=Services/CN=justin-permars-macbook-pro.local4";
//					registrationClient.registerPhotoSharingService(hostIdentity);
					
//					System.out.println("Registered " + hostIdentity + " as a photo sharing service");

					 hostIdentity = "/O=caBIG/OU=caGrid/OU=Training/OU=Services/CN=host/justin-permars-macbook-pro.local5";
//					registrationClient.registerPhotoSharingService(hostIdentity);
						System.out.println("Registered " + hostIdentity + " as a photo sharing service");
					
					try {
					 hostIdentity = "/O=caBIG/OU=caGrid/OU=Training/OU=Servistin-permars-macbook-pro.local";
					registrationClient.registerPhotoSharingService(hostIdentity);
					System.out.println("Registered " + hostIdentity + " as a photo sharing service");
					} catch(Exception e) {
						//ignore
					}

					try {
					
					 hostIdentity = "/O=caBIG/CNOU=caGrid/OU=Training/OU=Servistin-permars-macbook-pro.local";
					registrationClient.registerPhotoSharingService(hostIdentity);
					System.out.println("Registered " + hostIdentity + " as a photo sharing service");
					} catch(Exception e) {
						//ignore
					}

					
					/*
					System.out.println("Sleepy...");
					Thread.sleep(20000);
					
					registrationClient.unregisterPhotoSharingService(hostIdentity);
					*/
				} else {
					usage();
					System.exit(1);
				}
			} else {
				usage();
				System.exit(1);
			}

		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);

		}

	}

}
