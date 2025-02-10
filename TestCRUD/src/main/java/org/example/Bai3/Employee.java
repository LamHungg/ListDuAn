package org.example.Bai3;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    public int id;
    public String FirstName;
    public String LastName;
    public String Email;
}
