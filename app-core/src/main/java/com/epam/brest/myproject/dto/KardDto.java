package com.epam.brest.myproject.dto;

import com.epam.brest.myproject.domain.Kard;

import java.util.List;

/**
 * Created by bendar on 05.12.15.
 */
public class KardDto {

    private List<Kard> kards;

    private Integer totalKards;

    public List<Kard> getKards() {
        return kards;
    }

    public void setKards(List<Kard> kards) {
        this.kards = kards;
    }

    public Integer getTotalKards() {
        return totalKards;
    }

    public void setTotalKards(Integer totalKards) {
        this.totalKards = totalKards;
    }
}
