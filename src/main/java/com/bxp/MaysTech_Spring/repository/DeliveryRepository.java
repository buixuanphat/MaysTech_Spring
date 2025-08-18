package com.bxp.MaysTech_Spring.repository;

import com.bxp.MaysTech_Spring.dto.delivery.BestSellingResponse;
import com.bxp.MaysTech_Spring.dto.delivery.ProductRevenue;
import com.bxp.MaysTech_Spring.dto.delivery.TimeRevenue;
import com.bxp.MaysTech_Spring.entity.Delivery;
import com.bxp.MaysTech_Spring.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
    List<Delivery> findAllByUser_Id(Integer userId);

    List<Delivery> findAllByUser_IdAndStatus(Integer userId, String status);

    List<Delivery> getAllByStatus(String status);

    @Query(value = """
            SELECT 
                dd.product_id AS productId,
                CAST(COUNT(*) AS SIGNED) AS soLuongBan
            FROM 
                delivery_details dd
            JOIN 
                delivery d ON dd.delivery_id = d.id
            WHERE 
                d.status = 'delivered'
            GROUP BY 
                dd.product_id
            ORDER BY 
                soLuongBan DESC
            LIMIT 10
            """, nativeQuery = true)
    List<BestSellingResponse> getBestSellingProducts();

    List<Delivery> findAllByStatus(String status);

    List<Delivery> findAllByStatusOrderByCancellationRequestDesc(String status);


    @Query(value = """
                SELECT p.id, p.name, SUM(dd.total_price) AS revenue
                FROM product p
                JOIN delivery_details dd ON p.id = dd.product_id
                WHERE dd.delivery_id IN (
                    SELECT d.id
                    FROM delivery d
                    WHERE d.status = 'delivered'
                )
                GROUP BY p.id, p.name
                ORDER BY revenue ASC
            """, nativeQuery = true)
    List<ProductRevenue> getProductRevenue();


    @Query(value = """
        SELECT DATE_FORMAT(d.received_date, '%Y-%m-%d') AS time,
               SUM(dd.total_price) AS revenue
        FROM delivery d
        JOIN delivery_details dd ON d.id = dd.delivery_id
        WHERE d.status = 'delivered'
        GROUP BY DATE_FORMAT(d.received_date, '%Y-%m-%d')
        ORDER BY time ASC
        """, nativeQuery = true)
    List<Object[]> getDailyRevenue();



    @Query(value = """
            SELECT DATE_FORMAT(d.received_date, '%Y-%m') AS time, 
                   SUM(dd.total_price) AS revenue
            FROM delivery d
            JOIN delivery_details dd ON d.id = dd.delivery_id
            WHERE d.status = 'delivered'
            GROUP BY DATE_FORMAT(d.received_date, '%Y-%m')
            ORDER BY time ASC
            """, nativeQuery = true)
    List<Object[]> getMonthlyRevenue();


    @Query(value = """
            SELECT DATE_FORMAT(d.received_date, '%Y') AS time, 
                   SUM(dd.total_price) AS revenue
            FROM delivery d
            JOIN delivery_details dd ON d.id = dd.delivery_id
            WHERE d.status = 'delivered'
            GROUP BY DATE_FORMAT(d.received_date, '%Y')
            ORDER BY time ASC
            """, nativeQuery = true)
    List<Object[]> getYearlyRevenue();


}


