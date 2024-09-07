package ru.serpov.restaurantvoting.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.serpov.restaurantvoting.model.dto.impl.UserDto;
import ru.serpov.restaurantvoting.model.entity.impl.UserEntity;
import ru.serpov.restaurantvoting.repository.UserRepository;
import ru.serpov.restaurantvoting.service.AbstractService;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class UserService extends AbstractService<UserDto, UserEntity> {

    private final UserRepository userRepository;

    @Override
    public UserDto save(UserDto dto) {
        UserEntity entity = mapper.map(dto);
        UserEntity savedEntity = userRepository.prepareAndSave(entity);
        return mapper.map(savedEntity);
    }

    @Transactional(readOnly = true)
    public UserDto getByEmail(String email) {
        UserEntity entity = userRepository.getExistedByEmail(email);
        return mapper.map(entity);
    }

    public void setEnabled(int id, boolean enabled) {
        userRepository.updateEnabled(id, enabled);
    }
}
