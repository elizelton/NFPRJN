package repository;

import java.util.List;
import model.Cidade;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Gabriel Strack
 */
    public interface CidadeRepository extends MongoRepository<Cidade, String>{
        public List<Cidade> findByNomeLikeIgnoreCase(String nome); // Busca tudo que pareça o que está escrito
        public Cidade findByNome(String nome); // Busca tudo que pareça o que está escrito
}
