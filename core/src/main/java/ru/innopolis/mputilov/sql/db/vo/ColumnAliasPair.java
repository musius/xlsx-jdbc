package ru.innopolis.mputilov.sql.db.vo;

import lombok.Data;

@Data
public final class ColumnAliasPair {
    private final String tableAlias;
    private final String columnName;
}
