package pl.coderslab.model;


import java.sql.Date;
import java.math.BigDecimal;

public class Order {
    private int id=0;
    private Date acceptanceToRepair;
    private Date plannedFix;
    private Date startFix;

    private String problemDesc;
    private String fixDesc;
    private int statusId;
    private int repairedVehicleId;
    private BigDecimal price;
    private BigDecimal partsCost;
    private BigDecimal laborCost;
    private int workhours;
    private int employeeId;

    public Order(){

    }

    public Order(int id, Date acceptanceToRepair, Date plannedFix, Date startFix, int employeeId, String problemDesc, String fixDesc, int statusId, int repairedVehicleId,BigDecimal price, BigDecimal partsCost, BigDecimal laborCost, int workhours) {
        this.id = id;
        this.acceptanceToRepair = acceptanceToRepair;
        this.plannedFix = plannedFix;
        this.startFix = startFix;
        this.employeeId = employeeId;
        this.problemDesc = problemDesc;
        this.fixDesc = fixDesc;
        this.statusId = statusId;
        this.repairedVehicleId = repairedVehicleId;
        this.price=price;
        this.partsCost = partsCost;
        this.laborCost = laborCost;
        this.workhours = workhours;
    }

    public Order(Date acceptanceToRepair, Date plannedFix, Date startFix, int employeeId, String problemDesc, String fixDesc, int statusId, int repairedVehicleId,BigDecimal price, BigDecimal partsCost, BigDecimal laborCost, int workhours) {

        this.acceptanceToRepair = acceptanceToRepair;
        this.plannedFix = plannedFix;
        this.startFix = startFix;
        this.employeeId = employeeId;
        this.problemDesc = problemDesc;
        this.fixDesc = fixDesc;
        this.statusId = statusId;
        this.repairedVehicleId = repairedVehicleId;
        this.price=price;
        this.partsCost = partsCost;
        this.laborCost = laborCost;
        this.workhours = workhours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getAcceptanceToRepair() {
        return acceptanceToRepair;
    }

    public void setAcceptanceToRepair(Date acceptanceToRepair) {
        this.acceptanceToRepair = acceptanceToRepair;
       // return acceptanceToRepair;
    }

    public Date getPlannedFix() {
        return plannedFix;
    }

    public void setPlannedFix(Date plannedFix) {
        this.plannedFix = plannedFix;
    }

    public Date getStartFix() {
        return startFix;
    }

    public void setStartFix(Date startFix) {
        this.startFix = startFix;
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
    }

    public int getRepairedVehicleId() {
        return repairedVehicleId;
    }

    public void setRepairedVehicleId(int repairedVehicleId) {
        this.repairedVehicleId = repairedVehicleId;
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
