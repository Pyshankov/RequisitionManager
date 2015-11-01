package Controller;

import Model.Entity.DAO.RequisitionDAOImpl;
import Model.Entity.Requisition;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by pyshankov on 28.10.15.
 */
public class UpdateValueServlet extends HttpServlet {
    private static  org.hibernate.SessionFactory factory ;
    @Override
    public void init() throws ServletException {
        factory = SessionFactory.SessionFactory.getFactory();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");
        Requisition r = new Requisition(req.getParameter("requisitionDate"),
                req.getParameter("performTime"),
                req.getParameter("customerName"),
                req.getParameter("address"),
                req.getParameter("mobilePhone"),
                req.getParameter("content"),
                req.getParameter("reason"),
                req.getParameter("performer"),
                req.getParameter("timeToSent"),
                req.getParameter("completeMark"),
                req.getParameter("comment")
        );
        r.setId(Long.parseLong(req.getParameter("id")));
        RequisitionDAOImpl rdi = new RequisitionDAOImpl(factory);

        try {
            rdi.update(r.getId(),r);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("index").forward(req,resp);
    }
}