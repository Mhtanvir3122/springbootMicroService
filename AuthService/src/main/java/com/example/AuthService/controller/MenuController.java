package com.example.AuthService.controller;

import com.example.AuthService.model.Menu;
import com.example.AuthService.model.dto.MenuTreeDTO;
import com.example.AuthService.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

@RequestMapping("/auth/menus")
public class MenuController {

    private final MenuService menuService;

    // CREATE
    @PostMapping
    public ResponseEntity<Menu> create(@RequestBody Menu menu) {
        return ResponseEntity.ok(menuService.create(menu));
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Menu>> getAll() {
        return ResponseEntity.ok(menuService.getAll());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Menu> getById(@PathVariable Long id) {
        return ResponseEntity.ok(menuService.getById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Menu> update(@PathVariable Long id,
                                       @RequestBody Menu menu) {
        return ResponseEntity.ok(menuService.update(id, menu));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        menuService.delete(id);
        return ResponseEntity.ok("Deleted successfully");
    }


    @GetMapping("/data/{id}/menus")
    public ResponseEntity<List<MenuTreeDTO>> getUserMenus(@PathVariable Long id) {
        return ResponseEntity.ok(menuService.getUserMenuTree(id));
    }
}