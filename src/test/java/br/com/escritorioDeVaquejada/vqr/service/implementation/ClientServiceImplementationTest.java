package br.com.escritorioDeVaquejada.vqr.service.implementation;

import br.com.escritorioDeVaquejada.vqr.exception.ResourceNotFoundException;
import br.com.escritorioDeVaquejada.vqr.mapper.Mapper;
import br.com.escritorioDeVaquejada.vqr.model.Address;
import br.com.escritorioDeVaquejada.vqr.model.ClientModel;
import br.com.escritorioDeVaquejada.vqr.repository.ClientRepository;
import br.com.escritorioDeVaquejada.vqr.vo.client.ClientRequestVO;
import br.com.escritorioDeVaquejada.vqr.vo.client.ClientResponseVO;
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

    ClientRequestVO clientRequestVo;
    ClientResponseVO clientResponseVo;
    ClientModel clientModel;
    @Before("saveClientOk")
    public void setup(){
    }
    @Test
    @DisplayName("Deve salvar o usuário com sucesso")
    void saveClientOk() {
        clientRequestVo = new ClientRequestVO();
        clientRequestVo.setAddress(address);
        clientRequestVo.setEmail("String email");
        clientRequestVo.setNumber("123");
        clientRequestVo.setName("joao");

        clientResponseVo = new ClientResponseVO();
        clientResponseVo.setId(UUID.randomUUID());
        clientResponseVo.setAddress(clientRequestVo.getAddress());
        clientResponseVo.setEmail(clientRequestVo.getEmail());
        clientResponseVo.setNumber(clientRequestVo.getNumber());
        clientResponseVo.setName(clientRequestVo.getName());

        clientModel= new ClientModel("joao", "123", "String email", null, null);

        when(mapper.parseObject(clientRequestVo, ClientModel.class)).thenReturn(clientModel);
        when(clientRepository.save(clientModel)).thenReturn(clientModel);
        when(mapper.parseObject(clientModel, ClientResponseVO.class)).thenReturn(clientResponseVo);
        ClientResponseVO result = clientServices.saveClient(clientRequestVo);
        Assertions.assertThat(clientResponseVo).isEqualTo(result);
    }
    @Test
    @DisplayName("Deve lançar uma exceção quando tentar salvar")
    void saveClientBadRequestWithoutName(){
        //without Name test
        ClientRequestVO clientRequestVOWithoutName = new ClientRequestVO();
        clientRequestVOWithoutName.setAddress(address);
        clientRequestVOWithoutName.setEmail("String email");
        clientRequestVOWithoutName.setNumber("123");

        when(mapper.parseObject(clientRequestVOWithoutName, ClientModel.class)).thenThrow(new ConstraintViolationException(null));

        assertThrows(ConstraintViolationException.class, () -> clientServices.saveClient(clientRequestVOWithoutName));
    }
    @Test
    void saveClientBadRequestWithoutNumber(){
        ClientRequestVO clientRequestVOWithoutNumber = new ClientRequestVO();
        clientRequestVOWithoutNumber.setAddress(address);
        clientRequestVOWithoutNumber.setEmail("String email");
        clientRequestVOWithoutNumber.setName("nameExample");

        when(mapper.parseObject(clientRequestVOWithoutNumber, ClientModel.class)).thenThrow(new ConstraintViolationException(null));

        assertThrows(ConstraintViolationException.class, () -> clientServices.saveClient(clientRequestVOWithoutNumber));
    }
    @Test
    void saveClientBadRequestWithoutAddress(){
        ClientRequestVO clientRequestVOWithoutAddress = new ClientRequestVO();
        clientRequestVOWithoutAddress.setAddress(null);
        clientRequestVOWithoutAddress.setEmail("String email");
        clientRequestVOWithoutAddress.setName("nameExample");

        when(mapper.parseObject(clientRequestVOWithoutAddress, ClientModel.class)).thenThrow(new ConstraintViolationException(null));

        assertThrows(ConstraintViolationException.class, () -> clientServices.saveClient(clientRequestVOWithoutAddress));

    }

    //todo Consertar os erros nos testes gereados pela reimplementação do sistema de buscas
    /*

    @Test
    void findAll() {
        ClientModel clientModel1 = new ClientModel();
        ClientModel clientModel2 = new ClientModel();
        ClientModel clientModel3 = new ClientModel();
        List<ClientModel> listWithClients = new ArrayList<>(Arrays.asList(clientModel1, clientModel2, clientModel3));

        ClientResponseVO clientResponseVO1 = new ClientResponseVO();
        ClientResponseVO clientResponseVO2 = new ClientResponseVO();
        ClientResponseVO clientResponseVO3 = new ClientResponseVO();
        List<ClientResponseVO> clientVoListWithClientRequests = new ArrayList<>(Arrays.asList(clientResponseVO1, clientResponseVO2, clientResponseVO3));
        when(clientRepository.findAll()).thenReturn(listWithClients);
        when(mapper.parseListObjects(listWithClients, ClientResponseVO.class)).thenReturn(clientVoListWithClientRequests);
        List<ClientResponseVO> clientResponseVOListWithResult = clientServices.findAll();
        assertEquals(clientResponseVOListWithResult, clientVoListWithClientRequests);
    }
    @Test
    void findAllEmpty(){
        List<ClientModel> clientModels = new ArrayList<>();
        List<ClientResponseVO> clientResponseVOS = new ArrayList<>();
        when(clientRepository.findAll()).thenReturn(clientModels);
        when(mapper.parseListObjects(clientModels, ClientResponseVO.class)).thenReturn(clientResponseVOS);
        List<ClientResponseVO> clientResponseVOResult = clientServices.findAll();
        assertEquals(clientResponseVOResult, clientResponseVOS);
    }

     */

    @Test
    void findById() {
        ClientModel clientModel5 = new ClientModel();
        ClientResponseVO clientResponseVo = new ClientResponseVO();
        when(clientRepository.findById(UUID.fromString("d600aa5d-de5e-4446-90fa-c0d15579827e"))).thenReturn(Optional.of(clientModel5));
        when(mapper.parseObject(clientModel5, ClientResponseVO.class)).thenReturn(clientResponseVo);
        ClientResponseVO test = clientServices.findById(UUID.fromString("d600aa5d-de5e-4446-90fa-c0d15579827e"));
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
        ClientResponseVO test = clientServices.findById(UUID.fromString("d600aa5d-de5e-4446-90fa-c0d15579827e"));
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