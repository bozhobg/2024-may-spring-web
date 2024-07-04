package bg.softuni.pathfinder.model.dto;

public class PictureShortDTO {

    private String url;

    private String routeName;

    public String getUrl() {
        return url;
    }

    public PictureShortDTO setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getRouteName() {
        return routeName;
    }

    public PictureShortDTO setRouteName(String routeName) {
        this.routeName = routeName;
        return this;
    }
}
