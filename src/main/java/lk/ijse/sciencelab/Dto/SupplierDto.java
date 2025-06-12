package lk.ijse.sciencelab.Dto;



public class SupplierDto {
    private String supplierId;
    private String supplierName;
    private String contact;
    private String equipment;
    private String typeOfSupplier;

    public SupplierDto(String supplierId, String supplierName, String contact, String equipment, String typeOfSupplier) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.contact = contact;
        this.equipment = equipment;
        this.typeOfSupplier = typeOfSupplier;
    }

    public SupplierDto() {
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getTypeOfSupplier() {
        return typeOfSupplier;
    }

    public void setTypeOfSupplier(String typeOfSupplier) {
        this.typeOfSupplier = typeOfSupplier;
    }

    @Override
    public String toString() {
        return "SupplierDto{" +
                "supplierId='" + supplierId + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", contact='" + contact + '\'' +
                ", equipment='" + equipment + '\'' +
                ", typeOfSupplier='" + typeOfSupplier + '\'' +
                '}';
    }
}
