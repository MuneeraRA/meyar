package app.firstcode.meyar.data.service.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Diaa on 7/7/2017.
 */

public class Category {

    @SerializedName("ID")
    private int ID;
    @SerializedName("Name")
    private String Name;
    @SerializedName("Descriptions")
    private String Descriptions;
    @SerializedName("Image")
    private String Image;
    @SerializedName("Categories")
    private List<Categories> Categories;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDescriptions() {
        return Descriptions;
    }

    public void setDescriptions(String Descriptions) {
        this.Descriptions = Descriptions;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public List<Categories> getCategories() {
        return Categories;
    }

    public void setCategories(List<Categories> Categories) {
        this.Categories = Categories;
    }

    public static class Categories {
        @SerializedName("ID")
        private int ID;
        @SerializedName("ParentID")
        private int ParentID;
        @SerializedName("Name")
        private String Name;
        @SerializedName("Descriptions")
        private String Descriptions;
        @SerializedName("Image")
        private String Image;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public int getParentID() {
            return ParentID;
        }

        public void setParentID(int ParentID) {
            this.ParentID = ParentID;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getDescriptions() {
            return Descriptions;
        }

        public void setDescriptions(String Descriptions) {
            this.Descriptions = Descriptions;
        }

        public String getImage() {
            return Image;
        }

        public void setImage(String Image) {
            this.Image = Image;
        }
    }
}
