package br.com.letscode.assets;

import lombok.Getter;
import lombok.ToString;

import java.security.spec.RSAOtherPrimeInfo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@ToString
public class Operations implements Comparable<Operations> {
    private final LocalDateTime operationDate;
    private final String operationType;
    private final Double value;
    private final String operator;

    public Operations (String date, String type, String value, String operator){
        operationDate = LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.operationType = type;
        this.value = Double.parseDouble(value);
        this.operator = operator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Operations that = (Operations) o;

        if (!operationDate.equals(that.operationDate)) return false;
        if (!operationType.equals(that.operationType)) return false;
        if (!value.equals(that.value)) return false;
        return operator.equals(that.operator);
    }

    @Override
    public int hashCode() {
        int result = operationDate.hashCode();
        result = 31 * result + operationType.hashCode();
        result = 31 * result + value.hashCode();
        result = 31 * result + operator.hashCode();
        return result;
    }

    @Override
    public int compareTo(Operations o) {
        System.out.println(this.operationDate.compareTo(o.getOperationDate()));
        return 2*this.operationDate.compareTo(o.getOperationDate()) + this.value.compareTo(o.getValue());
    }
}