package com.viettel.demo.security;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class UserInformationVSA {
    private String username;
    private List<MenuVSA> menus;
}
