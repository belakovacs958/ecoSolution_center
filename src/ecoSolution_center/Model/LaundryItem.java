package ecoSolution_center.Model;

public class LaundryItem {

    private String description = "";
    private String itemStatus = "";
    private String clothingTypeName = "";
    private int laundryItemID = 0;

    public LaundryItem(String description, int laundryItemID, String itemStatus, String clothingTypeName) {
        this.description = description;
        this.laundryItemID = laundryItemID;
        this.itemStatus = itemStatus;
        this.clothingTypeName = clothingTypeName;
    }

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!THE GETTERS ARE USED AND THE TABLE VIEW IS NOT WORKING WITHOUT THEM!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


    public String getDescription() {
        return description;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public String getClothingTypeName() {
        return clothingTypeName;
    }

    public int getLaundryItemID() {
        return laundryItemID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public void setClothingTypeName(String clothingTypeName) {
        this.clothingTypeName = clothingTypeName;
    }

    public void setLaundryItemID(int laundryItemID) {
        this.laundryItemID = laundryItemID;
    }
}
