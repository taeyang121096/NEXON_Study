package nexon.study.jpa.shop.entity;

import jakarta.persistence.*;
import lombok.*;
import nexon.study.jpa.user.admin.entity.AdminUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "shop")
@Entity
public class Shop {

    @Setter(AccessLevel.NONE)
    @Id
    @Column(name = "shop_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_user_id")
    private AdminUser adminUser;

    @Column(name = "date")
    private LocalDateTime date;

    @ToString.Exclude
    @Builder.Default
    @OneToMany
    private List<ShopCategory> shopCategories = new ArrayList<>();

}
