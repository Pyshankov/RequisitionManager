package Model.Entity;

import javax.persistence.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by pyshankov on 27.10.15.
 */
@Entity
@Table(name = "requisition")
public class Requisition {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="requisition_id_seq")
    @SequenceGenerator(name="requisition_id_seq", sequenceName="requisition_id_seq", allocationSize=1)
    @Column(name = "id",unique = true,nullable = false)
    private long id;
    @Column(name = "requisitionDate")
    private Date requisitionDate;
    @Column(name = "performTime")
    private String performTime;
    @Column(name = "customerName")
    private String customerName;
    @Column(name = "address")
    private String address;
    @Column(name = "mobilePhone")
    private String mobilePhone;
    @Column(name = "content")
    private String content;
    @Column(name = "reason")
    private String reason;
    @Column(name = "performer")
    private String performer;
    @Column(name = "timeToSent")
    private Date timeToSent;
    @Column(name = "completeMark")
    private String completeMark;
    @Column(name = "comment")
    private String comment;

    public Requisition(){};

    public void setRequisition(Requisition e){
        id=e.getId();
        requisitionDate=e.getRequisitionDate();
        performTime=e.getPerformTime();
        customerName=e.getCustomerName();
        address=e.getAddress();
        mobilePhone=e.getMobilePhone();
        content=e.getContent();
        reason=e.getReason();
        performer=e.getPerformer();
        timeToSent=e.getTimeToSent();
        completeMark=e.getCompleteMark();
        comment=e.getComment();
    }

    public Requisition(String requisitionDate, String performTime, String customerName, String address,
                       String mobilePhone, String content, String reason, String performer, String timeToSent,
                       String completeMark, String comment) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.requisitionDate = new Date(format.parse(requisitionDate).getYear(),
                    format.parse(requisitionDate).getMonth(),
                    format.parse(requisitionDate).getDate());
            this.timeToSent = new Date(format.parse(timeToSent).getYear(),
                    format.parse(timeToSent).getMonth(),
                    format.parse(timeToSent).getDate());
        }catch (ParseException e){};

        this.performTime = performTime;
        this.customerName = customerName;
        this.address = address;
        this.mobilePhone = mobilePhone;
        this.content = content;
        this.reason = reason;
        this.performer = performer;

        this.completeMark = completeMark;
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getRequisitionDate() {
        return requisitionDate;
    }

    public void setRequisitionDate(Date requisitionDate) {
        this.requisitionDate = requisitionDate;
    }

    public String getPerformTime() {
        return performTime;
    }

    public void setPerformTime(String performTime) {
        this.performTime = performTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public Date getTimeToSent() {
        return timeToSent;
    }

    public void setTimeToSent(Date timeToSent) {
        this.timeToSent = timeToSent;
    }

    public String getCompleteMark() {
        return completeMark;
    }

    public void setCompleteMark(String completeMark) {
        this.completeMark = completeMark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
