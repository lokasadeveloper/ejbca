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

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.RemoveException;
import javax.naming.InitialContext;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.log4j.Logger;
import se.anatom.ejbca.BaseEntityBean;


/** Entity bean should not be used directly, use though Session beans.
 *
 * Entity Bean representing authorization admingroup.
 * Information stored:
 * <pre>
 * admingroupname
 * caid
 *
 * AccessRules
 * Admin entities
 * </pre>
 *
 * @version $Id: AdminGroupDataBean.java,v 1.4 2004-04-16 07:38:57 anatom Exp $
 */
public abstract class AdminGroupDataBean extends BaseEntityBean {

    private static Logger log = Logger.getLogger(AdminGroupDataBean.class);

    public abstract Integer getPK();
    public abstract void setPK(Integer pk);
    
    public abstract String getAdminGroupName();
    public abstract void setAdminGroupName(String admingroupname);

    public abstract int getCAId();
    public abstract void setCAId(int caid);    
    
    public abstract Collection getAdminEntities();
    public abstract void setAdminEntities(Collection adminentities);

    public abstract Collection getAccessRules();
    public abstract void setAccessRules(Collection accessrules);

     /**
     * @see se.anatom.ejbca.authorization.AdminGroupDataLocal
     */

    public void addAccessRules(Collection accessrules){
      Iterator iter = accessrules.iterator();
      while(iter.hasNext()){
        AccessRule accessrule = (AccessRule) iter.next();  
        try{
          AccessRulesDataLocal data = createAccessRule(accessrule);
         
          Iterator i =  getAccessRules().iterator();
          while(i.hasNext()){
            AccessRulesDataLocal ar = (AccessRulesDataLocal) i.next();
            if(ar.getAccessRuleObject().getAccessRule().equals(accessrule.getAccessRule())){
              getAccessRules().remove(ar);
              try{
                ar.remove();
              }catch(RemoveException e){ throw new EJBException(e.getMessage());}
              break;
             }
           }
          
          getAccessRules().add(data);
        }catch(Exception e){
        }
      } 
    } // addAccessRules

     /**
     * @see se.anatom.ejbca.authorization.AdminGroupDataLocal
     */
    public void removeAccessRules(Collection accessrules){
      Iterator iter = accessrules.iterator();
      while(iter.hasNext()){
        String accessrule = (String) iter.next();  
        
        Iterator i =  getAccessRules().iterator();
        while(i.hasNext()){
          AccessRulesDataLocal ar = (AccessRulesDataLocal) i.next();
          if(ar.getAccessRuleObject().getAccessRule().equals(accessrule)){
            getAccessRules().remove(ar);
            try{
              ar.remove();
            }catch(RemoveException e){ throw new EJBException(e.getMessage());}
            break;
          }
        }
      }  
    } // removeAccessRules

     /**
     * @see se.anatom.ejbca.authorization.AdminGroupDataLocal
     */
    public int getNumberOfAccessRules(){
       return  getAccessRules().size();
    } // getNumberOfAccessRules

     /**
     * @see se.anatom.ejbca.authorization.AdminGroupDataLocal
     */
    public Collection getAccessRuleObjects(){
      ArrayList returnval = new ArrayList();
      if(getAccessRules() != null){
        Iterator i =  getAccessRules().iterator();
        while(i.hasNext()){
          AccessRulesDataLocal ar = (AccessRulesDataLocal) i.next();
          returnval.add(ar.getAccessRuleObject());
        }
      }
      return returnval;
    } // getAccessRules

     /**
     * @see se.anatom.ejbca.authorization.AdminGroupDataLocal
     */

    public void addAdminEntities(Collection adminentities){
      Iterator iter = adminentities.iterator();  
      while(iter.hasNext()){  
        AdminEntity adminentity = (AdminEntity) iter.next();  
        try{
          AdminEntityDataLocal data = createAdminEntity(adminentity);
          AdminEntityPK datapk = new AdminEntityPK(getAdminGroupName(), getCAId(), adminentity.getMatchWith(), adminentity.getMatchType(), adminentity.getMatchValue());

          Iterator i =  getAdminEntities().iterator();
          while(i.hasNext()){
            AdminEntityDataLocal ue = (AdminEntityDataLocal) i.next();
            AdminEntityPK uepk= new AdminEntityPK(getAdminGroupName(), getCAId(), ue.getMatchWith()
                                                ,ue.getMatchType(),ue.getMatchValue());
            if(uepk.equals(datapk)){
              getAdminEntities().remove(ue);
              try{
                ue.remove();
              }catch(RemoveException e){ throw new EJBException(e.getMessage());}
              break;
           }
         }
         getAdminEntities().add(data);
         }catch(Exception e){}
      }  
    } // addAdminEntities


