package pl.coderslab.model;


import pl.coderslab.DateUtil;
import pl.coderslab.dao.EmployeeDao;
import pl.coderslab.dao.StatusDao;
import pl.coderslab.dao.VehicleDao;
import java.sql.Date;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Order {
    private int id = 0;
    private LocalDate acceptanceToRepair;
    private LocalDate plannedFix;
    private LocalDate startFix;
    private String problemDesc;
    private String fixDesc;
    private int statusId;
    private int repairedVehicleId;
    private BigDecimal price;
    private BigDecimal partsCost;
    private BigDecimal laborCost;
    private int workhours;
    private int employeeId;

    //    -----=====zmienne pomocnicze=====-----
    private Vehicle vehicle;
    private String statusName;
    private Employee employee;

    //    -----======konstruktory======-------
    public Order() {

    }

    public Order(int id, LocalDate acceptanceToRepair, LocalDate plannedFix, LocalDate startFix, int employeeId, String problemDesc, String fixDesc, int statusId, int repairedVehicleId, BigDecimal price, BigDecimal partsCost, BigDecimal laborCost, int workhours) {
        this.id = id;
        this.acceptanceToRepair = acceptanceToRepair;
        this.plannedFix = plannedFix;
        this.startFix = startFix;
        this.employeeId = employeeId;
        this.problemDesc = problemDesc;
        this.fixDesc = fixDesc;
        setStatusId(statusId);
        setRepairedVehicleId(repairedVehicleId);
        this.price = price;
        this.partsCost = partsCost;
        this.laborCost = laborCost;
        this.workhours = workhours;
    }

    public Order(LocalDate acceptanceToRepair, LocalDate plannedFix, LocalDate startFix, int employeeId, String problemDesc, String fixDesc, int statusId, int repairedVehicleId, BigDecimal price, BigDecimal partsCost, BigDecimal laborCost, int workhours) {

        this.acceptanceToRepair = acceptanceToRepair;
        this.plannedFix = plannedFix;
        this.startFix = startFix;
        this.employeeId = employeeId;
        this.problemDesc = problemDesc;
        this.fixDesc = fixDesc;
        setStatusId(statusId);
        setRepairedVehicleId(repairedVehicleId);
        this.price = price;
        this.partsCost = partsCost;
        this.laborCost = laborCost;
        this.workhours = workhours;
    }

    public Order(int id, String acceptanceToRepair, String plannedFix, String startFix, int employeeId, String problemDesc, String fixDesc, int statusId, int repairedVehicleId, BigDecimal price, BigDecimal partsCost, BigDecimal laborCost, int workhours) {
        this.id = id;
        setAcceptanceToRepair(acceptanceToRepair);
        setPlannedFix(plannedFix);
        setStartFix(startFix);
        this.employeeId = employeeId;
        this.problemDesc = problemDesc;
        this.fixDesc = fixDesc;
        setStatusId(statusId);
        setRepairedVehicleId(repairedVehicleId);
        this.price = price;
        this.partsCost = partsCost;
        this.laborCost = laborCost;
        this.workhours = workhours;
    }

    public Order(String acceptanceToRepair, String plannedFix, String startFix, int employeeId, String problemDesc, String fixDesc, int statusId, int repairedVehicleId, BigDecimal price, BigDecimal partsCost, BigDecimal laborCost, int workhours) {

        setAcceptanceToRepair(acceptanceToRepair);
        setPlannedFix(plannedFix);
        setStartFix(startFix);
        this.employeeId = employeeId;
        this.problemDesc = problemDesc;
        this.fixDesc = fixDesc;
        setStatusId(statusId);
        setRepairedVehicleId(repairedVehicleId);
        this.price = price;
        this.partsCost = partsCost;
        this.laborCost = laborCost;
        this.workhours = workhours;
    }

    //    ------======gettery i settery=====-------
    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getAcceptanceToRepair() {
        return acceptanceToRepair;
    }
    public Date getAcceptanceToRepairInSql() {
        if(acceptanceToRepair==null){
            return null;
        }
        return Date.valueOf(acceptanceToRepair);
    }

    public void setAcceptanceToRepair(LocalDate acceptanceToRepair) {
        this.acceptanceToRepair = acceptanceToRepair;
        // return acceptanceToRepair;
    }

    public void setAcceptanceToRepair(Date acceptanceToRepair) {
        if(acceptanceToRepair==null){
            this.acceptanceToRepair =null;
            return;
        }
        this.acceptanceToRepair = acceptanceToRepair.toLocalDate();
        // return acceptanceToRepair;
    }

    public LocalDate getPlannedFix() {
        return plannedFix;
    }

    public Date getPlannedFixInSql() {
        if(plannedFix==null){
            return null;
        }
        return Date.valueOf(plannedFix);
    }

    public void setPlannedFix(LocalDate plannedFix) {
        this.plannedFix = plannedFix;
    }

    public void setPlannedFix(Date plannedFix) {
        if(plannedFix==null){
            this.plannedFix=null;
            return;
        }
        this.plannedFix = plannedFix.toLocalDate();
    }

    public LocalDate getStartFix() {
        return startFix;
    }

    public Date getStartFixInSql() {
        if(plannedFix==null){
            return null;
        }
        return Date.valueOf(startFix);
    }

    public void setStartFix(LocalDate startFix) {
        this.startFix = startFix;
    }

    public void setStartFix(Date startFix) {
        if(startFix == null){
            this.startFix = null;
            return;
        }
            this.startFix = startFix.toLocalDate();

    }

    /**
     * seter do ustawiania  daty ze stringa
     * format daty musi byc taki:
     * rrrr-mm-dd lub rrrr/mm/dd
     *
     * @return true jesli format tekstu byl prawidlowy, false jesli nie
     * jesli format jest nieprawidlowy, data nie zostanie zmieniona
     */
    public boolean setStartFix(String startFix) {
        LocalDate bufor = DateUtil.setDateFormString(startFix);
        boolean sukces = bufor!=null;
        if(sukces) {
            this.startFix = bufor;
            return true;
        }
        return false;
    }

    /**
     * seter do ustawiania  daty ze stringa
     * format daty musi byc taki:
     * rrrr-mm-dd lub rrrr/mm/dd
     *
     * @return true jesli format tekstu byl prawidlowy, false jesli nie
     * jesli format jest nieprawidlowy, data nie zostanie zmieniona
     */
    public boolean setPlannedFix(String plannedFix) {
        LocalDate bufor = DateUtil.setDateFormString(plannedFix);
        boolean sukces = bufor!=null;
        if(sukces) {
            this.plannedFix = bufor;
            return true;
        }
        return false;
    }

    /**
     * seter do ustawiania  daty ze stringa
     * format daty musi byc taki:
     * rrrr-mm-dd lub rrrr/mm/dd
     *
     * @return true jesli format tekstu byl prawidlowy, false jesli nie
     * jesli format jest nieprawidlowy, data nie zostanie zmieniona
     */
    public boolean setAcceptanceToRepair(String accToRep) {
        LocalDate bufor = DateUtil.setDateFormString(accToRep);
        boolean sukces = bufor!=null;
        if(sukces) {
            this.acceptanceToRepair = bufor;
            return true;
        }
        return false;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {

        this.employeeId = employeeId;
    }

    public String getProblemDesc() {
        return problemDesc;
    }

    public void setProblemDesc(String problemDesc) {
        this.problemDesc = problemDesc;
    }

    public String getFixDesc() {
        return fixDesc;
    }

    public void setFixDesc(String fixDesc) {
        this.fixDesc = fixDesc;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
        try {
            statusName = StatusDao.getName(statusId);
        } catch (SQLException e) {
            e.printStackTrace();
            statusName = "status nieznany";
        }
    }

    public int getRepairedVehicleId() {
        return repairedVehicleId;
    }

    public void setRepairedVehicleId(int repairedVehicleId) {
        this.repairedVehicleId = repairedVehicleId;
        try {
            vehicle = VehicleDao.loadById(repairedVehicleId);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("problem z załadowaniem samochodu z bazy danych");
        }
    }

    public BigDecimal getPartsCost() {
        return partsCost;
    }

    public void setPartsCost(BigDecimal partsCost) {
        this.partsCost = partsCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public int getWorkhours() {
        return workhours;
    }

    public void setWorkhours(int workhours) {
        this.workhours = workhours;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public String getStatusName() {
        return statusName;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", acceptanceToRepair=" + acceptanceToRepair +
                ", plannedFix=" + plannedFix +
                ", startFix=" + startFix +
                ", problemDesc='" + problemDesc + '\'' +
                ", fixDesc='" + fixDesc + '\'' +
                ", statusId=" + statusId +
                ", repairedVehicleId=" + repairedVehicleId +
                ", price=" + price +
                ", partsCost=" + partsCost +
                ", laborCost=" + laborCost +
                ", workhours=" + workhours +
                ", employeeId=" + employeeId +
                '}';
    }
}
