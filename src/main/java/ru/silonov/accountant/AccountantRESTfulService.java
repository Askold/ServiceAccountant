package ru.silonov.accountant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.silonov.accountant.api.DataProvider;
import ru.silonov.accountant.model.AccountantEntity;

import javax.ws.rs.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Path("/accountant")
public class AccountantRESTfulService {

    private static final DataProvider dataProvider = new DataProvider();
    private static final Logger logger = LogManager.getLogger(AccountantRESTfulService.class);



    @Path("/add/{task}/{developer}")
    @POST
    @Produces("application/xml")
    public String getData(@PathParam("task") String task,
                                 @PathParam("developer") String developer) {

        AccountantEntity entity = new AccountantEntity(task, Long.parseLong(developer));
        logger.info(entity);
        dataProvider.insert(entity);

        return "<Entity>"//
                + "<ID>" + entity.getId() + "</ID>"//
                + "<date>" + entity.getDate() + "</date>"//
                + "<time>" + entity.getTime() + "</time>"//
                + "<task>" + entity.getTask() + "</task>"//
                + "<developer>" + entity.getUserId() + "</developer>"//
                + "</weather>";
    }

    @POST
    @Path("/get/{id}")
    @Produces("application/xml")
    public String postData(@PathParam("id") Long id){

        AccountantEntity entity = dataProvider.getById(id).orElseThrow();

        return "<Entity>"//
                + "<ID>" + entity.getId() + "</ID>"//
                + "<date>" + entity.getDate() + "</date>"//
                + "<time>" + entity.getTime() + "</time>"//
                + "<task>" + entity.getTask() + "</task>"//
                + "<developer>" + entity.getUserId() + "</developer>"//
                + "</weather>";
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces("application/xml")
    public boolean updData(@PathParam("id") Long id){
        AccountantEntity entity = new AccountantEntity();
        return dataProvider.delete(id);
    }
}
