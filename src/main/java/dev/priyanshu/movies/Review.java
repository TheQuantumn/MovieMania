package dev.priyanshu.movies;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "reviews")
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    public Review(String body) {
        this.body = body;
    }

    @Id
    @JsonSerialize(using = ToStringSerializer.class) // Ensures ObjectId is serialized as String
    @JsonProperty("_id") // Explicitly set the field name
    private ObjectId id;

    @JsonProperty("body")
    private String body;
}
