package ar.edu.utn.ba.ddsi.climalert.repositories;

import java.util.List;

// este repo vincula una entidad de dominio
// con las operaciones que se permiten realizar sobre el
public interface IRepository<Domain>
{
    List<Domain> findAll();
    Domain findByID(Long id);
    void delete(Long id);
    Domain save(Domain entity);
}
