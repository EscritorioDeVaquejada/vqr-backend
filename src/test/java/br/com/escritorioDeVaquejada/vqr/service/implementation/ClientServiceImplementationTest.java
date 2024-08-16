package br.com.escritorioDeVaquejada.vqr.service.implementation;

import br.com.escritorioDeVaquejada.vqr.exception.ResourceNotFoundException;
import br.com.escritorioDeVaquejada.vqr.mapper.Mapper;
import br.com.escritorioDeVaquejada.vqr.model.Address;
import br.com.escritorioDeVaquejada.vqr.model.ClientModel;
import br.com.escritorioDeVaquejada.vqr.repository.ClientRepository;
import br.com.escritorioDeVaquejada.vqr.vo.ClientVO;
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
class ClientServiceImplementationTest {
    @Mock
    private Address address;
    @Mock
    private Mapper mapper;

    @Mock
    private ClientRepository clientRepository;
//TODO ver se deve utilizar a interface ou a implementação mesmo
    @InjectMocks
    private ClientServiceImplementation clientServices;

    ClientVO clientVo;
    ClientModel clientModel;
    @Before("saveClientOk")
    public void setup(){
    }
    @Test
    @DisplayName("Deve salvar o usuário com sucesso")
    void saveClientOk() {
        clientVo = new ClientVO();
        clientVo.setAddress(address);
        clientVo.setEmail("String email");
        clientVo.setNumber("123");
        clientVo.setName("joao");
        clientModel= new ClientModel("joao", "123", "String email", null, null);

        when(mapper.parseObject(clientVo, ClientModel.class)).thenReturn(clientModel);
        when(clientRepository.save(clientModel)).thenReturn(clientModel);
        when(mapper.parseObject(clientModel, ClientVO.class)).thenReturn(clientVo);
        ClientVO clientVOResult = clientServices.saveClient(clientVo);
        Assertions.assertThat(clientVo).isEqualTo(clientVOResult);
    }
    @Test
    @DisplayName("Deve lançar uma exceção quando tentar salvar")
    void saveClientBadRequestWithoutName(){
        //without Name test
        ClientVO clientVOWithoutName = new ClientVO();
        clientVOWithoutName.setAddress(address);
        clientVOWithoutName.setEmail("String email");
        clientVOWithoutName.setNumber("123");

        when(mapper.parseObject(clientVOWithoutName, ClientModel.class)).thenThrow(new ConstraintViolationException(null));

        assertThrows(ConstraintViolationException.class, () -> clientServices.saveClient(clientVOWithoutName));
    }
    @Test
    void saveClientBadRequestWithoutNumber(){
        ClientVO clientVOWithoutNumber = new ClientVO();
        clientVOWithoutNumber.setAddress(address);
        clientVOWithoutNumber.setEmail("String email");
        clientVOWithoutNumber.setName("nameExample");

        when(mapper.parseObject(clientVOWithoutNumber, ClientModel.class)).thenThrow(new ConstraintViolationException(null));

        assertThrows(ConstraintViolationException.class, () -> clientServices.saveClient(clientVOWithoutNumber));
    }
    @Test
    void saveClientBadRequestWithoutAddress(){
        ClientVO clientVOWithoutAddress = new ClientVO();
        clientVOWithoutAddress.setAddress(null);
        clientVOWithoutAddress.setEmail("String email");
        clientVOWithoutAddress.setName("nameExample");

        when(mapper.parseObject(clientVOWithoutAddress, ClientModel.class)).thenThrow(new ConstraintViolationException(null));

        assertThrows(ConstraintViolationException.class, () -> clientServices.saveClient(clientVOWithoutAddress));

    }

    @Test
    void findAll() {
        ClientModel clientModel1 = new ClientModel();
        ClientModel clientModel2 = new ClientModel();
        ClientModel clientModel3 = new ClientModel();
        List<ClientModel> listWithClients = new ArrayList<>(Arrays.asList(clientModel1, clientModel2, clientModel3));

        ClientVO clientVO1 = new ClientVO();
        ClientVO clientVO2 = new ClientVO();
        ClientVO clientVO3 = new ClientVO();
        List<ClientVO> clientVoListWithClients= new ArrayList<>(Arrays.asList(clientVO1, clientVO2, clientVO3));
        when(clientRepository.findAll()).thenReturn(listWithClients);
        when(mapper.parseListObjects(listWithClients, ClientVO.class)).thenReturn(clientVoListWithClients);
        List<ClientVO> clientVOListWithResult = clientServices.findAll();
        assertEquals(clientVOListWithResult,clientVoListWithClients);
    }
    @Test
    void findAllEmpty(){
        List<ClientModel> clientModels= new ArrayList<>();
        List<ClientVO> clientVOS = new ArrayList<>();
        when(clientRepository.findAll()).thenReturn(clientModels);
        when(mapper.parseListObjects(clientModels, ClientVO.class)).thenReturn(clientVOS);
        List<ClientVO> clientVOResult = clientServices.findAll();
        assertEquals(clientVOResult, clientVOS);
    }

    @Test
    void findById() {
        ClientModel clientModel5 = new ClientModel();
        ClientVO clientVo = new ClientVO();
        when(clientRepository.findById(UUID.fromString("d600aa5d-de5e-4446-90fa-c0d15579827e"))).thenReturn(Optional.of(clientModel5));
        when(mapper.parseObject(clientModel5, ClientVO.class)).thenReturn(clientVo);
        ClientVO test = clientServices.findById(UUID.fromString("d600aa5d-de5e-4446-90fa-c0d15579827e"));
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
        ClientVO test = clientServices.findById(UUID.fromString("d600aa5d-de5e-4446-90fa-c0d15579827e"));
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