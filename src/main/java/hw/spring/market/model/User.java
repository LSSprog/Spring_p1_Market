package hw.spring.market.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table (name = "users_tbl")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "user_id")
    private Long id;

    @Column (name = "username_fld")
    private String username;

    @Column (name = "password_fld")
    private String pass;

    @Column (name = "email_fld")
    private String email;

    @CreationTimestamp
    @Column (name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column (name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToMany
    @JoinTable (name = "users_roles_tbl", joinColumns = @JoinColumn (name = "user_id"),
                inverseJoinColumns = @JoinColumn (name = "role_id"))
    private Collection<Role> roles;

    @OneToMany(mappedBy = "user") // обратку связь можно делать, но не обязательно. только если часто надо пользоваться
    private List<Order> orders;
}
