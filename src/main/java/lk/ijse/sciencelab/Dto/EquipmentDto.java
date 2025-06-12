package lk.ijse.sciencelab.Dto;


public class EquipmentDto {
    private String equipmentId;
    private String equipmentName;
    private String quantity;
    private String type;
    private String supplierId;

    public EquipmentDto() {
    }

    public EquipmentDto(String equipmentId, String equipmentName, String quantity, String type, String supplierId) {
        this.equipmentId = equipmentId;
        this.equipmentName = equipmentName;
        this.quantity = quantity;
        this.type = type;
        this.supplierId = supplierId;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public String toString() {
        return "EquipmentDto{" +
                "equipmentId='" + equipmentId + '\'' +
                ", equipmentName='" + equipmentName + '\'' +
                ", quantity='" + quantity + '\'' +
                ", type='" + type + '\'' +
                ", supplierId='" + supplierId + '\'' +
                '}';
    }

}