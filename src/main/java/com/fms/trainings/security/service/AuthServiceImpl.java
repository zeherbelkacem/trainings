package com.fms.trainings.security.service;

import com.fms.trainings.security.entities.Rrole;
import com.fms.trainings.security.entities.Uuser;
import com.fms.trainings.security.repository.RroleRepository;
import com.fms.trainings.security.repository.UuserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    String token = "";
    private RroleRepository rroleRepository;
    private UuserRepository uuserRepository;

    private PasswordEncoder passwordEncoder;

    public AuthServiceImpl(RroleRepository rroleRepository, UuserRepository uuserRepository, PasswordEncoder passwordEncoder) {
        this.rroleRepository = rroleRepository;
        this.uuserRepository = uuserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Uuser findUuserByUserName(String username) {
        return uuserRepository.findByUserName(username);
    }

    @Override
    public Uuser saveUuser(Uuser user) {

        //Creation
//		if(uuserRepository.findById(user.getUserId()).isEmpty()) {
////			if (uuserRepository.findByUserName(user.getUserName()) != null) {
////				throw new NotFoundEntityException("User with the sme userName exists");
////			}
//			String pwdEncoder = passwordEncoder.encode(user.getPassword());
//			user.setPassword(pwdEncoder);
//			return uuserRepository.save(user);
//		}
        //Updating
        //return uuserRepository.save(user);
        String pwdEncoder = passwordEncoder.encode(user.getPassword());
        user.setPassword(pwdEncoder);
        return uuserRepository.save(user);
    }

    @Override
    public Rrole saveRrole(Rrole role) {
        return rroleRepository.save(role);
    }

    @Override
    public Rrole getRoleByRoleName(String roleName) {
        return rroleRepository.findByRole(roleName);
    }

    @Override
    public List<Uuser> getAllUsers() {
        return uuserRepository.findAll();
    }

    @Override
    public Uuser addRoleToUser(String user, String role) {
        Uuser uuser = uuserRepository.findByUserName(user);
        uuser.getRoles().add(rroleRepository.findByRole(role));
        return uuserRepository.save(uuser);
    }

    @Override
    public void setToken(String token) {
        this.token = token;
    }
    @Override
    public String getToken() {
        return this.token;
    }

}
