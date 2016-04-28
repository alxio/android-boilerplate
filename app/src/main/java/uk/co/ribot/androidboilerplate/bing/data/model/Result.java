
package uk.co.ribot.androidboilerplate.bing.data.model;

import javax.annotation.Generated;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@Generated("org.jsonschema2pojo")
public class Result extends RealmObject {
    private String ID;
    private String Title;
    @PrimaryKey
    private String MediaUrl;
    private String SourceUrl;
    private String DisplayUrl;
    private String Width;
    private String Height;
    private String FileSize;
    private String ContentType;
    private Thumbnail Thumbnail;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getMediaUrl() {
        return MediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        MediaUrl = mediaUrl;
    }

    public String getSourceUrl() {
        return SourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        SourceUrl = sourceUrl;
    }

    public String getDisplayUrl() {
        return DisplayUrl;
    }

    public void setDisplayUrl(String displayUrl) {
        DisplayUrl = displayUrl;
    }

    public String getWidth() {
        return Width;
    }

    public void setWidth(String width) {
        Width = width;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getFileSize() {
        return FileSize;
    }

    public void setFileSize(String fileSize) {
        FileSize = fileSize;
    }

    public String getContentType() {
        return ContentType;
    }

    public void setContentType(String contentType) {
        ContentType = contentType;
    }

    public Thumbnail getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        Thumbnail = thumbnail;
    }
}
