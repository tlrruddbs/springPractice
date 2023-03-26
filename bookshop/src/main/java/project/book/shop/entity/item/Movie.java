package project.book.shop.entity.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import project.book.shop.entity.Item;

@Entity
@DiscriminatorValue("M")
@Getter
@Setter
public class Movie extends Item {
    private String director;
    private String actor;
}