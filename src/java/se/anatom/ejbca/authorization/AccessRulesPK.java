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

/**
 * Created 2002-jun-27 16:11:07
 * Code generated by the Forte For Java EJB Workshop
 * @author tomselleck
 */

public final class AccessRulesPK implements java.io.Serializable {

    public int pK;


    public AccessRulesPK(java.lang.String admingroupname, int caid, AccessRule accessrule) {
        this.pK =
        ((admingroupname==null?0:admingroupname.hashCode())
        ^
        ((int) caid)
        ^ 
        (accessrule.getAccessRule()==null?0:accessrule.getAccessRule().hashCode()));
    }

    public AccessRulesPK() {
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(java.lang.Object otherOb) {
        if (!(otherOb instanceof se.anatom.ejbca.authorization.AccessRulesPK)) {
            return false;
        }
        se.anatom.ejbca.authorization.AccessRulesPK other = (se.anatom.ejbca.authorization.AccessRulesPK) otherOb;
        return (pK==other.pK);
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return this.pK;
    }

}
