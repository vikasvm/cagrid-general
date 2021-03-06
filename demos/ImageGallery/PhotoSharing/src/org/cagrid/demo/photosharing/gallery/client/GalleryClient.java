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
package org.cagrid.demo.photosharing.gallery.client;

import java.io.InputStream;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;

import org.apache.axis.EngineConfiguration;
import org.apache.axis.client.AxisClient;
import org.apache.axis.client.Stub;
import org.apache.axis.configuration.FileProvider;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.apache.axis.types.URI.MalformedURIException;

import org.oasis.wsrf.properties.GetResourcePropertyResponse;

import org.globus.gsi.GlobusCredential;

import org.cagrid.demo.photosharing.gallery.stubs.GalleryPortType;
import org.cagrid.demo.photosharing.gallery.stubs.service.GalleryServiceAddressingLocator;
import org.cagrid.demo.photosharing.gallery.common.GalleryI;
import gov.nih.nci.cagrid.introduce.security.client.ServiceSecurityClient;

/**
 * This class is autogenerated, DO NOT EDIT GENERATED GRID SERVICE ACCESS METHODS.
 *
 * This client is generated automatically by Introduce to provide a clean unwrapped API to the
 * service.
 *
 * On construction the class instance will contact the remote service and retrieve it's security
 * metadata description which it will use to configure the Stub specifically for each method call.
 * 
 * @created by Introduce Toolkit version 1.3
 */
public class GalleryClient extends GalleryClientBase implements GalleryI {	

	public GalleryClient(String url) throws MalformedURIException, RemoteException {
		this(url,null);	
	}

	public GalleryClient(String url, GlobusCredential proxy) throws MalformedURIException, RemoteException {
	   	super(url,proxy);
	}
	
	public GalleryClient(EndpointReferenceType epr) throws MalformedURIException, RemoteException {
	   	this(epr,null);
	}
	
	public GalleryClient(EndpointReferenceType epr, GlobusCredential proxy) throws MalformedURIException, RemoteException {
	   	super(epr,proxy);
	}

	public static void usage(){
		System.out.println(GalleryClient.class.getName() + " -url <service url>");
	}
	
	public static void main(String [] args){
	    System.out.println("Running the Grid Service Client");
		try{
		if(!(args.length < 2)){
			if(args[0].equals("-url")){
			  GalleryClient client = new GalleryClient(args[1]);
			  // place client calls here if you want to use this main as a
			  // test....
			} else {
				usage();
				System.exit(1);
			}
		} else {
			usage();
			System.exit(1);
		}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

  public org.oasis.wsrf.lifetime.DestroyResponse destroy(org.oasis.wsrf.lifetime.Destroy params) throws RemoteException {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"destroy");
    return portType.destroy(params);
    }
  }

