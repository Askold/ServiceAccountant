package ru.silonov.accountant;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.silonov.accountant.api.DataProvider;
import ru.silonov.accountant.model.AccountantEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/accountant")
public class AccountantRESTfulService extends HttpServlet {

    private static final DataProvider dataProvider = new DataProvider();
    private static final Logger logger = LogManager.getLogger(AccountantRESTfulService.class);
    Gson gson = new Gson();
    static final long serialVersionUID = 1L;


    @Override
    public void init() {
        AccountantEntity accountant = new AccountantEntity();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<AccountantEntity> list = dataProvider.selectCurrent();
        String payload = gson.toJson(list);
        sendResponse(resp, payload);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String text = req.getParameter("report_text");
        String time = req.getParameter("time");
        String student = req.getParameter("student");

        if (text == null || time == null || student == null)
            throw new RuntimeException(Integer.toString(HttpServletResponse.SC_BAD_REQUEST));
        AccountantEntity entity = new AccountantEntity(text, Integer.parseInt(time), Integer.parseInt(student));
        dataProvider.insert(entity);
    }

//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String param = req.getParameter("id");
//        Integer key = (param == null) ? null : Integer.valueOf(param.trim());
//
//        if (key == null) {
//            throw new RuntimeException(Integer.toString(HttpServletResponse.SC_BAD_REQUEST));
//        }
//        try {
//            logger.info(key);
//            dataProvider.delete(key);
//        } catch (Exception e) {
//            throw new RuntimeException(Integer.toString(HttpServletResponse.SC_INTERNAL_SERVER_ERROR));
//        }
//    }

    /**
     * Send the response payload Json to the client
     * @param response HttpServletResponse
     * @param payload prepared Json
     */
    private void sendResponse(HttpServletResponse response, String payload) {
        try {
            OutputStream out = response.getOutputStream();
            out.write(payload.getBytes());
            out.flush();
        }
        catch(Exception e) {
            throw new RuntimeException(Integer.toString(HttpServletResponse.SC_INTERNAL_SERVER_ERROR));
        }
    }


}
