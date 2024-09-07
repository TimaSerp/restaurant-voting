package ru.serpov.restaurantvoting.controller;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.serpov.restaurantvoting.model.dto.CustomDto;
import ru.serpov.restaurantvoting.service.AbstractService;

import java.util.List;

import static ru.serpov.restaurantvoting.util.ReflectionUtil.getGenericArgument;
import static ru.serpov.restaurantvoting.util.ValidationUtil.assureIdConsistent;
import static ru.serpov.restaurantvoting.util.ValidationUtil.checkNew;

@Log4j2
@Setter(onMethod_ = @Autowired, onParam_ = @Lazy)
public abstract class AbstractController<DTO extends CustomDto> {

    protected AbstractService<DTO, ?> service;

    private final Class<DTO> dtoClass = getGenericArgument(getClass(), 0);

    public ResponseEntity<List<DTO>> getAllDtos() {
        log.info("Get all {}", dtoClass.getSimpleName());
        List<DTO> dtoList = service.getAll();
        log.info("Got {} {}", dtoList.size(), dtoClass.getSimpleName());
        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<List<DTO>> getAllDtos(Sort sort) {
        log.info("Get all {} sorted by {}", dtoClass.getSimpleName(), sort);
        List<DTO> dtoList = service.getAll(sort);
        log.info("Got {} {} sorted by {}", dtoList.size(), dtoClass.getSimpleName(), sort);
        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<DTO> getById(Integer id) {
        log.info("Get {} by id {}", dtoClass.getSimpleName(), id);
        DTO dto = service.getById(id);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<DTO> createNew(DTO dto) {
        log.info("Create new {}: {}", dtoClass.getSimpleName(), dto);
        checkNew(dto);
        DTO savedDto = service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDto);
    }

    public void deleteById(Integer id) {
        log.info("Delete {} by id {}", dtoClass.getSimpleName(), id);
        service.deleteById(id);
    }

    public ResponseEntity<DTO> updateExisted(DTO dto, Integer id) {
        log.info("Update {} by id {}: {}", dtoClass.getSimpleName(), id, dto);
        assureIdConsistent(dto, id);
        return ResponseEntity.ok(service.save(dto));
    }
}
