package ru.innopolis.mputilov.sql;

import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import ru.innopolis.mputilov.sql.jdbc.DataBase;
import ru.innopolis.mputilov.sql.jdbc.api.XlsConnectionFactory;
import ru.innopolis.mputilov.sql.jdbc.api.XlsPopulatorFactory;

public class DbModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(EventBus.class).toInstance(new EventBus("main"));
        bind(DataBase.class).in(Singleton.class);
        FactoryModuleBuilder factoryModuleBuilder = new FactoryModuleBuilder();
        install(factoryModuleBuilder.build(XlsPopulatorFactory.class));
        install(factoryModuleBuilder.build(XlsConnectionFactory.class));
    }
}
