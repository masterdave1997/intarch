package org.hbrs.semesterprojekt.entities;

import lombok.Data;
import org.bson.Document;
import org.springframework.data.annotation.Id;

@Data
public class Salesman {
    @Id
    private String id;
    private String firstname;
    private String lastname;

    public Document toDocument() {
        org.bson.Document document = new Document();
        document.append("firstname" , this.firstname );
        document.append("lastname" , this.lastname );
        document.append("id" , this.id);
        return document;
    }
}
