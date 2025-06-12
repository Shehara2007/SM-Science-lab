package lk.ijse.sciencelab.Dto;


public class ChemicalDto {
    private String chemicalId;
    private String chemicalName;
    private String quantity;
    private String concentration;
    private String supplierId;

    public ChemicalDto(String chemicalId, String chemicalName, String quantity, String concentration, String supplierId) {
        this.chemicalId = chemicalId;
        this.chemicalName = chemicalName;
        this.quantity = quantity;
        this.concentration = concentration;
        this.supplierId = supplierId;
    }

    public String getChemicalId() {
        return chemicalId;
    }

    public void setChemicalId(String chemicalId) {
        this.chemicalId = chemicalId;
    }

    public String getChemicalName() {
        return chemicalName;
    }

    public void setChemicalName(String chemicalName) {
        this.chemicalName = chemicalName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getConcentration() {
        return concentration;
    }

    public void setConcentration(String concentration) {
        this.concentration = concentration;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public String toString() {
        return "ChemicalDto{" +
                "chemicalId='" + chemicalId + '\'' +
                ", chemicalName='" + chemicalName + '\'' +
                ", quantity='" + quantity + '\'' +
                ", concentration='" + concentration + '\'' +
                ", supplierId='" + supplierId + '\'' +
                '}';
    }

    public ChemicalDto() {
    }
}