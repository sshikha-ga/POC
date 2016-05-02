package com.ga.domain.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ga.domain.modal.UserDTO;
import com.ga.persistence.entity.User;
import com.ga.persistence.entity.UserGroup;
import com.ga.service.IUserService;

@Controller
@RequestMapping(value = "/user")
public class UserGroupController {

    @Autowired
    IUserService userService;

    /* Find Users with group Manager and Agent */
    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    @ResponseBody
    public List<UserDTO> getUsers() {

        List<UserGroup> usergroupList = userService.getUser();
        List<UserDTO> dtoList = setUserGroupList(usergroupList);
        return removeDuplicates(dtoList);
    }

    /* Find Active Users with group Booker and Agent */
    @RequestMapping(value = "/getActiveUsers", method = RequestMethod.GET)
    @ResponseBody
    public List<UserDTO> getActiveUsers() {

        List<UserGroup> usergroupList = userService.findActiveUsers();
        List<UserDTO> dtoList = setUserGroupList(usergroupList);
        return removeDuplicates(dtoList);

    }

    /* Find Active Users with dynamic criterias */
    @RequestMapping(value = "/getUserByCriteria", method = RequestMethod.GET)
    @ResponseBody
    public List<UserDTO> getUserByCriteria(@RequestParam("criteria") String criteria) {

        String query = criteria;
        String clause = "AND";
        String[] temp = null;
        String delim = ":";
        ArrayList<String> fieldsValuePair;
        ArrayList<String[]> arrayList = new ArrayList<String[]>();
        List<UserDTO> dtoList = new ArrayList<UserDTO>();

        temp = query.split("\\|");

        if (query.contains("|")) {
            clause = "AND";
            temp = query.split("\\|");
        } else if (query.contains("^")) {
            clause = "OR";
            temp = query.split("\\^");
        }

        fieldsValuePair = new ArrayList<String>(Arrays.asList(temp));

        for (int j = 0; j < fieldsValuePair.size(); j++) {

            String array[] = fieldsValuePair.get(j).split(delim);
            arrayList.add(array);
        }

        if (query.contains("GROUP_ID") || query.contains("groupName")) {

            dtoList = setUserGroupList(userService.findUserGroupCriterias(arrayList, clause));

        } else {

            dtoList = setUserList(userService.findUserCriterias(arrayList, clause));

        }

        return removeDuplicates(dtoList);
    }

    /*set UserList in UserDTO*/
    private List<UserDTO> setUserList(List<User> userList1) {

        List<UserDTO> userDTOList = new ArrayList<UserDTO>();

        Iterator<User> iterator = userList1.iterator();

        while (iterator.hasNext()) {

            User user = iterator.next();
            UserDTO userdto = setDTO(user);
            userDTOList.add(userdto);
        }

        return userDTOList;
    }

    /*set User GroupList in UserDTO*/
    private List<UserDTO> setUserGroupList(List<UserGroup> userList1) {

        List<UserDTO> userDTOList = new ArrayList<UserDTO>();
        Iterator<UserGroup> iterator = userList1.iterator();

        while (iterator.hasNext()) {

            UserGroup user = iterator.next();

            UserDTO userdto = new UserDTO();
            userdto.setUserId(user.getUser().getUser_id());
            userdto.setFirstName(user.getUser().getFirstName());
            userdto.setLastName(user.getUser().getLastName());
            userdto.setEmail(user.getUser().getEmail());
            userdto.setStatus(user.getUser().getStatus());

            userDTOList.add(userdto);
        }

        return userDTOList;
    }

    /*set user into DTO*/
    private UserDTO setDTO(User user) {

        UserDTO userdto = new UserDTO();
        userdto.setUserId(user.getUser_id());
        userdto.setFirstName(user.getFirstName());
        userdto.setLastName(user.getLastName());
        userdto.setEmail(user.getEmail());
        userdto.setStatus(user.getStatus());

        return userdto;

    }

    /*remove duplicate users*/
    private List<UserDTO> removeDuplicates(List<UserDTO> dtoList) {

        List<UserDTO> dtoList1 = new ArrayList<UserDTO>();

        for (UserDTO dto : dtoList) {

            if (dtoList1.size() == 0) {
                dtoList1.add(dto);
            } else {
                for (UserDTO dto1 : dtoList1) {

                    if (dto1.getUserId() == dto.getUserId()) {
                        break;
                    } else {
                        dtoList1.add(dto);
                        break;
                    }
                }

            }

        }

        return dtoList1;
    }

}
