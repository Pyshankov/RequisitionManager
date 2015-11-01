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
public class DeleteServlet extends HttpServlet {
    private static  org.hibernate.SessionFactory factory ;

    @Override
    public void init() throws ServletException {
        factory = SessionFactory.SessionFactory.getFactory();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] delete = req.getParameterValues("selectedItem");
        RequisitionDAOImpl rdi = new RequisitionDAOImpl(factory);
        if(delete!=null){
            try {
            for(int i = 0 ; i < delete.length ; i++){

                    rdi.delete(Long.parseLong(delete[i]));

            }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        req.getRequestDispatcher("index").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}
