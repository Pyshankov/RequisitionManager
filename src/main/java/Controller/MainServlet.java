package Controller;

import Model.Entity.DAO.RequisitioDAO;
import Model.Entity.DAO.RequisitionDAOImpl;
import Model.Entity.Requisition;


import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 * Created by pyshankov on 27.10.15.
 */
public class MainServlet extends HttpServlet {
    private static  org.hibernate.SessionFactory factory ;

    @Override
    public void init() throws ServletException {
        factory = SessionFactory.SessionFactory.getFactory();
    }
//    @EJB
//    RequisitioDAO requisitioDAO;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");
        if (req.getParameter("fromDate")!=null&&req.getParameter("toDate")!=null&& !req.getParameter("fromDate").isEmpty() &&!req.getParameter("toDate").isEmpty() ) {
            String fromDate = req.getParameter("fromDate").trim();
            String toDate = req.getParameter("toDate").trim();
            HttpSession session = req.getSession();
            session.setAttribute("fromDate1", fromDate.trim());
            session.setAttribute("toDate1", toDate.trim());
            try {
                req.setAttribute("allInfo", new RequisitionDAOImpl(factory).getCheckDate(fromDate, toDate));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            req.getRequestDispatcher("main.jsp").forward(req, resp);
        }
        else {
            try {
                req.setAttribute("allInfo", new RequisitionDAOImpl(factory).getCheckDate(new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()),
                        new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date())));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            req.getRequestDispatcher("main.jsp").forward(req, resp);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
