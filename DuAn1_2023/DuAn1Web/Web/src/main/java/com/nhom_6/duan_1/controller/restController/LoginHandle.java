package com.nhom_6.duan_1.controller.restController;

import com.nhom_6.duan_1.model.req.UserReq;
import com.nhom_6.duan_1.serviceImp.UserServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class LoginHandle {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserServiceImp userServiceImp;

    @PostMapping("/login-handle")
    public ResponseEntity<?> loginHandle(@Validated @RequestBody UserReq userLogin, HttpServletRequest request, HttpSession session, HttpServletResponse response){

        String email = userLogin.getEmail();
        String password = userLogin.getPassword();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                email,
                password
        );
        try {
            // Tiến hành xác thực
            Authentication authentication = authenticationManager.authenticate(token);
            // Lưu vào Context Holder
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // Lưu vào trong session
            session.setAttribute("MY_SESSION", authentication.getName());
            SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);

            if (savedRequest ==null){
                return ResponseEntity.ok("/");
            }
            return ResponseEntity.ok(savedRequest.getRedirectUrl());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }

    @PostMapping("/sigin-handle")
    public ResponseEntity<?> siginHandle(@Validated @RequestBody UserReq userSigin){
        try {
            System.out.println(userSigin.getEmail());
            userServiceImp.signin(userSigin);
            return ResponseEntity.ok(new Exception("Thành công!"));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

}
