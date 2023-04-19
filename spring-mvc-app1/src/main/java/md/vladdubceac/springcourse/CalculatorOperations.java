package md.vladdubceac.springcourse;

public enum CalculatorOperations {
    MULTIPLICATION('*'),
    ADDITION('+'),
    SUBTRACTION('-'),
    DIVISION('/');

    private final char operationSign;

    private CalculatorOperations(char operationSign) {
        this.operationSign= operationSign;
    }

    public char getOperationSign() {
        return operationSign;
    }
}
