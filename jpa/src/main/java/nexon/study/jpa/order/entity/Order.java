package nexon.study.jpa.order.entity;

import jakarta.persistence.*;
import lombok.*;
import nexon.study.jpa.user.general.entity.User;

import java.time.LocalDateTime;
import java.util.List;

@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
@Entity
public class Order {

    @Setter(AccessLevel.NONE)
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDateTime date;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void setUserWithOrder(User user){
        this.user = user;
        user.getOrders().add(this);
    }


}
