package com.example.demo.controller.impl;

import com.example.demo.mapper.EstudianteMapper;
import com.example.demo.model.Estudiante;
import com.example.demo.service.EstudianteService;
import com.example.demo.util.CustomUtils;
import com.example.demo.util.EstudianteBuilder;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class EstudianteControllerImplTest {
    //TODO: create the data Test generator class EstudianteBuilder
    private static final String ENDPOINT_URL = "/estudiantes";
    @InjectMocks
    private EstudianteControllerImpl estudianteController;
    @MockBean
    private EstudianteService estudianteService;
    @MockBean
    private EstudianteMapper estudianteMapper;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.estudianteController).build();
    }

    @Test
    public void getAll() throws Exception {
        Mockito.when(estudianteMapper.asDTOList(ArgumentMatchers.any())).thenReturn(EstudianteBuilder.getListDTO());

        Mockito.when(estudianteService.findAll()).thenReturn(EstudianteBuilder.getListEntities());
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(estudianteMapper.asDTO(ArgumentMatchers.any())).thenReturn(EstudianteBuilder.getDTO());

        Mockito.when(estudianteService.findById(ArgumentMatchers.anyLong())).thenReturn(java.util.Optional.of(EstudianteBuilder.getEntity()));

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(estudianteService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(estudianteService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(estudianteMapper.asEntity(ArgumentMatchers.any())).thenReturn(EstudianteBuilder.getEntity());
        Mockito.when(estudianteService.save(ArgumentMatchers.any(Estudiante.class))).thenReturn(EstudianteBuilder.getEntity());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(EstudianteBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(estudianteService, Mockito.times(1)).save(ArgumentMatchers.any(Estudiante.class));
        Mockito.verifyNoMoreInteractions(estudianteService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(estudianteMapper.asEntity(ArgumentMatchers.any())).thenReturn(EstudianteBuilder.getEntity());
        Mockito.when(estudianteService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(EstudianteBuilder.getEntity());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(EstudianteBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(estudianteService, Mockito.times(1)).update(ArgumentMatchers.any(Estudiante.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(estudianteService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(estudianteService).deleteById(ArgumentMatchers.anyLong());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(estudianteService, Mockito.times(1)).deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(estudianteService);
    }
}