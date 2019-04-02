package com.prototipo_danilo.tasks.entities;

public class ImageT {
    private String ImageUrl;
    private String ImageTag;
    private String Label;
    private String RealIdProduto;


    // Getter Methods

    public String getImageUrl() {
        return ImageUrl;
    }

    public String getImageTag() {
        return ImageTag;
    }

    public String getLabel() {
        return Label;
    }

    // Setter Methods

    public void setImageUrl(String ImageUrl) {
        this.ImageUrl = ImageUrl;
    }

    public void setImageTag(String ImageTag) {
        this.ImageTag = ImageTag;
    }

    public void setLabel(String Label) {
        this.Label = Label;
    }

    public String getRealIdProduto() {
        return RealIdProduto;
    }

    public void setRealIdProduto(String realIdProduto) {
        RealIdProduto = realIdProduto;
    }
}