package com.github.RenatoTakaoka.ms_proposta.services;

import com.github.RenatoTakaoka.ms_proposta.dto.UserDTO;
import com.github.RenatoTakaoka.ms_proposta.dto.UserInfoDTO;
import com.github.RenatoTakaoka.ms_proposta.models.Proposta;
import com.github.RenatoTakaoka.ms_proposta.models.User;
import com.github.RenatoTakaoka.ms_proposta.repositories.UserRepository;
import com.github.RenatoTakaoka.ms_proposta.services.exceptions.DatabaseException;
import com.github.RenatoTakaoka.ms_proposta.services.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<UserInfoDTO> findAll() {
        List<User> users = repository.findAllWithPropostas();
        return users.stream().map(user -> {
            UserInfoDTO dto = modelMapper.map(user, UserInfoDTO.class);
            dto.setPropostasIds(user.getPropostas().stream().map(Proposta::getId).collect(Collectors.toList()));
            return dto;
        }).collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public UserInfoDTO findById(Long id) {
        User user = repository.findByIdWithPropostas(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        UserInfoDTO dto = modelMapper.map(user, UserInfoDTO.class);
        dto.setPropostasIds(user.getPropostas().stream()
                .map(Proposta::getId)
                .collect(Collectors.toList()));
        return dto;
    }



    @Transactional
    public UserDTO create(UserDTO userDTO) {
        User user = new User();
        user.setNome(userDTO.getNome());
        user.setSobrenome(userDTO.getSobrenome());
        user.setCpf(userDTO.getCpf());
        user.setTelefone(userDTO.getTelefone());
        user.setRenda(userDTO.getRenda());

        user = repository.save(user);

        UserDTO resultDTO = new UserDTO();
        resultDTO.setId(user.getId());
        resultDTO.setNome(user.getNome());
        resultDTO.setSobrenome(user.getSobrenome());
        resultDTO.setCpf(user.getCpf());
        resultDTO.setTelefone(user.getTelefone());
        resultDTO.setRenda(user.getRenda());

        return resultDTO;
    }


    @Transactional
    public UserDTO update(Long id, UserDTO userDTO) {
        User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        user.setNome(userDTO.getNome());
        user.setSobrenome(userDTO.getSobrenome());
        user.setCpf(userDTO.getCpf());
        user.setTelefone(userDTO.getTelefone());
        user.setRenda(userDTO.getRenda());

        user = repository.save(user);

        UserDTO resultDTO = new UserDTO();
        resultDTO.setId(user.getId());
        resultDTO.setNome(user.getNome());
        resultDTO.setSobrenome(user.getSobrenome());
        resultDTO.setCpf(user.getCpf());
        resultDTO.setTelefone(user.getTelefone());
        resultDTO.setRenda(user.getRenda());

        return resultDTO;
    }


    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Recurso não encontrado");
        }
    }

}
