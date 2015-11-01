package Controller;

import Model.Entity.DAO.RequisitionDAOImpl;
import Model.Entity.Requisition;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.PrettyXmlSerializer;
import org.htmlcleaner.TagNode;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by pyshankov on 28.10.15.
 */
public class PdfServlet extends HttpServlet {

    private static final String CHARSET = "UTF-8";
    public static File fontFile = new File("arialuni.ttf");
    private static  org.hibernate.SessionFactory factory ;
    @Override
    public void init() throws ServletException {
        factory = SessionFactory.SessionFactory.getFactory();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setContentType("application/pdf");
            req.setCharacterEncoding("UTF-8");
            HttpSession session = req.getSession();
            FileInputStream fis;
            String fDate;
            String tDate;
            synchronized (session) {
                 fDate = (String) session.getAttribute("fromDate1");
                 tDate = (String) session.getAttribute("toDate1");
            }


            if (fDate!=null&&tDate!=null&&fDate!=""&&tDate!="" ) {

                    fis = new FileInputStream(createPdf("hello.pdf", fDate, tDate));

            }
            else {
                fis = new FileInputStream(createPdf("hello.pdf",
                         new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()),
                        new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date())
                        ));

            }

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            try {
                for (int readNum; (readNum = fis.read(buf)) != -1;) {
                    bos.write(buf, 0, readNum);
                    System.out.println("read " + readNum + " bytes");
                }
            } catch (IOException ex) {

            }
            byte[] bytes = bos.toByteArray();

            resp.setContentLength(bytes.length);
            resp.getOutputStream().write(bytes);
        } catch (Exception ex) {
            resp.setContentType("text/html");

            PrintWriter out = resp.getWriter();
            out.write("<strong>Something wrong</strong><br /><br />");
            ex.printStackTrace(out);
            ex.printStackTrace();
        }
    }


    public static String createPdf(String filename,String fromDate,String toDate)
            throws DocumentException, IOException, SQLException {
        // step 1
        Document document = new Document(PageSize.A4.rotate());
        BaseFont unicode = BaseFont.createFont(fontFile.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

        FontSelector fs = new FontSelector();
        fs.addFont(new Font(unicode));
        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        // step 3
        document.open();
        // step 4

//        document.add(new Paragraph("Hello World!"));

        PdfPTable table = new PdfPTable(11);
       table.setTotalWidth(Element.MULTI_COLUMN_TEXT);

        java.util.List<Requisition> rList = new RequisitionDAOImpl(factory).getByDate(fromDate,toDate);
        Phrase s = fs.process("Дата:");
        PdfPCell c1 = new PdfPCell(s);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        s = fs.process("Время на выполнение");
        c1 = new PdfPCell(s);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        s=fs.process("ФИО");
        c1 = new PdfPCell(s);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        s=fs.process("Адресс");
        c1 = new PdfPCell(s);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        s=fs.process("Телефон");
        c1 = new PdfPCell(s);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        s=fs.process("Содержание");
        c1 = new PdfPCell(s);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        s=fs.process("Причина");
        c1 = new PdfPCell(s);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        s=fs.process("Передано на выполнение");
        c1 = new PdfPCell(s);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        s=fs.process("Выполнить до");
        c1 = new PdfPCell(s);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        s=fs.process("Отметка выполнения");
        c1 = new PdfPCell(s);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        s=fs.process("коментарий");
        c1 = new PdfPCell(s);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);
        for (Requisition r :rList){
            s=fs.process(String.valueOf(r.getRequisitionDate()));
            table.addCell(s);
            s = fs.process(r.getPerformTime())    ;
            table.addCell(s);
            s = fs.process(r.getCustomerName());
            table.addCell(s);
            s= fs.process(r.getAddress());
            table.addCell(s);
            s = fs.process(r.getMobilePhone());
            table.addCell(s);
            s= fs.process(r.getContent());
            table.addCell(s);
            s = fs.process(r.getReason());
            table.addCell(s);
            s = fs.process(r.getPerformer());
            table.addCell(s);
            s=fs.process(r.getPerformTime());
            table.addCell(s);
            s=fs.process(r.getCompleteMark());
            table.addCell(s);
            s=fs.process(r.getComment());
            table.addCell(s);
        }


        document.add(table);


        // step 5
        document.close();
        return filename;
    }






}
