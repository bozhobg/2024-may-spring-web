package bg.softuni.pathfinder.model.dto;

import bg.softuni.pathfinder.model.enums.Level;

public class UserProfileDTO {

    private String fullName;
    private String username;
    private Integer age;
    private Level level;

    public UserProfileDTO() {}

    public String getFullName() {
        return fullName;
    }

    public UserProfileDTO setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserProfileDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserProfileDTO setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Level getLevel() {
        return level;
    }

    public UserProfileDTO setLevel(Level level) {
        this.level = level;
        return this;
    }
}
