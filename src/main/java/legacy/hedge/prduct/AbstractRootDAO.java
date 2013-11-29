package legacy.hedge.prduct;

import javax.persistence.EntityManager;
import java.util.ArrayList;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public abstract class AbstractRootDAO<K,E> {

    protected abstract EntityManager entityManager();

    public abstract E load(K id);

    public abstract void delete(K id);

    public abstract void persist(E entity);

    public abstract E save(E entity);

    public ArrayList<E> saveAll(ArrayList<E> entities) {
        ArrayList<E> saved = new ArrayList<E>();
        for(E e : entities)
            saved.add(save(e));
        return saved;
    }

    public void deleteAll(ArrayList<K> entities) {
        for(K k : entities)
            delete(k);
    }


}
