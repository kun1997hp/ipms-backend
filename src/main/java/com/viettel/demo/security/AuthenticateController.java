package com.viettel.demo.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import viettel.passport.client.ObjectToken;
import viettel.passport.client.UserToken;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/oauthVsa")
public class AuthenticateController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ResponseEntity<UserInformationVSA> login(HttpServletRequest request) {
        UserToken vsaUserToken = (UserToken) request.getSession().getAttribute("vsaUserToken");
        if (vsaUserToken == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        List<ObjectToken> objectTokens = vsaUserToken.getObjectTokens();
        List<MenuVSA> menus = new ArrayList<>();
        for(int i = 0; i < objectTokens.size(); i++) {
            menus.add(new MenuVSA(objectTokens.get(i).getObjectCode(), objectTokens.get(i).getObjectUrl()));
        }
        UserInformationVSA user = new UserInformationVSA(vsaUserToken.getUserName(), menus);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
