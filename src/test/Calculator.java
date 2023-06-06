package test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;

public class Calculator {

    // 存储历史记录的栈
    private Stack<OperationDTO> stack;

    // 当前结果
    private Double result;

    public Calculator() {
        this.stack = new Stack<>();
        this.result = 0d;
    }

    public static BigDecimal convert(double num) {
        return new BigDecimal(Double.toString(num));
    }

    public void add(double num) {
        OperationDTO operationDTO = new OperationDTO(OperationType.ADD,num);
        BigDecimal decimal = convert(num);
        result = convert(result).add(decimal).doubleValue();
        stack.push(operationDTO);
    }

    public void subtract(double num) {
        OperationDTO operationDTO = new OperationDTO(OperationType.SUBTRACT,num);
        BigDecimal decimal = convert(num);
        result = convert(result).subtract(decimal).doubleValue();
        stack.push(operationDTO);
    }

    public void multiply(double num) {
        OperationDTO operationDTO = new OperationDTO(OperationType.MULTIPLY,num);
        BigDecimal decimal = convert(num);
        result = convert(result).multiply(decimal).doubleValue();
        stack.push(operationDTO);
    }

    public void divide(double num) throws ArithmeticException {
        if (num == 0) {
            throw new ArithmeticException("0不能被除");
        }
        OperationDTO operationDTO = new OperationDTO(OperationType.DIVIDE,num);
        BigDecimal decimal = convert(num);
        result = convert(result).divide(decimal,4, RoundingMode.HALF_UP).doubleValue();
        stack.push(operationDTO);
    }


    public void undo() {
        if (stack.empty()) {
            return;
        }
        OperationDTO lastOperation =  stack.pop();
        OperationType type = lastOperation.getOperationType();
        Double num = lastOperation.getNum();
        switch(type){
            case ADD:
                this.subtract(num);
                stack.pop();
                break;

            case SUBTRACT:
                this.add(num);
                stack.pop();
                break;
            //如果开始是乘以0  后面除以0
            case MULTIPLY:
                try {
                    this.divide(num);
                    stack.pop();
                } catch (Exception e){
                    throw new UnsupportedOperationException();
                }
                break;

            case DIVIDE:
                this.multiply(num);
                stack.pop();
                break;
            default:break;
        }

    }



    public void redo() throws UnsupportedOperationException {
        if(stack.empty()) {
            throw new UnsupportedOperationException();
        }
        OperationDTO lastOperation =  stack.peek();
        OperationType type = lastOperation.getOperationType();
        Double num = lastOperation.getNum();

        switch(type){
            case ADD:
                this.add(num);
                break;

            case SUBTRACT:
                this.subtract(num);
                break;

            case MULTIPLY:
                this.multiply(num);
                break;

            case DIVIDE:
                this.divide(num);
                break;

            default:break;
        }
    }



    public Double getResult() {
        return result;
    }


}