package test;


public enum OperationType {

    ADD("add", "加"),
    SUBTRACT("subtract", "减"),
    MULTIPLY("multiply", "乘"),
    DIVIDE("divide", "除");

    private String code;
    private String desc;

    OperationType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }


    public String getDesc() {
        return desc;
    }

    public static OperationType getType(String code){
        for(OperationType operationType : values()){
            if (operationType.getCode().equals(code)) {
                return operationType;
            }
        }
        return null;
    }
}
