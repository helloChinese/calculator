package test;


public class OperationDTO {

    private OperationType operationType;

    private Double num;

    public OperationDTO(OperationType operationType, Double num) {
        this.operationType = operationType;
        this.num = num;
    }

    public Double getNum() {
        return num;
    }


    public OperationType getOperationType() {
        return operationType;
    }

}
