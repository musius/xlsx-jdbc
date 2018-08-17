package ru.innopolis.mputilov.sql.builder;

import lombok.Getter;

import java.util.List;
import java.util.function.Function;

@Getter
public class TuplePredicateExpression implements Expression<Boolean> {
    private Function<List<String>, List<String>> lhsKeyExtractor;
    private Function<List<String>, List<String>> rhsKeyExtractor;
    private Column lhsColumn;
    private Column rhsColumn;

    public void setLhsKeyExtractor(Function<List<String>, List<String>> lhsKeyExtractor) {
        this.lhsKeyExtractor = lhsKeyExtractor;
    }

    public void setRhsKeyExtractor(Function<List<String>, List<String>> rhsKeyExtractor) {
        this.rhsKeyExtractor = rhsKeyExtractor;
    }

    public TuplePredicateExpression(Column lhsColumn,
                                    Column rhsColumn) {
        this.lhsColumn = lhsColumn;
        this.rhsColumn = rhsColumn;
    }

    @Override
    public Boolean eval(Context ctx) {
        List<String> lhsTuple = ctx.getLhsTuple();
        List<String> rhsTuple = ctx.getRhsTuple();
        List<String> leftKey = lhsKeyExtractor.apply(lhsTuple);
        List<String> rightKey = rhsKeyExtractor.apply(rhsTuple);
        return leftKey.equals(rightKey);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitTuplePredicateExpression(this);
    }
}
