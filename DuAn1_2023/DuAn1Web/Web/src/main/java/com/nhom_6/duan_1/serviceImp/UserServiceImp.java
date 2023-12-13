package com.nhom_6.duan_1.serviceImp;

import com.nhom_6.duan_1.model.entity.Address;
import com.nhom_6.duan_1.model.entity.Role;
import com.nhom_6.duan_1.model.entity.User;
import com.nhom_6.duan_1.model.req.UserReq;
import com.nhom_6.duan_1.repository.AddressResponsitory;
import com.nhom_6.duan_1.repository.RoleResponsitory;
import com.nhom_6.duan_1.repository.UserResponsitory;
import com.nhom_6.duan_1.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserResponsitory userResponsitory;

    @Autowired
    RoleResponsitory roleResponsitory;

    @Autowired
    AddressResponsitory addressResponsitory;
    @Override
    public User getLogin(HttpSession session) {
        String email = (String) session.getAttribute("MY_SESSION");
        if(email == null){
            return null;
        }
        return userResponsitory.findByEmail(email)
                .orElse(null);
    }

    public User getById(long id){
        return userResponsitory.findById(id)
                .orElse(null);
    }

    public void signin(UserReq userSigin) throws Exception {
        List<User> u =userResponsitory.findByNumberPhoneOrEmail(userSigin.getPhone(), userSigin.getEmail());
        System.out.println( u.size());
        if(u !=null && u.size() > 0) throw new Exception("Số điện thoại hoặc email đã tồn tại!");

        Role role = roleResponsitory.findByRoleName("Khách Hàng");
        List<Role> roles = new ArrayList<>();
        roles.add(role);

        Address address = new Address();
        address.setAddressDetail(userSigin.getAddress());
        addressResponsitory.save(address);

        User newUser = new User();
        newUser.setAddress(address);
        newUser.setEmail(userSigin.getEmail());
        newUser.setPassword(userSigin.getPassword());
        newUser.setFullName(userSigin.getName());
        newUser.setStatus("1");
        newUser.setRoles(roles);
        newUser.setNumberPhone(userSigin.getPhone());

        userResponsitory.save(newUser);
        address.setUser(newUser);
        addressResponsitory.save(address);
        if(roles!=null){
            role.getUserList().add(newUser);
            roleResponsitory.save(role);
        }
    }
}
