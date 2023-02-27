package br.com.jlgregorio.mystore.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categories_seq_id")
    private int id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(length = 255)
    private String description;
}
