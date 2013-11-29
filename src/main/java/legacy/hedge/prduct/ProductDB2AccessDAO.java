package legacy.hedge.prduct;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
@Repository
public class ProductDB2AccessDAO extends GenericDAO<Long, ProductDict> {

    @PersistenceContext
    private EntityManager em;

    public ProductDB2AccessDAO() {
        super(ProductDict.class);
    }
}
