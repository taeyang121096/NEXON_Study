package nexon.study.jpa.shop.entity;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class ShopCategoryId implements Serializable {

    private Long shop;

    private Long category;
}
