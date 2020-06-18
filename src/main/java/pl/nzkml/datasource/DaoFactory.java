package pl.nzkml.datasource;

public interface DaoFactory {
    public CrudDao createDao(DaoType type);

}
