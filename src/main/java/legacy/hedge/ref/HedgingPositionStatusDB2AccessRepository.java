package legacy.hedge.ref;

import legacy.hedge.HedgingPositionStatusConst;
import legacy.hedge.HedgingPositionStatusConst_;
import legacy.hedge.prduct.GenericDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * <p>
 * Title: legacy.hedge.PositionStatusDB2AccessRepository
 * </p>
 * <p>
 * Description: By calling this class Repository, we are moving towards the so
 * well-known Domain Driven Design. The next step will be to add the Aggregate
 * keyword all around. Value Object could be the next step, but performance
 * reason, one should make them mutable so that one could reuse them, this
 * will optimize the Garbage Collector
 * </p>
 *
 * @author eEvans
 * @version 1.0
 * @creationDate May 7, 2007
 */
@Repository
public class HedgingPositionStatusDB2AccessRepository extends GenericDAO<Long, HedgingPositionStatusConst> {

    /**
     *
     */
    public HedgingPositionStatusDB2AccessRepository() {
        super(HedgingPositionStatusConst.class);
    }

    /**
     * In fact it is not possible to delete the value, because one need them, so instead one marks them
     * as deleted. This is a trick Bill G. found to prevent the SQL exception that was written in the
     * logs.
     *
     * @param id
     */
    @Override
    public void delete(Long id) {

        HedgingPositionStatusConst entity = load(id);
        entity.markAsRemoved();
        save(entity);
    }

    public HedgingPositionStatusConst findByLabel(String key) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<HedgingPositionStatusConst> criteria = builder.createQuery(HedgingPositionStatusConst.class);

        Root<HedgingPositionStatusConst> hpscrRoot = criteria.from( HedgingPositionStatusConst.class );
        criteria.select(hpscrRoot);
        criteria.where(builder.equal( hpscrRoot.get( HedgingPositionStatusConst_.key ), key ));

        TypedQuery<HedgingPositionStatusConst> query = entityManager.createQuery(criteria);
        return query.getSingleResult();
    }
}
