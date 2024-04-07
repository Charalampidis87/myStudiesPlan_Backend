package gr.di.mystudiesplanservice;

public interface Identifiable<PK> {
    PK getId();

    void setId(PK pk);
}
