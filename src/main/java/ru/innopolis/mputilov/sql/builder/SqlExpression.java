package ru.innopolis.mputilov.sql.builder;

import lombok.Getter;
import ru.innopolis.mputilov.sql.db_impl.Table;

import java.util.UUID;

@Getter
public class SqlExpression implements Expression<Table> {
    private String id = UUID.randomUUID().toString();
    private Expression<Table> from;
    private Expression<Columns> columns;
    private TuplePredicateExpression where;

    public SqlExpression(Expression<Columns> columns, Expression<Table> from, TuplePredicateExpression where) {
        this.from = from;
        this.columns = columns;
        this.where = where;
    }

    @Override
    public Table eval(Context ctx) {
        // set in context projected columns for each mentioned table in select expression
        Columns columns = this.columns.eval(ctx);
        // set in context projected columns for this particular sql expression (relation)
        ctx.addProjectionColumns(id, columns);

//        where.eval(ctx);
        return from.eval(ctx);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitSqlExpression(this);
        columns.accept(visitor);
        from.accept(visitor);
        where.accept(visitor);
    }


}
