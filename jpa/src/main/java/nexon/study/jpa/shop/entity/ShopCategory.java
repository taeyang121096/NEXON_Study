package nexon.study.jpa.shop.entity;

import jakarta.persistence.*;
import lombok.*;
import nexon.study.jpa.shop.category.entity.Category;

@IdClass(ShopCategoryId.class)
@Entity
@Table(name = "shop_category")
public class ShopCategory {

    @Id
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

}
