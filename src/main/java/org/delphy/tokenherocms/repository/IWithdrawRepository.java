package org.delphy.tokenherocms.repository;

import org.delphy.tokenherocms.entity.Withdraw;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author mutouji
 */
public interface IWithdrawRepository extends MongoRepository<Withdraw, String> {
    /**
     * get all withdraws in that page
     * @param page page
     * @return all withdraws in that page
     */
    List<Withdraw> findAllByOrderByIdDesc(Pageable page);

    /**
     * find withdraws by name and address
     * @param name user name
     * @param address user address
     * @param page page
     * @return all withdraws in that page
     */
    List<Withdraw> findByNameLikeAndAddressLikeOrderByIdDesc(String name, String address, Pageable page);
    /**
     * find withdraws by name and address
     * @param name user name
     * @param page page
     * @return all withdraws in that page
     */
    List<Withdraw> findByNameLikeOrderByIdDesc(String name, Pageable page);
    /**
     * find withdraws by name and address
     * @param address user address
     * @param page page
     * @return all withdraws in that page
     */
    List<Withdraw> findByAddressLikeOrderByIdDesc(String address, Pageable page);

    /**
     * findByStatus
     * @param status status
     * @return return
     */
    List<Withdraw> findByStatus(Long status);

    /**
     *  return count
     * @param name name
     * @param address address
     * @return count
     */
    Long countByNameLikeAndAddressLike(String name, String address);

    /**
     * return count
     * @param name name
     * @return count
     */
    Long countByNameLike(String name);

    /**
     * count
     * @param address address
     * @return count
     */
    Long countByAddressLike(String address);
}
