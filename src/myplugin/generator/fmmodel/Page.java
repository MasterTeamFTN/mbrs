package myplugin.generator.fmmodel;

public class Page {

    private Boolean create;
    private Boolean update;
    private Boolean getAll;
    private Boolean details;

    public Page(Boolean create, Boolean update, Boolean getAll, Boolean details) {
        super();
        this.create = create;
        this.update = update;
        this.getAll = getAll;
        this.details = details;
    }

    public Boolean getCreate() {
        return create;
    }

    public void setCreate(Boolean create) {
        this.create = create;
    }

    public Boolean getUpdate() {
        return update;
    }

    public void setUpdate(Boolean update) {
        this.update = update;
    }

    public Boolean getGetAll() {
        return getAll;
    }

    public void setGetAll(Boolean getAll) {
        this.getAll = getAll;
    }

    public Boolean getDetails() {
        return details;
    }

    public void setDetails(Boolean details) {
        this.details = details;
    }
}
