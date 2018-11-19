package repository;

import model.Instituicao;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Gabriel Strack
 */
public interface InstituicaoRepository extends MongoRepository<Instituicao, String> {

    public Instituicao findByCnpj(String cnpjInst);
}
