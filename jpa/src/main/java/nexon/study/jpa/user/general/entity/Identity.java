package nexon.study.jpa.user.general.entity;

import jakarta.persistence.*;
import lombok.*;
import nexon.study.jpa.order.entity.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ToString
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "identity")
public class Identity {

    @Setter(AccessLevel.NONE)
    @Id
    @Column(name = "identity_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identity")
    private Long identity;

    @Column(name = "date")
    private LocalDateTime registerDate;

    @OneToOne
    @ToString.Exclude
    @JoinColumn(name = "user_id")
    private User user;

    public void setIdentityWithUser(User user) {
        this.user = user;
        user.setIdentity(this);
    }

}
