package org.example;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class XeOTO {
    private int id;

    private String ten;

    private float gia;

    private String ghiChu;
}
