package nexon.study.jpa.shop.category.entity;

import jakarta.persistence.*;
import lombok.*;
import nexon.study.jpa.shop.entity.ShopCategory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@ToString
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "category")
@Entity
public class Category {


    @Setter(AccessLevel.NONE)
    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private LocalDateTime date;

    @ToString.Exclude
    @Builder.Default
    @OneToMany
    private List<ShopCategory> shopCategories = new ArrayList<>();
}
