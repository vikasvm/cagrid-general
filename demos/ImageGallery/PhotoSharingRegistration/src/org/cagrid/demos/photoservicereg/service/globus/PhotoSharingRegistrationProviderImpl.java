package org.cagrid.demos.photoservicereg.service.globus;

import org.cagrid.demos.photoservicereg.service.PhotoSharingRegistrationImpl;

import java.rmi.RemoteException;

/** 
 * DO NOT EDIT:  This class is autogenerated!
 *
 * This class implements each method in the portType of the service.  Each method call represented
 * in the port type will be then mapped into the unwrapped implementation which the user provides
 * in the PhotoSharingRegistrationImpl class.  This class handles the boxing and unboxing of each method call
 * so that it can be correclty mapped in the unboxed interface that the developer has designed and 
 * has implemented.  Authorization callbacks are automatically made for each method based
 * on each methods authorization requirements.
 * 
 * @created by Introduce Toolkit version 1.3
 * 
 */
public class PhotoSharingRegistrationProviderImpl{
	
	PhotoSharingRegistrationImpl impl;
	
	public PhotoSharingRegistrationProviderImpl() throws RemoteException {
		impl = new PhotoSharingRegistrationImpl();
	}
	

    public org.cagrid.demos.photoservicereg.stubs.RegisterPhotoSharingServiceResponse registerPhotoSharingService(org.cagrid.demos.photoservicereg.stubs.RegisterPhotoSharingServiceRequest params) throws RemoteException, org.cagrid.demos.photoservicereg.stubs.types.RegistrationException {
    org.cagrid.demos.photoservicereg.stubs.RegisterPhotoSharingServiceResponse boxedResult = new org.cagrid.demos.photoservicereg.stubs.RegisterPhotoSharingServiceResponse();
    impl.registerPhotoSharingService(params.getHostIdentity());
    return boxedResult;
  }

    public org.cagrid.demos.photoservicereg.stubs.UnregisterPhotoSharingServiceResponse unregisterPhotoSharingService(org.cagrid.demos.photoservicereg.stubs.UnregisterPhotoSharingServiceRequest params) throws RemoteException, org.cagrid.demos.photoservicereg.stubs.types.RegistrationException {
    org.cagrid.demos.photoservicereg.stubs.UnregisterPhotoSharingServiceResponse boxedResult = new org.cagrid.demos.photoservicereg.stubs.UnregisterPhotoSharingServiceResponse();
    impl.unregisterPhotoSharingService(params.getHostIdentity());
    return boxedResult;
  }

}