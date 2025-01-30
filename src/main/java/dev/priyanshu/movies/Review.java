package dev.priyanshu.movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection="reviews")
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    public Review(String body) {
        this.body = body;
    }

    @Id
    private ObjectId id;
    private String body;
}
