package com.itjobapp.Service.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@Builder
@With
@AllArgsConstructor
public class Skills {
    String skillName;
}
