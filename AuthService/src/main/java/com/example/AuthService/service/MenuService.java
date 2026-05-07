package com.example.AuthService.service;

import com.example.AuthService.model.Menu;
import com.example.AuthService.model.Role;
import com.example.AuthService.model.User;
import com.example.AuthService.model.dto.MenuTreeDTO;
import com.example.AuthService.repository.MenuRepository;
import com.example.AuthService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final UserRepository userRepository;

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
        menu.setParent(updated.getParent());


        return menuRepository.save(menu);
    }

    // DELETE
//    public void delete(Long id) {
//        menuRepository.deleteById(id);
//    }

    public void delete(Long id) {

        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found"));

        // 👉 If parent has children → delete all recursively
        deleteRecursively(menu);

        // detach from parent
        if (menu.getParent() != null) {
            menu.getParent().getChildren().remove(menu);
        }

        menuRepository.delete(menu);
    }

    private void deleteRecursively(Menu menu) {

        if (menu.getChildren() != null && !menu.getChildren().isEmpty()) {
            for (Menu child : new ArrayList<>(menu.getChildren())) {
                deleteRecursively(child);
            }
        }

        menu.getChildren().clear();
        menuRepository.delete(menu);
    }
    public List<MenuTreeDTO> getUserMenuTree(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 🔥 roles null/empty check
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            return Collections.emptyList();
        }

        // 🔥 all menus collect (duplicate remove using Set)
        Set<Menu> menuSet = new HashSet<>();

        for (Role role : user.getRoles()) {
            if (role.getMenus() != null) {
                menuSet.addAll(role.getMenus());
            }
        }

        // 🔥 যদি menu empty হয়
        if (menuSet.isEmpty()) {
            return Collections.emptyList();
        }

        // 🔥 map তৈরি
        Map<Long, MenuTreeDTO> map = new HashMap<>();

        for (Menu menu : menuSet) {
            map.put(menu.getId(), new MenuTreeDTO(
                    menu.getId(),
                    menu.getName(),
                    menu.getTitle(),
                    menu.getType(),
                    menu.getIconClass(),
                    menu.getPath(),
                    menu.getCollapseId(),
                    menu.getBadgeCount(),
                    new ArrayList<>()
            ));
        }

        List<MenuTreeDTO> roots = new ArrayList<>();

        // 🔥 tree build
        for (Menu menu : menuSet) {
            if (menu.getParent() != null &&
                    map.containsKey(menu.getParent().getId())) {

                map.get(menu.getParent().getId())
                        .getChildren()
                        .add(map.get(menu.getId()));

            } else {
                roots.add(map.get(menu.getId()));
            }
        }

        return roots;
    }
}