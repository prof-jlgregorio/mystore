package br.com.jlgregorio.mystore.model;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.sql.Array;
import java.util.ArrayList;

@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(length = 255)
    private String description;

    //new column added - tags
    @Column(nullable = true)
    private ArrayList<String> tags;

}
