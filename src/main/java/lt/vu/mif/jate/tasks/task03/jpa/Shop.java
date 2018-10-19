/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.vu.mif.jate.tasks.task03.jpa;

import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author Svajunas
 */
public class Shop {

    private final DbManager db;

    public Shop(DbManager db) {
        this.db = db;
    }

    public DbManager getDb() {
        return db;
    }

    public <T> Set<T> filter(Class<T> clazz, Predicate<T> pred) {
        Set<T> result = db.getListOf(clazz).stream().filter(pred)
                .collect(Collectors.toSet());
        return result;
    }

    public <T, String> Set<String> filterAndMap(Class<T> clazz,
            Predicate<T> pred, Function<T, String> map) {
        Set<String> result = filter(clazz, pred).stream().map(map)
                .collect(Collectors.toSet());
        return result;
    }
}