     /**
     * @see se.anatom.ejbca.authorization.AdminGroupDataLocal
     */
    public void removeAdminEntities(Collection adminentities){
      Iterator iter = adminentities.iterator();

      while(iter.hasNext()){
        AdminEntity adminentity = (AdminEntity) iter.next();
        AdminEntityPK datapk = new AdminEntityPK(getAdminGroupName(), getCAId(), adminentity.getMatchWith(), adminentity.getMatchType(),adminentity.getMatchValue());

        Iterator i =  getAdminEntities().iterator();
        while(i.hasNext()){
          AdminEntityDataLocal ue = (AdminEntityDataLocal) i.next();
          AdminEntityPK uepk= new AdminEntityPK(getAdminGroupName(), getCAId(), ue.getMatchWith(),ue.getMatchType(),ue.getMatchValue());
          if(uepk.equals(datapk)){
            getAdminEntities().remove(ue);
            try{
              ue.remove();
            }catch(RemoveException e){ throw new EJBException(e.getMessage());}
            break;
          }
        }  
      }
    } // removeAdminEntities

     /**
     * @see se.anatom.ejbca.authorization.AdminGroupDataLocal
     */
    public int getNumberOfAdminEntities(){
      return getAdminEntities().size();
    } // getNumberOfAdminEntities

     /**
     * @see se.anatom.ejbca.authorization.AdminGroupDataLocal
     */
    public Collection getAdminEntityObjects(){
      ArrayList returnval = new ArrayList();
      if(getAdminEntities() != null){
        Iterator i =  getAdminEntities().iterator();
        while(i.hasNext()){
          AdminEntityDataLocal ae = (AdminEntityDataLocal) i.next();
          returnval.add(ae.getAdminEntity(getCAId()));
        }
      }
      return returnval;
    } // getAdminEntityObjects

     /**
     * @see se.anatom.ejbca.authorization.AdminGroupDataLocal
     */
    public AdminGroup getAdminGroup(){
      ArrayList accessrules = new ArrayList();
      ArrayList adminentities = new ArrayList();

      Iterator i = null;
      if(getAdminEntities()!=null){
        i =  getAdminEntities().iterator();
        while(i.hasNext()){
          AdminEntityDataLocal ae = (AdminEntityDataLocal) i.next();
          adminentities.add(ae.getAdminEntity(getCAId()));
        }
      }

      if(getAccessRules()!=null){
        i =  getAccessRules().iterator();
        while(i.hasNext()){
          AccessRulesDataLocal ar = (AccessRulesDataLocal) i.next();
          accessrules.add(ar.getAccessRuleObject());
        }
      }

      return new AdminGroup(getPK().intValue() ,getAdminGroupName(), getCAId(), accessrules, adminentities);
    } // getAdminGroup 

     /**
     * @see se.anatom.ejbca.authorization.AdminGroupDataLocal
     */
    public AdminGroup getAdminGroupNames(){                    
      return new AdminGroup(getPK().intValue() , getAdminGroupName(), getCAId(), null, null);
    } // getAdminGroupNames
    //
    // Fields required by Container
    //

    /**
     * Entity Bean holding data of raadmin profilegroups.
     * @param admingroupname.
     *
     **/

    public Integer ejbCreate(Integer pk, String admingroupname, int caid) throws CreateException {        
        setPK(pk);
        setAdminGroupName(admingroupname);
        setCAId(caid);
        log.debug("Created admingroup : "+admingroupname);

        return pk;
    }

    public void ejbPostCreate(Integer pk, String admingroupname, int caid) {

        // Do nothing. Required.

    }

    // Private Methods.
    private AdminEntityDataLocal createAdminEntity(AdminEntity adminentity) throws CreateException, javax.naming.NamingException{
      AdminEntityDataLocal returnval = null;
      InitialContext initial = new InitialContext();
      AdminEntityDataLocalHome home = (AdminEntityDataLocalHome) initial.lookup("java:comp/env/ejb/AdminEntityDataLocal");
      returnval= home.create(getAdminGroupName(), getCAId(), adminentity.getMatchWith(), adminentity.getMatchType(), adminentity.getMatchValue());
      return returnval;
    } // createAdminEntity

    private AccessRulesDataLocal createAccessRule(AccessRule accessrule) throws CreateException, javax.naming.NamingException{
      AccessRulesDataLocal returnval = null;
      InitialContext initial = new InitialContext();
      AccessRulesDataLocalHome home = (AccessRulesDataLocalHome) initial.lookup("java:comp/env/ejb/AccessRulesDataLocal");
      returnval= home.create(getAdminGroupName(), getCAId(), accessrule);
      return returnval;
    } // createAccessRule
}
