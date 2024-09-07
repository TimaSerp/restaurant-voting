package ru.serpov.restaurantvoting.service;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import ru.serpov.restaurantvoting.error.NotFoundException;
import ru.serpov.restaurantvoting.mapper.AbstractMapper;
import ru.serpov.restaurantvoting.model.dto.CustomDto;
import ru.serpov.restaurantvoting.model.entity.CustomEntity;
import ru.serpov.restaurantvoting.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static ru.serpov.restaurantvoting.util.ReflectionUtil.getGenericArgument;

@Log4j2
@Transactional
@Setter(onMethod_ = @Autowired, onParam_ = @Lazy)
public class AbstractService<DTO extends CustomDto, ENTITY extends CustomEntity> {

    protected BaseRepository<ENTITY> repository;
    protected AbstractMapper<DTO, ENTITY> mapper;

    @Transactional(readOnly = true)
    public List<DTO> getAll() {
        List<ENTITY> entities = repository.findAll();
        return mapper.mapEntities(entities);
    }

    @Transactional(readOnly = true)
    public List<DTO> getAll(Sort sort) {
        List<ENTITY> entities = repository.findAll(sort);
        return mapper.mapEntities(entities);
    }

    @Transactional(readOnly = true)
    public DTO getById(Integer id) {
        Optional<ENTITY> entity = repository.findById(id);
        if (entity.isEmpty()) {
            throw new NotFoundException(format("Not found %s by id: %s", getGenericArgument(getClass(), 0).getSimpleName(), id));
        }
        return mapper.map(entity.get());
    }

    public DTO save(DTO dto) {
        ENTITY entity = mapper.map(dto);
        ENTITY savedEntity = repository.save(entity);
        return mapper.map(savedEntity);
    }

    public void deleteById(Integer id) {
        repository.deleteExisted(id);
    }
}
