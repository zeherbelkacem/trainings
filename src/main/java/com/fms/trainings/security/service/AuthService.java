package com.fms.trainings.security.service;

import com.fms.trainings.security.entities.Rrole;
import com.fms.trainings.security.entities.Uuser;

import java.util.List;

public interface AuthService {

    /********Uuser*******************************/
    /**
     *
     * @param username
     * @return
     */
    public Uuser findUuserByUserName(String username);

    /**
     *
     * @param user
     * @return
     */
    public Uuser saveUuser(Uuser user);

    /*******Rrole*******************************/

    /**
     *
     * @param role
     * @return
     */
    public Rrole saveRrole(Rrole role);

    /**
     *
     * @param string
     * @return
     */
    public Rrole getRoleByRoleName(String string);

    public List<Uuser>  getAllUsers();

    public Uuser addRoleToUser(String user, String role);

    public void setToken(String token);
    public String getToken();
}
