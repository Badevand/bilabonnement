

package dk.kea.bilabonnement.bilabonnementsystem2.service;

import java.util.List;

public interface BaseService<T> {
    List<T> findAll();
}