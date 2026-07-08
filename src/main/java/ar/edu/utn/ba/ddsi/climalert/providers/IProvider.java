package ar.edu.utn.ba.ddsi.climalert.providers;

import java.util.Optional;
// este provider vincula una entidad del dominio
// con lo que se quiere proveer
public interface IProvider<Domain>{
    Optional<Domain> provide();
}
