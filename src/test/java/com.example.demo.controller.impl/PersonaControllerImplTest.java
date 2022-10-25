package com.example.demo.controller.impl;

import com.example.demo.mapper.PersonaMapper;
import com.example.demo.model.Persona;
import com.example.demo.service.PersonaService;
import com.example.demo.util.CustomUtils;
import com.example.demo.util.PersonaBuilder;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class PersonaControllerImplTest {
    //TODO: create the data Test generator class PersonaBuilder
    private static final String ENDPOINT_URL = "/personas";
    @InjectMocks
    private PersonaControllerImpl personaController;
    @MockBean
    private PersonaService personaService;
    @MockBean
    private PersonaMapper personaMapper;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.personaController).build();
    }

    @Test
    public void getAll() throws Exception {
        Mockito.when(personaMapper.asDTOList(ArgumentMatchers.any())).thenReturn(PersonaBuilder.getListDTO());

        Mockito.when(personaService.findAll()).thenReturn(PersonaBuilder.getListEntities());
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(personaMapper.asDTO(ArgumentMatchers.any())).thenReturn(PersonaBuilder.getDTO());

        Mockito.when(personaService.findById(ArgumentMatchers.anyLong())).thenReturn(java.util.Optional.of(PersonaBuilder.getEntity()));

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(personaService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(personaService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(personaMapper.asEntity(ArgumentMatchers.any())).thenReturn(PersonaBuilder.getEntity());
        Mockito.when(personaService.save(ArgumentMatchers.any(Persona.class))).thenReturn(PersonaBuilder.getEntity());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(PersonaBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(personaService, Mockito.times(1)).save(ArgumentMatchers.any(Persona.class));
        Mockito.verifyNoMoreInteractions(personaService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(personaMapper.asEntity(ArgumentMatchers.any())).thenReturn(PersonaBuilder.getEntity());
        Mockito.when(personaService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(PersonaBuilder.getEntity());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(PersonaBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(personaService, Mockito.times(1)).update(ArgumentMatchers.any(Persona.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(personaService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(personaService).deleteById(ArgumentMatchers.anyLong());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(personaService, Mockito.times(1)).deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(personaService);
    }
}