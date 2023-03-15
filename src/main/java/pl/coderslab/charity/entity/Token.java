package pl.coderslab.charity.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "tokens")
public class Token {
    @Id
    private String hashCode;
    @CreationTimestamp
    private LocalDateTime created;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
