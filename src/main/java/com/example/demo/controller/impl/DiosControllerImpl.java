package com.example.demo.controller.impl;

import com.example.demo.controller.DiosController;
import com.example.demo.dto.DiosDTO;
import com.example.demo.mapper.DiosMapper;
import com.example.demo.model.Dios;
import com.example.demo.service.DiosService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/dios")
@RestController
public class DiosControllerImpl implements DiosController {
    private final DiosService diosService;
    private final DiosMapper diosMapper;

    public DiosControllerImpl(DiosService diosService, DiosMapper diosMapper) {
        this.diosService = diosService;
        this.diosMapper = diosMapper;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DiosDTO save(@RequestBody DiosDTO diosDTO) {
        Dios dios = diosMapper.asEntity(diosDTO);
        return diosMapper.asDTO(diosService.save(dios));
    }

    @Override
    @GetMapping("/{id}")
    public DiosDTO findById(@PathVariable("id") Long id) {
        Dios dios = diosService.findById(id).orElse(null);
        return diosMapper.asDTO(dios);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        diosService.deleteById(id);
    }

    @Override
    @GetMapping
    public List<DiosDTO> list() {
        return diosMapper.asDTOList(diosService.findAll());
    }

    @Override
    @GetMapping("/page-query")
    public Page<DiosDTO> pageQuery(Pageable pageable) {
        Page<Dios> diosPage = diosService.findAll(pageable);
        List<DiosDTO> dtoList = diosPage
                .stream()
                .map(diosMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, diosPage.getTotalElements());
    }

    @Override
    @PutMapping("/{id}")
    public DiosDTO update(@RequestBody DiosDTO diosDTO, @PathVariable("id") Long id) {
        Dios dios = diosMapper.asEntity(diosDTO);
        return diosMapper.asDTO(diosService.update(dios, id));
    }
}