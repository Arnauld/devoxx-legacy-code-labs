package legacy.hedge.prduct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class GenericDAO<K, E> extends AbstractRootDAO<K,E> {

    private static Logger LOG = LoggerFactory.getLogger(GenericDAO.class);

    @PersistenceContext
    protected EntityManager entityManager;

    public Class<E> clazz;

    public GenericDAO(Class<E> clazz) {
        this.clazz = clazz;
    }

    @Override
    protected EntityManager entityManager() {
        return entityManager;
    }

    @Transactional
    public E load(K id) {
        return entityManager.find(clazz, id);
    }

    @Transactional
    public void delete(K id) {
        entityManager.remove(load(id));
    }

    @Transactional
    public void persist(E entity) {
        LOG.info("About to persist entity");
        entityManager.persist(entity);
    }

    @Transactional
    public E save(E entity) {
        LOG.info("About to save entity");
        return entityManager.merge(entity);
    }
}
