package infra;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class DAO<E> {

    private static EntityManagerFactory emf;
    private EntityManager em;

    private Class<E> classe;
    static {
        try{
            emf = Persistence.createEntityManagerFactory("exercicios-jpa");
        }catch (Exception e){
            // logar -> log4j
        }
    }
    public DAO(){
        this(null);
    }
    public DAO( Class<E> classe){
        this.classe = classe;
        em = emf.createEntityManager();
    }
    public DAO<E> abrirT() {
        em.getTransaction().begin();
        return this;
    }
    public DAO<E> fecharT(){
        em.getTransaction().commit();
        return this;
    }
    public DAO<E> incluirT(E  entidade){
        em.persist(entidade);
        return this;
    }
    public DAO<E> incluirAtomico(E  entidade){
        return this.abrirT().incluirT(entidade).fecharT();
    }

    public E obterPorID(Object id){
        return em.find(classe,id);
    }
    public List<E> obterAtomico(){
        return this.obterAtomico(10,0);
    }
    public List<E> obterAtomico(int limit, int deslocamento){
        if (classe == null){
            throw new UnsupportedOperationException("Classe nula!");
        }
        String jpql = "SELECT e FROM " +classe.getName() + " e";
        TypedQuery<E> query = em.createQuery(jpql,classe);
        query.setMaxResults(limit);
        query.setFirstResult(deslocamento);
        return query.getResultList();
    }
    public void fechar(){
        em.close();
    }



}
