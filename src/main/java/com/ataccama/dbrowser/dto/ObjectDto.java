package com.ataccama.dbrowser.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ObjectDto {
    private String name;
    private List<AttributeDto> attributes;
}
