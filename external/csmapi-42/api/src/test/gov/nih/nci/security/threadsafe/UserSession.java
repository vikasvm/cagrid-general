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
package test.gov.nih.nci.security.threadsafe;

public class UserSession implements Runnable{
	private final String name;
    private final int delay;
    public UserSession(String name, int delay) {
      this.name = name;
      this.delay = delay;
    }
    
    public void run() {
        System.out.println("Starting: " + name);
        try {
          Thread.sleep(delay);
          /**
           * Do your work here.
           */
          CSMAPIMySQLDeadLockTest sc = new CSMAPIMySQLDeadLockTest(name);
          //SessionCallsPatchTest sc = new SessionCallsPatchTest(name);
          sc.doWork();
        } catch (InterruptedException ignored) {
        	ignored.printStackTrace();
        }
        System.out.println("Done with: " + name);
      }


}
