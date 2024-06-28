package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.dto.RoleDTO;
import bg.softuni.mobilele.repository.RoleRepository;
import bg.softuni.mobilele.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(
            RoleRepository roleRepository,
            ModelMapper modelMapper
    ) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RoleDTO> getRolesAsDTOs() {

        return this.roleRepository.findAll()
                .stream()
                .map(e -> modelMapper.map(e, RoleDTO.class))
                .toList();
    }

    @Override
    public boolean isIdValid(Long id) {
        if (id == null) return false;

        return this.roleRepository.existsById(id);
    }
}
