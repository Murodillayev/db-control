package uz.pdp.dbcontrol.validation;

public interface Validator<CD, UD> {
    void validateForCreate(CD dto);
    void validateForUpdate(UD dto);
}
