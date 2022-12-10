package com.jogamais.ufcg.controllers;

import com.jogamais.ufcg.exceptions.UserException;
import com.jogamais.ufcg.models.User;
import com.jogamais.ufcg.services.UserService;
import com.jogamais.ufcg.utils.UserError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value="/users")
@CrossOrigin
public class UserController implements IController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            User user = userService.getById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserException e) {
            return UserError.errorUserNotExist();
        }

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            userService.deleteById(id);
            return new ResponseEntity<>("Usuário com ID: " + id + " removido com sucesso!", HttpStatus.OK);
        } catch(UserException e) {
            return UserError.errorUserNotExist();
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findAll() {
        List<User> users;

        try {
            users = userService.findAll();
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        catch (UserException e) {
            return UserError.errorUserNotExist();
        }
    }
}
