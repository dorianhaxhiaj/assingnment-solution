package com.enovation.assingmentsolution.domains;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class PostRequestDto {
    @NotNull
    @Size(max = 10,message = "Title max 10 char")
    private String title;
    @NotNull
    private String content;
    @NotNull
    private Date creationDate;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
