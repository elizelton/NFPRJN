/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import java.io.Serializable;
import model.MesEmpresa;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Gabriel Strack
 */
public interface MesEmpresaRepository extends MongoRepository<MesEmpresa, String> {
    
}
