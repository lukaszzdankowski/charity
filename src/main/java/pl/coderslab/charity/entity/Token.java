package pl.coderslab.charity.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "tokens")
public class Token {
    @Id
    private String hashCode;
    private String purpose;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
