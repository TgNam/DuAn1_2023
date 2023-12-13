package com.nhom_6.duan_1.controller.restController;

import com.nhom_6.duan_1.model.entity.User;
import com.nhom_6.duan_1.model.req.CartReq;
import com.nhom_6.duan_1.service.BillService;
import com.nhom_6.duan_1.service.UserService;
import com.nhom_6.duan_1.serviceImp.BillServiceImp;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cart")
public class CartRest {

    @Autowired
    UserService userService;



    @Autowired
    BillServiceImp billService;
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody CartReq entity, HttpSession session) {
        try {
            User u = userService.getLogin(session);
            if (u == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new Exception("Not Login!"));
            }
            billService.updateCart(entity);
            return ResponseEntity.ok("successfully updated");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody CartReq entity,
                                 @RequestParam("color")Long color,
                                 @RequestParam("size")Long size,
                                 HttpSession session) {
        try {
            User u = userService.getLogin(session);
            if (u == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new Exception("Not Login!"));
            }
            billService.addCart(entity,u.getId(),size,color);
            return ResponseEntity.ok("successfully add");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }
}
