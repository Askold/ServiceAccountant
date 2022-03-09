package ru.silonov.accountant;

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


@WebServlet("/accountant")
public class AccountantRESTfulService extends HttpServlet {

    private static final DataProvider dataProvider = new DataProvider();
    private static final Logger logger = LogManager.getLogger(AccountantRESTfulService.class);

    static final long serialVersionUID = 1L;


    @Override
    public void init() {
        AccountantEntity accountant = new AccountantEntity();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("id");
        Integer key = (param == null) ? null : Integer.valueOf(param.trim());
        // удалять можно только по одной записи за раз
        if (key == null) {
            throw new RuntimeException(Integer.toString(HttpServletResponse.SC_BAD_REQUEST));
        }
        try {
            dataProvider.delete(key);
        } catch (Exception e) {
            throw new RuntimeException(Integer.toString(HttpServletResponse.SC_INTERNAL_SERVER_ERROR));
        }
    }
}
