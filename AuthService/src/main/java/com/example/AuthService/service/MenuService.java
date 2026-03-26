package com.example.AuthService.service;

import com.example.AuthService.model.Menu;
import com.example.AuthService.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    // CREATE
    public Menu create(Menu menu) {
        return menuRepository.save(menu);
    }

    // GET ALL (tree format)
    public List<Menu> getAll() {
        return menuRepository.findByParentIsNull();
    }

    // GET BY ID
    public Menu getById(Long id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found"));
    }

    // UPDATE
    public Menu update(Long id, Menu updated) {
        Menu menu = getById(id);

        menu.setName(updated.getName());
        menu.setTitle(updated.getTitle());
        menu.setType(updated.getType());
        menu.setIconClass(updated.getIconClass());
        menu.setPath(updated.getPath());
        menu.setCollapseId(updated.getCollapseId());
        menu.setBadgeCount(updated.getBadgeCount());

        return menuRepository.save(menu);
    }

    // DELETE
    public void delete(Long id) {
        menuRepository.deleteById(id);
    }
}