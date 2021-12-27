package br.com.guilhermebehs.converters;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.guilhermebehs.converters.mocks.MockClient;
import br.com.guilhermebehs.data.models.ClientModel;
import br.com.guilhermebehs.data.vos.ClientVO;

public class DozerConverterTest {

	MockClient inputObject;

    @Before
    public void setUp() {
        inputObject = new MockClient();
    }

    @Test
    public void parseEntityToVOTest() {
        ClientVO output = DozerConverter.parseObject(inputObject.mockEntity(), ClientVO.class);
        Assert.assertEquals(Long.valueOf(0L), output.getId());
        Assert.assertEquals("First Name Test0", output.getName());
        Assert.assertEquals("Last Name Test0", output.getLastName());
        Assert.assertEquals("Addres Test0", output.getAddress());
        Assert.assertEquals("1993-03-21", output.getBirth());
    }

    @Test
    public void parseEntityListToVOListTest() {
        List<ClientVO> outputList = DozerConverter.parseListObjects(inputObject.mockEntityList(), ClientVO.class);
        ClientVO outputZero = outputList.get(0);
        
        Assert.assertEquals(Long.valueOf(0L), outputZero.getId());
        Assert.assertEquals("First Name Test0", outputZero.getName());
        Assert.assertEquals("Last Name Test0", outputZero.getLastName());
        Assert.assertEquals("Addres Test0", outputZero.getAddress());
        Assert.assertEquals("1993-03-21", outputZero.getBirth());
        
        ClientVO outputSeven = outputList.get(7);
        
        Assert.assertEquals(Long.valueOf(7L), outputSeven.getId());
        Assert.assertEquals("First Name Test7", outputSeven.getName());
        Assert.assertEquals("Last Name Test7", outputSeven.getLastName());
        Assert.assertEquals("Addres Test7", outputSeven.getAddress());
        Assert.assertEquals("1993-03-21", outputSeven.getBirth());
        
        ClientVO outputTwelve = outputList.get(12);
        
        Assert.assertEquals(Long.valueOf(12L), outputTwelve.getId());
        Assert.assertEquals("First Name Test12", outputTwelve.getName());
        Assert.assertEquals("Last Name Test12", outputTwelve.getLastName());
        Assert.assertEquals("Addres Test12", outputTwelve.getAddress());
        Assert.assertEquals("1993-03-21", outputTwelve.getBirth());
    }

    @Test
    public void parseVOToEntityTest() {
        ClientModel output = DozerConverter.parseObject(inputObject.mockVO(), ClientModel.class);
        Assert.assertEquals(Long.valueOf(0L), output.getId());
        Assert.assertEquals("First Name Test0", output.getName());
        Assert.assertEquals("Last Name Test0", output.getLastName());
        Assert.assertEquals("Addres Test0", output.getAddress());
        Assert.assertEquals("1993-03-21", output.getBirth());
    }

    @Test
    public void parserVOListToEntityListTest() {
        List<ClientModel> outputList = DozerConverter.parseListObjects(inputObject.mockVOList(), ClientModel.class);
        ClientModel outputZero = outputList.get(0);
        
        Assert.assertEquals(Long.valueOf(0L), outputZero.getId());
        Assert.assertEquals("First Name Test0", outputZero.getName());
        Assert.assertEquals("Last Name Test0", outputZero.getLastName());
        Assert.assertEquals("Addres Test0", outputZero.getAddress());
        Assert.assertEquals("1993-03-21", outputZero.getBirth());
        
        ClientModel outputSeven = outputList.get(7);
        
        Assert.assertEquals(Long.valueOf(7L), outputSeven.getId());
        Assert.assertEquals("First Name Test7", outputSeven.getName());
        Assert.assertEquals("Last Name Test7", outputSeven.getLastName());
        Assert.assertEquals("Addres Test7", outputSeven.getAddress());
        Assert.assertEquals("1993-03-21", outputSeven.getBirth());
        
        ClientModel outputTwelve = outputList.get(12);
        
        Assert.assertEquals(Long.valueOf(12L), outputTwelve.getId());
        Assert.assertEquals("First Name Test12", outputTwelve.getName());
        Assert.assertEquals("Last Name Test12", outputTwelve.getLastName());
        Assert.assertEquals("Addres Test12", outputTwelve.getAddress());
        Assert.assertEquals("1993-03-21", outputTwelve.getBirth());
    }
}
