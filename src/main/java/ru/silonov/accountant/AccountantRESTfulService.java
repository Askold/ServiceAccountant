package ru.silonov.accountant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.silonov.accountant.api.DataProvider;
import ru.silonov.accountant.model.AccountantEntity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Path("/accountant")
public class AccountantRESTfulService {

    private static final DataProvider dataProvider = new DataProvider();
    private static final Logger logger = LogManager.getLogger(AccountantRESTfulService.class);

    @Path("{task}/{developer}")
    @GET
    @Produces("application/xml")
    public String getWeather_XML(@PathParam("task") String task,
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
}
