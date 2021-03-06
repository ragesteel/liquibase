package liquibase.datatype.core;

import liquibase.database.Database;
import liquibase.database.core.MSSQLDatabase;
import liquibase.database.core.MySQLDatabase;
import liquibase.datatype.DataTypeInfo;
import liquibase.datatype.DatabaseDataType;
import liquibase.datatype.LiquibaseDataType;

@DataTypeInfo(name = "timestamp", aliases = {"timestamp", "java.sql.Types.TIMESTAMP", "java.sql.Timestamp"}, minParameters = 0, maxParameters = 1, priority = LiquibaseDataType.PRIORITY_DEFAULT)
public class TimestampType extends DateTimeType {
    private String originalDefinition;

    @Override
    public DatabaseDataType toDatabaseDataType(Database database) {
        if (database instanceof MySQLDatabase) {
            if (originalDefinition.contains(" ")) {
                return new DatabaseDataType(originalDefinition);
            }
            return new DatabaseDataType("TIMESTAMP");
        }
        if (database instanceof MSSQLDatabase) {
            return new DatabaseDataType("DATETIME");
        }
        return super.toDatabaseDataType(database);
    }

    @Override
    public void finishInitialization(String originalDefinition) {
        this.originalDefinition = originalDefinition.trim();
    }
}
