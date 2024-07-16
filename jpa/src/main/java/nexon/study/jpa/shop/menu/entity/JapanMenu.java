package nexon.study.jpa.shop.menu.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("JAPAN")
public class JapanMenu extends MenuItem {

    private String japanName;
}
