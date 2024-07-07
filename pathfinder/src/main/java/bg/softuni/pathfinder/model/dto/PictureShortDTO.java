package bg.softuni.pathfinder.model.dto;

public class PictureShortDTO {

    private String url;

    private String title;


    public String getUrl() {
        return url;
    }

    public PictureShortDTO setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public PictureShortDTO setTitle(String routeName) {
        this.title = routeName;
        return this;
    }
}
