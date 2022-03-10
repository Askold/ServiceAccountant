package ru.silonov.accountant.service;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.silonov.accountant.Constants;
import ru.silonov.accountant.api.DataProvider;
import ru.silonov.accountant.model.ReportEntity;
import ru.silonov.accountant.model.ReportEntityDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


@WebServlet("/report")
public class postReportServlet extends HttpServlet {

    private static final DataProvider dataProvider = new DataProvider();
    private static final Logger logger = LogManager.getLogger(getDailyReports.class);
    Gson gson = new Gson();
    static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String jsonString = getStringJsonFromREST();
        ReportEntityDTO dto = gson.fromJson(jsonString, ReportEntityDTO.class);
        ReportEntity entity = new ReportEntity(dto.getReport(), dto.getTime(), dto.getStudent());
        dataProvider.insert(entity);
    }

    public static String getStringJsonFromREST(){
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(Constants.WEB_HBN_CONFIG_PATH);
        String line = "";
        StringBuilder result = new StringBuilder();

        try {
            HttpResponse response = client.execute(request);
            //logger.info();
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            logger.info(e.getClass() + e.getMessage());
        }
        return result.toString();
    }
}