  public org.oasis.wsrf.lifetime.SetTerminationTimeResponse setTerminationTime(org.oasis.wsrf.lifetime.SetTerminationTime params) throws RemoteException {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"setTerminationTime");
    return portType.setTerminationTime(params);
    }
  }

  public org.cagrid.demo.photosharing.domain.ImageDescription addImage(org.cagrid.demo.photosharing.domain.Image image) throws RemoteException, org.cagrid.demo.photosharing.stubs.types.PhotoSharingException, org.cagrid.demo.photosharing.gallery.stubs.types.AuthorizationException {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"addImage");
    org.cagrid.demo.photosharing.gallery.stubs.AddImageRequest params = new org.cagrid.demo.photosharing.gallery.stubs.AddImageRequest();
    org.cagrid.demo.photosharing.gallery.stubs.AddImageRequestImage imageContainer = new org.cagrid.demo.photosharing.gallery.stubs.AddImageRequestImage();
    imageContainer.setImage(image);
    params.setImage(imageContainer);
    org.cagrid.demo.photosharing.gallery.stubs.AddImageResponse boxedResult = portType.addImage(params);
    return boxedResult.getImageDescription();
    }
  }

  public org.cagrid.demo.photosharing.domain.ImageDescription[] listImages() throws RemoteException, org.cagrid.demo.photosharing.gallery.stubs.types.AuthorizationException {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"listImages");
    org.cagrid.demo.photosharing.gallery.stubs.ListImagesRequest params = new org.cagrid.demo.photosharing.gallery.stubs.ListImagesRequest();
    org.cagrid.demo.photosharing.gallery.stubs.ListImagesResponse boxedResult = portType.listImages(params);
    return boxedResult.getImageDescription();
    }
  }

  public org.cagrid.demo.photosharing.domain.Image getImage(org.cagrid.demo.photosharing.domain.ImageDescription imageDescription) throws RemoteException, org.cagrid.demo.photosharing.gallery.stubs.types.AuthorizationException, org.cagrid.demo.photosharing.stubs.types.PhotoSharingException {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"getImage");
    org.cagrid.demo.photosharing.gallery.stubs.GetImageRequest params = new org.cagrid.demo.photosharing.gallery.stubs.GetImageRequest();
    org.cagrid.demo.photosharing.gallery.stubs.GetImageRequestImageDescription imageDescriptionContainer = new org.cagrid.demo.photosharing.gallery.stubs.GetImageRequestImageDescription();
    imageDescriptionContainer.setImageDescription(imageDescription);
    params.setImageDescription(imageDescriptionContainer);
    org.cagrid.demo.photosharing.gallery.stubs.GetImageResponse boxedResult = portType.getImage(params);
    return boxedResult.getImage();
    }
  }

  public void grantViewGalleryPrivileges(org.cagrid.demo.photosharing.domain.User user) throws RemoteException, org.cagrid.demo.photosharing.gallery.stubs.types.AuthorizationException, org.cagrid.demo.photosharing.stubs.types.PhotoSharingException {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"grantViewGalleryPrivileges");
    org.cagrid.demo.photosharing.gallery.stubs.GrantViewGalleryPrivilegesRequest params = new org.cagrid.demo.photosharing.gallery.stubs.GrantViewGalleryPrivilegesRequest();
    org.cagrid.demo.photosharing.gallery.stubs.GrantViewGalleryPrivilegesRequestUser userContainer = new org.cagrid.demo.photosharing.gallery.stubs.GrantViewGalleryPrivilegesRequestUser();
    userContainer.setUser(user);
    params.setUser(userContainer);
    org.cagrid.demo.photosharing.gallery.stubs.GrantViewGalleryPrivilegesResponse boxedResult = portType.grantViewGalleryPrivileges(params);
    }
  }

  public void revokeViewGalleryPrivileges(org.cagrid.demo.photosharing.domain.User user) throws RemoteException, org.cagrid.demo.photosharing.gallery.stubs.types.AuthorizationException, org.cagrid.demo.photosharing.stubs.types.PhotoSharingException {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"revokeViewGalleryPrivileges");
    org.cagrid.demo.photosharing.gallery.stubs.RevokeViewGalleryPrivilegesRequest params = new org.cagrid.demo.photosharing.gallery.stubs.RevokeViewGalleryPrivilegesRequest();
    org.cagrid.demo.photosharing.gallery.stubs.RevokeViewGalleryPrivilegesRequestUser userContainer = new org.cagrid.demo.photosharing.gallery.stubs.RevokeViewGalleryPrivilegesRequestUser();
    userContainer.setUser(user);
    params.setUser(userContainer);
    org.cagrid.demo.photosharing.gallery.stubs.RevokeViewGalleryPrivilegesResponse boxedResult = portType.revokeViewGalleryPrivileges(params);
    }
  }

  public void grantAddImagePrivileges(org.cagrid.demo.photosharing.domain.User user) throws RemoteException, org.cagrid.demo.photosharing.gallery.stubs.types.AuthorizationException, org.cagrid.demo.photosharing.stubs.types.PhotoSharingException {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"grantAddImagePrivileges");
    org.cagrid.demo.photosharing.gallery.stubs.GrantAddImagePrivilegesRequest params = new org.cagrid.demo.photosharing.gallery.stubs.GrantAddImagePrivilegesRequest();
    org.cagrid.demo.photosharing.gallery.stubs.GrantAddImagePrivilegesRequestUser userContainer = new org.cagrid.demo.photosharing.gallery.stubs.GrantAddImagePrivilegesRequestUser();
    userContainer.setUser(user);
    params.setUser(userContainer);
    org.cagrid.demo.photosharing.gallery.stubs.GrantAddImagePrivilegesResponse boxedResult = portType.grantAddImagePrivileges(params);
    }
  }

  public void revokeAddImagePrivileges(org.cagrid.demo.photosharing.domain.User user) throws RemoteException, org.cagrid.demo.photosharing.gallery.stubs.types.AuthorizationException, org.cagrid.demo.photosharing.stubs.types.PhotoSharingException {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"revokeAddImagePrivileges");
    org.cagrid.demo.photosharing.gallery.stubs.RevokeAddImagePrivilegesRequest params = new org.cagrid.demo.photosharing.gallery.stubs.RevokeAddImagePrivilegesRequest();
    org.cagrid.demo.photosharing.gallery.stubs.RevokeAddImagePrivilegesRequestUser userContainer = new org.cagrid.demo.photosharing.gallery.stubs.RevokeAddImagePrivilegesRequestUser();
    userContainer.setUser(user);
    params.setUser(userContainer);
    org.cagrid.demo.photosharing.gallery.stubs.RevokeAddImagePrivilegesResponse boxedResult = portType.revokeAddImagePrivileges(params);
    }
  }

  public void grantImageRetrievalPrivileges(org.cagrid.demo.photosharing.domain.ImageDescription imageDescription,org.cagrid.demo.photosharing.domain.User user) throws RemoteException, org.cagrid.demo.photosharing.gallery.stubs.types.AuthorizationException, org.cagrid.demo.photosharing.stubs.types.PhotoSharingException {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"grantImageRetrievalPrivileges");
    org.cagrid.demo.photosharing.gallery.stubs.GrantImageRetrievalPrivilegesRequest params = new org.cagrid.demo.photosharing.gallery.stubs.GrantImageRetrievalPrivilegesRequest();
    org.cagrid.demo.photosharing.gallery.stubs.GrantImageRetrievalPrivilegesRequestImageDescription imageDescriptionContainer = new org.cagrid.demo.photosharing.gallery.stubs.GrantImageRetrievalPrivilegesRequestImageDescription();
    imageDescriptionContainer.setImageDescription(imageDescription);
    params.setImageDescription(imageDescriptionContainer);
    org.cagrid.demo.photosharing.gallery.stubs.GrantImageRetrievalPrivilegesRequestUser userContainer = new org.cagrid.demo.photosharing.gallery.stubs.GrantImageRetrievalPrivilegesRequestUser();
    userContainer.setUser(user);
    params.setUser(userContainer);
    org.cagrid.demo.photosharing.gallery.stubs.GrantImageRetrievalPrivilegesResponse boxedResult = portType.grantImageRetrievalPrivileges(params);
    }
  }

  public void revokeImageRetrievalPrivileges(org.cagrid.demo.photosharing.domain.ImageDescription imageDescription,org.cagrid.demo.photosharing.domain.User user) throws RemoteException, org.cagrid.demo.photosharing.gallery.stubs.types.AuthorizationException, org.cagrid.demo.photosharing.stubs.types.PhotoSharingException {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"revokeImageRetrievalPrivileges");
    org.cagrid.demo.photosharing.gallery.stubs.RevokeImageRetrievalPrivilegesRequest params = new org.cagrid.demo.photosharing.gallery.stubs.RevokeImageRetrievalPrivilegesRequest();
    org.cagrid.demo.photosharing.gallery.stubs.RevokeImageRetrievalPrivilegesRequestImageDescription imageDescriptionContainer = new org.cagrid.demo.photosharing.gallery.stubs.RevokeImageRetrievalPrivilegesRequestImageDescription();
    imageDescriptionContainer.setImageDescription(imageDescription);
    params.setImageDescription(imageDescriptionContainer);
    org.cagrid.demo.photosharing.gallery.stubs.RevokeImageRetrievalPrivilegesRequestUser userContainer = new org.cagrid.demo.photosharing.gallery.stubs.RevokeImageRetrievalPrivilegesRequestUser();
    userContainer.setUser(user);
    params.setUser(userContainer);
    org.cagrid.demo.photosharing.gallery.stubs.RevokeImageRetrievalPrivilegesResponse boxedResult = portType.revokeImageRetrievalPrivileges(params);
    }
  }

  public java.lang.String getGalleryName() throws RemoteException, org.cagrid.demo.photosharing.stubs.types.PhotoSharingException {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"getGalleryName");
    org.cagrid.demo.photosharing.gallery.stubs.GetGalleryNameRequest params = new org.cagrid.demo.photosharing.gallery.stubs.GetGalleryNameRequest();
    org.cagrid.demo.photosharing.gallery.stubs.GetGalleryNameResponse boxedResult = portType.getGalleryName(params);
    return boxedResult.getResponse();
    }
  }

  public org.cagrid.demo.photosharing.domain.User[] listUsersWithAddPrivileges() throws RemoteException, org.cagrid.demo.photosharing.stubs.types.PhotoSharingException {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"listUsersWithAddPrivileges");
    org.cagrid.demo.photosharing.gallery.stubs.ListUsersWithAddPrivilegesRequest params = new org.cagrid.demo.photosharing.gallery.stubs.ListUsersWithAddPrivilegesRequest();
    org.cagrid.demo.photosharing.gallery.stubs.ListUsersWithAddPrivilegesResponse boxedResult = portType.listUsersWithAddPrivileges(params);
    return boxedResult.getUser();
    }
  }

  public org.cagrid.demo.photosharing.domain.User[] listAllUsersWithViewPrivileges() throws RemoteException, org.cagrid.demo.photosharing.stubs.types.PhotoSharingException {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"listAllUsersWithViewPrivileges");
    org.cagrid.demo.photosharing.gallery.stubs.ListAllUsersWithViewPrivilegesRequest params = new org.cagrid.demo.photosharing.gallery.stubs.ListAllUsersWithViewPrivilegesRequest();
    org.cagrid.demo.photosharing.gallery.stubs.ListAllUsersWithViewPrivilegesResponse boxedResult = portType.listAllUsersWithViewPrivileges(params);
    return boxedResult.getUser();
    }
  }

}
