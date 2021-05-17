package com.ataccama.dbrowser.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Filter {
    private String fieldName;
    private String fieldValue;
}
