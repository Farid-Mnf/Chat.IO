package io.chat.dto;

public class UserDTO {
    Long id;
    String email;
    String name;
    String image;
    public UserDTO(Long id, String email, String name, String image) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.image = image;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
}
