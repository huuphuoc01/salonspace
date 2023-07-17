package com.example.salonmanage.Entities;



import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    @Column(nullable = false)
    private Date date;
    @Column()
    private long discount;
    @Column(nullable = false)
    private int status;
    @Column(nullable = false)
    private int payment;
    @Column(nullable = false)
    private long totalPrice;
    @ManyToOne()
    @JoinColumn(name="branch_id")
    private Branch branch;

    @OneToMany(mappedBy = "booking")
    private Collection<BookingDetail> bookingDetails;
    public Booking() {
    }

    public Booking(Long ID, Date date, long discount, int status, int payment, long totalPrice, Branch branch, Collection<BookingDetail> bookingDetails) {
        this.ID = ID;
        this.date = date;
        this.discount = discount;
        this.status = status;
        this.payment = payment;
        this.totalPrice = totalPrice;
        this.branch = branch;
        this.bookingDetails = bookingDetails;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getDiscount() {
        return discount;
    }

    public void setDiscount(long discount) {
        this.discount = discount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Collection<BookingDetail> getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(Collection<BookingDetail> bookingDetails) {
        this.bookingDetails = bookingDetails;
    }

    @Override
    public String toString() {
        return "booking{" +
                "ID=" + ID +
                ", date=" + date +
                ", discount=" + discount +
                ", status=" + status +
                ", payment=" + payment +
                ", totalPrice=" + totalPrice +
                ", branch=" + branch +
                ", bookingDetails=" + bookingDetails +
                '}';
    }
}
