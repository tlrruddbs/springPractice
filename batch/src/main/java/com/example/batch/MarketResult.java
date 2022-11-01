package com.example.batch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "market_result")
@Entity
@Data
@NoArgsConstructor
public class MarketResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String comment;
    private int no;

    public MarketResult(String name, String comment, int no) {
        this.name = name;
        this.comment = comment;
        this.no = no;
    }
}
