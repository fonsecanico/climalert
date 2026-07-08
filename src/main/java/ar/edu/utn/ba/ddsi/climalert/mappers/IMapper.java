package ar.edu.utn.ba.ddsi.climalert.mappers;
// este mapper vincula una entidad de dominio con una respuesta que me llego
// externa al sistema
public interface IMapper<Domain, Response>
{
    Domain map(Response response);
}
