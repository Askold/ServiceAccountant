package ru.silonov.accountant.api;

import org.junit.*;
import ru.silonov.accountant.model.AccountantEntity;

import java.util.List;

public class DataProviderTest {
    static DataProvider dataProvider;
    static AccountantEntity entity;



    @BeforeClass
    public static void setUp() throws Exception {
        entity = new AccountantEntity("Some task", System.currentTimeMillis());
        System.out.println(entity);
        dataProvider = new DataProvider();
        entity = dataProvider.insert(entity);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        dataProvider.delete(entity.getId());
    }
    @Test
    public void getId() {
        entity = new AccountantEntity("Some task", System.currentTimeMillis());
        System.out.println(entity);
        entity = new DataProvider().insert(entity);

    }

//    @Test
//    public void getById() {
//        //Given: ID from prepared object
//        //When: Trying to find row with
//        AccountantEntity received = dataProvider.getById(entity.getId()).orElseThrow();
//        //Then: Received and prepared objects should be equal
//        Assert.assertEquals(entity, received);
//        System.out.println(entity);
//        System.out.println(received);
//    }

    @Test
    public void selectAll() {
        //Given: Prepared List of objects
        //When: Trying to receive all data from table
        List<AccountantEntity> stuffList = dataProvider.selectAll();
        //Then: Received and prepared lists should be equal
        System.out.println(stuffList.get(0));
        Assert.assertNotNull(stuffList);
    }

    @Test
    public void update() {
        //Given: editing Stuff object
        entity.setTask("qwerty");
        //When: trying to update it on DS
        Assert.assertTrue(dataProvider.update(entity));
        //Then: receiving edited object and compare it with prepared one
        Assert.assertEquals(dataProvider.getById(entity.getId()).orElseThrow(), entity);
    }

}