package com.covid.vacc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CVNResponse {
    int responseCode;
    String responseDescription;
    Object response;

}
