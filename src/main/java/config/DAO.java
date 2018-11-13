/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import Repository.EmpresaRepository;
import Repository.MesEmpresaRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repository.CidadeRepository;
import Repository.MesRepository;

/**
 *
 * @author Idomar
 */
public class DAO {

    private static final AnnotationConfigApplicationContext ctx
            = new AnnotationConfigApplicationContext(DBConfig.class);
    public static CidadeRepository cidadeRepository = ctx.getBean(CidadeRepository.class); //Objetos de persistencia ( que ficam no banco )
    public static EmpresaRepository empresaRepository = ctx.getBean(EmpresaRepository.class); //Objetos de persistencia ( que ficam no banco )
    public static MesRepository mesRepository = ctx.getBean(MesRepository.class); //Objetos de persistencia ( que ficam no banco )
    public static MesEmpresaRepository mesEmpresaRepository = ctx.getBean(MesEmpresaRepository.class); //Objetos de persistencia ( que ficam no banco )
    

}
