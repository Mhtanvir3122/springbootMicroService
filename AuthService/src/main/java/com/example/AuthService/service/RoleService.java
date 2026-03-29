package com.example.AuthService.service;


import com.example.AuthService.model.Menu;
import com.example.AuthService.model.Role;
import com.example.AuthService.model.dto.RoleUpdateRequest;
import com.example.AuthService.repository.MenuRepository;
import com.example.AuthService.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private final MenuRepository menuRepository;

    public RoleService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Role updateRole(Long id, Role roleDetails) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
        role.setName(roleDetails.getName());
        return roleRepository.save(role);
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    public List<Role> searchRole(String keyword) {
        return roleRepository.findByNameContainingIgnoreCase(keyword);
    }



    public Role updateRole(Long roleId, RoleUpdateRequest request) {

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        // update name
        role.setName(request.getName());

        // 🔥 fetch menus
        List<Menu> menus = menuRepository.findByIdIn(request.getMenuIds());

        // set menus
        role.setMenus(menus);

        return roleRepository.save(role);
    }
}

