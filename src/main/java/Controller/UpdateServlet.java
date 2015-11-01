package Controller;

import Model.Entity.DAO.RequisitionDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by pyshankov on 28.10.15.
 */
public class UpdateServlet extends HttpServlet {
    private static  org.hibernate.SessionFactory factory ;
    @Override
    public void init() throws ServletException {
        factory = SessionFactory.SessionFactory.getFactory();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");
        try {
            req.setAttribute("updateObj",new RequisitionDAOImpl(factory).getById(Long.parseLong(req.getParameter("edit"))));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("update.jsp").forward(req,resp);
    }
}
