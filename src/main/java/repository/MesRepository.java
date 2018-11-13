/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import java.util.List;
import model.Empresa;
import model.Mes;
import model.MesEmpresa;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Gabriel Strack
 */
public interface MesRepository extends MongoRepository<Mes, String> {

    public List<Mes> findByAnoAndMes(String ano, String mes);
}
