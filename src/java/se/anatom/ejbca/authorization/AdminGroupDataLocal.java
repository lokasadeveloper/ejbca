/*************************************************************************
 *                                                                       *
 *  EJBCA: The OpenSource Certificate Authority                          *
 *                                                                       *
 *  This software is free software; you can redistribute it and/or       *
 *  modify it under the terms of the GNU Lesser General Public           *
 *  License as published by the Free Software Foundation; either         *
 *  version 2.1 of the License, or any later version.                    *
 *                                                                       *
 *  See terms of license at gnu.org.                                     *
 *                                                                       *
 *************************************************************************/
 
package se.anatom.ejbca.authorization;

import java.util.Collection;
/**
 * For docs, see AdminGroupDataBean
 *
 * @version $Id: AdminGroupDataLocal.java,v 1.2 2004-04-16 07:38:57 anatom Exp $
 **/
public interface AdminGroupDataLocal extends javax.ejb.EJBLocalObject {

    // public methods

    public String getAdminGroupName();
    public void setAdminGroupName(String admingroupname);
    
    public int getCAId();
    public void setCAId(int caid);

    /**
     * Adds a Collection of AccessRule to the database. Changing their values if they already exists
     *
     */

    public void addAccessRules(Collection accessrules);

     /**
     * Removes a Collection of (String) accessrules from the database.
     *
     */
    public void removeAccessRules(Collection accessrules);

     /**
     * Returns the number of access rules in admingroup
     *
     * @return the number of accessrules in the database
     */
    public int getNumberOfAccessRules();

     /**
      * Returns all the accessrules as a Collection of AccessRules
      *
      */
    public Collection getAccessRuleObjects();

     /**
     * Adds a Collection of AdminEntity to the database. Changing their values if they already exists
     *
     */

    public void addAdminEntities(Collection adminentities);

     /**
     * Removes a Collection if AdminEntity from the database.
     *
     */
    public void removeAdminEntities(Collection adminentities);

     /**
     * Returns the number of user entities in admingroup
     *
     * @return the number of user entities in the database
     */
    public int getNumberOfAdminEntities();

     /**
      * Returns all the adminentities as Collection of AdminEntity.
      *
      */
    public Collection getAdminEntityObjects();

     /**
      * Returns the data in admingroup representation.
      */
    public AdminGroup getAdminGroup();
    
     /**
      * Returns an AdminGroup object only containing name and caid and no access data.
      */
    public AdminGroup getAdminGroupNames();

}

