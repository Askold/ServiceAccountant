package ru.silonov.accountant.api;

import com.google.gson.Gson;
import org.junit.*;
import ru.silonov.accountant.model.ReportEntity;

import java.util.List;

public class DataProviderTest {
    static DataProvider dataProvider;
    static ReportEntity entity;



//    @BeforeClass
//    public static void setUp() throws Exception {
//        entity = new AccountantEntity("Some task", (int) System.currentTimeMillis());
//        System.out.println(entity);
//        dataProvider = new DataProvider();
//        entity = dataProvider.insert(entity);
//    }
//
//    @AfterClass
//    public static void tearDown() throws Exception {
//        dataProvider.delete(entity.getId());
//    }


    @Test
    public void getId() {
        entity = new ReportEntity("Some task",15, (int) System.currentTimeMillis());
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
        List<ReportEntity> stuffList = dataProvider.selectAll();
        //Then: Received and prepared lists should be equal
        System.out.println(stuffList.get(0).getDate());
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

    @Test
    public void insert() {
        dataProvider = new DataProvider();
        for (int i =0; i < 10; i++){
            entity = dataProvider.insert(new ReportEntity("Some task",15 , (int) System.currentTimeMillis()));
        }



    }

    @Test
    public void delete() {
        dataProvider = new DataProvider();
        System.out.println(dataProvider.selectAll());
        dataProvider.delete(1854433074);
        System.out.println(dataProvider.selectAll());
    }

    @Test
    public void selectCurrent() {
        dataProvider = new DataProvider();
        List<ReportEntity> list = dataProvider.selectCurrent();
        Gson gson = new Gson();
        System.out.println(gson.toJson(list));
    }
}