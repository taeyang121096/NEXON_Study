package nexon.study.jpa.shop.menu.entity;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("KOREA")
public class KoreaMenu extends MenuItem {


    private String koreaName;
}
