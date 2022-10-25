package com.example.demo.controller.impl;

import com.example.demo.mapper.DiosMapper;
import com.example.demo.model.Dios;
import com.example.demo.service.DiosService;
import com.example.demo.util.CustomUtils;
import com.example.demo.util.DiosBuilder;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
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
public class DiosControllerImplTest {
    //TODO: create the data Test generator class DiosBuilder
    private static final String ENDPOINT_URL = "/dioss";
    @InjectMocks
    private DiosControllerImpl diosController;
    @MockBean
    private DiosService diosService;
    @MockBean
    private DiosMapper diosMapper;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.diosController).build();
    }

    @Test
    public void getAll() throws Exception {
        Mockito.when(diosMapper.asDTOList(ArgumentMatchers.any())).thenReturn(DiosBuilder.getListDTO());

        Mockito.when(diosService.findAll()).thenReturn(DiosBuilder.getListEntities());
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(diosMapper.asDTO(ArgumentMatchers.any())).thenReturn(DiosBuilder.getDTO());

        Mockito.when(diosService.findById(ArgumentMatchers.anyLong())).thenReturn(java.util.Optional.of(DiosBuilder.getEntity()));

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(diosService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(diosService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(diosMapper.asEntity(ArgumentMatchers.any())).thenReturn(DiosBuilder.getEntity());
        Mockito.when(diosService.save(ArgumentMatchers.any(Dios.class))).thenReturn(DiosBuilder.getEntity());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(DiosBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(diosService, Mockito.times(1)).save(ArgumentMatchers.any(Dios.class));
        Mockito.verifyNoMoreInteractions(diosService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(diosMapper.asEntity(ArgumentMatchers.any())).thenReturn(DiosBuilder.getEntity());
        Mockito.when(diosService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(DiosBuilder.getEntity());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(DiosBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(diosService, Mockito.times(1)).update(ArgumentMatchers.any(Dios.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(diosService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(diosService).deleteById(ArgumentMatchers.anyLong());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(diosService, Mockito.times(1)).deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(diosService);
    }
}