// package JFS6WDE.OnlineBusTicketBooking.Entities;

// import java.util.ArrayList;
// import java.util.List;

// import jakarta.persistence.CascadeType;
// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.FetchType;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.JoinTable;
// import jakarta.persistence.ManyToMany;
// import jakarta.validation.constraints.Email;
// import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.NotNull;
// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Data
// @Entity
// @NoArgsConstructor
// @AllArgsConstructor
// public class Admin {   
    
    
//     private static final long serialVersionUID = 1L;

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Integer adminID;

//     @NotNull(message = "Name can not be null!")
//     @NotBlank(message = "Name can not be blank!")
//     private String name;

//     @Email
//     @Column(unique = true)
//     private String email;

//     @NotNull(message="Password can not be null!")
//     @NotBlank(message= "Password can not be blank!")
//     private String password;

//     @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
//     @JoinTable(
//             name="admin_roles",
//             joinColumns={@JoinColumn(name="ADMIN_ID", referencedColumnName="adminID")},
//             inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="id")})
//     private List<Role> roles = new ArrayList<>();
// }
