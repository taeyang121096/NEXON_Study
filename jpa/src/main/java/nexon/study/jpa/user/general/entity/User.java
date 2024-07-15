package nexon.study.jpa.user.general.entity;

import jakarta.persistence.*;
import lombok.*;
import nexon.study.jpa.order.entity.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
public class User {

    @Setter(AccessLevel.NONE)
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id")
    private String userId;

    @Column(name = "pw")
    private String password;

    @Column(name = "date")
    private LocalDateTime registerDate;

    @OneToMany(mappedBy = "team")
    private List<Order> orders = new ArrayList<Order>();
}
