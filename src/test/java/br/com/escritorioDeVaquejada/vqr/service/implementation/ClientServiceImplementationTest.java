package br.com.escritorioDeVaquejada.vqr.service.implementation;

import br.com.escritorioDeVaquejada.vqr.exception.ResourceNotFoundException;
import br.com.escritorioDeVaquejada.vqr.mapper.Mapper;
import br.com.escritorioDeVaquejada.vqr.model.ClientModel;
import br.com.escritorioDeVaquejada.vqr.repository.ClientRepository;
import br.com.escritorioDeVaquejada.vqr.vo.address.AddressVO;
import br.com.escritorioDeVaquejada.vqr.vo.client.ClientDetailResponseVO;
import br.com.escritorioDeVaquejada.vqr.vo.client.ClientSaveVO;
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
    private AddressVO address;
    @Mock
    private Mapper mapper;

    @Mock
    private ClientRepository clientRepository;
    //TODO ver se deve utilizar a interface ou a implementação mesmo
    @InjectMocks
    private ClientServiceImplementation clientServices;

    ClientSaveVO clientSaveVo;
    ClientDetailResponseVO clientDetailResponseVo;
    ClientModel clientModel;
    @Before("saveClientOk")
    public void setup(){
    }
    @Test
    @DisplayName("Deve salvar o usuário com sucesso")
    void saveClientOk() {
        clientSaveVo = new ClientSaveVO();
        clientSaveVo.setAddress(address);
        clientSaveVo.setEmail("String email");
        clientSaveVo.setNumber("123");
        clientSaveVo.setName("joao");

        clientDetailResponseVo = new ClientDetailResponseVO();
        clientDetailResponseVo.setId(UUID.randomUUID());
        clientDetailResponseVo.setAddress(clientSaveVo.getAddress());
        clientDetailResponseVo.setEmail(clientSaveVo.getEmail());
        clientDetailResponseVo.setNumber(clientSaveVo.getNumber());
        clientDetailResponseVo.setName(clientSaveVo.getName());

        clientModel= new ClientModel("joao", "123", "String email", null, null);

        when(mapper.parseObject(clientSaveVo, ClientModel.class)).thenReturn(clientModel);
        when(clientRepository.save(clientModel)).thenReturn(clientModel);
        when(mapper.parseObject(clientModel, ClientDetailResponseVO.class)).thenReturn(clientDetailResponseVo);
        ClientDetailResponseVO result = clientServices.saveClient(clientSaveVo);
        Assertions.assertThat(clientDetailResponseVo).isEqualTo(result);
    }
    @Test
    @DisplayName("Deve lançar uma exceção quando tentar salvar")
    void saveClientBadRequestWithoutName(){
        //without Name test
        ClientSaveVO clientSaveVOWithoutName = new ClientSaveVO();
        clientSaveVOWithoutName.setAddress(address);
        clientSaveVOWithoutName.setEmail("String email");
        clientSaveVOWithoutName.setNumber("123");

        when(mapper.parseObject(clientSaveVOWithoutName, ClientModel.class)).thenThrow(new ConstraintViolationException(null));

        assertThrows(ConstraintViolationException.class, () -> clientServices.saveClient(clientSaveVOWithoutName));
    }
    @Test
    void saveClientBadRequestWithoutNumber(){
        ClientSaveVO clientSaveVOWithoutNumber = new ClientSaveVO();
        clientSaveVOWithoutNumber.setAddress(address);
        clientSaveVOWithoutNumber.setEmail("String email");
        clientSaveVOWithoutNumber.setName("nameExample");

        when(mapper.parseObject(clientSaveVOWithoutNumber, ClientModel.class)).thenThrow(new ConstraintViolationException(null));

        assertThrows(ConstraintViolationException.class, () -> clientServices.saveClient(clientSaveVOWithoutNumber));
    }
    @Test
    void saveClientBadRequestWithoutAddress(){
        ClientSaveVO clientSaveVOWithoutAddress = new ClientSaveVO();
        clientSaveVOWithoutAddress.setAddress(null);
        clientSaveVOWithoutAddress.setEmail("String email");
        clientSaveVOWithoutAddress.setName("nameExample");

        when(mapper.parseObject(clientSaveVOWithoutAddress, ClientModel.class)).thenThrow(new ConstraintViolationException(null));

        assertThrows(ConstraintViolationException.class, () -> clientServices.saveClient(clientSaveVOWithoutAddress));

    }

    //todo Consertar os erros nos testes gereados pela reimplementação do sistema de buscas
    /*

    @Test
    void findAll() {
        ClientModel clientModel1 = new ClientModel();
        ClientModel clientModel2 = new ClientModel();
        ClientModel clientModel3 = new ClientModel();
        List<ClientModel> listWithClients = new ArrayList<>(Arrays.asList(clientModel1, clientModel2, clientModel3));

        ClientDetailResponseVO clientResponseVO1 = new ClientDetailResponseVO();
        ClientDetailResponseVO clientResponseVO2 = new ClientDetailResponseVO();
        ClientDetailResponseVO clientResponseVO3 = new ClientDetailResponseVO();
        List<ClientDetailResponseVO> clientVoListWithClientRequests = new ArrayList<>(Arrays.asList(clientResponseVO1, clientResponseVO2, clientResponseVO3));
        when(clientRepository.findAll()).thenReturn(listWithClients);
        when(mapper.parseListObjects(listWithClients, ClientDetailResponseVO.class)).thenReturn(clientVoListWithClientRequests);
        List<ClientDetailResponseVO> clientResponseVOListWithResult = clientServices.findAll();
        assertEquals(clientResponseVOListWithResult, clientVoListWithClientRequests);
    }
    @Test
    void findAllEmpty(){
        List<ClientModel> clientModels = new ArrayList<>();
        List<ClientDetailResponseVO> clientResponseVOS = new ArrayList<>();
        when(clientRepository.findAll()).thenReturn(clientModels);
        when(mapper.parseListObjects(clientModels, ClientDetailResponseVO.class)).thenReturn(clientResponseVOS);
        List<ClientDetailResponseVO> clientResponseVOResult = clientServices.findAll();
        assertEquals(clientResponseVOResult, clientResponseVOS);
    }

     */

    @Test
    void findById() {
        ClientModel clientModel5 = new ClientModel();
        ClientDetailResponseVO clientDetailResponseVo = new ClientDetailResponseVO();
        when(clientRepository.findById(UUID.fromString("d600aa5d-de5e-4446-90fa-c0d15579827e"))).thenReturn(Optional.of(clientModel5));
        when(mapper.parseObject(clientModel5, ClientDetailResponseVO.class)).thenReturn(clientDetailResponseVo);
        ClientDetailResponseVO test = clientServices.findById(UUID.fromString("d600aa5d-de5e-4446-90fa-c0d15579827e"));
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
        ClientDetailResponseVO test = clientServices.findById(UUID.fromString("d600aa5d-de5e-4446-90fa-c0d15579827e"));
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