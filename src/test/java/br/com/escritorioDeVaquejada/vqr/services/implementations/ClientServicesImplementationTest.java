package br.com.escritorioDeVaquejada.vqr.services.implementations;

import br.com.escritorioDeVaquejada.vqr.exceptions.ResourceNotFoundException;
import br.com.escritorioDeVaquejada.vqr.mappers.Mapper;
import br.com.escritorioDeVaquejada.vqr.models.Address;
import br.com.escritorioDeVaquejada.vqr.models.ClientModel;
import br.com.escritorioDeVaquejada.vqr.repositories.ClientRepository;
import br.com.escritorioDeVaquejada.vqr.vo.ClientVo;
import jakarta.validation.ConstraintViolationException;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServicesImplementationTest {
    @Mock
    private Address address;
    @Mock
    private Mapper mapper;

    @Mock
    private ClientRepository clientRepository;
//TODO ver se deve utilizar a interface ou a implementação mesmo
    @InjectMocks
    private ClientServicesImplementation clientServices;

    ClientVo clientVo;
    ClientModel clientModel;
    @Before("saveClientOk")
    public void setup(){
    }
    @Test
    @DisplayName("Deve salvar o usuário com sucesso")
    void saveClientOk() {
        clientVo = new ClientVo();
        clientVo.setAddress(address);
        clientVo.setEmail("String email");
        clientVo.setNumber("123");
        clientVo.setName("joao");
        clientModel= new ClientModel("joao", "123", "String email", null, null);

        when(mapper.parseObject(clientVo, ClientModel.class)).thenReturn(clientModel);
        when(clientRepository.save(clientModel)).thenReturn(clientModel);
        when(mapper.parseObject(clientModel,ClientVo.class)).thenReturn(clientVo);
        ClientVo clientVoResult = clientServices.saveClient(clientVo);
        Assertions.assertThat(clientVo).isEqualTo(  clientVoResult);
    }
    @Test
    @DisplayName("Deve lançar uma exceção quando tentar salvar")
    void saveClientBadRequestWithoutName(){
        //without Name test
        ClientVo clientVoWithoutName = new ClientVo();
        clientVoWithoutName.setAddress(address);
        clientVoWithoutName.setEmail("String email");
        clientVoWithoutName.setNumber("123");

        when(mapper.parseObject(clientVoWithoutName, ClientModel.class)).thenThrow(new ConstraintViolationException(null));

        assertThrows(ConstraintViolationException.class, () -> clientServices.saveClient(clientVoWithoutName));
    }
    @Test
    void saveClientBadRequestWithoutNumber(){
        ClientVo clientVoWithoutNumber = new ClientVo();
        clientVoWithoutNumber.setAddress(address);
        clientVoWithoutNumber.setEmail("String email");
        clientVoWithoutNumber.setName("nameExample");

        when(mapper.parseObject(clientVoWithoutNumber, ClientModel.class)).thenThrow(new ConstraintViolationException(null));

        assertThrows(ConstraintViolationException.class, () -> clientServices.saveClient(clientVoWithoutNumber));
    }
    @Test
    void saveClientBadRequestWithoutAddress(){
        ClientVo clientVoWithoutAddress = new ClientVo();
        clientVoWithoutAddress.setAddress(null);
        clientVoWithoutAddress.setEmail("String email");
        clientVoWithoutAddress.setName("nameExample");

        when(mapper.parseObject(clientVoWithoutAddress, ClientModel.class)).thenThrow(new ConstraintViolationException(null));

        assertThrows(ConstraintViolationException.class, () -> clientServices.saveClient(clientVoWithoutAddress));

    }

    @Test
    void findAll() {
        ClientModel clientModel1 = new ClientModel();
        ClientModel clientModel2 = new ClientModel();
        ClientModel clientModel3 = new ClientModel();
        List<ClientModel> listWithClients = new ArrayList<>(Arrays.asList(clientModel1, clientModel2, clientModel3));

        ClientVo clientVo1 = new ClientVo();
        ClientVo clientVo2 = new ClientVo();
        ClientVo clientVo3 = new ClientVo();
        List<ClientVo> clientVoListWithClients= new ArrayList<>(Arrays.asList(clientVo1,clientVo2,clientVo3));
        when(clientRepository.findAll()).thenReturn(listWithClients);
        when(mapper.parseListObjects(listWithClients, ClientVo.class)).thenReturn(clientVoListWithClients);
        List<ClientVo> clientVoListWithResult = clientServices.findAll();
        assertEquals(clientVoListWithResult,clientVoListWithClients);
    }
    @Test
    void findAllEmpty(){
        List<ClientModel> clientModels= new ArrayList<>();
        List<ClientVo> clientVos = new ArrayList<>();
        when(clientRepository.findAll()).thenReturn(clientModels);
        when(mapper.parseListObjects(clientModels,ClientVo.class)).thenReturn(clientVos);
        List<ClientVo> clientVoResult = clientServices.findAll();
        assertEquals(clientVoResult,clientVos);
    }

    @Test
    void findById() {
        ClientModel clientModel5 = new ClientModel();
        ClientVo clientVo = new ClientVo();
        when(clientRepository.findById(UUID.fromString("d600aa5d-de5e-4446-90fa-c0d15579827e"))).thenReturn(Optional.of(clientModel5));
        when(mapper.parseObject(clientModel5,ClientVo.class)).thenReturn(clientVo);
        ClientVo test = clientServices.findById(UUID.fromString("d600aa5d-de5e-4446-90fa-c0d15579827e"));
        assertEquals(test,clientServices.findById(UUID.fromString("d600aa5d-de5e-4446-90fa-c0d15579827e")));
    }
    @Test
    void findByIdThrowsResourceNotFoundException(){
        ClientModel clientModel = new ClientModel();
        when(clientRepository.findById(any())).thenReturn(Optional.empty());
        UUID testId= UUID.randomUUID();
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,()->clientServices.findById(testId));
    }

    @Test
    void findEntityById() {
        ClientModel clientModel5 = new ClientModel();
        when(clientRepository.findById(UUID.fromString("d600aa5d-de5e-4446-90fa-c0d15579827e"))).thenReturn(Optional.of(clientModel5));
        ClientVo test = clientServices.findById(UUID.fromString("d600aa5d-de5e-4446-90fa-c0d15579827e"));
        assertEquals(test,clientServices.findById(UUID.fromString("d600aa5d-de5e-4446-90fa-c0d15579827e")));
    }
    @Test
     void findEntityByIdThrowsResourceNotFoundException() {
        ClientModel clientModel = new ClientModel();
        when(clientRepository.findById(any())).thenReturn(Optional.empty());
        UUID testId= UUID.randomUUID();
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,()->clientServices.findById(testId));
    }
}